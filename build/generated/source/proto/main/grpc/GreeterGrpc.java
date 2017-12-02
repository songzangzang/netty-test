import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: Student.proto")
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "proto.Greeter";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Student.HelloRequest,
      Student.HelloResponse> METHOD_SAY_HELLO =
      io.grpc.MethodDescriptor.<Student.HelloRequest, Student.HelloResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "proto.Greeter", "SayHello"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Student.HelloRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Student.HelloResponse.getDefaultInstance()))
          .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayHello"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Student.HelloRequest,
      Student.HelloResponse> METHOD_SAY_SERVER_STREAM_HELLO =
      io.grpc.MethodDescriptor.<Student.HelloRequest, Student.HelloResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "proto.Greeter", "SayServerStreamHello"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Student.HelloRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Student.HelloResponse.getDefaultInstance()))
          .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayServerStreamHello"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Student.HelloRequest,
      Student.HelloResponse> METHOD_SAY_CLIENT_STREAM_HELLO =
      io.grpc.MethodDescriptor.<Student.HelloRequest, Student.HelloResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "proto.Greeter", "SayClientStreamHello"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Student.HelloRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Student.HelloResponse.getDefaultInstance()))
          .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayClientStreamHello"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Student.HelloRequest,
      Student.HelloResponse> METHOD_STREAM_CONNECTION =
      io.grpc.MethodDescriptor.<Student.HelloRequest, Student.HelloResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "proto.Greeter", "streamConnection"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Student.HelloRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Student.HelloResponse.getDefaultInstance()))
          .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("streamConnection"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    return new GreeterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterFutureStub(channel);
  }

  /**
   */
  public static abstract class GreeterImplBase implements io.grpc.BindableService {

    /**
     */
    public void sayHello(Student.HelloRequest request,
        io.grpc.stub.StreamObserver<Student.HelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SAY_HELLO, responseObserver);
    }

    /**
     */
    public void sayServerStreamHello(Student.HelloRequest request,
        io.grpc.stub.StreamObserver<Student.HelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SAY_SERVER_STREAM_HELLO, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Student.HelloRequest> sayClientStreamHello(
        io.grpc.stub.StreamObserver<Student.HelloResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_SAY_CLIENT_STREAM_HELLO, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Student.HelloRequest> streamConnection(
        io.grpc.stub.StreamObserver<Student.HelloResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_STREAM_CONNECTION, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SAY_HELLO,
            asyncUnaryCall(
              new MethodHandlers<
                Student.HelloRequest,
                Student.HelloResponse>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            METHOD_SAY_SERVER_STREAM_HELLO,
            asyncServerStreamingCall(
              new MethodHandlers<
                Student.HelloRequest,
                Student.HelloResponse>(
                  this, METHODID_SAY_SERVER_STREAM_HELLO)))
          .addMethod(
            METHOD_SAY_CLIENT_STREAM_HELLO,
            asyncClientStreamingCall(
              new MethodHandlers<
                Student.HelloRequest,
                Student.HelloResponse>(
                  this, METHODID_SAY_CLIENT_STREAM_HELLO)))
          .addMethod(
            METHOD_STREAM_CONNECTION,
            asyncBidiStreamingCall(
              new MethodHandlers<
                Student.HelloRequest,
                Student.HelloResponse>(
                  this, METHODID_STREAM_CONNECTION)))
          .build();
    }
  }

  /**
   */
  public static final class GreeterStub extends io.grpc.stub.AbstractStub<GreeterStub> {
    private GreeterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     */
    public void sayHello(Student.HelloRequest request,
        io.grpc.stub.StreamObserver<Student.HelloResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SAY_HELLO, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sayServerStreamHello(Student.HelloRequest request,
        io.grpc.stub.StreamObserver<Student.HelloResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_SAY_SERVER_STREAM_HELLO, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Student.HelloRequest> sayClientStreamHello(
        io.grpc.stub.StreamObserver<Student.HelloResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_SAY_CLIENT_STREAM_HELLO, getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Student.HelloRequest> streamConnection(
        io.grpc.stub.StreamObserver<Student.HelloResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_STREAM_CONNECTION, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GreeterBlockingStub extends io.grpc.stub.AbstractStub<GreeterBlockingStub> {
    private GreeterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    /**
     */
    public Student.HelloResponse sayHello(Student.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SAY_HELLO, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<Student.HelloResponse> sayServerStreamHello(
        Student.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_SAY_SERVER_STREAM_HELLO, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreeterFutureStub extends io.grpc.stub.AbstractStub<GreeterFutureStub> {
    private GreeterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Student.HelloResponse> sayHello(
        Student.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SAY_HELLO, getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SAY_SERVER_STREAM_HELLO = 1;
  private static final int METHODID_SAY_CLIENT_STREAM_HELLO = 2;
  private static final int METHODID_STREAM_CONNECTION = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((Student.HelloRequest) request,
              (io.grpc.stub.StreamObserver<Student.HelloResponse>) responseObserver);
          break;
        case METHODID_SAY_SERVER_STREAM_HELLO:
          serviceImpl.sayServerStreamHello((Student.HelloRequest) request,
              (io.grpc.stub.StreamObserver<Student.HelloResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_CLIENT_STREAM_HELLO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayClientStreamHello(
              (io.grpc.stub.StreamObserver<Student.HelloResponse>) responseObserver);
        case METHODID_STREAM_CONNECTION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamConnection(
              (io.grpc.stub.StreamObserver<Student.HelloResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Student.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeter");
    }
  }

  private static final class GreeterFileDescriptorSupplier
      extends GreeterBaseDescriptorSupplier {
    GreeterFileDescriptorSupplier() {}
  }

  private static final class GreeterMethodDescriptorSupplier
      extends GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreeterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
              .addMethod(METHOD_SAY_HELLO)
              .addMethod(METHOD_SAY_SERVER_STREAM_HELLO)
              .addMethod(METHOD_SAY_CLIENT_STREAM_HELLO)
              .addMethod(METHOD_STREAM_CONNECTION)
              .build();
        }
      }
    }
    return result;
  }
}
