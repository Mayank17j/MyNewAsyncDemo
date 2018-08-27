package com.example.lenovo.mynewasyncdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MyProgressTask extends AsyncTask<Void,Integer,String> {
    //<para,progress,result>

    Context ctx;
    ProgressDialog pd;

    public MyProgressTask(Context ct){

        ctx=ct;
    }

    @Override
    protected void onPreExecute() {
        pd=new ProgressDialog(ctx);
        pd.setTitle("Downloading");
        pd.setMessage("Please wait...");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//DEFAULT ID IS SPINNER
        pd.setMax(5);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //It only cancel the appearing dialog box
                dialogInterface.cancel();
                //after canceling it, background progress is not stop, it can seen in Logcat
                // for this it might to cancel backend progress
                cancel(true);
            }
        });
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            //Here we can use real time task in place of "for"
            for (int i = 1;i<=5;i++) {
                //In every iteration, it wait for 1 sec=1000 millisec
                Thread.sleep(1000);
                //Log i for see progress in Logcat
                Log.i("Thread","Execute" + i);
                //To give update to the ProgressDialogBox
                publishProgress(i);
            }
            return "Successful";
        } catch (Exception e) {
            Log.i("Exception", e.getMessage());
            return "Failure";
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int myValue=values[0];
        pd.setProgress(myValue);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }
}
