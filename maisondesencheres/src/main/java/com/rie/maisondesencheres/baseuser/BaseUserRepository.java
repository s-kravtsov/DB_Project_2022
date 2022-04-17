package com.rie.maisondesencheres.baseuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {
	Optional<BaseUser> findByEmail(String email);
	
	@Transactional
    @Modifying
    @Query("UPDATE BaseUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableBaseUser(String email);
}
