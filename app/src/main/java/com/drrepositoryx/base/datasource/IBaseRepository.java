package com.drrepositoryx.base.datasource;

import android.support.annotation.NonNull;

import com.drrepositoryx.base.datasource.param.IBaseParams;
import com.drrepositoryx.base.datasource.callback.ILoadDatasCallback;
/**
 * 数据仓储 单例 泛型：返回数据类型 请求数据类型
 */
public interface IBaseRepository<T, P1 extends IBaseParams, P2 extends IBaseParams> {
    void getDatasByRemote(P1 params, @NonNull ILoadDatasCallback<T> callback);

    void getDatasByLocal(P2 params, @NonNull ILoadDatasCallback<T> callback);

    <T> T getDataSource(Class<T> clazz) throws InstantiationException, IllegalAccessException;

    void destroyAll();
}
