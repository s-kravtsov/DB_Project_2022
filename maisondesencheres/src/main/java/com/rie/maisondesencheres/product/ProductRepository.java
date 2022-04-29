package com.rie.maisondesencheres.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rie.maisondesencheres.sale.Bid;


/*
 * Le repertoire Produit. Est basé sur l'interface JpaRepository qui implemente les requêtes à la base de données.
 */
@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT e.* FROM Product e", nativeQuery = true)
	public List<Product> fetchAll();
	
	@Query(value = "select u.* from Product u where u.base_user_id = :#{#base_user_id}", nativeQuery = true)
	public List<Product> findByBase_user_id(Long base_user_id);
}
