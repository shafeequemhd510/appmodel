package com.example.myapp.updatechecklist;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapp.updatechecklist.model.CheckListResponse;
import com.example.myapp.updatechecklist.model.UpdateCheckListAction;
import com.example.myapp.updatechecklist.utils.APIService;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class UpdateCheckListRepository {

    private final CompositeDisposable disposables = new CompositeDisposable();
    MutableLiveData<UpdateCheckListAction> mAction;
    private static UpdateCheckListRepository instance;


    public static UpdateCheckListRepository getInstance(Context applicationContext) {
        if(instance == null){
            instance = new UpdateCheckListRepository(applicationContext);
        }

        return instance;
    }

    public UpdateCheckListRepository(Context applicationContext) {
        mAction=new MutableLiveData<>();
    }

    public MutableLiveData<UpdateCheckListAction> getAction() {
        return mAction;
    }

    public MutableLiveData<UpdateCheckListAction> getMutableAction() {
        return mAction;
    }

    public void getChecklist(APIService apiService, RequestBody body) {


        Disposable disposable = apiService.getCheckList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<CheckListResponse, CheckListResponse>() {
                    @Override
                    public CheckListResponse apply(CheckListResponse checkListResponse) throws Exception {
                        // TODO - note about sort

                        return checkListResponse;
                    }
                })
                .subscribeWith(new DisposableSingleObserver<CheckListResponse>() {
                    @Override
                    public void onSuccess(CheckListResponse checkListResponse) {



                            if (checkListResponse.getApiStatus().equals("COMPLETED")) {


                                mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.SUCCESS, checkListResponse));
                            }
                         else {

                            {
//                                mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.ERROR, checkListResponse));
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("RxError", "onError: " + e.getMessage());
                        // showError(e);
                    }
                });
        disposables.add(disposable);


    }

   /* public void getUpdation(APIService apiService, RequestBody body) {


        Disposable disposable = apiService.getCheckListUpdation(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<UpdateCheckListResponse, UpdateCheckListResponse>() {
                    @Override
                    public UpdateCheckListResponse apply(UpdateCheckListResponse updateCheckListResponse) throws Exception {
                        // TODO - note about sort

                        return updateCheckListResponse;
                    }
                })
                .subscribeWith(new DisposableSingleObserver<UpdateCheckListResponse>() {
                    @Override
                    public void onSuccess(UpdateCheckListResponse updateCheckListResponse) {

                        if (updateCheckListResponse.getStatus().equals("SUCCESS")) {


                            mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.SUCCESS_UPD, updateCheckListResponse));
                        } else {

                            mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.SUCCESS_UPD_ERROR, updateCheckListResponse));
                        }

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (throwable instanceof SocketTimeoutException)
                        {
                            mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.ERROR_TIMEOUT));
                        }else if (throwable instanceof UnknownHostException)
                        {
                            mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.ERROR_NO_INTERNET));
                        }else {
                            mAction.setValue(new UpdateCheckListAction(UpdateCheckListAction.API_ERROR,throwable.getMessage()));
                        }
                    }
                });
        disposables.add(disposable);


    }*/

    public void unSubscribeFromObservable() {

        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }
    }

}
