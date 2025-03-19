package com.younghun.library.service;

import com.younghun.library.model.Book;
import com.younghun.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService{
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){return bookRepository.findAll();}
    public Book findBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow( () -> new RuntimeException("その本はありません"));
        return book;
    }
    public List<Book> findBookByName(String name){return bookRepository.findAllByName(name);}
}
