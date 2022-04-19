package com.rie.maisondesencheres.sale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BidRepository extends JpaRepository<Bid, Long> {
	
	@Query(value = "select u.* from Bid u where u.base_user_id = :#{#base_user_id}", nativeQuery = true)
	public List<Bid> findByBase_user_id(Long base_user_id);
}
