package com.example.myapp.updatechecklist;

import android.app.Application;
import android.app.Dialog;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.myapp.updatechecklist.model.CheckListDatum;
import com.example.myapp.updatechecklist.model.UpdateCheckListAction;
import com.example.myapp.updatechecklist.utils.APIService;
import com.example.myapp.updatechecklist.utils.NetworkService;
import com.example.myapp.updatechecklist.utils.Services;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UpdateCheckListViewModel extends AndroidViewModel {

    public ObservableInt agendaRecycler;
    APIService apiService;

    private UpdateCheckListRepository updateCheckListRepository;

    List<CheckListDatum> checkListData;

    public final ObservableField<String> checklistHeader = new ObservableField<>("");

    public UpdateCheckListViewModel(@NonNull Application application) {
        super(application);

        updateCheckListRepository=UpdateCheckListRepository.getInstance(application.getApplicationContext());
        mAction=new MutableLiveData<>();
    }

    MutableLiveData<UpdateCheckListAction> mAction;

    public LiveData<UpdateCheckListAction> getAction() {
        mAction=updateCheckListRepository.getAction();
        return mAction;
    }

    public void listChecked(CheckListDatum checkedList, boolean status) {


        for (int i = 0; i < checkListData.size(); i++) {
            if(checkListData.get(i).getSlno().equals(checkedList.getSlno()))
            {

                checkListData.get(i).setStatus(status);

            }
        }
    }

    public void saveBtn(View view) {


        int status=0;
        for (CheckListDatum checkListDatum : checkListData) {
            if(!checkListDatum.isStatus())
            {
                status++;
            }

        }

        if(status!=0)
        {
            Toast.makeText(view.getContext(), "All fields needs to be marked", Toast.LENGTH_LONG).show();
        }
        else
        {
            initLoading(view);
//            sendRequestUpdation();
        }
    }

    public void sendRequestCheckList() {

//        SharedPreferences sharedPreferences = getApplication().getApplicationContext().getSharedPreferences(SharedPrefConstants.PREF_CONST, Context.MODE_PRIVATE);

        Map<String, Object> jsonParams = new HashMap<>();

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy" +
                "54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uY" +
                "W1lIjoiMjIxMDQiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjUwMDAiLC" +
                "JhdWQiOiJodHRwOi8vbG9jYWxob3N0OjUwMDAifQ.O0vAjgjMru5J4Aerr1L06_tM-pZq_-VL--64eKE-Hqo";
//        String stage_id = sharedPreferences.getString(SharedPrefConstants.STAGE_ID, SharedPrefConstants.DEFAULT_STAGE_ID);

        jsonParams.put("stageId",2);

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        apiService = NetworkService.getClient_LOS(token).create(APIService.class);
        updateCheckListRepository.getChecklist(apiService, body);

    }

 /*   public void sendRequestUpdation() {

        SharedPreferences sharedPreferences = getApplication().getApplicationContext().getSharedPreferences(SharedPrefConstants.PREF_CONST, Context.MODE_PRIVATE);

        Map<String, Object> jsonParams = new HashMap<>();

        String token = sharedPreferences.getString(SharedPrefConstants.TOKEN, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy" +
                "54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uY" +
                "W1lIjoiMjIxMDQiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjUwMDAiLC" +
                "JhdWQiOiJodHRwOi8vbG9jYWxob3N0OjUwMDAifQ.O0vAjgjMru5J4Aerr1L06_tM-pZq_-VL--64eKE-Hqo");
        String application_id = sharedPreferences.getString(SharedPrefConstants.APPLICATION_ID, "1");
        String stage_id = sharedPreferences.getString(SharedPrefConstants.STAGE_ID, "1");
        String user_id = sharedPreferences.getString(SharedPrefConstants.USER_ID, "16894");

        jsonParams.put("applicationId",Integer.parseInt(application_id));
        jsonParams.put("stageId",Integer.parseInt(stage_id));
        jsonParams.put("userId",Integer.parseInt(user_id));


        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        apiService = NetworkService.getClient_LOS(token).create(APIService.class);
        updateCheckListRepository.getUpdation(apiService, body);

    }*/

    public void setCheckList(List<CheckListDatum> data) {
        checkListData= data;

    }


    Dialog loading;
    public void initLoading(View view) {
        loading= Services.showProgressDialog(view.getContext());
    }

    public void cancelLoading()
    {
        if(loading!=null)
        {
            loading.cancel();
            loading=null;
        }
    }

    public void clickProfile(View view) {
        mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.CLICK_PROFILE));

    }


}
