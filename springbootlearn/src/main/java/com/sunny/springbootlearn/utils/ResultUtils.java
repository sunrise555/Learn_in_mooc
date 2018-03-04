package com.sunny.springbootlearn.utils;

import com.sunny.springbootlearn.entity.Result;

/**
 * Result对应的工具类
 * Created by Sunny on 2017/11/27.
 */
public class ResultUtils {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result successWithOutData(Object object) {
        return success(null);
    }

    public static Result fail(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
