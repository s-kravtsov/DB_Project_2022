package com.rie.maisondesencheres.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Entité de la base de données : Catégorie.
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Category {
	@SequenceGenerator(name = "id_Sequence_Category", sequenceName = "ID_SEQ_Category")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence_Category")
	private Long id;
	private String category_name;
	private String category_description;
	
	 
}
