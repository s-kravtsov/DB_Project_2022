package com.rie.maisondesencheres.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	
	private final ConfirmationTokenRepository confirmation_token_repository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmation_token_repository.save(token);
	}
	
	public Optional<ConfirmationToken> getToken(String token) {
        return confirmation_token_repository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmation_token_repository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
