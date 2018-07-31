### Netty 学习记录
- 整理netty相关特性和知识
- java nio方面的记录
- rpc框架的使用(pb, thrift, grpc)
 

### Netty4.X官方文档

###  前言

### 问题

  当前我们使用通用的应用或者类库去进行通信，举个例子，我们通常使用HTTP客户端类库在web服务器或者应用调用web服务去获得信息，然后，一个通用协议它们通常实现的都不是很好，我们一般不使用提供的HTTP协议来获得大的文件信息，e-mail 信息，还有一些实时的信息比如金融信息和多种类型的游戏信息，怎么能获得最佳协议实现选择指定哪一个来执行，举个例子，你可能想要去实现HTTP服务可以基于基本的AJAX进行应用优化，流媒体，大文件传输，你可能会想去设计并且实现一个完整的协议来帮进行定制化的选择，另一种情况当你想要去处理和老系统之间的私有通信已确保和其之间的互信，最重要的是怎么才能快速实现这种协议并且还能保证通信的稳定性和安全性。

####  解决方案
  
  Netty是致力于提供异步的事件驱动网络应用框架和组件产生迅速发展高性能，可维护性高，可伸缩的服务端和客户端
  
  换句话来说，netty是一个NIO客户端服务框架它可以快速并简单的创建网络框架的客户端和服务端，它非常简单并且把网络发展TCP和UDP,socket开发流程化
  
  快速和简单的意思不是说应用可能会产生性能上的问题，netty拥有很严密的设计，其中拥有的协议包括FTP,SMTP,HTTP和各种同二进制进行通信的协议，所以说netty可以找到方法去并进行使用，netty不会对性能，稳定，灵活进行妥协
  
  一些人可能发现其他网络应用框架也会出现相同的优点，但是你可能会想问一下netty和它们有什么不同，这个答案是建立在它上面的哲学，netty它让你在每天进行实现都会感觉到它拥有设计合理的api，它不是有型的但是它会改变你的生活，学习netty指南让netty像玩一样简单
  
####  启动
  
  如果你更喜欢自上而下的学习，那你可能想要从第二章开始，进行层级的覆盖并返回到这里
  
##### 开始前的准备
 
  在这章运行试例最低的要求有两点；和netty相配合的JDK版本要1.6或之上的，netty可以去首页进行下载并下载版本正确的JDK
  
  如果你在读，你可能会产生很多问题，请参考API说明，无论何时都会想知道更多关于它的知识，全部类名在文档中都有网络的链接和API的说明，并且，请不要犹豫的去netty的社区中和让我们知道不正确的信息，比如错误的语法和排版，并把更好的主意导入到说明文档中
  
##### 写一个Discard Server
  
  最简单协议不是"Hello World"，但是DISCARD，它是一个在没有接受到任何响应就会丢弃数据信息的协议
  
  去实现Discard 协议，它只要你不管接受到的数据，那就会让我们根据处理器的实现，具体可以根据netty进行I/O事件的生成
  
 ```javascript
 package io.netty.example.discard;

import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        ((ByteBuf) msg).release(); // (3)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
 ```
 
  1. DiscardServerHandler继承了ChannelInboundHandlerAdapter, 它实现了ChannelInboundHandler, ChannelInboundHandler提供了很多关于事件处理的方法并且你也可以把它们进行重写，在现在，它只要继承ChannelInboundHandlerAdapter或者也可以把它实现的接口进行实现
  2. 我们在这重写ChannelRead()事件处理方法，这个方法将会调用获得传送的信息，随便什么时候客户端都可以发送新的数据，在这个例子中，传递信息的类型是ByteBuf
  3. 实现Discard协议，处理时可以忽视传递来的信息，ByteBuf是一个reference-counted对象，它可以明确的调用released()方法进行释放，请一直保证它的作用是对释放进行处理，通常channelRead()处理方法是应用下列的方式进行实现
  
```javascript
@Override
public void channelRead(ChannelHandlerContext ctx, Object msg) {
    try {
        // Do something with msg
    } finally {
        ReferenceCountUtil.release(msg);
    }
}
 ``` 
  1. exceptionCaught()事件处理方法可以抛出异常信息，大多数情况下，异常应该被记录到日志并且把对应的channel进行关闭，可以对异常有多种的处理方式，举个例子，你可能想要在关闭连接之前对响应信息进行发送
  
  到目前为止都很顺利，我们需要实现上半个Discard 服务，现在剩下的就是写main()方法并启动DiscardServerHandler服务
 
```javascript
package io.netty.example.discard;
    
import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
    
public class DiscardServer {
    
    private int port;
    
    public DiscardServer(int port) {
        this.port = port;
    }
    
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // (3)
             .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new DiscardServerHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)          // (5)
             .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
    
            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)
    
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }
}
```

  1. NioEventLoopGroup是一个多线程的事件处理操作，netty提供各种各样的EventLoopGroup实现不同的传输。我们在实例中实现了一个服务应用，并且使用了两个NioEventLoopGroup,第一个，通常会称为"boos", 接受进来的连接。第二个，通常叫"worker",处理boos接受连接的信息并进行注册连接给worker,有多少线程使用并且怎么映射到Channel$在EventLoopGroup实现并且可以配置每一个构造函数
  2. ServerBootstrap是一个帮助类用来设置一个server,你可以使用一个channel来设置server,无论如何，请记住这是个乏味的过程，大多数情况下不需要这样做
  3. 这里我们指定去使用NioServerSocketChannel类，去使用一个新的Channel来进行实例化接受进入的连接
  4. 这个处理程序将会始终由一个新接受的渠道进行评估，ChannelInitializer()是一个指定的处理者来帮助配置一个新的Channel.它大部分想要根据新的Channel配置ChannelPipelinel()来添加一些处理器比如DiscardServerHandler去实现你的网络应用. 很可能您将向管道中添加更多的处理程序，并最终将这个匿名类提取到顶级类中
  5. 你能一直添加参数来指定Channel实现，我们下一个TCP/IP服务器，所以我们去添加socket选择比如tcpNoDelay和keepAlive. 请参考ChannelOptions的文档，来指定ChannelConfig实现去获得一个关于支付ChannelOption$的重写
  6. 是否注意到了option()和childOption()? option是NioServerSocketChannel会接受进来的连接，childOption()是Channel$接受父类ServerChannel, 在这种情况下不是NioServerSocket
  7. 我们现在准备好了，剩下就是绑定端口号和启动服务，这里，我们把端口号绑定成8080，你可以现在调用bind()方法来更换多种不同的端口号
  
#####   恭喜你刚刚使用netty完成了第一个服务器
  
#### 查看接受到的数据

  现在我们已经拥有了我们第一个服务器，我们想要测试一下它是否能正常工作，使用telent command方法可以简单的对其进行测试, 比如说，你可以调用localhost的8080端口看看会有什么状态显示
  
  然后，我们可以说这个服务器已经成功运行了么？ 我们并不知道它是否准备好因为它是个discards server. 你不会看到有任何的返回，那么证明它在正常工作，让我们对其进行修改并输出已经收到
  
  我们已经知道channelRead()方法是调用是用来接收所有的数据，让我们在其中填写一些代码

```javascript
@Override
public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ByteBuf in = (ByteBuf) msg;
    try {
        while (in.isReadable()) { // (1)
            System.out.print((char) in.readByte());
            System.out.flush();
        }
    } finally {
        ReferenceCountUtil.release(msg); // (2)
    }
}
```
  
  1. 可以把代码进行简写成System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII))
  2. 也可以选择在这里调用in.release()
  
  如果重启可以打印接收到的内容
  全部discard server的源码分布在io.netty.example.discard包内
  
#### 编写一个应答服务器

  到目前为止，我们在使用数据但是都没有回应，在服务器，无论用哪种方式，都会对请求进行响应，让我们学习怎么去写响应信息并传给客户端需要实现ECHO协议，把收到的信息进行返回
  
  与之前编写的唯一的不同就是我们需要在之前把得到的信息输出到控制台，因此，我们需要对ChannelRead()方法进行修改

```javascript  
     @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.write(msg); // (1)
        ctx.flush(); // (2)
    }
```  
  1. 一个ChannelHandlerContext提供了各种输入输出的时间操作，在这里我们调用write(Object)去把收到信息放入，请注意我们不是丢弃信息和之前的例子是不同的，因为netty把它写在了上面
  2. ctx.write(Object)它不会直接直接把信息传出，它有缓冲区，在之后我们需要调用ctx.flush(),同时也可以直接调用writeAndFlush(msg)
  
  如果你再次对服务端重启，你将会看到服务端输出你给它的信息
  全部echo server的源码分布在io.netty.example.echo包内
  
####  编写一个时间服务器

  去选择实现一个时间服务器，它和之前的例子不同只返回一条信息，其中包含一个32-bit的整数，没有接受到任何请求，返回一条信息并关闭，下面的例子是怎么发送信息，并且关闭连接
  
  因为我们会忽视所有接受到的信息，但只要建立连接就会发送一条信息，这一次我们不能使用channelRead()方法，我们将要重写channelActive()方法
  
```javascript 
package io.netty.example.time;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
```  
  1. 解释，channelActive()方法当连接时将会调用方法，并会输出一个32-bit的整数这个方法代表的是当前的时间
  2. 去发送一个新信息，我们期望分配一个新的缓冲区里面包含信息，我们去写一个32-bit的整数并且我们想要是一个ByteBuf它的容量至少是4个字符，通过ChannelHandlerContext.alloc()获得当前ByteBufAllocator并且分配一个新的buffer
  3. 通常，我们构造所编写的信息 
  但是应该在哪里呢？我们发送信息之前没有调用Nio中的java.nio.ByteBuffer.flip()么？ByteBuf不是这样的方法因为它有两个指针，是读取操作和写入操作，
当你进行写操作的时候ByteBuf不会改变reader的指针，读取指针和写入指针分别代表着开始和结束
  相比来说，Nio buffer中没有提供一种方法来判断开始和结束，不用调用flip方法，当你忘记flip缓冲区内的内容就会遇到麻烦可能会出现没有数据发送或者不正确的数据被发送，这样的错误不会在netty中发生，因为它有不同的指针类型，你会发现使用很容易
  另一个需要注意ChannelHandlerContext.write()(还有writeAndFlush())方法的返回一个ChannelFuture.一个ChannelFuture代表了还没有发生的IO操作，这表示，一些请求操作可能不会执行因为对于netty来说操作都是异步的，举个例子，下列的代码可能会在发送信息之前被关闭

```javascript   
  Channel ch = ...;
  ch.writeAndFlush(message);
  ch.close();
```

  因此，你需要确保ChannelFuture完成之后进行调用close()方法，由write()方法进行返回，并且它被写入的时候会通知监听器，请注意它，close()也可能不会立即关闭，并且它会返回一个ChannelFuture对象
  4. 当写入请求完成之后我们怎么获得通知？这是一个简单的adding一个ChannelFutureListener并返回一个ChannelFuture，在这里，我们创建一个新的匿名的ChannelFutureListener, 它操作结束会进行关闭
  
  作为选择，你可以简化编码使用一个预定义的监听器
  ```javascript 
  f.addListener(ChannelFutureListener.CLOSE);
  ```
  你可以使用Unix的rdate指令
  ```javascript 
  $ rdate -o <port> -p <host>
  ```
  <port>是我们在main()方法中指定的<host>通常是localhost
  
#### 编写一个时间客户端
  
  不同的discard和响应服务器，我们需要客户端有时间协议，因为人不能把一个32-bit的数据转换成一个时间类型的日期，在这里我们说如何保证服务端正常工作并且学习在netty中客户端的书写
  
  服务端和客户端在netty中最大的差异就是Bootstrap和Channel实现和使用，请看下列的实现

```javascript  
package io.netty.example.time;

public class TimeClient {
    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            
            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
```
  
  1. Bootstrap和ServerBootstrap很相似它们的区别在它不是服务端的通道而是客户端的
  2. 如果你指定一个EventLoopGroup,它将会使用一个既包括boos group和worker group, 但是在客户端boos并没有使用
  3. 然而不是NioServerSocketChannel，NioSocketChannel会开始创建一个客户端方面的通道
  4. 注意在这里不用使用childOption()不像ServerBootstrap那样，因为客户端SocketChannel没有父类
  5. 我们应该调用connect()方法代替bind()方法
  
  正如你所见，它与服务端的代码并没有什么不同，关于ChannelHandler是如何实现的? 它会从服务端获得一个32-bit的整数，并进行格式化，打印出格式化后的时间，并关闭连接

```javascript    
package io.netty.example.time;

import java.util.Date;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg; // (1)
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
```  
  1. 在TCP/IP,netty读取ByteBuf里面的数据
  
  它看起来非常简单，与服务器的实例没有什么不同，然而，这个处理器将会拒绝提供IndexOutOfBoundsException,我们在下节继续讨论  
  
#### 处理基于流的传输
  
  关于Socket Buffer的小警告
  
  再基于流的传输通常使用TCP/IP，接受套接字数据存储在缓冲区中，基于流的传输的缓冲区不是一个数据包队列。而是一个字节队列，它的意思就是说你发送两条信息会在两个独立的数据包内，操作系统不会把它们看两条消息但是只把它们当做一堆字节，因此无法保证读取到的和你写入的完全相等，举个例子，我们系统已经使用TCP/IP接收了三个包
  
  No puml file found!
  
  因为是使用流的基础协议的一般属性，有很大可能在应用中只能读取片段
  
  No puml file found!
  
  因此一个接受的部分，并管它是服务端还是客户端，应该把接受到的数据变成放入一个或多个有意义的框架这样容易理解应用，在上面的例子，接受数据应该向下列这样
  No puml file found!

#### 第一个解决方案
  
 现在让我们回到客户端的例子，我们在这有一个问题32-bit的整数它是一个非常小的数据量，并且它没有很大可能被分散，然而这个问题是它可能被分散，随着数据量的增大，分散的几率也就会上升
 
 一个简单的解决方案是在内部创建一个缓冲区并且等待4个字节传输进去，下面是被修改后的TimeClientHandler
 
```javascript 
 package io.netty.example.time;

import java.util.Date;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf buf;
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(4); // (1)
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release(); // (1)
        buf = null;
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m); // (2)
        m.release();
        
        if (buf.readableBytes() >= 4) { // (3)
            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
``` 

 1. 一个ChannelHandler有两个生命周期的监听方法，handerAdded()和handlerRemoved(),你可以执行一个任意的初始化任务只要不是会阻塞很长时间
 2. 首先，全部收到的数据都应该储存到缓冲区
 3. 之后，在处理器必须检出buf中是否有足够的数据，在这个实例中是4个字节，然后在进行实际业务逻辑
 
#### 第二个解决方案
 
 尽管第一个解决方案已经解决了Time客户端的问题，在这个改进后的处理器修改后不是很干净，想象一个更复杂的协议，它由多个字段组成，比如可变长度字段，你需要对ChannelInboundHandler进行实现，但系统也变得不好维护
 
 你可能注意到了，你可以向ChannelPipline添加更多ChannelHandler, 因此，你可以分割一个整体的ChannelHandler为了减少应用的复杂性，分成多个模块，举个例子，可以把TimeClientHandler
 1. TimeDecoder 处理碎片问题
 2. 初始简单版本TimeClientHandler
 
 幸运的是netty提供了一个可延伸的类来有助于你把问题写入到盒子里

```javascript 
 package io.netty.example.time;

public class TimeDecoder extends ByteToMessageDecoder { // (1)
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
        if (in.readableBytes() < 4) {
            return; // (3)
        }
        
        out.add(in.readBytes(4)); // (4)
    }
}
```

 1. ByteTiMessageDecoder实现了ChannelInboundHandler很容易可以对碎片分散的数据进行处理
 2. ByteToMessageDecoder调用decode()方法在有数据进行接入时会在内部维护缓冲区中的数据
 3. decode()在缓冲区未满的时候可以决定不添加任何东西，ByteToMessageDecoder当有新的信息传入的时候将会调用decode()
 4. 如果decode()添加对象添加到外面，它的意思是成功编译一条信息，ByteToMessageDecoder将会discard累计缓冲区的一部分，请记得不需要多次解码一个信息，ByteToMessageDecoder将会一直调用decode()方法知道没有任何东西进行输出
 
 现在我们有了另一个处理器添加到ChannelPipeline,我们应该在TimeClient修改ChannelInitializer实现
 
```javascript 
 b.handler(new ChannelInitializer<SocketChannel>() {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
    }
}); 
``` 
 
 如果你是一个爱冒险的人，你可能会尝试ReplayingDecoder这就简化了编码器，你可能希望去使用 ReplayingDecoder， 可以在API中查看更多关于它的信息

```javascript  
 public class TimeDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(
            ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        out.add(in.readBytes(4));
    }
}
``` 
  
 同时，netty提供了开箱即用的解压器，这使您更容易实现大多协议，并帮助避免了一个单一无法维护的程序的实现，参阅下列的包以获得更多示例

```javascript 
io.netty.example.factorial for a binary protocol, and
io.netty.example.telnet for a text line-based protocol.
``` 

#### 使用POJO来替代ByteBuf

 在全部示例中我们传输最最要使用ByteBuf来进行数据的传输，我们将在客户端和服务器进行应用  

 在ChannelHandler中使用POJO优势是明显的，你处理的信息可维护性会变得更高，在客户端和服务端的示例中，我们读取了一个32-bit的整数并且这不是使用ByteBuf的主要问题，然而你将会在实现时进行分离
  
 现在让我们定义一个新的UnixTime

```javascript 
 package io.netty.example.time;

import java.util.Date;

public class UnixTime {

    private final long value;
    
    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }
    
    public UnixTime(long value) {
        this.value = value;
    }
        
    public long value() {
        return value;
    }
        
    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
} 
```
 
 我们对其中的信息进行修正
 
```javascript
 @Override
protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
    if (in.readableBytes() < 4) {
        return;
    }

    out.add(new UnixTime(in.readUnsignedInt()));
}
```

有了新的解码器不用在使用ByteBuf了

```javascript
@Override
public void channelRead(ChannelHandlerContext ctx, Object msg) {
    UnixTime m = (UnixTime) msg;
    System.out.println(m);
    ctx.close();
}
```

更简单更优雅对吧？同样也可以应用的服务端，让我们修改TimeServerHandler 时间

```javascript
@Override
public void channelActive(ChannelHandlerContext ctx) {
    ChannelFuture f = ctx.writeAndFlush(new UnixTime());
    f.addListener(ChannelFutureListener.CLOSE);
}
```
现在唯一缺失的就是编码器，需要实现ChannelOutboundHandler将UnixTime转换成ByteBuf,这比编写编码要简单的多因为它不会考虑信息碎片和编码
```javascript
package io.netty.example.time;

public class TimeEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        UnixTime m = (UnixTime) msg;
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeInt((int)m.value());
        ctx.write(encoded, promise); // (1)
    }
}
```

1. 在这一行有很多重要的事要做
  * 第一我们按照原始的ChannelPromise，当编码数据写入到网络的时候netty可以知道是成功还是失败
  * 第二我们不需要调用ctx.flush(),它是一个独立的处理器，它对flush进行了重写的操作
简单的说你可以使用MessageToByteEncoder

```javascript
public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
        out.writeInt((int)msg.value());
    }
}
```

最后一个任务是在TimeServerHandler之前向ChannelPipeline中插入TimeEncoder，这是一个简单的练习

#### 把你的应用停止

停止一个netty是很简单的通常只需要都是在EventLoopGroup中使用shutdownGracefully()方法，它会返回一个Future，当EventLoopGroup完全终止，所有该组的Channel都已关闭，会进行通知

***


