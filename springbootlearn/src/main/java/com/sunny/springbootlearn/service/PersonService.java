package com.sunny.springbootlearn.service;

import com.sunny.springbootlearn.entity.Person;
import com.sunny.springbootlearn.enums.ResultEnum;
import com.sunny.springbootlearn.exception.PersonException;
import com.sunny.springbootlearn.properties.PersonProperties;
import com.sunny.springbootlearn.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 逻辑判断
 * Created by Sunny on 2017/11/27.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void getAge(Integer id) throws Exception{
        Person person = personRepository.findOne(id);
        Integer age = person.getAge();
        if (age < 10) {
            // 上小学
            throw new PersonException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age > 10 && age < 16) {
            // 上初中
            throw new PersonException(ResultEnum.MIDDLE_SCHOOL);
        }
    }
}
