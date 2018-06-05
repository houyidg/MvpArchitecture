package com.drrepository.base;

import android.os.Bundle;

public interface IBasePresenter<V extends IBaseView> {
    public void attachView(V mvpView);
    void onCreate(Bundle savedInstanceState);
    public void detachView();
    public boolean isViewAttached();
    public V getView();
}
