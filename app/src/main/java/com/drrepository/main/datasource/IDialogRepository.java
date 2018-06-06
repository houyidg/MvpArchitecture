package com.drrepository.main.datasource;

import android.support.annotation.NonNull;

import com.drrepository.base.datasource.IBaseParams;
import com.drrepository.base.datasource.ILoadDatasCallback;

public interface IDialogRepository<T, P1 extends IBaseParams, P2 extends IBaseParams> {
    void getDatasByRemote(P1 params, @NonNull ILoadDatasCallback<T> callback);

    void getDatasByLocal(P2 params, @NonNull ILoadDatasCallback<T> callback);

    void destoryAll();
}
