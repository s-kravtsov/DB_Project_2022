package com.rie.maisondesencheres.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;

import com.rie.maisondesencheres.baseuser.BaseUser;
import com.rie.maisondesencheres.room.Category;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Product {
	
	@SequenceGenerator(name = "id_Sequence_product", sequenceName = "ID_SEQ_product")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_product")
	private Long id;
	private String product_name;
	private Long benefice_price;
	@ManyToOne
	private Category category;
	
	private Long stock;
	
	@ManyToOne
	private BaseUser base_user;
	
	@Autowired
	public Product(String product_name, Long benefice_price, Category category, Long stock, BaseUser base_user) {
		this.product_name = product_name;
		this.benefice_price = benefice_price;
		this.category = category;
		this.stock = stock;
		this.base_user = base_user;
	}
	
}
