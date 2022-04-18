package com.rie.maisondesencheres.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rie.maisondesencheres.room.Room;

@Repository
@Transactional(readOnly = true)
public interface StockRepository extends JpaRepository<Stock, Long> {
	
	@Query(value = "SELECT e.* FROM Stock e", nativeQuery = true)
	public List<Stock> fetchAll();

}
