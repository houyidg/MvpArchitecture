package com.drrepository.main;

import android.content.Context;

import com.drrepository.R;
import com.drrepository.base.BaseActivity;
import com.drrepository.base.BasePresenter;
import com.drrepository.main.model.DialogModel;
import com.drrepository.main.model.FailModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 *
 */
public class MainActivity extends BaseActivity<MainPresenter, List<DialogModel>, FailModel,MainActivity> {

    @Override
    public void onInitView() {

    }

    @Override
    public void onStartLoadData() {

    }

    @Override
    public boolean onLoadDataSuccess(List<DialogModel> data) {
        return false;
    }

    @Override
    public boolean onLoadDataFail(FailModel data) {
        return false;
    }

    ///////////////////////////////默認初始化內容/////////////////////////////////////////
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter getInitPresenter() {
        return new MainPresenter();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public WeakReference<MainActivity> getCurrentContext() {
        return new WeakReference(this);
    }
    ///////////////////////////////默認初始化內容/////////////////////////////////////////
}
