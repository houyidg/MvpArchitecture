package com.drrepositoryx.base.datasource.manager;

import android.support.v4.util.ArrayMap;

import com.drrepositoryx.base.datasource.IBaseDataSource;
import com.drrepositoryx.main.datasource.remote.IRemoteCoinDataSource;

import java.lang.ref.SoftReference;

public class DataSourceManager<T extends IBaseDataSource> {
    /**
     * arraymap 存储数据量 不易过大
     */
    public ArrayMap<String, SoftReference<T>> map = new ArrayMap<>();

    public static volatile DataSourceManager instance;

    public static DataSourceManager getInstance(){
        if(instance==null){
            synchronized (DataSourceManager.class){
                if(instance==null){
                    instance = new DataSourceManager();
                }
            }
        }
        return instance;
    }

    public T getAssignDataSource(Class<T> clazz ) throws IllegalAccessException, InstantiationException {
        String simpleName = clazz.toString();
        SoftReference<T> reference = map.get(simpleName);
        T model=null;
        if(reference!=null && reference.get()!=null){
            model = reference.get();
        }else{
            model = clazz.newInstance();
            map.put(simpleName, new SoftReference(model));
        }
        return model;
    }
}
