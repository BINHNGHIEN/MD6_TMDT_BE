package com.example.md6_final_project_be.service.category;


import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.model.Category;
import com.example.md6_final_project_be.repository.ICategoryRepository;
import com.example.md6_final_project_be.security.useprinciple.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    UserDetailService userDetailService;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category save(Category category) {
        AppUser appUser = userDetailService.getCurrentUser();
        category.setAppUser(appUser);
        return categoryRepository.save(category);
    }

    @Override
    public Boolean existsByNameCategory(String nameCategory) {
        return categoryRepository.existsByNameCategory(nameCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Page<Category> findAllByNameCategoryContaining(String nameCategory, Pageable pageable) {
        return categoryRepository.findAllByNameCategoryContaining(nameCategory, pageable);
    }

    @Override
    public Page<Category> findByNameCategoryQuery(String nameCategory, Pageable pageable) {
        return categoryRepository.findByNameCategoryQuery(nameCategory,pageable);
    }

}
