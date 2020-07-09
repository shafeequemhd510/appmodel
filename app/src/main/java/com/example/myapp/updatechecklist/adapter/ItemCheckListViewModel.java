package com.example.myapp.updatechecklist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.example.myapp.MainActivity;
import com.example.myapp.updatechecklist.UpdateCheckListActivity;
import com.example.myapp.updatechecklist.model.CheckListDatum;
import com.example.myapp.updatechecklist.model.UpdateCheckListAction;


public class ItemCheckListViewModel extends BaseObservable {

    MutableLiveData<UpdateCheckListAction> mAction;
    private CheckListDatum checkListDatum;
    private Context context;

    public ItemCheckListViewModel(CheckListDatum checkListDatum, Context context, MutableLiveData<UpdateCheckListAction> mAction) {
        this.checkListDatum = checkListDatum;
        this.context = context;
        this.mAction= mAction;
    }

//    public LiveData<UpdateCheckListAction> getAction() {
//        return mAction;
//    }

    public String checkList() {
        return checkListDatum.getCheckList();
    }

    public void onTypeChecked(boolean checked, int i) {
        if (checked) {

            mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.ADAPTER_CHECKED,checkListDatum));
        } else {
            mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.ADAPTER_UNCHECKED,checkListDatum));
        }
    }

//    public String Centername() {
//        return centersList.getCenterName();
//    }


    public void onItemClick(View view) {


        context.startActivity(UpdateCheckListActivity.launchApplicationId(view.getContext(), checkListDatum));
    }

    public void setCheckListDatum(CheckListDatum checkListDatum) {
        this.checkListDatum = checkListDatum;
        notifyChange();
    }
}
