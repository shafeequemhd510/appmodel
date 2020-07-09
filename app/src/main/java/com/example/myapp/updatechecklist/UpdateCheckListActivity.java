package com.example.myapp.updatechecklist;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.example.myapp.databinding.ActivityUpdateCheckListBinding;
import com.example.myapp.updatechecklist.adapter.UpdateCheckListAdapter;
import com.example.myapp.updatechecklist.model.CheckListDatum;
import com.example.myapp.updatechecklist.model.UpdateCheckListAction;
import com.example.myapp.updatechecklist.utils.SharedPrefConstants;

import java.util.List;

public class UpdateCheckListActivity extends MainActivity {

    UpdateCheckListAdapter adapter;

    UpdateCheckListViewModel updateCheckListViewModel;
    ActivityUpdateCheckListBinding binding;
    SharedPreferences sharedPreferences;

    public static Intent launchApplicationId(Context context, CheckListDatum checkListDatum) {

        Intent intent;

        intent= new Intent(context,MainActivity.class);


        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(
                getLayoutInflater(),
                R.layout.activity_update_check_list, null, false);

        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(SharedPrefConstants.PREF_CONST, MODE_PRIVATE);

        adapter = new UpdateCheckListAdapter();
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));

        updateCheckListViewModel = new ViewModelProvider(this).get(UpdateCheckListViewModel.class);

        binding.setUpdateCheckListViewModel(updateCheckListViewModel);
        updateCheckListViewModel.sendRequestCheckList();
 /*       binding.tvUsername.setText(sharedPreferences.getString(SharedPrefConstants.USER_NAME,""));
        binding.tvCenterName.setText(sharedPreferences.getString(SharedPrefConstants.CENTER_NAME,""));*/

        SharedPreferences sharedPreferences = this.getSharedPreferences(SharedPrefConstants.PREF_CONST, Context.MODE_PRIVATE);

        String header = sharedPreferences.getString(SharedPrefConstants.CHECKLIST_HEADER,"");

        updateCheckListViewModel.checklistHeader.set(header);

        updateCheckListViewModel.getAction().observe(this, new Observer<UpdateCheckListAction>() {
            @Override
            public void onChanged(UpdateCheckListAction action) {
                updateCheckListViewModel.cancelLoading();

                if (action.getValue() == UpdateCheckListAction.SUCCESS) {


                    List<CheckListDatum> data = action.getCheckListResponse().getData().getCheckListData();

                    int i = 0;
                    for (CheckListDatum resu:
                            data ) {

                        Toast.makeText(UpdateCheckListActivity.this,data.get(i).getCheckList(), Toast.LENGTH_LONG).show();
                        Log.d("vw", "onChanged: "+data.get(i).getCheckList());
                        i++;
                    }

                    UpdateCheckListAdapter adapters = (UpdateCheckListAdapter) binding.list.getAdapter();
                    adapters.setAdapter(data);



                }
                else if (action.getValue() == UpdateCheckListAction.SUCCESS_UPD_ERROR) {



                }else if (action.getValue() == UpdateCheckListAction.API_ERROR)
                {

                }else if (action.getValue() == UpdateCheckListAction.ERROR_NO_INTERNET)
                {

                }else if (action.getValue() == UpdateCheckListAction.ERROR_TIMEOUT)
                {

                }else if (action.getValue() == UpdateCheckListAction.CLICK_PROFILE)
                {
                  /*  updateCheckListViewModel.cancelLoading();
                    Intent intent = new Intent(UpdateCheckListActivity.this, UserProfileActivity.class);
                    startActivity(intent);*/
                }


            }
        });

        adapter.getmAction().observe(this, new Observer<UpdateCheckListAction>() {
            @Override
            public void onChanged(UpdateCheckListAction action2) {

                if (action2.getValue() == UpdateCheckListAction.ADAPTER_CHECKED) {

//                    updateCheckListViewModel.listChecked(action2.getCheckListDatum(), true);

                }
                else if (action2.getValue()== UpdateCheckListAction.ADAPTER_UNCHECKED){


//                    updateCheckListViewModel.listChecked(action2.getCheckListDatum(),false);
                }

            }
        });


    }
}
