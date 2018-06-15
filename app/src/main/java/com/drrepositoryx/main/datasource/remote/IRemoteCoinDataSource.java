package com.drrepositoryx.main.datasource.remote;

import android.support.annotation.NonNull;

import com.drrepositoryx.base.datasource.IBaseDataSource;
import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.datasource.param.IBaseParams;
import com.drrepositoryx.main.model.BaseModel;

public interface IRemoteCoinDataSource<P extends IBaseParams,T>  extends IBaseDataSource<P,T> {


    boolean cancelByTag(String tag);
}
