package com.rie.maisondesencheres.room;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Room {
	@SequenceGenerator(name = "id_Sequence_room", sequenceName = "ID_SEQ_room")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_room")
	private Long id;
	
	@ManyToOne
	private Category category;
	
	@Autowired
	public Room(Category category) {
		this.category = category;
	}
}
