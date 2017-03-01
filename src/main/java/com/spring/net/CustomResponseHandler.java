package com.spring.net;

/**
 * Created by maobg on 2016/1/29.
 */
public interface CustomResponseHandler<T> {
    public void handleResponse(T response);
}
