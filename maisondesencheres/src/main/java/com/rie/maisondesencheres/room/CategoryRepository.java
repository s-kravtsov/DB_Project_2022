package com.rie.maisondesencheres.room;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * Le repertoire Catégorie. Est basé sur l'interface JpaRepository qui implemente les requêtes à la base de données.
 */
@Repository
@Transactional(readOnly = true)
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(value = "SELECT e.* FROM Category e", nativeQuery = true)
	public List<Category> fetchAll();
    
	public Optional<Category> findById(Long category_id);
}
