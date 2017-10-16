package com.test.thrift.test2;


import com.test.thrift.generated.GodService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

public class MyThriftServer {

    public static void main(String[] args) throws Exception {

        TNonblockingServerSocket socket = new TNonblockingServerSocket(9999);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        GodService.Processor<GodServiceImpl> processor = new GodService.Processor<>(new GodServiceImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("服务器启动");
        server.serve();

    }


}
