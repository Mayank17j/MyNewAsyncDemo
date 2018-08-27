package com.example.lenovo.mynewasyncdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyProgressTask mpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view) {
        mpt=new MyProgressTask(this);
        mpt.execute();//(for multi threading pass more arguments)
    }
}
