package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {

	List<Product> getAllProducts();

	List<Product> getByKeyword(String keyword);

	List<Product> getPageProducts(Integer pageNo, Integer pageSize, String sort);

	Product getProductById(Long id);

	Product insert(Product newProduct);

	void updateProduct(Long id, Product updateProduct);

	void deleteProduct(Long productId);

	List<Product> getListProductBetweenDate(Date dateFrom, Date dateTo);
	
	List<Product> getListProductInDate(Date date);
}
