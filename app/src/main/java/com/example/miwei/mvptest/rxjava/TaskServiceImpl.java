package com.example.miwei.mvptest.rxjava;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TaskServiceImpl implements TaskService{
    @Override
    public void executeTask(Runnable task) {

    }

    @Override
    public <T> void executeTask(final Callable<T> callable, final TaskCallback<T> listener) {

        Observable<T> observable = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                T result = callable.call();
                e.onNext(result);
                e.onComplete();
            }
        });
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() {
            @Override
            public void accept(T o) throws Exception {
                if(listener != null){
                    listener.onResult(o);
                }
            }
        });

    }

    @Override
    public void postTask(Runnable task) {

    }
}
