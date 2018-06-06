package com.drrepository.base;

import android.content.Context;

import java.lang.ref.WeakReference;

public interface IBaseView<S, F, D> {
    void onInitView();

    void onStartLoadData();

    boolean onLoadDataSuccess(boolean isRefresh,S data);

    boolean onLoadDataFail(F data);

    Context getContext();

    WeakReference<D> getCurrentContext();
}
