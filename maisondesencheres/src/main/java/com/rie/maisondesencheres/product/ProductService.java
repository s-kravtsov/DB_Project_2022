package com.rie.maisondesencheres.product;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rie.maisondesencheres.baseuser.BaseUser;
import com.rie.maisondesencheres.sale.Bid;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
	
	private final ProductRepository product_repository;
	
	public List<Product> fetchAll() {
		return product_repository.fetchAll();
	}
	
	public Collection<Product> findByBase_user_id(BaseUser base_user) {
		return product_repository.findByBase_user_id(base_user.getId());
	}
}
