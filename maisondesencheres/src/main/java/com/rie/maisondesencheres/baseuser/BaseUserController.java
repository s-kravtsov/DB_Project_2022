package com.rie.maisondesencheres.baseuser;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rie.maisondesencheres.room.Room;
import com.rie.maisondesencheres.sale.Bid;
import com.rie.maisondesencheres.sale.BidService;
import com.rie.maisondesencheres.sale.SaleController;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BaseUserController {
	
	BidService bid_service;
	
	@GetMapping("/profile")
	public String profileView(Model model) {
		BaseUser base_user = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("base_user", base_user);
		Collection<Bid> base_user_bids = bid_service.findByBase_user_id(base_user);
		model.addAttribute("base_user_bids", base_user_bids);
		return "baseUser";
	}
}
