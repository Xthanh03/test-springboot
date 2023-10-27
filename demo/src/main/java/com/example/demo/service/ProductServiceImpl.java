package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
	    this.productRepository = productRepository;
	}

	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	
	@Override
	public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sort) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sort));
		 
        Page<Product> pagedResult = productRepository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
	}
	
	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product insert(Product newProduct) {
		return productRepository.save(newProduct);
	}

	@Override
	public void updateProduct(Long id, Product updateProduct) {
		Product productFromDb = productRepository.findById(id).get();
        System.out.println(productFromDb.toString());
        productFromDb.setName(updateProduct.getName());
        productFromDb.setPrice(updateProduct.getPrice());
        productFromDb.setDescription(updateProduct.getDescription());
        productRepository.save(productFromDb);
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
		
	}

}
