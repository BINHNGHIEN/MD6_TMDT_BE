package com.example.md6_final_project_be.controller;


import com.example.md6_final_project_be.dto.response.ResponseMessage;
import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.model.Category;
import com.example.md6_final_project_be.security.useprinciple.UserDetailService;
import com.example.md6_final_project_be.service.category.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RequestMapping("auth/category")
@RestController
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    UserDetailService userDetailService;
    @GetMapping
    public ResponseEntity<?> pageCategory(@PageableDefault(sort = "nameCategory", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Category> categoryPage = categoryService.findAll(pageable);
        if(categoryPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        AppUser appUser = userDetailService.getCurrentUser();
        if(!appUser.getUsername().equals("Anonymous")){
            if(categoryService.existsByNameCategory(category.getNameCategory())){
                return new ResponseEntity<>(new ResponseMessage("no_name_category"), HttpStatus.OK);
            }
            if(category.getAvatarCategory()==null){
                category.setAvatarCategory("https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.png?alt=media&token=3511cf81-8df2-4483-82a8-17becfd03211");
            }
            categoryService.save(category);
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        }
       return new ResponseEntity<>(new ResponseMessage("create_failed"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if(!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteById(category.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success!"), HttpStatus.OK);
    }
    @GetMapping("/search/{nameCategory}")
    public ResponseEntity<?> searchByNameCategory(@PathVariable String nameCategory, @PageableDefault(sort = "nameCategory", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Category> categoryPage = categoryService.findByNameCategoryQuery(nameCategory, pageable);
        if(categoryPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }
@GetMapping("/{id}")
    public ResponseEntity<?> detailCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if(!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
}
@PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category){
    Optional<Category> category1 = categoryService.findById(id);
    if(!category1.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if(categoryService.existsByNameCategory(category.getNameCategory())){
        if(!category.getAvatarCategory().equals(category1.get().getAvatarCategory())){
            category1.get().setAvatarCategory(category.getAvatarCategory());
            categoryService.save(category1.get());
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("no_name_category"), HttpStatus.OK);
    }
    category1.get().setNameCategory(category.getNameCategory());
    category1.get().setAvatarCategory(category.getAvatarCategory());
    categoryService.save(category1.get());
    return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
}
    @GetMapping("/list")
    public ResponseEntity<?> getListCategory(){
        List<Category> categoryList = categoryService.findAll();
        if(categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
