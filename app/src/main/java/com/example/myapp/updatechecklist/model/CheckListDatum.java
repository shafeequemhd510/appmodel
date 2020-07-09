
package com.example.myapp.updatechecklist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckListDatum {

    public CheckListDatum(Integer slno, String checkList) {
        this.slno = slno;
        this.checkList = checkList;
    }

    @SerializedName("slno")
    @Expose
    private Integer slno;
    @SerializedName("checkList")
    @Expose
    private String checkList;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private  boolean status;


    public Integer getSlno() {
        return slno;
    }

    public void setSlno(Integer slno) {
        this.slno = slno;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }

}
