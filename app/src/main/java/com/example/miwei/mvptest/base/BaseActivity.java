package com.example.miwei.mvptest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class BaseActivity extends FragmentActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mwww","onCreate" + this.getClass().getName());

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("mwww","BaseActivity onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("mwww","BaseActivity onStart");
    }
}
