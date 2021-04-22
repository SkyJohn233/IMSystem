package IMsystem.Server.Chatting;

import IMsystem.Server.DataCrontol.Chater;
import IMsystem.Server.DataCrontol.DataControl;
import IMsystem.Server.DataCrontol.Room;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;

import java.util.concurrent.ConcurrentHashMap;

@ChannelHandler.Sharable
class ChatServerInboundHandler extends ChannelInboundHandlerAdapter {
    private ServerChannelManager manager = ServerChannelManager.getInstance();
    private DataControl dataControl = DataControl.getInstance();
    private void work(ChannelHandlerContext ctx,ByteBuf buffer) {
        int rlen = buffer.readableBytes();
        if (rlen >= 4) {
            byte[] bytes1 = new byte[4];
            int msgLen = buffer.getInt(buffer.readerIndex());
            if (msgLen <= rlen - 4) {
                byte[] jsonData = new byte[msgLen];
                buffer.readBytes(jsonData);
                JSONObject data = JSON.parseObject(new String(jsonData));
                String code = (String) data.get("code");
                JSONObject ans = new JSONObject();
                switch (code) {
                    case "Login":
                        String name = data.getJSONObject("data").getString("userName");
                        int sex = data.getJSONObject("data").getInteger("sex");
                        Chater ct = new Chater(-1, name, sex);
                        dataControl.addChater(ct);
                        manager.addUser(ctx.channel(), ct.hashCode());
                        ConcurrentHashMap<Integer, Room> rooms = dataControl.getAllRome();
                        ans.put("code","ListRoom");
                        ans.put("data",rooms);

                        break;
                    case "EnterRoom":
                        Integer id = data.getJSONObject("data").getInteger("roomId");
                        break;
                    case "Send":
                        String sendMsg = data.getJSONObject("data").getString("sendMessage");
                        Integer roomId = data.getJSONObject("data").getInteger("roomId");
                        break;
                }
            }

        }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        ByteBuf buffer=(ByteBuf)msg;
        work(ctx,buffer);


    }
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        manager.add(ctx.channel());
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        manager.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        manager.remove(ctx.channel());
        ctx.channel().close();
    }
}

@ChannelHandler.Sharable
class ChatServerOutboundHandler extends ChannelOutboundHandlerAdapter{

}
