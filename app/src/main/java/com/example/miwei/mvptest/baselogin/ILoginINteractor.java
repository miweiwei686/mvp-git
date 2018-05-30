package com.example.miwei.mvptest.baselogin;

public interface ILoginINteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username, String password, ILoginINteractor.OnLoginFinishedListener listener);
}
