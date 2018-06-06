package com.drrepository.base.datasource;

import android.support.annotation.NonNull;

import java.util.HashMap;

public interface IBaseDataSource<T,P extends IBaseParams> {

    void getDatas(P params, @NonNull final ILoadDatasCallback<T> callback);

    void getData(P params, @NonNull final ILoadDataCallback<T> callback);

    void saveData(T data);

    void updateData(T data);

    void removeData(T data);
}