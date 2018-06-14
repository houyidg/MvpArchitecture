package com.drrepositoryx.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.drrepositoryx.R;
import com.drrepositoryx.base.view.BaseActivity;
import com.drrepositoryx.main.model.CoinModel;
import com.drrepositoryx.main.model.FailModel;
import com.drrepositoryx.main.util.DividerItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class MainActivity extends BaseActivity<MainPresenter> implements IMainView<List<CoinModel>, FailModel>,RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_reload)
    TextView tvReload;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    CommonAdapter<CoinModel> commonAdapter;
    List<CoinModel> dataList = new ArrayList<>();
    @BindView(R.id.rg_list)
    RadioGroup rgList;


    @Override
    public void onInitView() {
        ButterKnife.bind(this);
        showSelectView(R.id.pb_loading);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.reLoadCoinData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                mPresenter.loadMoreCoinData();
            }
        });
        commonAdapter = new CommonAdapter<CoinModel>(getContext(), R.layout.item_coin, dataList) {
            @Override
            protected void convert(ViewHolder holder, CoinModel coinModel, int position) {
                double percent_change_24h = coinModel.getQuotes().getUSD().getPercent_change_24h();
                boolean isUp24h = false;
                if (percent_change_24h > 0) {
                    isUp24h = true;
                }
                holder.setBackgroundRes(R.id.llBp, isUp24h ? R.color.green : R.color.red);
                holder.setText(R.id.tvTitile, coinModel.getSymbol());
                holder.setText(R.id.tvValue, "$" + coinModel.getQuotes().getUSD().getPrice());
                holder.setText(R.id.tv24Percent,  coinModel.getQuotes().getUSD().getPercent_change_24h() + "%");
                holder.setText(R.id.tvRank, "rank:" + coinModel.getRank());
                holder.setText(R.id.tvMarketCap, "market cap: $" + coinModel.getQuotes().getUSD().getMarket_cap());
            }
        };
        rvList.setAdapter(commonAdapter);
        rgList.setOnCheckedChangeListener(this);
    }


    @Override
    public boolean onLoadDataSuccess(boolean isRefresh, List<CoinModel> data) {
        showSelectView(R.id.refreshLayout);
        if (isRefresh) {
            dataList.clear();
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();//传入false表示加载失败
        }
        dataList.addAll(data);
        commonAdapter.notifyDataSetChanged();
        return true;
    }

    /**
     * id
     *
     * @param id
     */
    public void showSelectView(int id) {
        tvReload.setVisibility(id == tvReload.getId() ? View.VISIBLE : View.GONE);
        refreshLayout.setVisibility(id == refreshLayout.getId() ? View.VISIBLE : View.GONE);
        pbLoading.setVisibility(id == pbLoading.getId() ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onLoadDataFail(boolean isRefresh, FailModel data) {
        if (isRefresh) {
            showSelectView(R.id.tv_reload);
        }

        refreshLayout.finishRefresh(false);
        refreshLayout.finishLoadMore(false);//传入false表示加载失败
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

    @OnClick(R.id.tv_reload)
    public void onViewClicked() {
        showSelectView(R.id.pb_loading);
        mPresenter.reLoadCoinData();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.rb_id:{
                mPresenter.setmSortType(MainPresenter.SortType.SortById);
                break;
            }
            case R.id.rb_percent_change_24h:{
                mPresenter.setmSortType(MainPresenter.SortType.SortBy24Percent);
                break;
            }
            case R.id.rb_rank:{
                mPresenter.setmSortType(MainPresenter.SortType.SortByRank);
                break;
            }
            case R.id.rb_volume_24h:{
                mPresenter.setmSortType(MainPresenter.SortType.SortByVolume24h);
                break;
            }
        }
        showSelectView(R.id.pb_loading);
        mPresenter.reLoadCoinData();
    }

    ///////////////////////////////默認初始化內容/////////////////////////////////////////
}
