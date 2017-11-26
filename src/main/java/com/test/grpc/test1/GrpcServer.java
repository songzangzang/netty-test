package com.test.grpc.test1;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * grpc 服务
 *
 * @author songxibo
 */
public class GrpcServer {

    private int port = 9999;
    private Server server;

    /**
     * 启动服务
     *
     * @throws Exception
     */
    private void start() throws Exception {

        server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();

        System.out.println("服务端启动...");

    }

    /**
     * 无响应时退出
     *
     * @throws Exception
     */
    private void blockUntilShutdown() throws Exception {

        if (server != null) {
            server.awaitTermination();
        }

    }

    public static void main(String[] args) throws Exception{

        GrpcServer server = new GrpcServer();

        server.start();
        server.blockUntilShutdown();

    }


}
