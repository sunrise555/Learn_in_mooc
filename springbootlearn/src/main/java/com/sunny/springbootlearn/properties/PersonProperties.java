package com.sunny.springbootlearn.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Sunny on 2017/11/26.
 */
@Component
//匹配前缀为person的属性
@ConfigurationProperties(prefix = "person")
public class PersonProperties {

    private String name;

    private Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
