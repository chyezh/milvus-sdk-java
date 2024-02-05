package io.milvus.rg;

import java.util.Arrays;
import java.util.Map;

import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.collection.response.ListCollectionsResp;
import io.milvus.v2.service.replica.request.GetReplicasReq;
import io.milvus.v2.service.replica.response.GetReplicasResp;
import io.milvus.v2.service.rg.request.CreateResourceGroupReq;
import io.milvus.v2.service.rg.request.DropResourceGroupReq;
import io.milvus.v2.service.rg.request.ListResourceGroupsReq;
import io.milvus.v2.service.rg.request.TransferReplicaReq;
import io.milvus.v2.service.rg.request.UpdateResourceGroupsReq;
import io.milvus.v2.service.rg.response.ListResourceGroupsResp;
import io.milvus.v2.service.rg.util.ResourceGroupConfig;
import io.milvus.v2.service.rg.util.ResourceGroupLimit;
import io.milvus.v2.service.rg.util.ResourceGroupTransfer;

public class ResourceGroupManagement {
    static String RECYCLE_RG = "__recycle_resource_group";
    static String DEFAULT_RG = "__default_resource_group";
    static Integer RECYCLE_RG_REQUEST_NODE_NUM = 0;
    static Integer RECYCLE_RG_LIMIT_NODE_NUM = 100000;

    private MilvusClientV2 client;

    /**
     * list all resource groups.
     * 
     * @return map of resource group name and resource group info.
     */
    public Map<String, ResourceGroupInfo> listResourceGroups() {
        // list all resource groups.
        ListResourceGroupsResp listResourceGroups = client.listResourceGroups(new ListResourceGroupsReq());

        // list all collections.
    }

    /**
     * Initialize the cluster with a recycle resource group.
     * 
     * @param defaultResourceGroupNodeNum The number of query nodes to initialize
     *                                    the default resource group.
     */
    public void initializeCluster(Integer defaultResourceGroupNodeNum) {
        // Create a recycle resource group to hold all redundant query node.
        client.createResourceGroup(CreateResourceGroupReq.builder()
                .resourceGroupName(RECYCLE_RG)
                .config(ResourceGroupConfig.builder()
                        .requests(new ResourceGroupLimit(RECYCLE_RG_REQUEST_NODE_NUM))
                        .limits(new ResourceGroupLimit(RECYCLE_RG_LIMIT_NODE_NUM))
                        .build())
                .build());

        this.scaling(DEFAULT_RG, defaultResourceGroupNodeNum);
    }

    /**
     * Create a resource group with given nodeNum.
     *
     * @param resourceGroupName
     * @param requestNodeNum
     */
    public void createResourceGroup(String resourceGroupName, Integer requestNodeNum) {
        client.createResourceGroup(CreateResourceGroupReq.builder()
                .resourceGroupName(resourceGroupName)
                .config(ResourceGroupConfig.builder()
                        .requests(new ResourceGroupLimit(requestNodeNum))
                        .limits(new ResourceGroupLimit(requestNodeNum))
                        .from(Arrays.asList(new ResourceGroupTransfer(RECYCLE_RG)))
                        .to(Arrays.asList(new ResourceGroupTransfer(RECYCLE_RG)))
                        .build())
                .build());
    }

    /**
     * Drop a resource group, before drop resource group, you should scale the resource group to 0 first.
     * 
     * @param resourceGroupName
     */
    public void dropResourceGroup(String resourceGroupName) {
        client.dropResourceGroup(new DropResourceGroupReq(resourceGroupName));
    }

    /**
     * Scale the number of nodes in a resource group.
     * 
     * @param resourceGroupName
     * @param requestNodeNum
     */
    public void scaling(String resourceGroupName, Integer requestNodeNum) {
        // Update a resource group with given nodeNum.
        // Set the resource group transfer to recycle resource group by default.
        Map<String, ResourceGroupConfig> rgConfigs = Map.of(
                resourceGroupName, ResourceGroupConfig.builder()
                        .requests(new ResourceGroupLimit(requestNodeNum))
                        .limits(new ResourceGroupLimit(requestNodeNum))
                        .from(Arrays.asList(new ResourceGroupTransfer(RECYCLE_RG)))
                        .to(Arrays.asList(new ResourceGroupTransfer(RECYCLE_RG)))
                        .build());
        client.updateResourceGroup(new UpdateResourceGroupsReq(rgConfigs));
    }

    /**
     * Transfer a database to specified resource group.
     * Only support single replica now.
     * 
     * @param dbName The name of the database to transfer.
     * @param resourceGroupName The name of the target resource group.
     */
    public void transferDataBaseToResourceGroup(String dbName, String resourceGroupName) {
        client.useDatabase(dbName);
        ListCollectionsResp resp = client.listCollections();
        for (String collection : resp.getCollectionNames()) {
            // Get the current resource group of the collection.
            GetReplicasResp replica = client.getReplicas(new GetReplicasReq(collection));
            if (replica.getReplicaInfos().size() > 1) {
                // Multi replica is now supported now.
                throw new RuntimeException("Replica number is greater than 1, did not support now");
            }
            String currentResourceGroupName = replica.getReplicaInfos().get(0).getResourceGroupName();
            // Is already in the target resource group, skip it.
            if (currentResourceGroupName == resourceGroupName) {
                continue;
            }

            client.transferReplica(TransferReplicaReq.builder()
                    .dbName(dbName)
                    .collectionName(collection)
                    .sourceResourceGroupName(currentResourceGroupName)
                    .targetResourceGroupName(resourceGroupName)
                    .replicaNum(1)
                    .build());
        }
    }
}
