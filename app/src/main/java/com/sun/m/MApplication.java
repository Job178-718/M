package com.sun.m;

import android.app.Application;
import android.content.Context;

import com.sun.m.Dao.FileDao;

import org.jetbrains.annotations.NotNull;

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FileDao.getInstance(getContext());
    }

    public Context getContext(){
        return getApplicationContext();
    }

}
