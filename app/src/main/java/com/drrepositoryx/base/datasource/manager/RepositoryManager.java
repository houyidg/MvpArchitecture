package com.drrepositoryx.base.datasource.manager;

import android.support.v4.util.ArrayMap;

import com.drrepositoryx.base.datasource.IBaseRepository;

import java.lang.ref.SoftReference;

public class RepositoryManager<T extends IBaseRepository> {
    /**
     * arraymap 存储数据量 不易过大
     */
    public ArrayMap<String, SoftReference<T>> map = new ArrayMap<>();

    public static volatile RepositoryManager instance;

    public static RepositoryManager getInstance(){
        if(instance==null){
            synchronized (RepositoryManager.class){
                if(instance==null){
                    instance = new RepositoryManager();
                }
            }
        }
        return instance;
    }

    public T getAssignRepository(Class<T> clazz ) throws IllegalAccessException, InstantiationException {
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
