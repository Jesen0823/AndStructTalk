package com.jesen.skin_library_dync.model;

import android.content.res.Resources;

public class SkinCache {
    // 皮肤包资源
    private Resources skinResource;
    // 皮肤包资源所在包名
    private String skinPackageName;

    public SkinCache(Resources skinResource, String skinPackageName) {
        this.skinResource = skinResource;
        this.skinPackageName = skinPackageName;
    }

    public Resources getSkinResource() {
        return skinResource;
    }

    public String getSkinPackageName() {
        return skinPackageName;
    }
}
