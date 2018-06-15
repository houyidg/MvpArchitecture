package com.drrepositoryx.main.datasource.remote;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drrepositoryx.base.MyApplication;
import com.drrepositoryx.base.datasource.BaseRemoteDataSource;
import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.datasource.param.RequestParams;
import com.drrepositoryx.main.model.CoinModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RemoteCoinDataSource extends BaseRemoteDataSource implements IRemoteCoinDataSource<RequestParams<String>,List<CoinModel>> {

    @Override
    public boolean cancelByTag(String tag) {
        Call call = callMap.get(tag);
        if(call!=null && !call.isCanceled()){
            call.cancel();
        }
        return true;
    }

    @Override
    public void getList(RequestParams<String> params,final @NonNull ILoadDataCallback<List<CoinModel>> callback) {
        Call newCall = getCall(params);
        enqueue(newCall,new Callback() {
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
                    Map<String, Object> map =  JSON.parseObject(data);
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
                            callback.onDataLoaded(list);
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

    @Override
    public void save(List<CoinModel> model) {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
