package Repository;

import java.util.HashMap;
import java.util.Map;

public enum Archive {

    //Can call Archives with archive.intValue
    Persistence(0),Transaction(1),Search(2);
    private int i;
    private static Map map = new HashMap<>();

    Archive(int i) {
        this.i = i;
    }

    static{
        for (Archive archive : Archive.values()){
            map.put(archive.i, archive);
        }
    }

    public static Object intValue(int archive)
    {
        return map.get(archive);
    }

    public int getIntValue(){
        return  i;
    }
}
