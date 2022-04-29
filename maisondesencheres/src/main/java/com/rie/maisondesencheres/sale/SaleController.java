package com.rie.maisondesencheres.sale;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rie.maisondesencheres.baseuser.BaseUser;
import com.rie.maisondesencheres.product.Product;
import com.rie.maisondesencheres.room.Category;
import com.rie.maisondesencheres.room.CategoryService;
import com.rie.maisondesencheres.room.Room;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SaleController {
	
	private final SaleService sale_service;
	private final CategoryService category_service;
	
	@PostMapping("/sale")
	public String saleUpdate(HttpServletRequest request, Model model) {
		
		Sale sale = sale_service.findById(Long.parseLong(request.getParameter("sale_code"))).get();
		BaseUser base_user = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long amount = Long.parseLong(request.getParameter("amount"));
		Long quantity = Long.parseLong(request.getParameter("quantity"));
		
		Bid bid = new Bid(base_user, false, amount, LocalDateTime.now().plusHours(2), sale, quantity);
		
		boolean is_type_down = request.getParameter("room_type") == "DOWN";
		
		boolean bid_success = sale_service.placeBid(sale, bid, is_type_down);
		
		if(bid_success) {
			return "bidConfirmation";
		} else {
			return "bidError";
		}
		
	}
	
	@GetMapping("/saleDetail")
	public String saleDetail(HttpServletRequest request, Model model) {
		
		Sale sale = sale_service.findById(Long.parseLong(request.getParameter("sale_code"))).get();
		BaseUser base_user = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (sale.getLot().getProduct().getBase_user().getId() != base_user.getId()) {
			return "saleError";
		}
		
		model.addAttribute("sale", sale);
		
		return "saleDetail";
	}
	
	@GetMapping("/sale/new")
	public String publishSaleForm(Model model) {
		Collection<Category> categories = category_service.fetchAll();
		model.addAttribute("categories", categories);
		return "newSale";
		
	}

}
