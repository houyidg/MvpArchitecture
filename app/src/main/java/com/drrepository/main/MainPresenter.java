package com.drrepository.main;

import android.os.Bundle;

import com.drrepository.base.datasource.IBaseRepository;
import com.drrepository.base.datasource.callback.ILoadDatasCallback;
import com.drrepository.base.presenter.BasePresenter;
import com.drrepository.main.datasource.CoinRepository;
import com.drrepository.base.datasource.param.RequestParams;
import com.drrepository.main.model.CoinModel;

import java.util.List;

/**
 * 需要的其他的方法扩展,
 * presenter驱动ui执行
 */
public class MainPresenter extends BasePresenter<MainActivity> {
    IBaseRepository mDialogRepository;
    MainActivity view;
    String url = "https://api.coinmarketcap.com/v2/ticker/";
    int startIndex = 1;
    int pageCount = 10;
    //https://api.coinmarketcap.com/v2/ticker/?limit=10
    public void onCreate(Bundle savedInstanceState) {
        mDialogRepository = CoinRepository.getInstance();
        view = getView();
        view.onInitView();
        startIndex = 1;
        loadMoreCoinData();
    }

    public void reLoadCoinData(){
        startIndex = 1;
        loadMoreCoinData();
    }

    public void loadMoreCoinData(){
        RequestParams requestParams = new RequestParams(url, RequestParams.TYPE.GET);
        requestParams.addUrlParameter("limit", pageCount+"");
        requestParams.addUrlParameter("start", startIndex+"");
        mDialogRepository.getDatasByRemote(requestParams, new ILoadDatasCallback<CoinModel>() {
            @Override
            public void onDatasLoaded(List<CoinModel> dataList) {
                if (isViewAttached()){
                    boolean isRefresh = startIndex==1;
                    view.onLoadDataSuccess(isRefresh,dataList);
                }
                startIndex+=pageCount;
            }
            @Override
            public void onDataNotAvailable() {
                if (isViewAttached()){
                    boolean isRefresh = startIndex==1;
                    view.onLoadDataFail(isRefresh,null);
                }
            }
        });
    }

    @Override
    public void onDestory() {
        view = null;
        mDialogRepository.destoryAll();
        mDialogRepository = null;
    }
}
