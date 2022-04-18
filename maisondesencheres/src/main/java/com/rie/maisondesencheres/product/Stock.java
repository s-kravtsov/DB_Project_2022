package com.rie.maisondesencheres.product;

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
public class Stock {
	@SequenceGenerator(name = "id_Sequence_stock", sequenceName = "ID_SEQ_stock")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_stock")
	private Long id;
	private Long quantity;
	@ManyToOne
	private BaseUser base_user;
	@ManyToOne
	private Product product;
	
	@Autowired
	public Stock(Long quantity, BaseUser base_user, Product product) {
		this.quantity = quantity;
		this.base_user = base_user;
		this.product = product;
	}
	
	public String showStockProduct() {
		return product.getProduct_name();
	}

}
