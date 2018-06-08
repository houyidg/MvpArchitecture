package com.drrepositoryx.main.datasource;

import android.support.annotation.NonNull;

import com.drrepositoryx.base.datasource.DataSourceManager;
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
        try {
            localCoinDataSource = getDataSource(LocalCoinDataSource.class);
            remoteCoinDataSource = getDataSource(RemoteCoinDataSource.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
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
    public <T> T getDataSource(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return (T) DataSourceManager.getInstance().getAssignDataSource(clazz);
    }

    @Override
    public void destroyAll() {
        localCoinDataSource = null;
        remoteCoinDataSource = null;
    }
}
