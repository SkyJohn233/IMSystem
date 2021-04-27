package IMsystemServer.DataCrontol;

public class Chater {
    int cid;
    String name;
    int sex; // 1 male  -1 female 0 unknown
    public Chater(int id,String name,int sex){
        this.cid=id;this.name=name;this.sex=sex;
    }
    @Override
    public int hashCode(){
        return (cid+name).hashCode();
    }
}
