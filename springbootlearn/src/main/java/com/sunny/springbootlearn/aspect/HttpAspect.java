package com.sunny.springbootlearn.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by Sunny on 2017/11/27.
 */
@Aspect
@Component
public class HttpAspect {
//    @Before("execution(public * com.sunny.springbootlearn.controller.PersonController.*(..))")
//    public void log() {
//        System.out.println("拦截");
//    }
//
//    @After("execution(public * com.sunny.springbootlearn.controller.PersonController.*(..))")
//    public void end() {
//        System.out.println("执行完毕");
//    }

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.sunny.springbootlearn.controller.PersonController.*(..))")
    public void pointcut() {

    }
    @Before("pointcut()")
    public void log() {
        logger.info("11111");
    }
    @After("pointcut()")
    public void end() {
        logger.info("22222");
    }
}
