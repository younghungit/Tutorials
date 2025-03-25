package com.younghun.library.controller;

import com.younghun.library.model.Author;
import com.younghun.library.service.AuthorService;
import com.younghun.library.service.BookService;
import com.younghun.library.service.CategoryService;
import com.younghun.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/authors")
    public String findAllAuthors(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/delete-author/{id}")
    public String deleteAuthor(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try{authorService.deleteAuthorById(id);}
        catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "まずは関連書籍の削除をお願いします");
            return "redirect:/authors";
        }
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    @GetMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Model model) {
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        return "update-author";
    }

    @PostMapping("/update-save-author/{id}")
    public String saveAuthor(@PathVariable Long id, Author author,
                             BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "update-author";
        }
        authorService.updateAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/add-author")
    public String addAuthor(Author author, Model model) {
        model.addAttribute("author", author);
        return "add-author";
    }

    @PostMapping("/save-author")
    public String saveNewAuthor(Author author,
                                BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "add-author";
        }
        authorService.createAuthor(author);
        return "redirect:/authors";
    }

}
