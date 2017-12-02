package com.test.grpc.test3;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.Student;
import com.test.grpc.test2.GrpcClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * 客户端
 *
 * @author songxibo
 * @date 2017/12/2下午1:42
 */
public class GrpcClientStream {

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterStub stub;

    public GrpcClientStream(String host, int port) {

        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();

        stub = GreeterGrpc.newStub(channel);

    }

    /**
     * 传递流调用服务端
     *
     * @param name    传送名称
     * @param message 传送信息
     */
    public void callServer(String name, String message) throws InterruptedException {

        StreamObserver<Student.HelloResponse> responseObserver = new StreamObserver<Student.HelloResponse>() {

            @Override
            public void onNext(Student.HelloResponse value) {

                System.out.println("server reponse" + value.getMessage());

            }

            @Override
            public void onError(Throwable t) {

                System.out.println(t.getMessage());

            }

            @Override
            public void onCompleted() {

                System.out.println("completed!");

            }

        };

        StreamObserver<Student.HelloRequest> request = stub.sayClientStreamHello(responseObserver);

        request.onNext(Student.HelloRequest.newBuilder().setName(name).setMessage(message).build());
        request.onCompleted();
        Thread.sleep(1000);

    }

    public static void main(String[] args) throws Exception{

        GrpcClientStream clientStream = new GrpcClientStream("localhost", 9999);

        clientStream.callServer("卡尔大魔王", "great");

    }


}
