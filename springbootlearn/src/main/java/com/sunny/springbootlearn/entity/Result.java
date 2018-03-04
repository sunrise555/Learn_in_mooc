package com.sunny.springbootlearn.entity;

/**
 * 返回到前端的数据格式约束
 * Created by Sunny on 2017/11/27.
 */
public class Result<T> {
    /*状态码*/
    private int code;
    /*状态信息*/
    private String msg;
    /*返回的数据*/
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
