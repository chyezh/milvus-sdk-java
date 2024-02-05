package io.milvus.v2.service.replica.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GetReplicasReq {
    private String collectionName;

    public GetReplicasReq(String collectionName) {
        this.collectionName = collectionName;
    }

    public io.milvus.grpc.GetReplicasRequest toGRPC() {
        return io.milvus.grpc.GetReplicasRequest.newBuilder()
                .setCollectionName(collectionName)
                .build();
    }

    @Override
    public String toString() {
        return String.format("GetReplicasReq{collectionName='%s'}", collectionName);
    }
}
