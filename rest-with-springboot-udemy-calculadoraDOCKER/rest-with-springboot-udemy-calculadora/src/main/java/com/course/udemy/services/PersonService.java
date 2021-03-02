package com.course.udemy.services;

import com.course.udemy.converter.DozerConverter;
import com.course.udemy.converter.custom.PersonConverter;
import com.course.udemy.data.model.Person;
import com.course.udemy.data.vo.PersonVOV2;
import com.course.udemy.exception.ResourceNotFoundException;
import com.course.udemy.data.vo.PersonVO;
import com.course.udemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonConverter converter;

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        repository.delete(entity);
    }

    public PersonVOV2 createPersonV2(PersonVOV2 person) {
        Person entity = converter.convertVOToEntity(person);

        return converter.converterEntityToVO(repository.save(entity));
    }

    public PersonVO createPerson(PersonVO person) {
        Person entity = DozerConverter.parseObject(person, Person.class);

        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO updatePerson(PersonVO person) {
        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        return DozerConverter.parseObject(repository.save(entity), PersonVO.class);

    }

    public PersonVO findById(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    @Transactional
    public PersonVO disablePerson(Long id) {
        repository.disablePerson(id);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public Page<PersonVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertToPersonVO);
    }

    public Page<PersonVO> findPersonByName(String firstName, Pageable pageable){
        var page = repository.findPersonByName(firstName, pageable);
        return page.map(this::convertToPersonVO);
    }

    private PersonVO convertToPersonVO(Person entity){
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

}


