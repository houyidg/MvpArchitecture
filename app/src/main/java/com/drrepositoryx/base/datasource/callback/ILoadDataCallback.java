package com.drrepositoryx.base.datasource.callback;

public interface ILoadDataCallback<T> {

        void onDataLoaded(T dataList);

        void onDataNotAvailable();
    }
