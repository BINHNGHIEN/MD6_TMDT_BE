package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.dto.response.ResponseMessage;
import com.example.md6_final_project_be.model.AppUser;
import com.example.md6_final_project_be.model.Product;
import com.example.md6_final_project_be.model.Role;
import com.example.md6_final_project_be.service.product.IProductService;
import com.example.md6_final_project_be.service.product.ProductServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductServiceIMPL productService;

    // tìm theo tên gần đúng
    @GetMapping("/search/{name}")
    public ResponseEntity<?> findUserByName(@PathVariable String name) {
        Iterable<Product> products = productService.findByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // up ảnh
    @PostMapping("/upImg/{id}")
    public ResponseEntity<?> upImg(@RequestBody String img, @PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        product.get().setAvatarProduct(img);
        productService.save(product.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // chỉnh sửa sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@RequestBody Product product, @PathVariable Long id){
        Optional<Product> product1 = productService.findById(id);
        product.setId(product1.get().getId());
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // tìm kiếm theo bộ lọc
    @GetMapping("/{name}")
    public ResponseEntity<?> searchByCategory(@PathVariable String name){

    }
}

