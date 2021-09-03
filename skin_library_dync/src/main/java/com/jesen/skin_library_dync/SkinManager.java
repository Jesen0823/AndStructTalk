package com.jesen.skin_library_dync;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import com.jesen.skin_library_dync.model.SkinCache;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 皮肤管理器
 * 加载应用资源（app内置：res/xxx） or 存储资源（下载皮肤包：net163.skin）
 */

public class SkinManager {
    private static final String TAG = "SkinManager";
    private static SkinManager instance;

    private Application application;
    // app 默认内置的资源
    private Resources appResources;
    // 皮肤包里的资源
    private Resources skinResource;
    // 皮肤包资源所在包名
    private String skinPackageName;
    // 是否加载应用app内置的自带皮肤资源
    private boolean isDefaultSkin;
    private Map<String, SkinCache> cacheSkin;

    // 方法名
    private static final String ADD_ASSET_PATH = "addAssetPath";


    private SkinManager(Application application) {
        this.application = application;
        appResources = application.getResources();
        cacheSkin = new HashMap<>();
    }

    /**
     * 单例方法，目的是初始化app内置资源（越早越好，用户的操作可能是：换肤后的第2次冷启动）
     */
    public static void init(Application application) {
        if (instance == null) {
            synchronized (SkinManager.class) {
                if (instance == null) {
                    instance = new SkinManager(application);
                }
            }
        }
    }

    public static SkinManager getInstance() {
        return instance;
    }


    /**
     * 加载皮肤包资源
     *
     * @param skinPath 皮肤包路径
     */
    public void loadSkinResources(String skinPath) {
        if (TextUtils.isEmpty(skinPath)) {
            isDefaultSkin = true;
            return;
        }
        Log.i(TAG,"loadSkinResources, skinPath: "+skinPath);

        // app冷启动，热启动可以取缓存
        if (cacheSkin.containsKey(skinPath)) {
            isDefaultSkin = false;
            SkinCache skinCache = cacheSkin.get(skinPath);
            if (skinCache != null) {
                skinResource = skinCache.getSkinResource();
                skinPackageName = skinCache.getSkinPackageName();
                return;
            }
        }

        try {
            // 创建资源管理器
            AssetManager assetManager = AssetManager.class.newInstance();

            // 通过反射执行方法AssetManager中的addAssetPath和 setApkAssets
            Method addAssetPathMethod = assetManager.getClass().getDeclaredMethod(ADD_ASSET_PATH, String.class);
            addAssetPathMethod.setAccessible(true);
            addAssetPathMethod.invoke(assetManager, skinPath);
            //==============================================================================
            // 如果还是担心@hide限制，可以反射addAssetPathInternal()方法，参考源码366行 + 387行
            //==============================================================================

            // 创建加载外部的皮肤包(net163.skin)文件Resources（注：依然是本应用加载）
            skinResource = new Resources(assetManager,
                    appResources.getDisplayMetrics(), appResources.getConfiguration());

            // 根据apk文件路径（皮肤包也是apk文件），获取该应用的包名,固定写法。兼容5.0 - 9.0
            if (application.getPackageManager()== null){
                Log.d(TAG,"application getPackageName is null.............");
            }
            skinPackageName = application.getPackageManager()
                    .getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName;

            if (skinPackageName== null){
                Log.d(TAG,"application skinPackageName is null.............");
                return;
            }

            // 无法获取皮肤包应用的包名，则加载app内置资源
            isDefaultSkin = TextUtils.isEmpty(skinPackageName);
            if (!isDefaultSkin) {
                cacheSkin.put(skinPath, new SkinCache(skinResource, skinPackageName));
            }

            Log.e(TAG,"skinPackageName >>> "+skinPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            isDefaultSkin = true;
        }
    }

    /**
     * 参考：resources.arsc资源映射表
     * 通过ID值获取资源 Name 和 Type
     *
     * @param resourceId 资源ID值
     * @return 如果没有皮肤包则加载app内置资源ID，反之加载皮肤包指定资源ID
     */
    private int getSkinResourceIds(int resourceId) {
        // 优化：如果没有皮肤包或者没做换肤动作，直接返回app内置资源！
        if (isDefaultSkin) return resourceId;

        // 使用app内置资源加载，是因为内置资源与皮肤包资源一一对应（“netease_bg”, “drawable”）
        String resourceName = appResources.getResourceEntryName(resourceId);
        String resourceType = appResources.getResourceTypeName(resourceId);

        Log.d(TAG,"getSkinResourceIds, resourceName:"+resourceName+", " +
                "resourceType:"+resourceType+",skinPackageName: "+skinPackageName);

        // 动态获取皮肤包内的指定资源ID
        // getResources().getIdentifier(“netease_bg”, “drawable”, “com.netease.skin.packages”);
        int skinResourceId = skinResource.getIdentifier(resourceName, resourceType, skinPackageName);

        // 源码1924行：(0 is not a valid resource ID.)
        isDefaultSkin = skinResourceId == 0;
        return skinResourceId == 0 ? resourceId : skinResourceId;
    }

    public boolean isDefaultSkin() {
        return isDefaultSkin;
    }

    //==============================================================================================

    // 传入内置默认的resourceId，返回的是皮肤包里面的对应resourceId，找不到则仍然返回默认内置资源id

    public int getColor(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        return isDefaultSkin ? appResources.getColor(ids) : skinResource.getColor(ids);
    }

    public ColorStateList getColorStateList(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        return isDefaultSkin ? appResources.getColorStateList(ids) : skinResource.getColorStateList(ids);
    }

    // mipmap和drawable统一用法（待测）
    public Drawable getDrawableOrMipMap(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        return isDefaultSkin ? appResources.getDrawable(ids) : skinResource.getDrawable(ids);
    }

    public String getString(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        return isDefaultSkin ? appResources.getString(ids) : skinResource.getString(ids);
    }

    // 返回值特殊情况：可能是color / drawable / mipmap
    public Object getBackgroundOrSrc(int resourceId) {
        // 需要获取当前属性的类型名Resources.getResourceTypeName(resourceId)再判断
        String resourceTypeName = appResources.getResourceTypeName(resourceId);

        switch (resourceTypeName) {
            case "color":
                return getColor(resourceId);

            case "mipmap": // drawable / mipmap
            case "drawable":
                return getDrawableOrMipMap(resourceId);
        }
        return null;
    }

    // 获得字体
    public Typeface getTypeface(int resourceId) {
        // 通过资源ID获取资源path，参考：resources.arsc资源映射表
        String skinTypefacePath = getString(resourceId);
        // 路径为空，使用系统默认字体
        if (TextUtils.isEmpty(skinTypefacePath)) return Typeface.DEFAULT;
        return isDefaultSkin ? Typeface.createFromAsset(appResources.getAssets(), skinTypefacePath)
                : Typeface.createFromAsset(skinResource.getAssets(), skinTypefacePath);
    }
}
