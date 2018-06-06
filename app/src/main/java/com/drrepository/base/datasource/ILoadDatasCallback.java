package com.drrepository.base.datasource;

import java.util.List;

public interface ILoadDatasCallback<T> {

        void onDatasLoaded(List<T> dataList);

        void onDataNotAvailable();
    }