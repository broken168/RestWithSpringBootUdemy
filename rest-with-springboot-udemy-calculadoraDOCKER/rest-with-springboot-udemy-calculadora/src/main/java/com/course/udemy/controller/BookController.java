package com.course.udemy.controller;

import com.course.udemy.data.model.Book;
import com.course.udemy.data.vo.BookVO;
import com.course.udemy.data.vo.PersonVO;
import com.course.udemy.data.vo.PersonVOV2;
import com.course.udemy.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService service;


    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO findById(@PathVariable("id") Long id){
        BookVO bookVO = service.findById(id);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookVO> findAll(){
        List<BookVO> bookVOS = service.findAll();
        bookVOS.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return bookVOS;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO create(@RequestBody BookVO book){
        BookVO bookVO = service.createBook(book);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO update(@RequestBody BookVO book){
        BookVO bookVO = service.updateBook(book);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
