package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repositories.ProductRepository;

import utils.DateUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class ProductServiceImpl implements ProductService {
	ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	@Override
	public List<Product> getByKeyword(String keyword) {
		List<Product> productList = productRepository.findAll();
		List<Product> result = new ArrayList<>();
		for (Product product : productList) {
			if (product.getName().trim().toLowerCase().contains(keyword.toLowerCase())) {
				result.add(product);
			}
		}
		return result;
	}

	@Override
	public List<Product> getPageProducts(Integer pageNo, Integer pageSize, String sort) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sort));

		Page<Product> pagedResult = productRepository.findAll(paging);

		if (pagedResult.hasContent()) {
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
		updateProduct.setUpdated_time(new Date());
		Product productFromDb = productRepository.findById(id).get();
		System.out.println(productFromDb.toString());
		productFromDb.setName(updateProduct.getName());
		productFromDb.setPrice(updateProduct.getPrice());
		productFromDb.setDescription(updateProduct.getDescription());
		productFromDb.setUpdated_time(updateProduct.getUpdated_time());
		productRepository.save(productFromDb);
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public List<Product> getListProductBetweenDate(Date dateFrom, Date dateTo) {
		List<Product> listReturn = new ArrayList<>();
		List<Date> arrayDates = DateUtils.getDaysBetweenDates(dateFrom, dateTo);
		for (Date date : arrayDates) {
			List<Product> listOrderBydate = getListProductInDate(date);

			listReturn.addAll(listOrderBydate);
		}
		return listReturn;
	}

	@Override
	public List<Product> getListProductInDate(Date date) {
		List<Product> listFull = productRepository.findAll();
		List<Product> listReturn = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		for (Product product : listFull) {
			Date productTime = product.getCreated_time();
			String productTimeString = dateFormat.format(productTime);
			if (dateString.equals(productTimeString)) {
				listReturn.add(product);
			}
		}
		return listReturn;
	}

}
