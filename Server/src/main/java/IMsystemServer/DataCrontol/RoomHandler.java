package IMsystemServer.DataCrontol;

import IMsystemServer.Chatting.ServerChannelManager;
import io.netty.channel.*;

@ChannelHandler.Sharable
class RoomInboundHandler extends ChannelInboundHandlerAdapter {
    private DataControl data =DataControl.getInstance();


    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){

    }
}

@ChannelHandler.Sharable
class RoomOutboundHandler extends ChannelOutboundHandlerAdapter{
    private DataControl data=DataControl.getInstance();
    private ServerChannelManager manager = ServerChannelManager.getInstance();

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise){

    }
}
