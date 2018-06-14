package com.drrepositoryx.main.datasource.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drrepositoryx.base.MyApplication;
import com.drrepositoryx.base.datasource.IBaseDataSource;
import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.datasource.callback.ILoadDatasCallback;
import com.drrepositoryx.base.datasource.net.OKHttpUtil;
import com.drrepositoryx.base.datasource.param.RequestParams;
import com.drrepositoryx.main.model.CoinModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class RemoteCoinDataSource implements IBaseDataSource<CoinModel, RequestParams<String>> {
    OKHttpUtil okHttpUtil;
    OkHttpClient requestQueue;
    private static String TAG = "RemoteCoinDataSource";
    private Map<String,Call> callMap = new HashMap<>();

    public RemoteCoinDataSource() {
        okHttpUtil = OKHttpUtil.init();
        requestQueue = okHttpUtil.getRequestQueue();
    }

    @Override
    public void getDatas(RequestParams<String> params, @NonNull final ILoadDatasCallback<CoinModel> callback) {
        Call newCall = getCall(params);
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

    private Call getCall(RequestParams<String> params) {
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

        Call newCall = requestQueue.newCall(requestBuilder.build());
        callMap.put(params.getTag(),newCall);
        return newCall;
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

    @Override
    public boolean cancelByTag(String tag) {
        Call call = callMap.get(tag);
        if(call!=null && !call.isCanceled()){
            call.cancel();
        }
        return true;
    }
}
