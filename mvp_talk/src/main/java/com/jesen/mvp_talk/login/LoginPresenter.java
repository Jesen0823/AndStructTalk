package com.jesen.mvp_talk.login;

import com.jesen.mvp_talk.base.BasePresenter;
import com.jesen.mvp_talk.bean.BaseEntity;
import com.jesen.mvp_talk.bean.UserInfo;

public class LoginPresenter extends BasePresenter<LoginActivity,LoginModel,LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>() {
            @Override
            public void requestLogin(String name, String pwd) {

                // 交给Model去完成
                try {
                    m.getContract().executeLogin(name,pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 也可以P层自己完成
                // 也可以由其他模块去处理
            }

            @Override
            public void responseResult(UserInfo userInfo) {
                // 拿到View层的契约
                getView().getContract().handleResult(userInfo);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}
