package com.course.udemy.services;

import com.course.udemy.converter.DozerConverter;
import com.course.udemy.data.model.Book;
import com.course.udemy.data.vo.BookVO;
import com.course.udemy.exception.ResourceNotFoundException;
import com.course.udemy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public BookVO findById(Long id){
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        repository.delete(entity);
    }


    public BookVO createBook(BookVO bookVO) {
        Book entity = DozerConverter.parseObject(bookVO, Book.class);

        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }

    public BookVO updateBook(BookVO bookVO) {
        Book entity = repository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());
        return DozerConverter.parseObject(repository.save(entity), BookVO.class);

    }


    public List<BookVO> findAll() {
        return DozerConverter.parseListObject(repository.findAll(), BookVO.class);
    }

}
