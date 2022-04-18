package com.rie.maisondesencheres.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;

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
	private Long product_cost;
	@ManyToOne
	private Category category;
	
	@Autowired
	public Product(String product_name, Long product_cost, Category category) {
		this.product_name = product_name;
		this.product_cost = product_cost;
		this.category = category;
	}
	
}
