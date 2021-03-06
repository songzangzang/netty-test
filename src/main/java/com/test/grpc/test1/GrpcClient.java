package com.test.grpc.test1;

import com.test.grpc.GreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import static com.test.grpc.Student.*;

/**
 * grpc 客户端
 *
 * @author songxibo
 */
public class GrpcClient {

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterBlockingStub blockingStub;


    public GrpcClient(String host, int port) {

        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);

    }

    /**
     * 传入对象并返回一个对象
     *
     * @param name    对象名称
     * @param message 对象信息
     */
    public void callServer(String name, String message) {

        HelloRequest request = HelloRequest.newBuilder()
                .setName(name).setMessage(message).build();

        HelloResponse response;

        try {

            response = blockingStub.sayHello(request);
            System.out.println("服务端响应 " + response.getMessage());

        } catch (Exception e) {

            throw new RuntimeException("grpc client failure{}", e);

        }

    }


    public static void main(String[] args) {

        GrpcClient client = new GrpcClient("localhost", 9999);

        client.callServer("卡尔大魔王", "great!");

    }

}
