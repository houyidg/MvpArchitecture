package com.drrepositoryx.base.presenter;


import com.drrepositoryx.base.datasource.IBaseRepository;
import com.drrepositoryx.base.datasource.RepositoryManager;
import com.drrepositoryx.base.view.IBaseView;


public abstract class BasePresenter<V extends IBaseView>  implements IBasePresenter<V> {
    protected V mvpView;

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

    @Override
    public IBaseView getBaseView() {
        return mvpView;
    }

    @Override
    public <T extends IBaseRepository> T getRepository(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return (T) RepositoryManager.getInstance().getAssignRepository(clazz);
    }
}
