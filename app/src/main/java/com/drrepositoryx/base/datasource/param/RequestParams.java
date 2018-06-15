package com.drrepositoryx.base.datasource.param;


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
    public enum Priority{
        FIRST,
        GENERAL
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    String url;
    RequestParams.TYPE method;
    Priority priority = Priority.GENERAL;
    Map<String, String> urlParameters;
    Map<String, String> headers;
    static Map<String, String> commonHeaders=new HashMap<>();

    T content;
    boolean needCache;

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
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public static void addParamsToCommonHeader(String key,String value){
        commonHeaders.put(key,value);
    }
    public static void removeParamsToCommonHeader(String key){
        commonHeaders.remove(key);
    }

    public static void setCommonHeader( Map<String, String> headers){
        if(headers!=null)
        commonHeaders = headers;
    }

    public Map<String, String> getHeaders() {
        headers.putAll(commonHeaders);
        return headers;
    }

    public T getContent() {
        return content;
    }

    public boolean isNeedCache() {
        return needCache;
    }


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

    @Override
    public String getTag() {
        return url;
    }
}
