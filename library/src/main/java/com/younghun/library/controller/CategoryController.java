package com.younghun.library.controller;

import com.younghun.library.model.Category;
import com.younghun.library.service.BookService;
import com.younghun.library.service.CategoryService;
import com.younghun.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private PublisherService publisherService;

    @GetMapping("/categories")
    public String findAllCategories(Model model) {
        List<Category> categorys = categoryService.findAllCategories();
        model.addAttribute("categories", categorys);
        return "categories";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategory(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "まずは関連書籍の削除をお願いします");
            return "redirect:/categories";
        }
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }

    @GetMapping("/update-category/{id}")
    public String updatecategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "update-category";
    }

    @PostMapping("/update-save-category/{id}")
    public String savecategory(@PathVariable Long id, Category category,
                             BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "update-category";
        }
        categoryService.updateCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/add-category")
    public String addCategory(Category category, Model model) {
        model.addAttribute("category", category);
        return "add-category";
    }

    @PostMapping("/save-category")
    public String saveNewCategory(Category category,
                                BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "add-category";
        }
        categoryService.createCategory(category);
        return "redirect:/categories";
    }
}
