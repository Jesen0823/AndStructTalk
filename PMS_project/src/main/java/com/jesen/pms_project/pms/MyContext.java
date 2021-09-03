package com.jesen.pms_project.pms;

public class MyContext {

    public MyPackageManager getPackageManager() {
        return new MyApplicationPackageManager();
    }

}
