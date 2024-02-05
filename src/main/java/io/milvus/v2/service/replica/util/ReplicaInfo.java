package io.milvus.v2.service.replica.util;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ReplicaInfo {
    private String resourceGroupName;
    private Map<String, Integer> outboundNodesNum; // outbound access rg -> querynode num.

    public static ReplicaInfo fromGRPC(io.milvus.grpc.ReplicaInfo replicaInfo) {
        return ReplicaInfo.builder()
                .resourceGroupName(replicaInfo.getResourceGroupName())
                .outboundNodesNum(replicaInfo.getNumOutboundNodeMap())
                .build();
    }
}
