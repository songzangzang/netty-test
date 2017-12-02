package com.test.grpc.test2;

import com.test.grpc.GreeterImpl;
import com.test.grpc.test1.GrpcServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * 服务端
 *
 * @author songxibo
 * @date 2017/12/2下午1:32
 */
public class GrpcServerStream {

    private int port = 9999;

    private Server server;

    /**
     * 启动服务
     *
     * @throws Exception
     */
    private void start() throws Exception {

        server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();

        // jvm回调钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            System.out.println("jvm 关闭");

            shundown();

        }));


        System.out.println("服务端启动...");

    }

    /**
     * 等待阻塞
     *
     * @throws Exception
     */
    private void await() throws Exception {

        if (server != null) {
            server.awaitTermination();
        }

    }

    private void shundown() {

        if (server != null) {

            server.shutdown();

        }

    }

    public static void main(String[] args) throws Exception {

        GrpcServerStream server = new GrpcServerStream();

        server.start();
        server.await();

    }

}
