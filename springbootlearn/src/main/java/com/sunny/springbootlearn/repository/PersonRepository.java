package com.sunny.springbootlearn.repository;

import com.sunny.springbootlearn.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 该类完成数据库的增删改查功能
 * Created by Sunny on 2017/11/26.
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    // 根据年龄来查询，可能匹配多个记录
    public List<Person> findByAge(Integer age);
}
