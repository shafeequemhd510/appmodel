package com.example.myapp.updatechecklist.utils;



import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.myapp.R;



public class Services {



   public static Dialog showProgressDialog(Context context) {

       Dialog warningDialog = new Dialog(context);
       warningDialog.setContentView(R.layout.layout_progress_dialog);


       warningDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


       warningDialog.setCanceledOnTouchOutside(false);
       warningDialog.setCancelable(false);
       warningDialog.show();
       return warningDialog;
   }



}



