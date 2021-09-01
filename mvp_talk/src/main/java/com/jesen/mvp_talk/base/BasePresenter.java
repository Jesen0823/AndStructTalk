package com.jesen.mvp_talk.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel,CONTRACT> {

    protected M m;
    private WeakReference<V> vWeakReference;

    // 获取子类具体的契约
    public abstract CONTRACT getContract();

    public BasePresenter(){
        m = getModel();
    }

    public void unBindView() {
        if (vWeakReference != null){
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    // 获取View
    public V getView(){
        if (vWeakReference != null){
            return vWeakReference.get();
        }
        return null;
    }

    public abstract M getModel();
}









