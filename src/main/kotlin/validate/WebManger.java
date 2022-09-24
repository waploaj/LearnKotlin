package validate;

import common.DTObject;

import java.io.Serializable;

public class WebManger implements Serializable, Cloneable {

    protected String QueryStr;

    protected DTObject resultObject = new DTObject();
    protected static final String ErrorMessage = "ErrorMessage";

    protected boolean BlackCheckReq;
    protected boolean FetchCheckReq;

    public boolean getBoolen(String s){
        if (s == null || !s.equalsIgnoreCase("true")) return false;
        else return true;
    }

    public void init_ResultObj(DTObject inputObj){
        QueryStr = "";
        resultObject.clearMap();
        resultObject.setValue(ErrorMessage,"");

        if (inputObj.containKey("FetchCheckReq")){
            FetchCheckReq = getBoolen(inputObj.getValue("FetchREQ").toLowerCase().toString());
        }
        if (inputObj.containKey("BlackCheckReq"))  BlackCheckReq = getBoolen(inputObj.getValue("bleckceh").toLowerCase().toString());

    }
}
