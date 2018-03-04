package com.sunny.springbootlearn.exception;

import com.sunny.springbootlearn.enums.ResultEnum;

/**
 * Created by Sunny on 2017/11/27.
 */
public class PersonException extends RuntimeException {
    //spring只对抛出的运行时异常实现事务回滚
    private Integer code;

    public PersonException (ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
