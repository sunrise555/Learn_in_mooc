package com.sunny.springbootlearn.handler;

import com.sunny.springbootlearn.entity.Result;
import com.sunny.springbootlearn.exception.PersonException;
import com.sunny.springbootlearn.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sunny on 2017/11/27.
 */
//统一异常捕获
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handlerExcep(Exception e) {
        if (e instanceof PersonException) {
            PersonException personException = (PersonException)e;
            return ResultUtils.fail(personException.getCode(),e.getMessage());
        }
        return ResultUtils.fail(-1, e.getMessage());
    }
}


