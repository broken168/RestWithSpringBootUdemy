package com.course.udemy.controller;

import com.course.udemy.data.vo.PersonVO;
import com.course.udemy.data.vo.PersonVOV2;
import com.course.udemy.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PagedResourcesAssembler<PersonVO> assembler;

    public PersonController() {
    }

    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO findById(@PathVariable("id") Long id){
        PersonVO personVo = personService.findById(id);
        personVo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVo;
    }

    @PatchMapping(path = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO disablePerson(@PathVariable("id") Long id){
        PersonVO personVo = personService.disablePerson(id);
        personVo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVo;
    }

    //@CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction)
    {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> personVOS = personService.findAll(pageable);
        personVOS.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        PagedModel<EntityModel<PersonVO>> resourcesAssembler = assembler.toModel(personVOS);

        return new ResponseEntity (resourcesAssembler, HttpStatus.OK);
    }

    @GetMapping(path = "/findPersonByName/{firstName}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction)
    {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> personVOS = personService.findPersonByName(firstName, pageable);
        personVOS.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        PagedModel<EntityModel<PersonVO>> resourcesAssembler = assembler.toModel(personVOS);

        return new ResponseEntity (resourcesAssembler, HttpStatus.OK);
    }

    @PostMapping(path = "/V2")
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return personService.createPersonV2(person);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person){
        PersonVO personVo = personService.createPerson(person);
        personVo.add(linkTo(methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
        return personVo;
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person){
        PersonVO personVo = personService.updatePerson(person);
        personVo.add(linkTo(methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
        return personVo;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        personService.delete(id);
        return ResponseEntity.ok().build();
    }
}
