package com.example.myapp.updatechecklist.model;



public class UpdateCheckListAction {

    public static final int SUCCESS = 1;
    public static final int USER_PASSWORD_ERROR = 3;
    public static final int ERROR_TOAST = 2;
    public static final int VALIDATION_ERROR = 4;
    public static final int CAMERA_CLICK = 5;
    public static final int IMAGE_SUCCESS = 6;
    public static final int ERROR_TIMEOUT = 7;
    public static final int ERROR_NO_INTERNET = 8;
    public static final int NO_IMAGE_SELECTED = 9;
    public static final int SUCCESS_UPD = 10;
    public static final int ADAPTER_CHECKED = 11;
    public static final int ADAPTER_UNCHECKED = 12;
    public static final int SUCCESS_UPD_ERROR = 13;
    public static final int CLICK_PROFILE = 32;
    public static final int API_ERROR = 17;

    public UpdateCheckListAction(int mAction) {
        this.mAction = mAction;
    }

    private final int mAction;
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public UpdateCheckListAction(int mAction, String error) {
        this.mAction = mAction;
        this.error = error;
    }

    private CheckListResponse checkListResponse;

    public UpdateCheckListAction(int mAction, CheckListResponse checkListResponse) {
        this.mAction = mAction;
        this.checkListResponse = checkListResponse;
    }

/*    private UpdateCheckListResponse updateCheckListResponse;

    public UpdateCheckListAction(int mAction, UpdateCheckListResponse updateCheckListResponse) {
        this.mAction = mAction;
        this.updateCheckListResponse = updateCheckListResponse;
    }*/

    CheckListDatum checkListDatum;

    public UpdateCheckListAction(int mAction, CheckListDatum checkListDatum) {
        this.mAction = mAction;
        this.checkListDatum=checkListDatum;
    }


    public int getValue() {
        return mAction;
    }

    public CheckListResponse getCheckListResponse() {
        return checkListResponse;
    }

//    public UpdateCheckListResponse getUpdateCheckListResponse() {
//        return updateCheckListResponse;
//    }

    public CheckListDatum getCheckListDatum() {
        return checkListDatum;
    }
}
