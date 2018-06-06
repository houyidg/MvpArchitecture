package com.drrepository.main.datasource;

import android.support.annotation.NonNull;

import com.drrepository.base.datasource.ILoadDatasCallback;
import com.drrepository.main.datasource.local.LocalDialogDataSource;
import com.drrepository.base.datasource.param.DbParams;
import com.drrepository.base.datasource.param.RequestParams;
import com.drrepository.main.datasource.remote.RemoteDialogDataSource;
import com.drrepository.main.model.CoinModel;


public class DialogRepository implements IDialogRepository<CoinModel, RequestParams, DbParams> {
    private static volatile DialogRepository instance;
    private LocalDialogDataSource localDialogDataSource;
    private RemoteDialogDataSource remoteDialogDataSource;

    private DialogRepository() {
        localDialogDataSource = new LocalDialogDataSource();
        remoteDialogDataSource = new RemoteDialogDataSource();
    }

    public static DialogRepository getInstance() {
        if (instance == null) {
            synchronized (DialogRepository.class) {
                if (instance == null) {
                    instance = new DialogRepository();
                }
            }
        }
        return instance;
    }


    @Override
    public void getDatasByRemote(RequestParams params, @NonNull ILoadDatasCallback<CoinModel> callback) {
        remoteDialogDataSource.getDatas(params, callback);
    }

    @Override
    public void getDatasByLocal(DbParams params, @NonNull ILoadDatasCallback<CoinModel> callback) {
        localDialogDataSource.getDatas(params, callback);
    }

    @Override
    public void destoryAll() {
        instance = null;
        localDialogDataSource = null;
        remoteDialogDataSource = null;
    }
}
