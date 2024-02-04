package io.milvus.v2.service.rg.response;

import io.milvus.v2.service.rg.util.ResourceGroup;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DescribeResourceGroupResp {
    private ResourceGroup resourceGroup;

    public static DescribeResourceGroupResp fromGRPC(io.milvus.grpc.DescribeResourceGroupResponse response) {
        io.milvus.grpc.ResourceGroup rg = response.getResourceGroup();
        return DescribeResourceGroupResp.builder().resourceGroup(ResourceGroup.fromGRPC(rg)).build();
    }
}