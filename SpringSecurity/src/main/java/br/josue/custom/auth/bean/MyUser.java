package br.josue.custom.auth.bean;

import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/*
 * REMOVES NULL FIELDS FROM RESPONSE
 * REF: http://stackoverflow.com/questions/6049523/how-to-configure-spring-mvc-3-to-not-return-null-object-in-json-response
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MyUser implements UserDetails{

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	
	/* Spring Security fields*/
    private List<Role> authorities;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "MyUser [username=" + username + ", password=" + password + ", authorities=" + authorities + "]";
	}

	
	
}
