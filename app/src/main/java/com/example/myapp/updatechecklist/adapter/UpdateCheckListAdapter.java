package com.example.myapp.updatechecklist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapp.R;
import com.example.myapp.databinding.ChecklistrowBinding;
import com.example.myapp.updatechecklist.model.CheckListDatum;
import com.example.myapp.updatechecklist.model.UpdateCheckListAction;

import java.util.Collections;
import java.util.List;

public class UpdateCheckListAdapter extends RecyclerView.Adapter<UpdateCheckListAdapter.UpdateCheckListHolder> {

    private List<CheckListDatum> datumList;
    MutableLiveData<UpdateCheckListAction> mAction;

    public UpdateCheckListAdapter() {
        this.datumList = Collections.emptyList();
         mAction= new MutableLiveData<>();
    }


    public void setAdapter(List<CheckListDatum> datumList) {
        this.datumList = datumList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UpdateCheckListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ChecklistrowBinding checklistrowBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.checklistrow,
                        parent, false);
        return new UpdateCheckListHolder(checklistrowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateCheckListHolder holder, int position) {
        holder.bindCentersList(datumList.get(position),mAction);
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public MutableLiveData<UpdateCheckListAction> getmAction() {
        return mAction;
    }

    public class UpdateCheckListHolder extends RecyclerView.ViewHolder{

       ChecklistrowBinding checklistrowBinding;


        public UpdateCheckListHolder( ChecklistrowBinding checklistrowBinding) {
            super(checklistrowBinding.checkList);
            this.checklistrowBinding = checklistrowBinding;
        }

        void bindCentersList(CheckListDatum listDatum, MutableLiveData<UpdateCheckListAction> mAction) {
            if (checklistrowBinding.getItemCheckListViewModel() == null) {
                checklistrowBinding.setItemCheckListViewModel(

                        new ItemCheckListViewModel(listDatum, itemView.getContext(),mAction));

            }
            else {

                    checklistrowBinding.getItemCheckListViewModel().setCheckListDatum(listDatum);
            }
    }

}}
