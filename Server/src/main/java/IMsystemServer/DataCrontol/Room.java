package IMsystemServer.DataCrontol;
import java.util.concurrent.ConcurrentHashMap;

class ChaterRoomProf{
    String nickname;
    public ChaterRoomProf(Chater ct){
        nickname=ct.name;
    }
    public ChaterRoomProf(String nickname){
        this.nickname=nickname;
    }
}

public class Room {
    ConcurrentHashMap<Integer,ChaterRoomProf> chaters;
    int rid;
    String roomName;
    public Room(int id,String name){
        this.rid=id;this.roomName=name;
        chaters=new ConcurrentHashMap<>();
    }
    @Override
    public int hashCode(){
        return (roomName+rid).hashCode();
    }
    public boolean enter(Chater chater){
        if(chaters.containsKey(chater.cid)) return false;
        chaters.put(chater.cid,new ChaterRoomProf(chater));
        return true;
    }
    public boolean exit(Chater ct){
        ChaterRoomProf prof=chaters.remove(ct.cid);
        return prof != null;
    }
    public boolean modifyProf(Chater chater,String nickname){
        if(!chaters.containsKey(chater.cid)) return false;
        ChaterRoomProf prof=chaters.get(chater.cid);
        //modify
        prof.nickname=nickname;

        chaters.replace(chater.cid,prof);
        return true;
    }
}
