package com.sunny.springbootlearn.enums;

/**
 * 对结果中的code和msg统一管理
 * Created by Sunny on 2017/11/27.
 */
public enum ResultEnum {
    UNKOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    PRIMARY_SCHOOL(100,"上小学"),
    MIDDLE_SCHOOL(101,"上初中");

    private Integer code;

    private  String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
