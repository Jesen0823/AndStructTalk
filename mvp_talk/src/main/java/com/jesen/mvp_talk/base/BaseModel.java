package com.jesen.mvp_talk.base;

/**
 * 接受P层交给他的需求
 * */
public abstract class BaseModel<P extends BasePresenter, CONTRACT> {

    protected P p;

    public BaseModel(P p){
        this.p = p;
    }

    public abstract CONTRACT getContract();
}
