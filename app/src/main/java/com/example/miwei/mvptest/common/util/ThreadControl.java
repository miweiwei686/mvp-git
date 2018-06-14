package com.example.miwei.mvptest.common.util;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.renderscript.Sampler;

import com.example.miwei.mvptest.MaskActivity;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用来延时处理多个请求
 */
public class ThreadControl {
    private static final String TAG = "ThreadControl";
    private static final byte[] mLock = new byte[0]; //定义同步锁对象
    private static ThreadControl mInstance = null;
    private ExecutorService executor = null;
    private Handler handler;
    private Runnable unLockOrderExecutor = null;

    /**
     * 容错两次展示最大时间间隔
     */
    private static final int MAXINTERVAL = 30 * 1000;

    private ArrayBlockingQueue<String> arrayOrderToken = new ArrayBlockingQueue<String>(500);

    private long mPrevPushMessage = 0L;

    private int displayTime = 5;

    public final static ThreadControl get() {
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new ThreadControl();
            }
            return mInstance;
        }
    }

    private ThreadControl() {
        handler = new Handler(Looper.getMainLooper());
    }


    /**
     *
     * @param context
     * @param value
     */
    public synchronized void handlePushInfo(Context context, String value) {
        // 过滤重复订单id推送
        if (arrayOrderToken.contains(value)) {
            return;
        }
        arrayOrderToken.add(value);

        // 防止队列堵塞的bug引起无法继续展示
        if (System.currentTimeMillis() - mPrevPushMessage >= MAXINTERVAL) {
            unLock();
        }
        mPrevPushMessage = System.currentTimeMillis();
        displayPushInfo(context, value);
    }

    public void setDispalyTime(int time) {

        this.displayTime = time;

    }

    /**
     * 展示订单推送消息
     * @param context
     * @param pushMessage
     */
    private void displayPushInfo(final Context context, final String pushMessage) {

        //使用单线程池来依次显示请求数据
        if(executor == null || executor.isShutdown() || executor.isTerminated()){
            executor = Executors.newSingleThreadExecutor();
        }

        executor.submit(new Runnable() {
            @Override
            public void run() {
//                wakeUpAndUnlock(context);
                // 从队列移除order token

                arrayOrderToken.remove(pushMessage);

                //                // 拉起界面
                startOrderActivity(context, pushMessage);
                synchronized (mLock) {//使用同步锁代码块来展示时间
                    try {
                        if(unLockOrderExecutor != null){
                            handler.removeCallbacks(unLockOrderExecutor);
                            unLockOrderExecutor = null;
                        }
                        unLockOrderExecutor = new Runnable() {
                            @Override
                            public void run() {
                                unLock();
                                unLockOrderExecutor = null;
                            }
                        };
                        handler.postDelayed(unLockOrderExecutor, displayTime * 1000);
                        mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



    public void unLock() {
        synchronized (mLock) {
            if(unLockOrderExecutor != null){
                handler.removeCallbacks(unLockOrderExecutor);
                unLockOrderExecutor = null;
            }

            mLock.notifyAll();
        }
    }

    public void unLockInterval() {
        synchronized (mLock) {
            if(unLockOrderExecutor != null){
                handler.removeCallbacks(unLockOrderExecutor);
                unLockOrderExecutor = null;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mLock.notifyAll();
        }
    }

    public void clearUp() {
        if(executor != null){
            executor.shutdownNow();
        }
        synchronized (mLock) {
            mLock.notifyAll();
        }
    }


    /**
     * 拉起相应的展示界面
     *
     * @param pushMessage
     */
    private void startOrderActivity(final Context context, String pushMessage) {
        Intent intent = new Intent();
        intent.setClass(context, MaskActivity.class);
        intent.putExtra("value", displayTime);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void wakeUpAndUnlock(Context context) {
//        try {
//            PowerManager pm = (PowerManager)context.getSystemService("power");
//            PowerManager.WakeLock wl = pm.newWakeLock(268435462, "bright");
//            wl.acquire();
//        } catch (Exception var2) {
//            var2.printStackTrace();
//        }
//
//    }
}
