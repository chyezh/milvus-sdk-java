package io.milvus.v2.service.rg.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DescribeResourceGroupReq {
    private String resourceGroupName;

    public DescribeResourceGroupReq(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName;
    }

    public io.milvus.grpc.DescribeResourceGroupRequest toGRPC() {
        return io.milvus.grpc.DescribeResourceGroupRequest.newBuilder().setResourceGroup(resourceGroupName).build();
    }

    @Override
    public String toString() {
        return String.format("DescribeResourceGroupRequest{resourceGroupName=%s}", resourceGroupName);
    }
}
