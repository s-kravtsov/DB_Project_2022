package com.rie.maisondesencheres.sale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;

import com.rie.maisondesencheres.product.Stock;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Lot {
	@SequenceGenerator(name = "id_Sequence_lot", sequenceName = "ID_SEQ_lot")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_lot")
	private Long id;
	
	@ManyToOne
	private Stock stock;
	
	private Long quantity_to_sell;
	
	@Autowired
	public Lot(Stock stock, Long quantity_to_sell) {
		this.stock = stock;
		this.quantity_to_sell = quantity_to_sell;
	}
	
	public String showLot() {
		return quantity_to_sell + " " + stock.showStockProduct();
	}
}
