package com.drrepositoryx.base.datasource;

import android.support.annotation.NonNull;

import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.datasource.param.IBaseParams;
import com.drrepositoryx.main.model.BaseModel;

public interface IBaseDataSource<P extends IBaseParams,T> {
    void getList(P params ,@NonNull final ILoadDataCallback<T> callback);
    void save(T model);
    void update();
    void delete();
}
