package com.drrepository.base.presenter;


import com.drrepository.base.view.IBaseView;

public abstract class BasePresenter<V extends IBaseView>  implements  IBasePresenter<V> {
    public V mvpView;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mvpView != null;
    }

    @Override
    public V getView() {
        return mvpView;
    }
}
