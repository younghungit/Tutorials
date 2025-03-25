package com.younghun.library.controller;

import com.younghun.library.model.Author;
import com.younghun.library.model.Book;
import com.younghun.library.model.Category;
import com.younghun.library.model.Publisher;
import com.younghun.library.service.AuthorService;
import com.younghun.library.service.BookService;
import com.younghun.library.service.CategoryService;
import com.younghun.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/books")
    public String findAllBooks(Model model) {
        List<Book> books =bookService.findAllBooks();
        model.addAttribute("books", books);

        return "books";
    }

    @GetMapping("/bookinfo/{id}")
    public String findBookById(Model model,@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "bookinfo";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBookById(Model model,@PathVariable Long id) {
        bookService.deleteBookById(id);
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    @GetMapping("/update-book/{id}")
    public String updateBookById(Model model,@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "update-book";
    }

    @PostMapping("/save-update/{id}")
    public String saveUpdateBookById(@PathVariable Long id,Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "update-book";
        }
        bookService.updateBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "bookinfo";
    }






    @GetMapping("/add-book")
    public String addBook(Book book, Model model) {
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("categories", categoryService.findAllCategories());
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
