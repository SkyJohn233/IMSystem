package IMsystemServer.DataCrontol;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class DataControl {
    private ConcurrentHashMap<Integer,Room> rooms = new ConcurrentHashMap<>();
    private int roomNum=0,chaterNum=0;
    private ReentrantLock lockRoom=new ReentrantLock(),lockChater = new ReentrantLock();
    private ConcurrentHashMap<Integer,Chater> chaters = new ConcurrentHashMap<>();

    public void addChater(Chater ori){
       lockChater.lock();
       ori.cid=chaterNum++;
       lockChater.unlock();
       chaters.put(ori.cid,ori);
      // return ori;
    }
    public void addRoom(Room ori){
        lockRoom.lock();
        ori.rid=roomNum++;
        lockRoom.unlock();
        rooms.put(ori.rid,ori);
        //return ori;
    }
    public Chater getChater(int uid){
        return chaters.get(uid);
    }
    public Room getRoom(int rid){
        return rooms.get(rid);
    }
    public ConcurrentHashMap<Integer, Room> getAllRome(){
        return rooms;
    }

    // 单例
    private DataControl(){}
    private enum EnumDataContorl{
        INSTANCE;
        private final DataControl inst=new DataControl();
        private DataControl getInstance(){
            return inst;
        }
    }
    public static DataControl getInstance(){
        return EnumDataContorl.INSTANCE.getInstance();
    }
}
