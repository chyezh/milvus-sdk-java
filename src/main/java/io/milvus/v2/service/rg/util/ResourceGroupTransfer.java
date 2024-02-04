package io.milvus.v2.service.rg.util;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResourceGroupTransfer {
    private String resourceGroupName;

    public static ResourceGroupTransfer fromGRPC(io.milvus.grpc.ResourceGroupTransferOrBuilder grpcTransfer) {
        return ResourceGroupTransfer.builder().resourceGroupName(grpcTransfer.getResourceGroup()).build();
    }

    public io.milvus.grpc.ResourceGroupTransfer toGRPC() {
        return io.milvus.grpc.ResourceGroupTransfer.newBuilder().setResourceGroup(resourceGroupName).build();
    }
}
