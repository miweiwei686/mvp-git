package com.example.miwei.mvptest.rxjava;

import java.util.concurrent.Callable;

public interface TaskService {

    /**
     * 在非主线程执行指定的任务，该任务没有返回值
     * @param task 指定的任务
     */
    void executeTask(Runnable task);

    /**
     * 在非主线程执行指定的任务，任务执行完成后在主线程回调该任务的返回值
     * @param callable 带有返回值的任务
     * @param listener 回调接口
     * @param <T> 任务返回值的类型
     */
    <T> void executeTask(Callable<T> callable, TaskCallback<T> listener);

    /**
     * 在主线程执行特定的任务，该任务没有返回值
     * @param task 指定任务
     */
    void postTask(Runnable task);
}
