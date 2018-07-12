package com.example.miwei.mvptest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.miwei.mvptest.common.util.ThreadControl;

import org.w3c.dom.Text;

public class MaskActivity extends Activity{

    private TextView tv_show;
    private Button btn_cancle;
    private int showNum = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask);
        tv_show = (TextView)findViewById(R.id.tv_shownumber);
        btn_cancle = (Button)findViewById(R.id.btn_cancel);
        initData();
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    // 移除所有的msg.what为0等消息，保证只有一个循环消息队列再跑
                    tv_show.setText(String.valueOf(showNum--));
                    mHandler.removeMessages(0);

                    // 再次发出msg，循环更新
                    mHandler.sendEmptyMessageDelayed(0, 1000);
                    break;
                default:
                    break;
            }
        };
    };



    private void initData() {

        showNum = getIntent().getIntExtra("value",5);

        tv_show.setText(String.valueOf(showNum));

        mHandler.sendEmptyMessageDelayed(0, 1000);

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        ThreadControl.get().unLockInterval();
    }
}
