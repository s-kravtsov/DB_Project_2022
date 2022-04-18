package com.rie.maisondesencheres.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rie.maisondesencheres.room.Room;
import com.rie.maisondesencheres.room.RoomRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {
	
	private final StockRepository stock_repository;
	
	public List<Stock> fetchAll() {
		return stock_repository.fetchAll();
	}

}
