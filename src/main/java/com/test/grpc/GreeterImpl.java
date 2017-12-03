package com.test.grpc;

import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(Student.HelloRequest request, StreamObserver<Student.HelloResponse> responseObserver) {

        Student.HelloResponse r = Student.HelloResponse.newBuilder()
                .setMessage("Hello " + request.getName() + "消息为 " + request.getMessage()).build();

        responseObserver.onNext(r);
        responseObserver.onCompleted();

    }

    @Override
    public void sayServerStreamHello(Student.HelloRequest request, StreamObserver<Student.HelloResponse> responseObserver) {

        String time = LocalDateTime.now().toString();

        responseObserver.onNext(Student.HelloResponse.newBuilder().setMessage("Stream Server " + time).build());
        responseObserver.onNext(Student.HelloResponse.newBuilder().setMessage("Stream Server " + time).build());
        responseObserver.onNext(Student.HelloResponse.newBuilder().setMessage("Stream Server " + time).build());
        responseObserver.onNext(Student.HelloResponse.newBuilder().setMessage("Stream Server " + time).build());
        responseObserver.onNext(Student.HelloResponse.newBuilder().setMessage("Stream Server " + time).build());
        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<Student.HelloRequest> sayClientStreamHello(StreamObserver<Student.HelloResponse> responseObserver) {

        return new StreamObserver<Student.HelloRequest>() {

            @Override
            public void onNext(Student.HelloRequest value) {

                System.out.println(value.getName() + value.getMessage());
                responseObserver.onNext(Student.HelloResponse.newBuilder().setMessage("hello " + value.getName()).build());

            }

            @Override
            public void onError(Throwable t) {

                System.out.println("error");

            }

            @Override
            public void onCompleted() {

                responseObserver.onCompleted();

            }

        };

    }

    @Override
    public StreamObserver<Student.HelloRequest> streamConnection(StreamObserver<Student.HelloResponse> responseObserver) {

        return new StreamObserver<Student.HelloRequest>() {

            @Override
            public void onNext(Student.HelloRequest value) {

                System.out.println(value.getName() + ", " + value.getMessage());

                responseObserver.onNext(Student.HelloResponse.newBuilder().setMessage(LocalDateTime.now().toString()).build());

            }

            @Override
            public void onError(Throwable t) {

                System.out.println("error");

            }

            @Override
            public void onCompleted() {

                responseObserver.onCompleted();

            }

        };

    }

}
