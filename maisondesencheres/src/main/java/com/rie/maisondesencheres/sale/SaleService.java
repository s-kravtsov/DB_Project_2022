package com.rie.maisondesencheres.sale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rie.maisondesencheres.baseuser.BaseUser;
import com.rie.maisondesencheres.room.Room;

import lombok.AllArgsConstructor;

/*
 * La classe service. Contient un repertoire comme attribut et est utilisée par les controlleurs pour evoquer les
 * methodés qui interrogent la base de données. 
 */
@Service
@AllArgsConstructor
public class SaleService {
	private final SaleRepository sale_repository;
	
	public Optional<Sale> findById(Long sale_id) {
		return sale_repository.findById(sale_id);
	}
	
	public Collection<Sale> findByBase_user(Long base_user_id) {
		Collection<Sale> all_sales = sale_repository.fetchAll();
		ArrayList<Sale> base_user_sales = new ArrayList<Sale>();
		for(Sale sale : all_sales) {
			if (sale.getLot().getProduct().getBase_user().getId() == base_user_id) {
				base_user_sales.add(sale);
			}
		}	
		return base_user_sales;
	}
	
	public boolean placeBid(Sale sale, Bid bid, Boolean is_type_down) {
		
		BaseUser base_user = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println("\n\n\n\n\n");
		boolean closed = sale.getClosed();
		System.out.println("closed = " + closed);
		boolean self_bid = sale.getLot().getProduct().getBase_user().getId() == base_user.getId();
		System.out.println("self_bid = " + self_bid);
		boolean lower_then_initial = sale.getStart_price() > bid.getAmount() && !is_type_down;
		System.out.println("lower_then_initial = " + lower_then_initial);
		boolean lower_then_best = sale.getBestBid() != null && sale.getBestBid().getAmount() > bid.getAmount();
		System.out.println("lower_then_best = " + lower_then_best);
		boolean invalid_quantity = bid.getQuantity() > sale.getLot().getQuantity_to_sell() || bid.getQuantity() <= 0;
		System.out.println("invalid_quantity = " + invalid_quantity);
		boolean invalid_amount = bid.getAmount() <= 0;
		System.out.println("invalid_amount = " + invalid_amount);
		
		boolean multiple_offer_violation = false;
		if (!sale.getMultiple_offer()) {
			for(Bid sale_bid : sale.getBids()) {
				if(sale_bid.getBase_user().getId() == bid.getBase_user().getId()) {
					multiple_offer_violation = true;
				}
			}
		}
		System.out.println("multiple_offer_violation = " + multiple_offer_violation);
		System.out.println("\n\n\n\n\n");
		
		
		if (closed || self_bid || lower_then_initial || lower_then_best || multiple_offer_violation || invalid_quantity || invalid_amount) {
			return false;
		} else {
			sale.addBid(bid);
			sale_repository.save(sale);
			return true;
		}
		
	}
	
	public void saveSale(Sale sale) {
		sale_repository.save(sale);
	}
	
	
}
