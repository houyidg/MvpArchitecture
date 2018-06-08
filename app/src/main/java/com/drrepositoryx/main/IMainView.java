package com.drrepositoryx.main;


public interface IMainView<S, F> {

    boolean onLoadDataSuccess(boolean isRefresh, S data);

    boolean onLoadDataFail(boolean isRefresh, F data);

}
