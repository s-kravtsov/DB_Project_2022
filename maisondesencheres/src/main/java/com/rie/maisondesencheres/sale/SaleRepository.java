package com.rie.maisondesencheres.sale;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(value = "select u.* from Sale u", nativeQuery = true)
	public Collection<Sale> fetchAll();

}
