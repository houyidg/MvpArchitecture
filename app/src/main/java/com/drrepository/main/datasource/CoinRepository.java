package com.drrepository.main.datasource;

import android.support.annotation.NonNull;

import com.drrepository.base.datasource.IBaseDataSource;
import com.drrepository.base.datasource.IBaseRepository;
import com.drrepository.base.datasource.callback.ILoadDatasCallback;
import com.drrepository.main.datasource.local.LocalCoinDataSource;
import com.drrepository.base.datasource.param.DbParams;
import com.drrepository.base.datasource.param.RequestParams;
import com.drrepository.main.datasource.remote.RemoteCoinDataSource;
import com.drrepository.main.model.CoinModel;


public class CoinRepository implements IBaseRepository<CoinModel, RequestParams, DbParams> {
    private static volatile CoinRepository instance;
    private IBaseDataSource localCoinDataSource;
    private IBaseDataSource remoteCoinDataSource;

    private CoinRepository() {
        localCoinDataSource = new LocalCoinDataSource();
        remoteCoinDataSource = new RemoteCoinDataSource();
    }

    public static CoinRepository getInstance() {
        if (instance == null) {
            synchronized (CoinRepository.class) {
                if (instance == null) {
                    instance = new CoinRepository();
                }
            }
        }
        return instance;
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
    public void destoryAll() {
        instance = null;
        localCoinDataSource = null;
        remoteCoinDataSource = null;
    }
}
