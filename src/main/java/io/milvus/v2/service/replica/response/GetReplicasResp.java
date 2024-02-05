package io.milvus.v2.service.replica.response;

import java.util.List;

import io.milvus.v2.service.replica.util.ReplicaInfo;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.util.stream.Collectors;

@Data
@SuperBuilder
public class GetReplicasResp {
    private List<ReplicaInfo> replicaInfos;

    public static GetReplicasResp fromGRPC(io.milvus.grpc.GetReplicasResponse response) {
        return GetReplicasResp.builder()
                .replicaInfos(response.getReplicasList()
                        .stream()
                        .map(replicaInfo -> ReplicaInfo.fromGRPC(replicaInfo))
                        .collect(Collectors.toList()))
                .build();
    }
}
