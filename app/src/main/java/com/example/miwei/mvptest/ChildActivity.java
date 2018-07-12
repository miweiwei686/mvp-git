package com.example.miwei.mvptest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.miwei.mvptest.base.BaseActivity;

public class ChildActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mwww","ChildActivity Oncreate");

    }



}
