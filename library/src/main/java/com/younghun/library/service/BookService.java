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
    public void updateBook(Book book) {
        // Fetch the existing Book by ID first
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Update fields (name, description, isbn)
        existingBook.setName(book.getName());
        existingBook.setDescription(book.getDescription());
        existingBook.setIsbn(book.getIsbn());

        // If relationships (authors, categories, publishers) are changed, update them
        if (book.getAuthors() != null) {
            existingBook.getAuthors().clear();
            existingBook.getAuthors().addAll(book.getAuthors());
        }

        if (book.getCategories() != null) {
            existingBook.getCategories().clear();
            existingBook.getCategories().addAll(book.getCategories());
        }

        if (book.getPublishers() != null) {
            existingBook.getPublishers().clear();
            existingBook.getPublishers().addAll(book.getPublishers());
        }

        // Save the updated Book object
        bookRepository.save(existingBook);
    }

    public void deleteBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow( () -> new RuntimeException("その本はありません"));
        bookRepository.deleteById(book.getId());}

}
