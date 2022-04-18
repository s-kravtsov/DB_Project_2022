package com.rie.maisondesencheres.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rie.maisondesencheres.room.Room;

@Repository
@Transactional(readOnly = true)
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
