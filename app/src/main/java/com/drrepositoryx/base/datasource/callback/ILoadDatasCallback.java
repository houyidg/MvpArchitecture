package com.drrepositoryx.base.datasource.callback;

import java.util.List;

public interface ILoadDatasCallback<T> {

        void onDatasLoaded(List<T> dataList);

        void onDataNotAvailable();
    }