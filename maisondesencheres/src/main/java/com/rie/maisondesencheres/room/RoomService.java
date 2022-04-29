package com.rie.maisondesencheres.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/*
 * La classe service. Contient un repertoire comme attribut et est utilisée par les controlleurs pour evoquer les
 * methodés qui interrogent la base de données. 
 */
@Service
@AllArgsConstructor
public class RoomService {
	
	private final RoomRepository room_repository;
	
	public List<Room> fetchAll() {
		return room_repository.fetchAll();
	}
	
	public Optional<Room> findById(Long room_id) {
		return room_repository.findById(room_id);
	}
	
	public void saveRoom(Room room) {
		room_repository.save(room);
	}
}
