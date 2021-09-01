package com.jesen.mvvm.vm;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.jesen.mvvm.databinding.ActivityLoginBinding;
import com.jesen.mvvm.model.UserInfo;

public class LoginViewModel {

    public UserInfo userInfo;

    public TextWatcher nameInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // 双向绑定1：view层接收用户输入，改变Model层的javaBean属性
            userInfo.name.set(String.valueOf(charSequence));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public TextWatcher pwdInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public LoginViewModel(ActivityLoginBinding binding) {
        userInfo = new UserInfo();
        // 绑定ViewModel和View
        binding.setViewModel(this);
    }

    public View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 双向绑定2：model层的变更怎么影响View层
                    SystemClock.sleep(2000);
                    if ("jesen".equals(userInfo.name.get()) && "123".equals(userInfo.pwd.get())){
                        Log.d("Login","login success.");
                        userInfo.name.set("登录成功");
                    }else {
                        Log.d("Login","login failed.");
                        userInfo.name.set("登录失败");
                    }
                }
            }).start();
        }
    };
}
