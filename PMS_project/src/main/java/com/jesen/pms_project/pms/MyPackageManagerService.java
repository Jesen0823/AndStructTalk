package com.jesen.pms_project.pms;

import android.content.pm.PackageInfo;
import android.os.RemoteException;

import com.jesen.pms_project.MyIPackageManager;

public class MyPackageManagerService extends MyIPackageManager.Stub {

    @Override
    public PackageInfo getPackageInfo(String packageName, int flags, int userId) throws RemoteException {
        return null;
    }
}
