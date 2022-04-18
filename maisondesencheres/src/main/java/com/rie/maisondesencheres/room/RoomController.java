package com.rie.maisondesencheres.room;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.rie.maisondesencheres.room.Room;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@AllArgsConstructor
public class RoomController {
	
	private RoomService room_service;
	
	
	@GetMapping("/")
	public String globalView(/*HttpServletRequest request, ModelMap model_map*/Model model) {
		/*
		String name = request.getParameter("name");
		model_map.put("name", name);
		*/
		List<Room> rooms = room_service.fetchAll();
		model.addAttribute("rooms", rooms);
		return "global";
	}
	
	@GetMapping("/room")
	public String roomView(HttpServletRequest request, Model model) {
		String room_code = request.getParameter("room_code");
		Room room = room_service.findById(Long.parseLong(room_code)).get();
		model.addAttribute("room", room);
		return "room";
	}

}
