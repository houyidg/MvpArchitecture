package com.drrepository.base.datasource;

public interface ILoadDataCallback<T> {

        void onDataLoaded(T dataList);

        void onDataNotAvailable();
    }
