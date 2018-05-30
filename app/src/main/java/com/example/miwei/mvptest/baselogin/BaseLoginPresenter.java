package com.example.miwei.mvptest.baselogin;

import com.example.miwei.mvptest.common.mvp.BasePresenter;

public class BaseLoginPresenter extends BasePresenter<BaseLoginView> implements ILoginPresenter,ILoginINteractor.OnLoginFinishedListener{

    private BaseLoginView loginView;
    private ILoginINteractor loginInteractor;

    public BaseLoginPresenter(BaseLoginView loginView, ILoginINteractor loginInteractor) {
        super(loginView);
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onUsernameError() {
        if (isViewAttached()) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (isViewAttached()) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }

    }

    @Override
    public void onSuccess() {
        if (isViewAttached()) {
            loginView.navigateToHome();
        }
    }

    @Override
    public void validateCredentials(String username, String password) {

        if (isViewAttached()) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        detachView();
    }
}
