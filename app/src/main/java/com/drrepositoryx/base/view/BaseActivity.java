package com.drrepositoryx.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.drrepositoryx.base.presenter.IBasePresenter;

/**
 *
 * @param <P> presenter泛型
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView<P> {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(getLayoutId());
            mPresenter = getPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
                mPresenter.onCreate(savedInstanceState);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    public P getPresenter() {
        if (mPresenter != null) {
            return mPresenter;
        }
        return getInitPresenter();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    public abstract int getLayoutId();

}
