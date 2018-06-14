package com.drrepositoryx.base.datasource;

import android.support.annotation.NonNull;

import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.datasource.callback.ILoadDatasCallback;
import com.drrepositoryx.base.datasource.param.IBaseParams;

public interface IBaseDataSource<T,P extends IBaseParams> {

    void getDatas(P params, @NonNull final ILoadDatasCallback<T> callback);

    void getData(P params, @NonNull final ILoadDataCallback<T> callback);

    void saveData(T data);

    void updateData(T data);

    void removeData(T data);

    boolean cancelByTag(String tag);
}
