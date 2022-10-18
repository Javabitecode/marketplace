package org.zuzex.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zuzex.exception.ShopAlreadyExistsException;
import org.zuzex.model.Category;
import org.zuzex.repository.CategoryRepository;
import org.zuzex.service.CategoryService;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.zuzex.constant.ExceptionConstants.CATEGORY_IS_EXISTS;
import static org.zuzex.constant.ExceptionConstants.CATEGORY_NOT_FOUND;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CategoryServiceIml implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND));
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Category createCategory(Category category) {
        boolean checkCategory = categoryRepository.findByName(category.getName()).isPresent();
        if (checkCategory)
            throw new ShopAlreadyExistsException(CATEGORY_IS_EXISTS);
        categoryRepository.persist(category);
        return category;
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
