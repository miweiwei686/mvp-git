package com.example.miwei.mvptest.common.mvp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

public class BasePresenter <V extends IBaseView> implements IPresenter<V>{

    private V viewRef;



    /**
     * Instantiates a new Base presenter.
     * @param view    the view
     */
    public BasePresenter(V view){
        attachView(view);
    }

    /**
     * Instantiates a new Base presenter.
     */
    public BasePresenter() {
    }


    @Override
    public void attachView(V view) {
        viewRef = view;
    }

    /**
     * Gets view.
     * @return the view
     */
    @UiThread
    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef;
    }

    /**
     * Is view attached boolean.
     * @return the boolean
     */
    @UiThread
    public boolean isViewAttached() {
        return viewRef != null;
    }


    @UiThread
    @Override public void detachView() {
        if (viewRef != null) {
            viewRef = null;
        }
    }
}
