package com.rie.maisondesencheres.baseuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rie.maisondesencheres.registration.token.ConfirmationToken;
import com.rie.maisondesencheres.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

/*
 * La classe service. Contient un repertoire comme attribut et est utilisée par les controlleurs pour evoquer les
 * methodés qui interrogent la base de données. 
 */
@Service
@AllArgsConstructor
public class BaseUserService implements UserDetailsService {
	
	private final BaseUserRepository baseuser_repository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmation_token_service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return baseuser_repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Utilisateur inconnu."));
	}
	
	public String signUpUser(BaseUser base_user) {
		boolean user_exists = baseuser_repository.findByEmail(base_user.getUsername()).isPresent();
		if (user_exists) {
			throw new IllegalStateException("Email déjà utilisé.");
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(base_user.getPassword());
		
		base_user.setPassword(encodedPassword);
		
		baseuser_repository.save(base_user);
		
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmation_token = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), base_user);
		
		confirmation_token_service.saveConfirmationToken(confirmation_token);
		
	
		
		return token;
	}
	
	public int enableBaseUser(String email) {
        return baseuser_repository.enableBaseUser(email);
    }

}
