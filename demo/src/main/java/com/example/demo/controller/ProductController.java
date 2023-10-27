package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	// Get all product
	@GetMapping("")
	public ResponseEntity<List<Product>> getProducts() {
		LOGGER.info("Getting All Products");
		List<Product> products = productService.getProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// Get paging product
	@GetMapping("/page")
	public ResponseEntity<List<Product>> getPageProducts(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		LOGGER.info("Getting Page Products");
		List<Product> list = productService.getAllProducts(pageNo, pageSize, sortBy);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	// Get product by id
	@GetMapping({ "/{productId}" })
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
		LOGGER.info("Getting the Product, Id:" + productId);
		return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
	}

	// Create product
	@PostMapping
	public ResponseEntity<Product> insertProduct(@Valid @RequestBody Product product) {
		LOGGER.info("Creating a Product");
		Product product1 = productService.insert(product);
		return new ResponseEntity<>(product1, HttpStatus.CREATED);
	}

	// Update product by id
	@PutMapping({ "/{productId}" })
	public ResponseEntity<Product> updateProduct(@Valid @PathVariable("productId") Long productId,
			@RequestBody Product product) {
		productService.updateProduct(productId, product);
		LOGGER.info("Updated the Product, Id:" + productId);
		return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
	}

	// Delete product by id
	@DeleteMapping({ "/{productId}" })
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId) {
		LOGGER.info("Deleting the Product, Id:" + productId);
		productService.deleteProduct(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}