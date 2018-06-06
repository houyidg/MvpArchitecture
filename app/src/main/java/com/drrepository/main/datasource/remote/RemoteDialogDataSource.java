package com.drrepository.main.datasource.remote;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drrepository.base.MyApplication;
import com.drrepository.base.datasource.IBaseDataSource;
import com.drrepository.base.datasource.ILoadDataCallback;
import com.drrepository.base.datasource.ILoadDatasCallback;
import com.drrepository.base.datasource.net.OKHttpUtil;
import com.drrepository.base.datasource.param.RequestParams;
import com.drrepository.main.model.CoinModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RemoteDialogDataSource implements IBaseDataSource<CoinModel, RequestParams<String>> {
    OKHttpUtil okHttpUtil;
    OkHttpClient requestQueue;
    private static String TAG = "RemoteDialogDataSource";

    public RemoteDialogDataSource() {
        okHttpUtil = OKHttpUtil.init();
        requestQueue = okHttpUtil.getRequestQueue();
    }

    @Override
    public void getDatas(RequestParams<String> params, @NonNull final ILoadDatasCallback<CoinModel> callback) {
        Request.Builder requestBuilder = new Request.Builder();
        //url
        String url = params.getUrl();
        //urlparameter
        String parameters = params.getUrlParameters();
        if (parameters != null)
            url += "?" + parameters;

        requestBuilder.url(url);
        //header
        Map<String, String> headers = params.getHeaders();
        Set<Map.Entry<String, String>> entries = headers.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            requestBuilder.addHeader(key, value);
        }
        //method body
        RequestParams.TYPE method = params.getMethod();
        requestBuilder = getRequestBuildByMethod(method, requestBuilder, params.getContent());

        //needCache
        boolean needCache = params.isNeedCache();
//        if(needCache){
//            CacheControl cacheControl;
//            cacheControl = new CacheControl(new CacheControl.Builder().noStore());
//            requestBuilder.cacheControl(cacheControl);
//        }

        Call newCall = requestQueue.newCall(requestBuilder.build());
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyApplication.application.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onDataNotAvailable();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                try {
                    JSONObject jsonObject = JSON.parseObject(content);
                    String data = jsonObject.getString("data");
                    Map<String, Object> map = (Map<String, Object>) JSON.parseObject(data);
                    Log.e(TAG, "onResponse: data:" + map.size() + "," + Thread.currentThread().getName());
                    Set<String> keySet = map.keySet();
                    final List<CoinModel> list = new ArrayList<>();
                    for (String key : keySet) {
                        Object o = map.get(key);
                        CoinModel coinModel = JSON.parseObject(o.toString(), CoinModel.class);
                        list.add(coinModel);
                    }
                    MyApplication.application.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onDatasLoaded(list);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    MyApplication.application.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onDataNotAvailable();
                        }
                    });
                }
            }
        });
    }

    private Request.Builder getRequestBuildByMethod(RequestParams.TYPE method, Request.Builder requestBuilder, String content) {
        switch (method) {
            case GET: {
                requestBuilder = requestBuilder.get();
                break;
            }
            case POST: {
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), content);
                requestBuilder = requestBuilder.post(requestBody);
                break;
            }
            case PUT: {
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), content);
                requestBuilder = requestBuilder.put(requestBody);
                break;
            }
            case DELETE: {
                requestBuilder = requestBuilder.delete();
                break;
            }
        }

        return requestBuilder;
    }

    @Override
    public void getData(RequestParams<String> params, @NonNull ILoadDataCallback<CoinModel> callback) {

    }

    @Override
    public void saveData(CoinModel data) {

    }

    @Override
    public void updateData(CoinModel data) {

    }

    @Override
    public void removeData(CoinModel data) {

    }
}
