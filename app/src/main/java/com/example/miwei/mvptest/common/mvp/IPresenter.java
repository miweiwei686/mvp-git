package com.example.miwei.mvptest.common.mvp;

import android.support.annotation.UiThread;

/**
 * The interface Presenter.
 * @param <V> the type parameter
 */
public interface IPresenter<V extends IBaseView> {

  /**
   * Attach view.
   * @param view the view
   */
  @UiThread
  void attachView(V view);

  /**
   * Detach view.
   */
  @UiThread
  void detachView();
}
