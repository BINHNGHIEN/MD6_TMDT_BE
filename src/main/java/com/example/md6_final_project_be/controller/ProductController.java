package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.model.Product;
import com.example.md6_final_project_be.service.ProductServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @PostMapping("/addImg/{id}")
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
    @GetMapping("/searchCategory/{name}")
    public ResponseEntity<?> searchProductByCategory(@PathVariable String name){
        Iterable<Product> products = productService.findByCategory(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // thêm sản phẩm
    @PostMapping()
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    // xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

