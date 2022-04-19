package com.rie.maisondesencheres.sale;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;

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
public class Bid {
	@SequenceGenerator(name = "id_Sequence_lot", sequenceName = "ID_SEQ_lot")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_lot")
	private Long id;
	@ManyToOne
	private BaseUser base_user;
	private Boolean accepted;
	private Long amount;
	private LocalDateTime tstamp;
	
	@ManyToOne
	private Sale sale;
	
	@Autowired
	public Bid(BaseUser base_user, Boolean accepted, Long amount, LocalDateTime tstamp, Sale sale) {
		this.base_user = base_user;
		this.accepted = accepted;
		this.amount = amount;
		this.tstamp = tstamp;
		this.sale = sale;
	}

}
