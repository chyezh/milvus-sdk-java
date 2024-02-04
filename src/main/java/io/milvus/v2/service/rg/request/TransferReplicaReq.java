package io.milvus.v2.service.rg.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TransferReplicaReq {
    private String sourceResourceGroupName;
    private String targetResourceGroupName;
    private String dbName;
    private String collectionName;
    private Integer replicaNum;

    public io.milvus.grpc.TransferReplicaRequest toGRPC() {
        return io.milvus.grpc.TransferReplicaRequest.newBuilder()
                .setSourceResourceGroup(sourceResourceGroupName)
                .setTargetResourceGroup(targetResourceGroupName)
                .setDbName(dbName)
                .setCollectionName(collectionName)
                .setNumReplica(replicaNum)
                .build();
    }

    @Override
    public String toString() {
        return String.format("TransferReplica{%s->%s, collection:%s, replica:%d}", sourceResourceGroupName,
                targetResourceGroupName, collectionName, replicaNum);
    }
}
