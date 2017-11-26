package com.test.grpc.test1;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.Student;
import io.grpc.stub.StreamObserver;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(Student.HelloRequest request, StreamObserver<Student.HelloReponse> responseObserver) {

        Student.HelloReponse r = Student.HelloReponse.newBuilder()
                .setMessage("Hello " + request.getName() + "消息为 " + request.getMessage()).build();

        responseObserver.onNext(r);
        responseObserver.onCompleted();

    }



}
