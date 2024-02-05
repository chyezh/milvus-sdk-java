package io.milvus.v2.service.rg.request;

import io.milvus.v2.service.rg.util.ResourceGroupConfig;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CreateResourceGroupReq {
     private String resourceGroupName;
     private ResourceGroupConfig config;

     public CreateResourceGroupReq(String resourceGroupName, ResourceGroupConfig config) {
          this.resourceGroupName = resourceGroupName;
          this.config = config;
     }

     public io.milvus.grpc.CreateResourceGroupRequest toGRPC() {
          return io.milvus.grpc.CreateResourceGroupRequest.newBuilder().setResourceGroup(resourceGroupName)
                    .setConfig(config.toGRPC()).build();
     }

     @Override
     public String toString() {
          return String.format("CreateResourceGroupRequest{resourceGroupName=%s}", resourceGroupName);
     }
}