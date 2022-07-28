package com.example.md6_final_project_be.controller;

import com.example.md6_final_project_be.model.CartElement;
import com.example.md6_final_project_be.model.Product;
import com.example.md6_final_project_be.model.User;
import com.example.md6_final_project_be.security.useprinciple.UserDetailService;
import com.example.md6_final_project_be.service.*;
import com.example.md6_final_project_be.service.product.ProductServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartElementController {
    @Autowired
    private CartElementServiceImpl cartElementService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ProductServiceIMPL productService;
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping
    public ResponseEntity<Iterable<CartElement>> showCart() {
        User user = userDetailService.getCurrentUser();
        return new ResponseEntity<>(cartElementService.findAllByUser(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartElement> addProductToCartElement(@RequestBody CartElement cart) {
        User user = userDetailService.getCurrentUser();
        cart.setUser(user);
        return new ResponseEntity<>(cartElementService.save(cart), HttpStatus.CREATED);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartElement> findCartElementById(@PathVariable Long cartId) {
        return new ResponseEntity<>(cartElementService.findById(cartId).get(), HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<CartElement> deleteCartElement(@PathVariable Long cartId) {
        Optional<CartElement> cartElement = cartElementService.findById(cartId);
        if (cartElement.isPresent()) {
            cartElementService.remove(cartId);
            return new ResponseEntity<>(cartElement.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<CartElement> deleteAllCartElementByUserId(@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            cartElementService.deleteAllByUser(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reduce/{cartId}")
    public ResponseEntity<CartElement> reduceQuantityOfCartElement(@PathVariable Long cartId) {
        Optional<CartElement> cartElement1 = cartElementService.findById(cartId);
        CartElement cartElement2 = new CartElement(cartElement1.get().getProduct(), cartElement1.get().getUser(), cartElement1.get().getQuantity() - 1);
        cartElement2.setId(cartId);
        if (cartElement1.isPresent()) {
            cartElementService.save(cartElement2);
            return new ResponseEntity<>(cartElement2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/increase/{cartId}")
    public ResponseEntity<CartElement> increaseQuantityOfCartElement(@PathVariable Long cartId) {
        Optional<CartElement> cartElement1 = cartElementService.findById(cartId);
        CartElement cartElement2 = new CartElement(cartElement1.get().getProduct(), cartElement1.get().getUser(), cartElement1.get().getQuantity() + 1);
        cartElement2.setId(cartId);
        if (cartElement1.isPresent()) {
            cartElementService.save(cartElement2);
            return new ResponseEntity<>(cartElement2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
