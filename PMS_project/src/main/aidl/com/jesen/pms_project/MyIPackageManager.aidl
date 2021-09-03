// MyIPackageManager.aidl
package com.jesen.pms_project;

// Declare any non-default types here with import statements

interface MyIPackageManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    PackageInfo getPackageInfo(String packageName, int flags, int userId);
}