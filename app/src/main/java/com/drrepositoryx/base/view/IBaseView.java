package com.drrepositoryx.base.view;

import android.content.Context;

import com.drrepositoryx.base.presenter.IBasePresenter;

import java.lang.ref.WeakReference;

public interface IBaseView<P extends IBasePresenter> {

    void onInitView();

    Context getContext();

    P getPresenter();

    P getInitPresenter();
}
