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
		
		
		boolean closed = sale.getClosed();

		boolean self_bid = sale.getLot().getProduct().getBase_user().getId() == base_user.getId();
		
		boolean lower_then_initial = sale.getStart_price() > bid.getAmount() && !is_type_down;
		
		boolean lower_then_best = sale.getBestBid() != null && sale.getBestBid().getAmount() > bid.getAmount();
		
		boolean invalid_quantity = bid.getQuantity() > sale.getLot().getQuantity_to_sell() || bid.getQuantity() <= 0;
		
		boolean invalid_amount = bid.getAmount() <= 0;
		
		
		boolean multiple_offer_violation = sale.getMultiple_offer();
		if (!multiple_offer_violation) {
			for(Bid sale_bid : sale.getBids()) {
				if(sale_bid.getBase_user().getId() == bid.getBase_user().getId()) {
					multiple_offer_violation = true;
				}
			}
		}
		
		
		
		
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
