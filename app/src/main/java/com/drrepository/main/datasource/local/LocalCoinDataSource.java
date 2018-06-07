package com.drrepository.main.datasource.local;

import android.support.annotation.NonNull;

import com.drrepository.base.datasource.IBaseDataSource;
import com.drrepository.base.datasource.callback.ILoadDataCallback;
import com.drrepository.base.datasource.callback.ILoadDatasCallback;
import com.drrepository.base.datasource.param.DbParams;
import com.drrepository.main.model.CoinModel;


public class LocalCoinDataSource implements IBaseDataSource<CoinModel,DbParams>  {

    @Override
    public void getDatas(DbParams params, @NonNull ILoadDatasCallback<CoinModel> callback) {

    }

    @Override
    public void getData(DbParams params, @NonNull ILoadDataCallback<CoinModel> callback) {

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
