package com.rie.maisondesencheres.room;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomService {
	
	private final RoomRepository room_repository;
	
	public List<Room> fetchAll() {
		return room_repository.fetchAll();
	}
}
