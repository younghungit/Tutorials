package com.younghun.library.service;

import com.younghun.library.model.Book;
import com.younghun.library.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService{
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks(){return bookRepository.findAll();}
    public Book findBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow( () -> new RuntimeException("その本はありません"));
        return book;
    }
    public List<Book> findBookByName(String name){return bookRepository.findAllByName(name);}
    public void createBook(Book book){bookRepository.save(book);}
    public void updateBook(Book book){bookRepository.save(book);}
    public void deleteBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow( () -> new RuntimeException("その本はありません"));
        bookRepository.deleteById(book.getId());}

}
