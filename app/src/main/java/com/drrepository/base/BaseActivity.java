package com.drrepository.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.drrepository.R;

import java.lang.ref.WeakReference;

//,S, F, D extends WeakReference
public abstract class BaseActivity<P extends IBasePresenter, S, F, D > extends AppCompatActivity implements IBaseView<S, F, D> {
    P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(getLayoutId());
            mPresenter = getPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    private P getPresenter() {
        if (mPresenter != null) {
            return mPresenter;
        }
        return getInitPresenter();
    }

    public abstract int getLayoutId();

    public abstract P getInitPresenter();
}
