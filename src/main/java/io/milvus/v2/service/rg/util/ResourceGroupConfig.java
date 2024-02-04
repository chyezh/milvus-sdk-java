package io.milvus.v2.service.rg.util;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.stream.Collectors;
import java.util.List;

@Data
@SuperBuilder
public class ResourceGroupConfig {
    private ResourceGroupLimit requests;
    private ResourceGroupLimit limits;
    private List<ResourceGroupTransfer> from;
    private List<ResourceGroupTransfer> to;

    @Getter
    public class Transfer {
        private String resourceGroupName;
    }

    public static ResourceGroupConfig fromGRPC(io.milvus.grpc.ResourceGroupConfigOrBuilder grpcConfig) {
        return ResourceGroupConfig.builder()
                .requests(ResourceGroupLimit.fromGRPC(grpcConfig.getRequests()))
                .limits(ResourceGroupLimit.fromGRPC(grpcConfig.getLimits()))
                .from(grpcConfig.getFromList().stream()
                        .map(transfer -> ResourceGroupTransfer.fromGRPC(transfer))
                        .collect(Collectors.toList()))
                .to(grpcConfig.getToList().stream()
                        .map(transfer -> ResourceGroupTransfer.fromGRPC(transfer))
                        .collect(Collectors.toList()))
                .build();
    }

    public io.milvus.grpc.ResourceGroupConfig toGRPC() {
        io.milvus.grpc.ResourceGroupConfig.Builder builder = io.milvus.grpc.ResourceGroupConfig.newBuilder()
                .setRequests(io.milvus.grpc.ResourceGroupLimit.newBuilder().setNodeNum(requests.getNodeNum()))
                .setLimits(io.milvus.grpc.ResourceGroupLimit.newBuilder().setNodeNum(limits.getNodeNum()));
        for (ResourceGroupTransfer transfer : from) {
            builder.addFrom(transfer.toGRPC());
        }
        for (ResourceGroupTransfer transfer : to) {
            builder.addTo(transfer.toGRPC());
        }
        return builder.build();
    }
}
