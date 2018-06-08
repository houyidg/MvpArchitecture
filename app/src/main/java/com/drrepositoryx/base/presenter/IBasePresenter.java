package com.drrepositoryx.base.presenter;

import android.os.Bundle;

import com.drrepositoryx.base.datasource.IBaseRepository;
import com.drrepositoryx.base.view.IBaseView;

public interface IBasePresenter<V extends IBaseView> {
    void attachView(V mvpView);

    void onCreate(Bundle savedInstanceState);

    void detachView();

    void onDestroy();

    boolean isViewAttached();

    V getView();

    IBaseView getBaseView();

    <T extends IBaseRepository>T getAssignRepository(Class<T> clazz) throws InstantiationException, IllegalAccessException;
}
