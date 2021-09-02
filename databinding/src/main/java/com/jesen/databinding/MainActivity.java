package com.jesen.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;

import com.jesen.databinding.databinding.ActivityMainBinding;
import com.jesen.databinding.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        // 通过binding改变View
        User user = new User();
        user.setName("卡卡西");
        user.setPwd("123");
        binding.setUser(user);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setName("卡卡东东");
                user.setPwd("999");
            }
        },5000);
    }
}