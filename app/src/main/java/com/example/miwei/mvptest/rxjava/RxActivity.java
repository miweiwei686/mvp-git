package com.example.miwei.mvptest.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        rxTest();

        taskTest(0, new DatabaseInitListener() {
            @Override
            public void onDBInitialized() {
                Log.d("mwww","init success");
            }

            @Override
            public void onDBInitFailed() {
                Log.d("mwww","init failed");
            }
        });

    }

    public interface DatabaseInitListener{
        void onDBInitialized();
        void onDBInitFailed();
    }


    private void taskTest(final int i,final DatabaseInitListener listener) {

        TaskService taskService = new TaskServiceImpl();

        taskService.executeTask(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                if(i == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }, new TaskCallback<Boolean>() {
            @Override
            public void onResult(Boolean result) {
                if(result) {
                    listener.onDBInitialized();
                } else {
                    listener.onDBInitFailed();
                }
            }
        });


    }

    private void rxTest() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("mwww","onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d("mwww","onNext : " + "value is : " + String.valueOf(value));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("mwww","onComplete");
            }
        };

        observable.subscribe(observer);
    }

}
