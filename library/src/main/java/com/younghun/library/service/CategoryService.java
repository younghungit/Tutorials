package com.younghun.library.service;

import com.younghun.library.model.Category;
import com.younghun.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> findAllCategories(){return categoryRepository.findAll();}
    public Category findCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("その分類はありません。"));
        return category;
    }

    public void createCategory(Category category){categoryRepository.save(category);}
    public void updateCategory(Category category){categoryRepository.save(category);}
    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("その分類はありません。"));
        categoryRepository.deleteById(category.getId());

    }

}
