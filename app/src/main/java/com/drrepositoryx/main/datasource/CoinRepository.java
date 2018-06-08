package com.drrepositoryx.main.datasource;

import android.support.annotation.NonNull;

import com.drrepositoryx.base.datasource.IBaseDataSource;
import com.drrepositoryx.base.datasource.IBaseRepository;
import com.drrepositoryx.base.datasource.callback.ILoadDatasCallback;
import com.drrepositoryx.main.datasource.local.LocalCoinDataSource;
import com.drrepositoryx.base.datasource.param.DbParams;
import com.drrepositoryx.base.datasource.param.RequestParams;
import com.drrepositoryx.main.datasource.remote.RemoteCoinDataSource;
import com.drrepositoryx.main.model.CoinModel;

public class CoinRepository implements IBaseRepository<CoinModel, RequestParams, DbParams> {
    private IBaseDataSource localCoinDataSource;
    private IBaseDataSource remoteCoinDataSource;

    public CoinRepository() {
        localCoinDataSource = new LocalCoinDataSource();
        remoteCoinDataSource = new RemoteCoinDataSource();
    }

    @Override
    public void getDatasByRemote(RequestParams params, @NonNull ILoadDatasCallback<CoinModel> callback) {
        remoteCoinDataSource.getDatas(params, callback);
    }

    @Override
    public void getDatasByLocal(DbParams params, @NonNull ILoadDatasCallback<CoinModel> callback) {
        localCoinDataSource.getDatas(params, callback);
    }

    @Override
    public void destroyAll() {
        localCoinDataSource = null;
        remoteCoinDataSource = null;
    }
}
