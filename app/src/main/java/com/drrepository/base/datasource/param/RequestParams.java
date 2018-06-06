package com.drrepository.base.datasource.param;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 */
public class RequestParams<T extends Object> implements IBaseParams {
    public enum TYPE {
        GET,
        POST,
        PUT,
        DELETE
    }

    public String getUrl() {
        return url;
    }

    public TYPE getMethod() {
        return method;
    }

    public String getUrlParameters() {
        String parameter=null;
        Set<Map.Entry<String, String>> entries = urlParameters.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(parameter==null){
                parameter=key+"="+value;
            }else{
                parameter+="&"+key+"="+value;
            }
        }
        return parameter;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public T getContent() {
        return content;
    }

    public boolean isNeedCache() {
        return needCache;
    }

    String url;
    RequestParams.TYPE method;
    Map<String, String> urlParameters;
    Map<String, String> headers;
    T content;
    boolean needCache;

    public RequestParams(String url, RequestParams.TYPE method) {
        this.url = url;
        this.method = method;
        urlParameters = new HashMap<>();
        headers = new HashMap<>();
    }

    public RequestParams(RequestParams.TYPE method) {
        this(null, method);
    }

    public RequestParams addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public RequestParams setHeader(Map<String, String> header) {
        if (header != null) {
            headers = header;
        }
        return this;
    }

    public RequestParams addUrlParameter(String key, String value) {
        urlParameters.put(key, value);
        return this;
    }

    public RequestParams setUrlParameter(Map<String, String> parameters) {
        if (parameters != null) {
            urlParameters = parameters;
        }
        return this;
    }

    public RequestParams setContent(T content) {
        this.content = content;
        return this;
    }

    public RequestParams setNeedCache(boolean needCache) {
        this.needCache = needCache;
        return this;
    }
}
