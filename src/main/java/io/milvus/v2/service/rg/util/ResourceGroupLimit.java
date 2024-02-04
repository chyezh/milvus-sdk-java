package io.milvus.v2.service.rg.util;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResourceGroupLimit {
    private Integer nodeNum;

    public static ResourceGroupLimit fromGRPC(io.milvus.grpc.ResourceGroupLimitOrBuilder grpcLimit) {
        return ResourceGroupLimit.builder().nodeNum(grpcLimit.getNodeNum()).build();
    }

    public io.milvus.grpc.ResourceGroupLimit toGRPC() {
        return io.milvus.grpc.ResourceGroupLimit.newBuilder().setNodeNum(nodeNum).build();
    }
}
