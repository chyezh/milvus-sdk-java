package io.milvus.v2.service.rg.util;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResourceGroupTransfer {
    private String resourceGroupName;

    public ResourceGroupTransfer(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName;
    }

    public ResourceGroupTransfer(io.milvus.grpc.ResourceGroupTransferOrBuilder grpcTransfer) {
        this.resourceGroupName = grpcTransfer.getResourceGroup();
    }

    public io.milvus.grpc.ResourceGroupTransfer toGRPC() {
        return io.milvus.grpc.ResourceGroupTransfer.newBuilder().setResourceGroup(resourceGroupName).build();
    }
}
