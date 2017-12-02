package com.test.grpc.test2;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.Student;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.Optional;

/**
 * 客户端
 *
 * @author songxibo
 * @date 2017/12/2下午1:32
 */
public class GrpcClient {

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public GrpcClient(String host, int port) {

        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);

    }

    /**
     * 传入对象并返回一个流
     *
     * @param name    对象名称
     * @param message 对象信息
     */
    public void callServerStream(String name, String message) {

        Student.HelloRequest request = Student.HelloRequest.newBuilder()
                .setName(name).setMessage(message).build();

        Student.HelloResponse response;

        try {

            Iterator<Student.HelloResponse> helloArray = blockingStub.sayServerStreamHello(request);

            Optional.ofNullable(helloArray).ifPresent(array -> array.forEachRemaining(h -> System.out.println(h)));

        } catch (Exception e) {

            throw new RuntimeException("grpc client stream failure{}", e);

        }

    }

    public static void main(String[] args) {

        GrpcClient client = new GrpcClient("localhost", 9999);

        client.callServerStream("卡尔大魔王1", "hello");

    }

}



