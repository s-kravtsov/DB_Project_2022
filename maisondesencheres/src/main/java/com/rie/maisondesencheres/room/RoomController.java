package com.rie.maisondesencheres.room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rie.maisondesencheres.baseuser.BaseUser;
import com.rie.maisondesencheres.product.Product;
import com.rie.maisondesencheres.room.Room;
import com.rie.maisondesencheres.sale.Lot;
import com.rie.maisondesencheres.sale.Sale;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@AllArgsConstructor
public class RoomController {
	
	private RoomService room_service;
	private final CategoryService category_service;
	
	
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
		room.refreshSales();
		room_service.saveRoom(room);
		model.addAttribute("room", room);
		return "room";
	}
	
	@PostMapping("/postSale")
	public String publishSale(HttpServletRequest request) {
		
		System.out.println("\n\n\n\n");
		Long category_id = Long.parseLong(request.getParameter("category_id"));
		String product_name = request.getParameter("product_name");
		Long benefice_price = Long.parseLong(request.getParameter("benefice_price"));
		Long stock = Long.parseLong(request.getParameter("stock"));
		BaseUser poster = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long start_price = Long.parseLong(request.getParameter("start_price"));
		Boolean multiple_offer = request.getParameter("multiple_offer").equals("1");

		Boolean limited = request.getParameter("limited").equals("1");

		LocalDateTime end_tstamp = null;
		if(request.getParameter("end_tstamp") != "") {
			end_tstamp = LocalDateTime.parse(request.getParameter("end_tstamp"), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
		}
		Boolean revocable = request.getParameter("revocable").equals("1");

		Room room = room_service.findById(category_id).get();
		Category category = category_service.findById(category_id).get();
		Product product = new Product(product_name, benefice_price, category, stock, poster);
		Lot lot = new Lot(product, stock);
		
		
		Sale sale = new Sale(lot, start_price, multiple_offer, limited, LocalDateTime.now().plusHours(2), end_tstamp, revocable, false);

		room.addSale(sale);
		room_service.saveRoom(room);
		return "salePosted";
		
	}

}
