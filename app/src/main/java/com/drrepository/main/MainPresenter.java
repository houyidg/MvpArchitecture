package com.drrepository.main;

import android.os.Bundle;
import com.drrepository.base.presenter.BasePresenter;
import com.drrepository.main.datasource.DialogRepository;
import com.drrepository.main.datasource.IDialogRepository;

/**
 * 需要的其他的方法扩展,
 * presenter驱动ui执行
 *
 */
public class MainPresenter extends BasePresenter<MainActivity> {

    IDialogRepository mDialogRepository = new DialogRepository();

    public void onCreate(Bundle savedInstanceState) {

    }

}
