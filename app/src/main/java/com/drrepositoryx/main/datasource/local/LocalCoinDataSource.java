package com.drrepositoryx.main.datasource.local;

import android.support.annotation.NonNull;

import com.drrepositoryx.main.datasource.remote.IRemoteCoinDataSource;
import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.datasource.param.DbParams;
import com.drrepositoryx.main.model.CoinModel;

import java.util.List;


public class LocalCoinDataSource implements ILocalCoinDataSource<DbParams,List<CoinModel>> {


    @Override
    public boolean cancelByTag(String tag) {
        return false;
    }


    @Override
    public void getList(DbParams params, @NonNull ILoadDataCallback<List<CoinModel>> callback) {

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
