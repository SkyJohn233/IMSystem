package IMsystemServer.Chatting;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;

import java.util.concurrent.ConcurrentHashMap;

public class ServerChannelManager {

    private ConcurrentHashMap<ChannelId, Channel> channels =new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer,ChannelId> userHashChannels= new ConcurrentHashMap<>();
    private static final AttributeKey<String> CHANNEL_ATTR_KEY_USER=AttributeKey.newInstance("userhash");
    public void add(Channel chan){
        //channels.remove(chan.id());
        channels.put(chan.id(),chan);
    }


    public void addUser(Channel chan,Integer userhash){
        //add(chan);
        //userHashChannels.remove(userhash);
        userHashChannels.put(userhash,chan.id());
        chan.attr(CHANNEL_ATTR_KEY_USER).set(userhash.toString());
    }
    public void remove(Channel chan){
        channels.remove(chan.id());
        if(chan.hasAttr(CHANNEL_ATTR_KEY_USER)){
            Integer key = Integer.valueOf(chan.attr(CHANNEL_ATTR_KEY_USER).get());
            userHashChannels.remove(key);
        }
    }
    // singleton
    private ServerChannelManager(){}
    private enum enumSeChanManager{
        INSTANCE;
        private final ServerChannelManager inst=new ServerChannelManager();
        private ServerChannelManager getInstance(){
            return inst;
        }
    }
    public static ServerChannelManager getInstance(){
        return enumSeChanManager.INSTANCE.getInstance();
    }
}
