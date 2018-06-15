package com.drrepositoryx.main.datasource.local;

import android.support.annotation.NonNull;

import com.drrepositoryx.base.datasource.IBaseDataSource;
import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.datasource.param.IBaseParams;

public interface ILocalCoinDataSource<P extends IBaseParams,T> extends IBaseDataSource<P,T> {

    boolean cancelByTag(String tag);
}
