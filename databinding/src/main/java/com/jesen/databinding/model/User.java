package com.jesen.databinding.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.jesen.databinding.BR;

public class User extends BaseObservable {

    public String name;
    public String pwd;

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }
}
