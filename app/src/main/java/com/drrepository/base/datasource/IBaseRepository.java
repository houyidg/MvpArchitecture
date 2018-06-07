package com.drrepository.base.datasource;

import android.support.annotation.NonNull;

import com.drrepository.base.datasource.param.IBaseParams;
import com.drrepository.base.datasource.callback.ILoadDatasCallback;

public interface IBaseRepository<T, P1 extends IBaseParams, P2 extends IBaseParams> {
    void getDatasByRemote(P1 params, @NonNull ILoadDatasCallback<T> callback);

    void getDatasByLocal(P2 params, @NonNull ILoadDatasCallback<T> callback);

    void destoryAll();
}
