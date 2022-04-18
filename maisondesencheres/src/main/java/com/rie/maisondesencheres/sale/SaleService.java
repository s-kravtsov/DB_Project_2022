package com.rie.maisondesencheres.sale;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rie.maisondesencheres.room.Room;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleService {
	private final SaleRepository sale_repository;
	
	public Optional<Sale> findById(Long sale_id) {
		return sale_repository.findById(sale_id);
	}
	
	public void placeBid(Sale sale, Bid bid) {
		sale.addBid(bid);
		sale_repository.save(sale);
	}
}
