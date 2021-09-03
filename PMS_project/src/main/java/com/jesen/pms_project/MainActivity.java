package com.jesen.pms_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.jesen.pms_project.pms.MyContext;
import com.jesen.pms_project.pms.MyPackageManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        try {
            getPackageManager().getPackageInfo("",0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        MyContext myContext = new MyContext();
        myContext.getPackageManager().getPackageInfo("",0);
    }
}



















