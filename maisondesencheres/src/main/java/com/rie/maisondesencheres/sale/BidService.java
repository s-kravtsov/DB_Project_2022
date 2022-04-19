package com.rie.maisondesencheres.sale;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.rie.maisondesencheres.baseuser.BaseUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BidService {
	
	BidRepository bid_repository;
	
	public Collection<Bid> findByBase_user_id(BaseUser base_user) {
		return bid_repository.findByBase_user_id(base_user.getId());
	}

}
