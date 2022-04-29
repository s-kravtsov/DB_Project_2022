package com.rie.maisondesencheres.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/*
 * La classe representant la requete pour inscrire un utilisateur.
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	private final String first_name;
	private final String last_name;
	private final String email;
	private final String password;
	
	public String getFirstName() {
		return first_name;
	}
	public String getLastName() {
		return last_name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
}
