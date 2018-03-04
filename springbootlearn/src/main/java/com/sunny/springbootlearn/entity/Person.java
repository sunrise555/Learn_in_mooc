package com.sunny.springbootlearn.entity;


import javafx.scene.shape.VLineTo;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * Created by Sunny on 2017/11/26.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    //限制最小年龄
    @Min(value = 18, message = "未成年禁止")
    private Integer age;

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

