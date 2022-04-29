package com.rie.maisondesencheres.registration;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rie.maisondesencheres.baseuser.BaseUser;
import com.rie.maisondesencheres.baseuser.BaseUserRole;
import com.rie.maisondesencheres.baseuser.BaseUserService;

import com.rie.maisondesencheres.registration.token.ConfirmationToken;
import com.rie.maisondesencheres.registration.token.ConfirmationTokenRepository;
import com.rie.maisondesencheres.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

/*
 * La classe service. Contient un repertoire comme attribut et est utilisée par les controlleurs pour evoquer les
 * methodés qui interrogent la base de données. 
 */
@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final BaseUserService baseuser_service;
	private final ConfirmationTokenService confirmation_token_service;


	public String register(RegistrationRequest request) {
		
		String token = baseuser_service.signUpUser(new BaseUser(request.getFirstName(),request.getLastName(), request.getEmail(), request.getPassword(), BaseUserRole.USER, "not specified"));
		
		String link = "http://34.65.198.124:8080/api/v1/registration/confirm?token=" + token;
		

		return link;
	}
	
	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmation_token_service
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmation_token_service.setConfirmedAt(token);
        baseuser_service.enableBaseUser(
                confirmationToken.getBaseUser().getEmail());
        return "confirmed";
	}

}
