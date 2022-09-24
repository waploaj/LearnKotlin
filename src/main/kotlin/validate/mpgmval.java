package validate;


import common.DTObject;


public class mpgmval extends WebManger {

    public DTObject mpgmIdKeyPressed(DTObject inputObj){
       try{
           String _mpgmId = "";
           if (inputObj.containKey("mpgmid") == true){
               if (inputObj.getValue("_mpgmid") == null){
                   _mpgmId = "";
               }else{
                  _mpgmId = inputObj.getValue("_mpgmId").trim().toString();
               }
           }
           init_ResultObj(inputObj);
           if (String.valueOf(_mpgmId).equalsIgnoreCase("")){
               resultObject.setValue("ErrorMessage","BlackCheckReq");
           }

       }catch (Exception Err){
            resultObject.setValue(ErrorMessage,"ErrorOccurd");
       }
       return resultObject.copyofDTO();
    }

    public DTObject mpgmDescKeypressed(DTObject inputObject){
        try {
            String _mpgmDesc = "";
            if (inputObject.containKey("_mpgmDesc") == true){
                if (inputObject.getValue("_mpgmDesc") == null){
                    _mpgmDesc = "";
                }else {
                    _mpgmDesc = inputObject.getValue("_mpgmDesc").trim().toString();
                }
            }
            init_ResultObj(inputObject);
            if (String.valueOf(_mpgmDesc).equalsIgnoreCase("")){
                resultObject.setValue(ErrorMessage,"BlackCheck");
            }

        }catch (Exception Error){
            resultObject.setValue("Error", "ErrorOccured");
        }
        return resultObject.copyofDTO();
    }

    public DTObject moduleKeyPressed(DTObject inputObject){
        try {
            String moduleId = "";
            AccessValidator Acvalidator = new AccessValidator();
            DTObject Dtoinput = new DTObject();
            if (inputObject.containKey("moduleId") == true){
                if (inputObject.getValue("moduleId") == null){
                    moduleId = "";
                }else {
                    moduleId = inputObject.getValue("moduleId").trim().toString();
                }
            }
            init_ResultObj(inputObject);
            if (String.valueOf(moduleId).equalsIgnoreCase("")){
                resultObject.setValue(ErrorMessage,"BlackCheck");
            }
            Dtoinput.clearMap();
            Dtoinput.setValue("ModCo",moduleId);
            resultObject = Acvalidator.valmodulecd(Dtoinput);
        }catch (Exception Err){
            resultObject.setValue(ErrorMessage,"Error occured");
        }
        return resultObject.copyofDTO();
    }
}
