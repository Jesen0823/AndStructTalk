package com.jesen.pms_project.pms;

import android.content.pm.PackageInfo;
import android.os.RemoteException;

import com.jesen.pms_project.MyIPackageManager;

public class MyApplicationPackageManager extends MyPackageManager {

    private MyIPackageManager mPM;

    @Override
    public PackageInfo getPackageInfo(String packageName, int flags) {
        try {
            return mPM.getPackageInfo(packageName, flags, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
