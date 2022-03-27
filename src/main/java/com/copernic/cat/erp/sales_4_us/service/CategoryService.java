package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Category;
import com.copernic.cat.erp.sales_4_us.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }

    @Transactional
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Transactional
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Category findCategory(Category category) {
        return categoryRepository.findById(category.getId()).orElse(null);
    }
}
