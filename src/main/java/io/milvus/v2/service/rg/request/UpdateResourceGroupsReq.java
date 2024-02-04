package io.milvus.v2.service.rg.request;

import io.milvus.v2.service.rg.util.ResourceGroupConfig;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@SuperBuilder
public class UpdateResourceGroupsReq {
    private Map<String, ResourceGroupConfig> resourceGroups;

    public io.milvus.grpc.UpdateResourceGroupsRequest toGRPC() {
        io.milvus.grpc.UpdateResourceGroupsRequest.Builder builder = io.milvus.grpc.UpdateResourceGroupsRequest
                .newBuilder();
        resourceGroups.forEach((k, v) -> {
            builder.putResourceGroups(k, v.toGRPC());
        });
        return builder.build();
    }

    @Override
    public String toString() {
        return String.format("UpdateResourceGroupsRequest{resourceGroupNames:%s}",
                resourceGroups.keySet().stream().collect(Collectors.joining(",")));
    }
}
