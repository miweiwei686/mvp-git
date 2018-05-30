package com.example.miwei.mvptest.baselogin;

import com.example.miwei.mvptest.common.mvp.IBaseView;

public interface BaseLoginView extends IBaseView{
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
