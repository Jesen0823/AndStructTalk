package com.jesen.mvp_talk.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jesen.mvp_talk.R;
import com.jesen.mvp_talk.base.BaseView;
import com.jesen.mvp_talk.bean.BaseEntity;
import com.jesen.mvp_talk.bean.UserInfo;

public class LoginActivity extends BaseView<LoginPresenter,LoginContract.View> {

    private EditText nameEt;
    private EditText pwdEt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void handleResult(UserInfo userInfo) {
                if (userInfo != null){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    private void initView(){
        nameEt = findViewById(R.id.edit_name);
        pwdEt = findViewById(R.id.edit_name);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(view ->{
            doLogin();
        });
    }

    private void doLogin() {
        String name = nameEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        p.getContract().requestLogin(name,pwd);
    }
}