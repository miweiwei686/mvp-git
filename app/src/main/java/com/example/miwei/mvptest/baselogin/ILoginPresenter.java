package com.example.miwei.mvptest.baselogin;

public interface ILoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
