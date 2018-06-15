package com.drrepositoryx.main.datasource;

import android.support.annotation.NonNull;

import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.datasource.manager.DataSourceManager;
import com.drrepositoryx.main.datasource.local.ILocalCoinDataSource;
import com.drrepositoryx.main.datasource.remote.IRemoteCoinDataSource;
import com.drrepositoryx.base.datasource.IBaseRepository;
import com.drrepositoryx.main.datasource.local.LocalCoinDataSource;
import com.drrepositoryx.base.datasource.param.DbParams;
import com.drrepositoryx.base.datasource.param.RequestParams;
import com.drrepositoryx.main.datasource.remote.RemoteCoinDataSource;
import com.drrepositoryx.main.model.CoinModel;
import java.util.List;

public class CoinRepository implements IBaseRepository<List<CoinModel>, RequestParams, DbParams> {
    private ILocalCoinDataSource localCoinDataSource;
    private IRemoteCoinDataSource remoteCoinDataSource;

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
    public void getDatasByRemote(RequestParams params, @NonNull ILoadDataCallback<List<CoinModel>> callback) {
        remoteCoinDataSource.getList(params, callback);
    }

    @Override
    public void getDatasByLocal(DbParams params, @NonNull ILoadDataCallback<List<CoinModel>> callback) {
        localCoinDataSource.getList(params, callback);
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
