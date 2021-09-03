package com.jesen.test_skin_library_dync;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.jesen.skin_library_dync.base.SkinActivity;
import com.jesen.skin_library_dync.utils.PreferencesUtils;

import java.io.File;
import java.io.IOException;

public class MainActivity extends SkinActivity {
    private static final String TAG = "MainActivity";

    private String skinPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 换肤包路径
        /*skinPath = Environment.getExternalStorageDirectory().getAbsolutePath()
               + File.separator+ "jesen_pkg.skin";*/
        skinPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                + File.separator+"jesen_pkg.skin";
                Log.d(TAG,"skinPath = "+skinPath);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED){
                requestPermissions(perms,200);
            }
        }

        if (("jesen_pkg").equals(PreferencesUtils.getString(this, "currentSkin"))) {
            skinDynamic(skinPath, R.color.skin_item_color);
            //PreferencesUtils.putString(this,"currentSkin","jesen_pkg");
        } else {
            defaultSkin(R.color.purple_200);
            //PreferencesUtils.putString(this,"currentSkin","default");
        }
    }

    @Override
    protected boolean openChangeSkin() {
        // 换肤开关
        return true;
    }

    public void jumpSelf(View view) {
        startActivity(new Intent(this, this.getClass()));
    }

    public void skinDefault(View view) {
        if ((!("default").equals(PreferencesUtils.getString(this,"currentSkin")))){
            Log.d(TAG,"skinDefault, start...");
            long startTime = System.currentTimeMillis();
            defaultSkin(R.color.purple_200);
            PreferencesUtils.putString(this,"currentSkin","default");
            long endTime = System.currentTimeMillis();
            Log.d(TAG,"skinDefault, end, use time:"+(endTime-startTime));

        }
    }

    public void skinDynamic(View view) {
        if (!("jesen_pkg").equals(PreferencesUtils.getString(this,"currentSkin"))){
            Log.d(TAG,"skinDynamic, start...");
            long startTime = System.currentTimeMillis();
            skinDynamic(skinPath,R.color.skin_item_color);
            PreferencesUtils.putString(this,"currentSkin","jesen_pkg");
            long endTime = System.currentTimeMillis();
            Log.d(TAG,"skinDynamic, end, use time:"+(endTime-startTime));

        }
    }
}