package com.jesen.mvp_talk.login;

import com.jesen.mvp_talk.base.BaseModel;
import com.jesen.mvp_talk.bean.UserInfo;

// 接受P层给它的需求
public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {

    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) throws Exception {
                if ("jesen".equalsIgnoreCase(name) && "123".equals(pwd)) {
                    p.getContract().responseResult(new UserInfo("jesen"));
                } else {
                    p.getContract().responseResult(null);
                }
            }
        };
    }
}
