package com.rie.maisondesencheres.sale;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rie.maisondesencheres.baseuser.BaseUser;
import com.rie.maisondesencheres.room.Room;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SaleController {
	
	private final SaleService sale_service;
	
	@PostMapping("/sale")
	public String saleUpdate(HttpServletRequest request, Model model) {
		
		Sale sale = sale_service.findById(Long.parseLong(request.getParameter("sale_code"))).get();
		BaseUser base_user = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long amount = Long.parseLong(request.getParameter("amount"));
		
		Bid bid = new Bid(base_user, false, amount, LocalDateTime.now());
		
		sale_service.placeBid(sale, bid);
		return "bidConfirmation";
	}

}
