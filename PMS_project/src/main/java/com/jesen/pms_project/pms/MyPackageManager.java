package com.jesen.pms_project.pms;

import android.content.pm.PackageInfo;

public abstract class MyPackageManager {

    public abstract PackageInfo getPackageInfo(String packageName, int flags);

}
