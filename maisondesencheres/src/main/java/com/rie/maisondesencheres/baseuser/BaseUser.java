package com.rie.maisondesencheres.baseuser;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class BaseUser implements UserDetails {
	
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	private Long id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private BaseUserRole baseuser_role;
	private Boolean locked;
	private Boolean enabled;
	
	public BaseUser(String first_name, String last_name, String email, String password, BaseUserRole baseuser_role) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.baseuser_role = baseuser_role;
		this.locked = false;
		this.enabled = false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(baseuser_role.name());
		return null;
	}

	@Override
	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String p) {
		this.password = p;
	}

	@Override
	public String getUsername() {
	
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return enabled;
	}
	
	public String getFullName() {
		return first_name + ' ' + last_name;
	}

}
