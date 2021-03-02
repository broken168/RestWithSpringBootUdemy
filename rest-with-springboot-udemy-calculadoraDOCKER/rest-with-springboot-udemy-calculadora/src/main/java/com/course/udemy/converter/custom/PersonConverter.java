package com.course.udemy.converter.custom;

import com.course.udemy.data.model.Person;
import com.course.udemy.data.vo.PersonVOV2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonConverter {

    public PersonVOV2 converterEntityToVO(Person person){
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date().toString());
        vo.setFirstName(person.getFirstName());
        vo.setGender(person.getGender());
        vo.setLastName(person.getLastName());
        return vo;
    }

    public Person convertVOToEntity(PersonVOV2 person){
        Person entity = new Person();
        entity.setLastName(person.getLastName());
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setGender(person.getGender());
        return entity;
    }
}
