package com.rie.maisondesencheres.room;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/*
 * La classe service. Contient un repertoire comme attribut et est utilisée par les controlleurs pour evoquer les
 * methodés qui interrogent la base de données. 
 */
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
