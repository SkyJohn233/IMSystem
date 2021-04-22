package IMsystem.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {
    private static final String ip="127.0.0.1";
    private static final int port = 11233;
    public ChatClient(){
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap clientBootstrap = new Bootstrap();
        clientBootstrap.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch){
                                ch.pipeline().addLast(new ChatClientHandler());
                            }
                        })
                        .option(ChannelOption.SO_KEEPALIVE,true)
                        .option(ChannelOption.TCP_NODELAY,true);
        try {
            ChannelFuture fut = clientBootstrap.connect(ip, port).sync();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
