package io.milvus.v2.service.replica;

import io.milvus.grpc.MilvusServiceGrpc;
import io.milvus.v2.service.BaseService;
import io.milvus.v2.service.replica.request.*;
import io.milvus.v2.service.replica.response.*;

public class ReplicaService extends BaseService {
    public GetReplicasResp getReplicas(MilvusServiceGrpc.MilvusServiceBlockingStub blockingStub, GetReplicasReq request) {
        io.milvus.grpc.GetReplicasResponse resp = blockingStub.getReplicas(request.toGRPC());
        rpcUtils.handleResponse(request.toString(), resp.getStatus());
        return GetReplicasResp.fromGRPC(resp);
    }
}
