package com.test.grpc.test4;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.Student;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;

/**
 * 客户端
 *
 * @author songxibo
 * @date 2017/12/2下午6:56
 */
public class GrpcClientStream {

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterStub stu;

    public GrpcClientStream(String host, int port) {

        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();

        stu = GreeterGrpc.newStub(channel);

    }

    /**
     * 双向流通信
     *
     * @param name    名称
     */
    public void callServer(String name) throws InterruptedException {


        StreamObserver<Student.HelloRequest> request = stu.streamConnection(new StreamObserver<Student.HelloResponse>() {

            @Override
            public void onNext(Student.HelloResponse value) {

                System.out.println(value.getMessage());

            }

            @Override
            public void onError(Throwable t) {

                System.out.println(t.getMessage());

            }

            @Override
            public void onCompleted() {

                System.out.println("completed!");

            }

        });

        for (int i = 0; i < 10; i++) {

            request.onNext(Student.HelloRequest.newBuilder().setName(name).setMessage(LocalDateTime.now().toString()).build());
            Thread.sleep(1000);

        }

        request.onCompleted();

    }

    public static void main(String[] args) throws InterruptedException {

        GrpcClientStream clientStream = new GrpcClientStream("localhost", 9999);

        clientStream.callServer("卡尔大魔王");

    }

}
