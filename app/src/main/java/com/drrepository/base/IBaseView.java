package com.drrepository.base;

import android.content.Context;

import com.drrepository.base.presenter.IBasePresenter;

import java.lang.ref.WeakReference;

public interface IBaseView<P extends IBasePresenter,S, F, D> {

    void onInitView();

    boolean onLoadDataSuccess(boolean isRefresh, S data);

    boolean onLoadDataFail(boolean isRefresh,F data);

    Context getContext();

    WeakReference<D> getCurrentContext();

    public P getPresenter();

    public P getInitPresenter();
}
