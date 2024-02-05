package io.milvus.v2.service.rg.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListResourceGroupsReq {
    public ListResourceGroupsReq() {
    }

    public io.milvus.grpc.ListResourceGroupsRequest toGRPC() {
        return io.milvus.grpc.ListResourceGroupsRequest.newBuilder().build();
    }

    @Override
    public String toString() {
        return "ListResourceGroupsRequest";
    }
}
