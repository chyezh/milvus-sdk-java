package io.milvus.v2.service.rg.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DropResourceGroupReq {
    private String resourceGroupName;

    public io.milvus.grpc.DropResourceGroupRequest toGRPC() {
        return io.milvus.grpc.DropResourceGroupRequest.newBuilder().setResourceGroup(resourceGroupName).build();
    }

    @Override
    public String toString() {
        return String.format("DropResourceGroupRequest{resourceGroupName=%s}", resourceGroupName);
    }
}
