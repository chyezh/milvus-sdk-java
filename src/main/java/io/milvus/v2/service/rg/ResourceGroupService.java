package io.milvus.v2.service.rg;

import io.milvus.grpc.MilvusServiceGrpc;
import io.milvus.v2.service.BaseService;
import io.milvus.v2.service.rg.request.*;
import io.milvus.v2.service.rg.response.*;
import io.milvus.grpc.Status;

public class ResourceGroupService extends BaseService {
    public void createResourceGroup(MilvusServiceGrpc.MilvusServiceBlockingStub blockingStub,
            CreateResourceGroupReq request) {
        Status status = blockingStub.createResourceGroup(request.toGRPC());
        rpcUtils.handleResponse(request.toString(), status);
    }

    public void updateResourceGroups(MilvusServiceGrpc.MilvusServiceBlockingStub blockingStub,
            UpdateResourceGroupsReq request) {
        Status status = blockingStub.updateResourceGroups(request.toGRPC());
        rpcUtils.handleResponse(request.toString(), status);
    }

    public void dropResourceGroup(MilvusServiceGrpc.MilvusServiceBlockingStub blockingStub,
            DropResourceGroupReq request) {
        Status status = blockingStub.dropResourceGroup(request.toGRPC());
        rpcUtils.handleResponse(request.toString(), status);
    }

    public ListResourceGroupsResp listResourceGroups(MilvusServiceGrpc.MilvusServiceBlockingStub blockingStub,
            ListResourceGroupsReq request) {
        io.milvus.grpc.ListResourceGroupsResponse response = blockingStub.listResourceGroups(request.toGRPC());
        rpcUtils.handleResponse(request.toString(), response.getStatus());
        return new ListResourceGroupsResp(response);
    }

    public DescribeResourceGroupResp describeResourceGroup(MilvusServiceGrpc.MilvusServiceBlockingStub blockingStub,
            DescribeResourceGroupReq request) {
        io.milvus.grpc.DescribeResourceGroupResponse response = blockingStub
                .describeResourceGroup(request.toGRPC());
        rpcUtils.handleResponse(request.toString(), response.getStatus());
        return new DescribeResourceGroupResp(response);
    }

    public void transferReplica(MilvusServiceGrpc.MilvusServiceBlockingStub blockingStub,
            TransferReplicaReq request) {
        Status status = blockingStub.transferReplica(request.toGRPC());
        rpcUtils.handleResponse(request.toString(), status);
    }
}
