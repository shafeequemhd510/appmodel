
package com.example.myapp.updatechecklist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckListResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("apiStatus")
    @Expose
    private String apiStatus;
    @SerializedName("responseMsg")
    @Expose
    private String responseMsg;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("errorList")
    @Expose
    private List<ErrorList> errorList = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<ErrorList> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorList> errorList) {
        this.errorList = errorList;
    }

}
