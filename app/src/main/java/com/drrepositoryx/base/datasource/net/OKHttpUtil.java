package com.drrepositoryx.base.datasource.net;

import android.content.Context;

import com.drrepositoryx.base.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * Created by Yves Yang on 2016/5/31.
 */


public class OKHttpUtil {
    private static final int HTTP_CACHE = 10 * 1024 * 1024;
    static volatile OKHttpUtil mOKHttpUtil = null;
    static Context mContext;
    OkHttpClient okHttpClient;

    private OKHttpUtil(Context context) {
        mContext = context;
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(), HTTP_CACHE))
                .build();
    }

    public static OKHttpUtil init() {
        if (mOKHttpUtil == null) {
            synchronized (OKHttpUtil.class) {
                if (mOKHttpUtil == null) {
                    mOKHttpUtil = new OKHttpUtil(MyApplication.application);
                }
            }
        }
        return mOKHttpUtil;
    }

    public OkHttpClient getOkHttpClient(){
        init();
        return okHttpClient;
    }
}
