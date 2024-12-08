package com.example.SpringBootMVC.service;

import com.example.SpringBootMVC.Repo.ProductRepo;
import com.example.SpringBootMVC.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public void saveProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        productRepo.save(product);
    }

    public Product getProductById(int pid) {
        return productRepo.findById(pid).orElse(new Product(-1));
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProdcts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
