package com.rie.maisondesencheres.baseuser;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rie.maisondesencheres.room.Room;
import com.rie.maisondesencheres.room.RoomService;
import com.rie.maisondesencheres.sale.Bid;
import com.rie.maisondesencheres.sale.BidService;
import com.rie.maisondesencheres.sale.Sale;
import com.rie.maisondesencheres.sale.SaleController;
import com.rie.maisondesencheres.sale.SaleService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BaseUserController {
	
	BidService bid_service;
	SaleService sale_service;
	RoomService room_service;
	
	@GetMapping("/profile")
	public String profileView(Model model) {
		Collection<Room> rooms = room_service.fetchAll();
		for(Room room : rooms) {
			room.refreshSales();
		}
		BaseUser base_user = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("base_user", base_user);
		Collection<Bid> base_user_bids = bid_service.findByBase_user_id(base_user);
		model.addAttribute("base_user_bids", base_user_bids);
		Collection<Sale> base_user_sales = sale_service.findByBase_user(base_user.getId());
		model.addAttribute("base_user_sales", base_user_sales);
		return "baseUser";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
	   
		return "global";
	}
}
