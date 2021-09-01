package com.jesen.mvp_talk.login;

import com.jesen.mvp_talk.bean.BaseEntity;

/**
 * 契约类
 * */
public interface LoginContract {

    interface Model{
        void executeLogin(String name, String pwd) throws Exception;
    }

    interface View<T extends BaseEntity>{
        void handleResult(T t);
    }

    interface Presenter <T extends BaseEntity>{
        void requestLogin(String name,String pwd);

        void responseResult(T t);
    }
}









