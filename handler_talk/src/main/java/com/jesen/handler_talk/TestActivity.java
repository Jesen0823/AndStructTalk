package com.jesen.handler_talk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private TextView tv;

    // 写法1：
    private Handler handler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            tv.setText("handler1: "+ message.obj.toString());
            jump2Test2();
            return false;
        }
    });
    // 写法2：
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            tv.setText("handler2: "+ msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv = findViewById(R.id.tv);
        //test();
        oomTest();
    }

    private void test() {
     new Thread(new Runnable() {
         @Override
         public void run() {
             Message message = new Message();
             message.obj = "Paper";
             message.what = 11;

             handler2.sendMessage(message);
         }
     }).start();
    }

    private void oomTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();

                message.what = 2;
                // 休眠过程中销毁页面
                if (handler1 != null) {
                    handler1.sendMessageDelayed(message, 3000);
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Test","---onDestroy");
        handler1.removeMessages(2);
        handler1 = null;
    }

    private void jump2Test2(){
        Intent intent = new Intent(this, Test2Activity.class);
        startActivity(intent);
    }
}