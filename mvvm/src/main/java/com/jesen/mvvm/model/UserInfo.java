package com.jesen.mvvm.model;

import androidx.databinding.ObservableField;

public class UserInfo {
    // 被观察者的属性应该设为public
    public ObservableField<String> name = new ObservableField<>();

    public ObservableField<String> pwd = new ObservableField<>();
}
