package com.rie.maisondesencheres.room;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
	
	private final CategoryRepository category_repository;
	
	public List<Category> fetchAll() {
		return category_repository.fetchAll();
	}
	
	public Optional<Category> findById(Long category_id) {
		return category_repository.findById(category_id);
	}
}
