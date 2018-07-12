/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.example.miwei.mvptest.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.miwei.mvptest.A;
import com.example.miwei.mvptest.ChildActivity;
import com.example.miwei.mvptest.MainActivity;
import com.example.miwei.mvptest.MaskActivity;
import com.example.miwei.mvptest.R;
import com.example.miwei.mvptest.annotationtest.FileType;
import com.example.miwei.mvptest.annotationtest.MyButterknife;
import com.example.miwei.mvptest.annotationtest.Student;
import com.example.miwei.mvptest.baselogin.BaseLoginInteractorImpl;
import com.example.miwei.mvptest.baselogin.BaseLoginPresenter;
import com.example.miwei.mvptest.baselogin.BaseLoginView;
import com.example.miwei.mvptest.common.util.ThreadControl;


public class LoginActivity extends Activity implements BaseLoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private BaseLoginPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);

        presenter = new BaseLoginPresenter(this,new BaseLoginInteractorImpl());
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override public void setPasswordError() {
            password.setError(getString(R.string.password_error));
    }

    @Override public void navigateToHome() {
        startActivity(new Intent(this, ScrollLayoutActivity.class));
        finish();
    }

    @Override public void onClick(View v) {
//        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
        ThreadControl.get().setDispalyTime(8);
        for(int i = 0;i<3;i++) {

            ThreadControl.get().handlePushInfo(this,String.valueOf(i));

        }

    }

}
