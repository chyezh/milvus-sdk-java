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

    public static ResourceGroup fromGRPC(io.milvus.grpc.ResourceGroup rg) {
        return ResourceGroup.builder().resourceGroupName(rg.getName())
                .availableNodeNum(rg.getNumAvailableNode()).loadedReplicaNum(rg.getNumLoadedReplicaMap())
                .outgoingNodeNum(rg.getNumOutgoingNodeMap()).incomingNodeNum(rg.getNumIncomingNodeMap())
                .resourceGroupConfig(ResourceGroupConfig.fromGRPC(rg.getConfig())).build();
    }
}
