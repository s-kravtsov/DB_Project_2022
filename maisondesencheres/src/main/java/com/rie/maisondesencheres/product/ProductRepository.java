package com.rie.maisondesencheres.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT e.* FROM Product e", nativeQuery = true)
	public List<Product> fetchAll();
}
