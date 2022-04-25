package com.rie.maisondesencheres.room;

import com.rie.maisondesencheres.sale.Sale;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

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
public class Room {
	@SequenceGenerator(name = "id_Sequence_room", sequenceName = "ID_SEQ_room")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_room")
	private Long id;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(cascade = { CascadeType.MERGE })
	private Collection<Sale> sales;
	
	@Enumerated(EnumType.STRING)
	private RoomType room_type;
	
	@Autowired
	public Room(Category category, RoomType room_type) {
		this.category = category;
		this.room_type = room_type;
	}
	
	public void addSale(Sale sale) {
		sales.add(sale);
	}
	
	public Collection<Sale> getOpenSales() {
		ArrayList<Sale> open_sales = new ArrayList<Sale>();
		for(Sale sale : sales) {
			if(!sale.getClosed()) {
				open_sales.add(sale);
			}
		}
		return open_sales;
	}
	
	@Scheduled(cron = "0 0/1 * * * *")
	public void refreshSales() {
		for(Sale sale : sales) {
			sale.refresh(room_type == RoomType.DOWN);
		}
	}
}
