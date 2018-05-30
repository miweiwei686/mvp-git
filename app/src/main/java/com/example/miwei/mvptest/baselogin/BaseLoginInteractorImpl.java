package com.example.miwei.mvptest.baselogin;

import android.os.Handler;
import android.text.TextUtils;

public class BaseLoginInteractorImpl implements ILoginINteractor{
    @Override
    public void login(final String username, final String password, final ILoginINteractor.OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameError();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    listener.onPasswordError();
                    return;
                }
                listener.onSuccess();
            }
        }, 2000);
    }

}
