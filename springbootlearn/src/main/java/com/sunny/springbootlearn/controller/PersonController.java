package com.sunny.springbootlearn.controller;

import com.sunny.springbootlearn.entity.Person;
import com.sunny.springbootlearn.entity.Result;
import com.sunny.springbootlearn.repository.PersonRepository;
import com.sunny.springbootlearn.service.PersonService;
import com.sunny.springbootlearn.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.List;

/**
 * 对Person表的增删改查
 * Created by Sunny on 2017/11/26.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    /**
     * 获取person列表
     * @return
     */
    @GetMapping(value = "/persons")
    public List<Person> personList() {
        return personRepository.findAll();
    }

    /**
     * 增加一条记录，并返回该记录对象
     * 当年龄小于18时不添加
     * @return
     */
    @PostMapping(value = "/persons")
    public Result<Person> addPerson(@Valid Person person, BindingResult bindingResult) {
        //bindingResult包含验证以后的结果
        if (bindingResult.hasErrors()) {
            return ResultUtils.fail(1,
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(personRepository.save(person));

    }

    @GetMapping("/persons/{age}")
    public List<Person> queryByAge(@PathVariable("age") Integer age) {
        return personRepository.findByAge(age);
    }

    @GetMapping(value = "/persons/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
        personService.getAge(id);
    }
}
