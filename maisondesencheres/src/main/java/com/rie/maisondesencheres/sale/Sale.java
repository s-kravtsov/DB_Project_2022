package com.rie.maisondesencheres.sale;

import java.time.LocalDateTime;
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
	@OneToOne
	private Lot lot;
	private Long start_price;
	@Enumerated(EnumType.STRING)
	private SaleType sale_type;
	private Boolean multiple_offer;
	private Boolean limited;
	private LocalDateTime start_tstamp;
	private LocalDateTime end_tstamp;
	private Boolean revocable;
	
	@OneToMany(cascade = { CascadeType.ALL })
	private Collection<Bid> bids;
	
	@Autowired
	public Sale(Lot lot, Long start_price, SaleType sale_type, Boolean multiple_offer, Boolean limited, LocalDateTime start_tstamp, LocalDateTime end_tstamp, Boolean revocable) {
		this.lot = lot;
		this.start_price = start_price;
		this.sale_type = sale_type;
		this.multiple_offer = multiple_offer;
		this.limited = limited;
		this.start_tstamp = start_tstamp;
		this.end_tstamp = end_tstamp;
		this.revocable = revocable;
	}
	
	public void addBid(Bid bid) {
		bids.add(bid);
	}
	
	public Bid getBestBid() {
		Bid best_bid;
		if(bids.size() > 0) {
			best_bid = bids.iterator().next();
		} else {
			best_bid = new Bid((BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), false, start_price, LocalDateTime.now(), this);
		}
		
		for (Bid bid : bids) {
			if(bid.getAmount() > best_bid.getAmount()) {
				best_bid = bid;
			}
		}
		return best_bid;
	}
	

}
