package io.milvus.v2.service.rg.util;

import com.google.rpc.context.AttributeContext.Resource;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResourceGroupLimit {
    private Integer nodeNum;

    public ResourceGroupLimit(Integer nodeNum) {
        this.nodeNum = nodeNum;
    }

    public ResourceGroupLimit(io.milvus.grpc.ResourceGroupLimitOrBuilder grpcLimit) {
        this.nodeNum = grpcLimit.getNodeNum();
    }

    public io.milvus.grpc.ResourceGroupLimit toGRPC() {
        return io.milvus.grpc.ResourceGroupLimit.newBuilder().setNodeNum(nodeNum).build();
    }
}
