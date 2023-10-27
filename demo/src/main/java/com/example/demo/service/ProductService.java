package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {

	List<Product> getProducts();

	List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sort);

	Product getProductById(Long id);

	Product insert(Product newProduct);

	void updateProduct(Long id, Product updateProduct);

	void deleteProduct(Long productId);
}
