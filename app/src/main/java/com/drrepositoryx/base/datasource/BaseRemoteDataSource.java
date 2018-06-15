package com.drrepositoryx.base.datasource;

import com.drrepositoryx.base.datasource.net.OKHttpUtil;
import com.drrepositoryx.base.datasource.param.IBaseParams;
import com.drrepositoryx.base.datasource.param.RequestParams;
import com.drrepositoryx.main.model.BaseModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class BaseRemoteDataSource {
    protected static String TAG = "RemoteCoinDataSource";
    protected OKHttpUtil okHttpUtil;
    protected OkHttpClient okHttpClient;
    protected Map<String, Call> callMap = new HashMap<>();
    protected Dispatcher dispatcher;

    public BaseRemoteDataSource() {
        okHttpUtil = OKHttpUtil.init();
        okHttpClient = okHttpUtil.getOkHttpClient();
        dispatcher = okHttpClient.dispatcher();
    }

    protected void enqueue(Call newCall, Callback callback) {
        newCall.enqueue(callback);
//        dispatcher.getMaxRequests();
//        dispatcher.queuedCalls();
    }

    protected Call getCall(RequestParams<String> params) {
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
        Call newCall = okHttpClient.newCall(requestBuilder.build());
        callMap.put(params.getTag(),newCall);
        return newCall;
    }

    protected Request.Builder getRequestBuildByMethod(RequestParams.TYPE method, Request.Builder requestBuilder, String content) {
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
}
