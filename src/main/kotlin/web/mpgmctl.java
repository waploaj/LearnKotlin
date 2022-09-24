package web;

import common.DTObject;
import validate.mpgmval;

public class mpgmctl {

    public mpgmctl(){
        mpgvalObje = new mpgmval();
    }

    DTObject revealDto = new DTObject();
    mpgmval mpgvalObje;

    private String mpgmProgrammId= "";
    private String mpgmDescription = "";
    private String moduleId = "";
    private String mpgmErrorMsg = "";
    private String mtxnStatus = "";

    public void setMpgmProgrammId(String mpgmProgrammId) {
        this.mpgmProgrammId = mpgmProgrammId;
    }

    public String getMpgmProgrammId(){
        return mpgmProgrammId;
    }

    public void setMpgmDescription(String mpgmDescription){
        this.mpgmDescription = mpgmDescription;
    }

    public String getMpgmDescription(){
        return mpgmDescription;
    }

    public void setModuleId(String moduleId){
        this.moduleId = moduleId;
    }

    public String getModuleId(){
        return moduleId;
    }

    private boolean revalmoduleId(){
        revealDto.clearMap();
        revealDto.setValue("moduleId", moduleId.trim());
        revealDto = mpgvalObje.mpgmIdKeyPressed(revealDto);
        if (!revealDto.getValue("ErrorKey").trim().equalsIgnoreCase("")){
            mpgmErrorMsg = "MF:txtModuleId"+revealDto.getValue("ErrorKey");
            return false;
        }
        return true;
    }

    private boolean revalpgmclass(){
        revealDto.clearMap();
        revealDto.setValue("mpgm_desc", mpgmDescription.trim());
        revealDto = mpgvalObje.mpgmDescKeypressed(revealDto);
        if (!revealDto.getValue("ErrorKey").equalsIgnoreCase("")){
            mpgmErrorMsg = "MF:txtDesc"+revealDto.getValue("ErrorKey");
            return false;
        }
        return true;
    }

    private boolean revalidatempgm(){
        if (!revalmoduleId()){
            return false;
        }
        if (!revalpgmclass()){
            return false;
        }
        mpgmErrorMsg = "";
        return true;
    }

    public String peristData(){
        if (revalidatempgm() == true){
            DTObject currDto = new DTObject();
            DTObject resultDto = new DTObject();
            currDto.clearMap();

            currDto.setValue("mpgm_id", mpgmProgrammId);
            currDto.setValue("descn", mpgmDescription);
            currDto.setValue("moduleId", moduleId);

            try {

            }catch (Exception e){

            }finally {
                if ("Success".equals(resultDto.getValue("success"))){
                    mtxnStatus = "0";
                }else {
                    mtxnStatus ="1";
                    mpgmErrorMsg = "";
                    if (resultDto.containKey("errMsg")){
                        mpgmErrorMsg = resultDto.getValue("errMsg");
                    }
                }
            }

            {return  "Success";}
        }else {
            {return "failure";}
        }
    }



}
