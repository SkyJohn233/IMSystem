package IMsystemServer.Auth;

import IMsystemServer.DataCrontol.DataControl;
import IMsystemServer.Chatting.ServerChannelManager;
import io.netty.channel.*;

@ChannelHandler.Sharable
class LoginInboundHandler extends ChannelInboundHandlerAdapter {
    private ServerChannelManager manager=ServerChannelManager.getInstance();
    private DataControl data = DataControl.getInstance();

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){

    }
}

@ChannelHandler.Sharable
class LoginOutboundHandler extends ChannelOutboundHandlerAdapter {
    private DataControl data=DataControl.getInstance();
    private ServerChannelManager manager = ServerChannelManager.getInstance();

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise){

    }
}
