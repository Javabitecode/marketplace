package org.zuzex.service;

import org.zuzex.model.Category;
import org.zuzex.service.impl.CategoryServiceIml;

import java.util.List;

public interface CategoryService {

    Category getCategoryByName(String name);

    Category getCategoryById(Long id);

    List<Category> getAllCategory();

    Category createCategory(Category category);

    void deleteCategory(Long id);
}
