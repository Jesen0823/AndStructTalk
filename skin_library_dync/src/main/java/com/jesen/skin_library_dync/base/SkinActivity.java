package com.jesen.skin_library_dync.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import com.jesen.skin_library_dync.SkinManager;
import com.jesen.skin_library_dync.core.CustomAppCompatViewInflater;
import com.jesen.skin_library_dync.core.ViewsMatch;
import com.jesen.skin_library_dync.utils.ActionBarUtils;
import com.jesen.skin_library_dync.utils.NavigationUtils;
import com.jesen.skin_library_dync.utils.StatusBarUtils;

/**
 * 换肤Activity父类
 * <p>
 * 用法：
 * 1、继承此类
 * 2、重写openChangeSkin()方法
 */
public class SkinActivity extends AppCompatActivity {

    private CustomAppCompatViewInflater viewInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if (openChangeSkin()) {
            if (viewInflater == null) {
                viewInflater = new CustomAppCompatViewInflater(context);
            }
            viewInflater.setName(name);
            viewInflater.setAttrs(attrs);
            return viewInflater.autoMatch();
        }
        return super.onCreateView(parent, name, context, attrs);
    }

    /**
     * @return 是否开启换肤，增加此开关是为了避免开发者误继承此父类，导致未知bug
     */
    protected boolean openChangeSkin() {
        return false;
    }

    /**
     * 默认皮肤
     * @param themeColorId 主题色
     * */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void defaultSkin(int themeColorId) {
        this.skinDynamic(null, themeColorId);
    }

    /**
     * 动态换肤（api限制：5.0版本）
     * @param themeColorId 主题色
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void skinDynamic(String skinPath, int themeColorId) {
        SkinManager.getInstance().loadSkinResources(skinPath);

        if (themeColorId != 0) {
            int themeColor = SkinManager.getInstance().getColor(themeColorId);
            StatusBarUtils.forStatusBar(this, themeColor);
            NavigationUtils.forNavigation(this, themeColor);
            ActionBarUtils.forActionBar(this, themeColor);
        }

        applyViews(getWindow().getDecorView());
    }

    /**
     * 控件回调监听，如果匹配则给控件执行换肤方法
     */
    protected void applyViews(View view) {
        if (view instanceof ViewsMatch) {
            ViewsMatch viewsMatch = (ViewsMatch) view;
            viewsMatch.skinnableView();
        }

        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                applyViews(parent.getChildAt(i));
            }
        }
    }
}
