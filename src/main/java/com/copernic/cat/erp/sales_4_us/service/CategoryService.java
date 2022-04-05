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

    //Llistem totes les categories
    @Transactional
    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }

    //Eliminem la categoria seleccionada
    @Transactional
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    //Guardem la categoria
    @Transactional
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    //Busquem la categoria pel seu id
    @Transactional(readOnly = true)
    public Category findCategory(Category category) {
        return categoryRepository.findById(category.getId()).orElse(null);
    }
}
