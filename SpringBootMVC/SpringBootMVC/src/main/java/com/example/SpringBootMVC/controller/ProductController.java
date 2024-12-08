package com.example.SpringBootMVC.controller;

import com.example.SpringBootMVC.Repo.ProductRepo;
import com.example.SpringBootMVC.model.Product;
import com.example.SpringBootMVC.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
//
    @Autowired
    ProductRepo prepo;
    @Autowired
    ProductService productService;

        @GetMapping("/products")
        public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(prepo.findAll(), HttpStatus.OK);
        }
        @GetMapping("/product/{id}")
        public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
            Product p= prepo.findById(id).orElse(new Product(-1));
            if(p.getId()!=-1){
                return new ResponseEntity<>(p,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        @GetMapping("product/{productId}/image")
        ResponseEntity<byte[]> getImageById(@PathVariable("productId") int pid){
           Product product=  productService.getProductById(pid);
           return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
        }

        @PostMapping("/product")
        public ResponseEntity<?> setProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
            try {
                productService.saveProduct(product,imageFile);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (IOException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @PutMapping("/product/{id}")
        public ResponseEntity<String> updateProduct(@PathVariable("id") int id,@RequestPart Product product, @RequestPart MultipartFile imageFile){
            try {
                productService.saveProduct(product,imageFile);
                return new ResponseEntity<>("Updated",HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        @DeleteMapping("/product/{id}")
        public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
            Product product = productService.getProductById(id);
            if(product.getId()!=-1){
                productService.deleteProduct(id);
                return new ResponseEntity<>("Deleted",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/products/search")
        public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
            List<Product> products= productService.searchProdcts(keyword);
            return new ResponseEntity<>(products,HttpStatus.OK);
        }

}
