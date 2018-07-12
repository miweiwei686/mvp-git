package com.example.miwei.mvptest.baselogin;

import com.example.miwei.mvptest.common.mvp.IBaseView;

public interface BaseLoginView extends IBaseView{

    void showProgress();//显示loading框

    void hideProgress();//隐藏loading框

    void setUsernameError();//处理用户名错误

    void setPasswordError();//处理密码错误

    void navigateToHome();
}
