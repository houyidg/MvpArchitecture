package com.drrepositoryx.main;

import android.os.Bundle;

import com.drrepositoryx.base.datasource.IBaseRepository;
import com.drrepositoryx.base.datasource.callback.ILoadDataCallback;
import com.drrepositoryx.base.presenter.BasePresenter;
import com.drrepositoryx.main.datasource.CoinRepository;
import com.drrepositoryx.base.datasource.param.RequestParams;
import com.drrepositoryx.main.model.CoinModel;

import java.util.List;

/**
 * 需要的其他的方法扩展,
 * presenter驱动ui执行
 */
public class MainPresenter extends BasePresenter<MainActivity> {
    IBaseRepository mCoinRepository;
    String url = "https://api.coinmarketcap.com/v2/ticker/";
    int startIndex = 1;
    int pageCount = 10;

    public void setmSortType(SortType mSortType) {
        this.mSortType = mSortType;
    }

    SortType mSortType = SortType.SortById;
    enum SortType{
        SortById("id"),SortByRank("rank"),SortBy24Percent("percent_change_24h"),SortByVolume24h("volume_24h");
        private  String value;
        SortType(String value){
            this.value = value;
        }

    }
    //https://api.coinmarketcap.com/v2/ticker/?limit=10
    public void onCreate(Bundle savedInstanceState) {
        try {
            mCoinRepository = getAssignRepository(CoinRepository.class);
            mvpView.onInitView();
            startIndex = 1;
            loadMoreCoinData();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void reLoadCoinData(){
        startIndex = 1;
        loadMoreCoinData();
    }

    public void loadMoreCoinData(){
        RequestParams requestParams = new RequestParams(url, RequestParams.TYPE.GET);
        requestParams.addUrlParameter("limit", pageCount+"");
        requestParams.addUrlParameter("start", startIndex+"");
        requestParams.addUrlParameter("sort", mSortType.value);
        mCoinRepository.getDatasByRemote(requestParams, new ILoadDataCallback<List<CoinModel>>() {
            @Override
            public void onDataLoaded(List<CoinModel> dataList) {
                if (isViewAttached()){
                    boolean isRefresh = startIndex==1;
                    mvpView.onLoadDataSuccess(isRefresh,dataList);
                }
                startIndex+=pageCount;
            }
            @Override
            public void onDataNotAvailable() {
                if (isViewAttached()){
                    boolean isRefresh = startIndex==1;
                    mvpView.onLoadDataFail(isRefresh,null);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        mCoinRepository.destroyAll();
        mCoinRepository = null;
    }
}
