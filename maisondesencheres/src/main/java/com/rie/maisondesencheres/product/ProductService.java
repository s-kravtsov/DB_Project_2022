package com.rie.maisondesencheres.product;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
	
	private final ProductRepository product_repository;
	
	public List<Product> fetchAll() {
		return product_repository.fetchAll();
	}
}
