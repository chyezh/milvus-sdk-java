package io.milvus.v2.service.rg.response;

import io.milvus.v2.service.rg.util.ResourceGroup;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DescribeResourceGroupResp {
    private ResourceGroup resourceGroup;

    public DescribeResourceGroupResp(io.milvus.grpc.DescribeResourceGroupResponse response) {
        this.resourceGroup = new ResourceGroup(response.getResourceGroup());
    }
}