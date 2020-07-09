
package com.example.myapp.updatechecklist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("isDataAvailable")
    @Expose
    private Boolean isDataAvailable;
    @SerializedName("checkListData")
    @Expose
    private List<CheckListDatum> checkListData = null;

    public Boolean getIsDataAvailable() {
        return isDataAvailable;
    }

    public void setIsDataAvailable(Boolean isDataAvailable) {
        this.isDataAvailable = isDataAvailable;
    }

    public List<CheckListDatum> getCheckListData() {
        return checkListData;
    }

    public void setCheckListData(List<CheckListDatum> checkListData) {
        this.checkListData = checkListData;
    }

}
