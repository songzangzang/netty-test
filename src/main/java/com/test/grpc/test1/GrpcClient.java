package com.test.grpc.test1;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.Student;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

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

    public void callServer (String name, String message) {

        Student.HelloRequest request = Student.HelloRequest.newBuilder()
                .setName(name).setMessage(message).build();

        Student.HelloReponse reponse;

        try {

            reponse = blockingStub.sayHello(request);
            System.out.println("服务端响应 " +  reponse.getMessage());

        } catch (Exception e) {

            throw new RuntimeException("grpc client failure", e);

        }

    }


    public static void main(String[] args) {

        GrpcClient client = new GrpcClient("localhost", 9999);

        client.callServer("卡尔大魔王", "great!");

    }

}
