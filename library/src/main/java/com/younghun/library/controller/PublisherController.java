package com.younghun.library.controller;

import com.younghun.library.service.AuthorService;
import com.younghun.library.service.BookService;
import com.younghun.library.service.CategoryService;
import com.younghun.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PublisherController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;
}
