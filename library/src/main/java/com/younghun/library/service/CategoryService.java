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
}
