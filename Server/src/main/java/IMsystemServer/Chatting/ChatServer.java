package IMsystemServer.Chatting;

import IMsystemServer.DataCrontol.DataControl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class ChatServer  {
    private static final int port = 11233;
    private NioEventLoopGroup bossGroup=null,workerGroup=null;
    private ServerBootstrap serverBootstrap=null;
    private DataControl dataControl;
    private Channel channel;
    public void start() throws  InterruptedException{
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<NioSocketChannel>() {
                            protected void initChannel(NioSocketChannel ch){
                                ch.pipeline().addLast(new ChatServerInboundHandler());
                            }
                        })
                        .option(ChannelOption.SO_BACKLOG,1024)
                        .childOption(ChannelOption.SO_KEEPALIVE,true)
                        .childOption(ChannelOption.TCP_NODELAY,true);
        ChannelFuture fut = serverBootstrap.bind(port).sync();
        if(fut.isSuccess()){
            channel=fut.channel();
        }
    }
    public void shutdown(){
        if(channel!=null){
            channel.close();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
