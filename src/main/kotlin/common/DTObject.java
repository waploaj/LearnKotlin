package common;

import java.io.Serializable;
import java.util.HashMap;

public class DTObject implements Serializable, Cloneable {

    protected HashMap MapObject = new  HashMap();

    public Object clone(){
        try {
            return super.clone();
        }catch (CloneNotSupportedException e){
            return null;
        }
    }

    public DTObject copyofDTO(){
        return (DTObject) this.clone();
    }

    public boolean containKey(String input){
        return MapObject.containsKey(input);
    }

    public String getValue(String keyName){
        return (String) getValue(keyName);
    }

    public void  setValue(String keyName, String value){
        MapObject.put(keyName, value);
    }

    public void clearMap(){
        if(MapObject.size() >0){
            MapObject = new HashMap();
        }
        MapObject.clear();
    }

}
