package io.milvus.v2.service.rg.util;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResourceGroup {
    private String resourceGroupName;
    private Integer availableNodeNum;
    private java.util.Map<String, Integer> loadedReplicaNum;
    private java.util.Map<String, Integer> outgoingNodeNum;
    private java.util.Map<String, Integer> incomingNodeNum;
    private ResourceGroupConfig resourceGroupConfig;

    public ResourceGroup(io.milvus.grpc.ResourceGroup rg) {
        this.resourceGroupName = rg.getName();
        this.availableNodeNum = rg.getNumAvailableNode();
        this.loadedReplicaNum = rg.getNumLoadedReplicaMap();
        this.outgoingNodeNum = rg.getNumOutgoingNodeMap();
        this.incomingNodeNum = rg.getNumIncomingNodeMap();
        this.resourceGroupConfig = new ResourceGroupConfig(rg.getConfig());
    }
}
