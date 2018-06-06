package com.drrepository.base.presenter;

import android.os.Bundle;

import com.drrepository.base.IBaseView;

public interface IBasePresenter<V extends IBaseView> {
    void attachView(V mvpView);

    void onCreate(Bundle savedInstanceState);

    void detachView();

    void onDestory();

    boolean isViewAttached();

    V getView();
}
