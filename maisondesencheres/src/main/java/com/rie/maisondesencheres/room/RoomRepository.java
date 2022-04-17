package com.rie.maisondesencheres.room;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface RoomRepository extends JpaRepository<Room, Long> {
	
    @Query(value = "SELECT e.* FROM Room e", nativeQuery = true)
	public List<Room> fetchAll();

}
