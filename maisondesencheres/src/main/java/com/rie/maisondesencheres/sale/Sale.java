package com.rie.maisondesencheres.sale;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rie.maisondesencheres.baseuser.BaseUser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Sale {
	
	@SequenceGenerator(name = "id_Sequence_sale", sequenceName = "ID_SEQ_sale")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_sale")
	private Long id;
	@OneToOne(cascade = {CascadeType.MERGE})
	private Lot lot;
	private Long start_price;
	private Boolean multiple_offer;
	private Boolean limited;
	private LocalDateTime start_tstamp; 
	private LocalDateTime end_tstamp; 
	private Boolean revocable;
	private Boolean closed;
	private LocalDateTime last_refresh;
	
	@OneToMany(cascade = { CascadeType.ALL })
	private Collection<Bid> bids;
	
	@Autowired
	public Sale(Lot lot, Long start_price, Boolean multiple_offer, Boolean limited, LocalDateTime start_tstamp, LocalDateTime end_tstamp, Boolean revocable, Boolean closed) {
		this.lot = lot;
		this.start_price = start_price;
		this.multiple_offer = multiple_offer;
		this.limited = limited;
		this.start_tstamp = start_tstamp;
		this.end_tstamp = end_tstamp;
		this.revocable = revocable;
		this.closed = closed;
		this.last_refresh = LocalDateTime.now();
	}
	
	@Autowired
	public Sale(Lot lot, Long start_price, Boolean multiple_offer, Boolean limited, LocalDateTime start_tstamp, Boolean revocable, Boolean closed) {
		this.lot = lot;
		this.start_price = start_price;
		this.multiple_offer = multiple_offer;
		this.limited = limited;
		this.start_tstamp = start_tstamp;
		this.revocable = revocable;
		this.closed = closed;
		this.last_refresh = LocalDateTime.now();
	}
	
	public void addBid(Bid bid) {
		bids.add(bid);
	}
	
	public void acceptBid(Bid bid_to_accept) {
		/*
		for (Bid bid : bids) {
			if(bid.getId() == bid_to_accept.getId()) {
				bid.setAccepted(true);
			}
		}
		*/
		bid_to_accept.setAccepted(true);
		lot.getProduct().setStock(lot.getProduct().getStock() - lot.getQuantity_to_sell());
	}
	
	public Bid getBestBid() {
		Bid best_bid = null;
		if(bids.size() > 0) {
			best_bid = bids.iterator().next();
			for (Bid bid : bids) {
				if(bid.getAmount() > best_bid.getAmount()) {
					best_bid = bid;
				}
			}
		} 
		/*
		else {
			best_bid = new Bid((BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), false, start_price, LocalDateTime.now(), this, this.getLot().getProduct().getStock());
		}
		*/
		
		return best_bid;
	}
	
	public void close() {
		end_tstamp = LocalDateTime.now();
		closed = true;
	}
	
	public void refresh(boolean is_type_down) {
		
		boolean open = !closed;
		boolean bids_exist = this.getBestBid() != null;
		boolean benefitial = bids_exist && this.getBestBid().getAmount() >= lot.getProduct().getBenefice_price();
		boolean revocable_check = benefitial || !revocable;
		boolean ten_since_bid = bids_exist && this.getBestBid().getTstamp().plusMinutes(10).isBefore(LocalDateTime.now());
		boolean ten_since_start = start_tstamp.plusMinutes(10).isBefore(LocalDateTime.now());
		boolean one_since_refresh = last_refresh != null && last_refresh.plusMinutes(1).isBefore(LocalDateTime.now());
		
		boolean accept_down = bids_exist && is_type_down;
		boolean accept_up = bids_exist && ten_since_bid && revocable_check;
		boolean to_close = (accept_down || accept_up || (!(accept_down || accept_up) && ten_since_start)) && !closed;
		
		
		if(!closed && (accept_down || accept_up)) {
			this.acceptBid(this.getBestBid());
			this.close();
		}
		
		if(to_close) {
			this.close();
		}
		
		if(open && is_type_down && one_since_refresh) {
			this.setStart_price((long)(start_price * 0.99));
		}
		
		last_refresh = LocalDateTime.now();

	}
	
	public Long getMinLeft() {
		if(closed) {
			return (long) 0;
		} else if (this.getBestBid() != null) {
			return ChronoUnit.MINUTES.between(LocalDateTime.now(), this.getBestBid().getTstamp().plusMinutes(10));
		} 
		return ChronoUnit.MINUTES.between(LocalDateTime.now(), start_tstamp.plusMinutes(10));
		
	}
}
