package com.jesen.mvp_talk.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseView<P extends BasePresenter,CONTRACT> extends Activity {

    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 弱引用
        p = getPresenter();
        // 绑定
        p.bindView(this);
    }

    // 让P层做什么
    public abstract CONTRACT getContract();

    // 从子类获取P的契约
    public abstract P getPresenter();

    // presenter有一次，告知给View层
    public void error(Exception e){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解除绑定
        p.unBindView();
    }
}













