package com.example.lenovo.cniao5_shop_master;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by ${GuoZhaoHui} on 2016/12/3.
 * Abstract:
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
