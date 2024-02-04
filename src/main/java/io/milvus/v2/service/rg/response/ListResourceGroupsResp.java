package io.milvus.v2.service.rg.response;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListResourceGroupsResp {
    private List<String> resourcesGroupNames;

    public static ListResourceGroupsResp fromGRPC(io.milvus.grpc.ListResourceGroupsResponse response) {
        return ListResourceGroupsResp.builder().resourcesGroupNames(response.getResourceGroupsList()).build();
    }
}
