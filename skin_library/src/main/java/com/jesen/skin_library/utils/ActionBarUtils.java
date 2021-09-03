package com.jesen.skin_library.utils;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ActionBarUtils {

    public static void forActionBar(AppCompatActivity appCompatActivity){
        TypedArray a = appCompatActivity.getTheme().obtainStyledAttributes(0, new int[]{
                android.R.attr.colorPrimary
        });
        int color = a.getColor(0,0);
        a.recycle();
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null){
            actionBar.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    public static void forActionBar(AppCompatActivity activity, int skinColor) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(skinColor));
        }
    }
}
