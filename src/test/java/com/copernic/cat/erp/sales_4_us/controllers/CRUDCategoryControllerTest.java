package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Category;
import com.copernic.cat.erp.sales_4_us.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CRUDCategoryControllerTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createCategory() {
        Category category = new Category();

        category.setName("Shoes");
        category.setDescription("Good shoes");
        category.setId(1);

        Category savedCategory = categoryRepository.save(category);
        Assert.notNull(savedCategory, "La categoria s'ha desat correctament");
    }

    @Test
    public void findCategoryById() {
        Integer id = 2;
        Optional<Category> category = categoryRepository.findById(id);

        System.out.println(category);

        Assert.isTrue(category.get().getId() == id);
    }

    @Test
    public void listAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();

        for (Category category : categories) {
            System.out.println(category);
        }

        Assert.notEmpty(categories, "Hi han categories a la BBDD.");
    }

    @Test
    public void deleteCategory() {
        Integer id = 2;

        categoryRepository.deleteById(2);

        boolean deletedCategory = categoryRepository.findById(id).isEmpty();

        Assert.isTrue(deletedCategory, "S'ha eliminat la categoria.");
    }

}