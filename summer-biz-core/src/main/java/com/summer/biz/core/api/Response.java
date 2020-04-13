package com.summer.biz.core.api;

public interface Response<T> {

    boolean isSuccess();

    String errCode();

    String errName();

    String errMsg();

    T getResponseData();
}
