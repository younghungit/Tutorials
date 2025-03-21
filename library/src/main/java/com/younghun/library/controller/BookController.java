package com.younghun.library.controller;

import com.younghun.library.model.Book;
import com.younghun.library.service.AuthorService;
import com.younghun.library.service.BookService;
import com.younghun.library.service.CategoryService;
import com.younghun.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/findAllBooks")
    public String findAllBooks(Model model) {
        List<Book> books =bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/findBookById/{id}")
    public String findBookById(Model model,@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/deleteBookById/{id}")
    public String deleteBookById(Model model,@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/updateBook/{id}")
    public String updateBookById(Model model,@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @PostMapping("/save-update/{id}")
    public String saveUpdateBookById(Model model,@PathVariable Long id, BindingResult bindingResult, Book book) {
        if (bindingResult.hasErrors()) {
            return "updateBook";
        }
        bookService.updateBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/add-book")
    public String addBook(Book book, Model model) {
        model.addAttribute("book", book);
        return "add-book";
    }

    @PostMapping("/save-book")
    public String saveBook(Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
        bookService.createBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/books";
    }



}
