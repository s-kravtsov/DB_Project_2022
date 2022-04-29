package com.rie.maisondesencheres.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.rie.maisondesencheres.baseuser.BaseUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Le token qu'il faut valider pour activer son profil. Par valider on entend cliquer sur le lien retourné par 
 * la methode s'inscription register() dans RegistrationService.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
	
	@SequenceGenerator(name = "conft_Sequence", sequenceName = "ID_SEQ_conft")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "conft_Sequence")
	private Long id;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private LocalDateTime expiredAt; 
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "base_user_id")
	private BaseUser base_user;
	
	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, BaseUser base_user) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt; 
		this.confirmedAt = null;
		this.base_user = base_user;
		
	}
	
	public BaseUser getBaseUser() {
		return base_user;
	}
	
}
