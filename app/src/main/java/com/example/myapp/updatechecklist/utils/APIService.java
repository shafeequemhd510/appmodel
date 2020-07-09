package com.example.myapp.updatechecklist.utils;




import com.example.myapp.updatechecklist.model.CheckListResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {




    /*
    Sample Code

    @FormUrlEncoded
    @POST("read_data.php")
    Call<JsonObject> getImageData(
            @Field("api_data") String api_data
    );


    @GET("/{input}")
    Call<JsonObject> getlistdatas(@Path(value = "input", encoded = true) String input);
*/



    @POST("api/centers/checklist")
    Single<CheckListResponse> getCheckList(
            @Body RequestBody body);



   /* @POST("api/cibil/cibilscore")
    Single<CibilCheckResponse> checkCibil(
            @Body RequestBody body);

    @POST("api/general/verifyotp")
    Single<OtpVerifyResponse> otpVerify(
            @Body RequestBody body);*/
}
