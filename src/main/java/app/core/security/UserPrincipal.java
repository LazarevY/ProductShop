package app.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import app.data.modeles.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

	private Long id;
	private String email;
	@JsonIgnore
	private String password;

	public static UserPrincipal create(User user) {
		
		return new UserPrincipal(user.getId(), user.getEmail(), user.getPasswordHash());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object otherUser) {
		if(otherUser == null) return false;
		else if (!(otherUser instanceof UserDetails)) return false;
		else return (otherUser.hashCode() == hashCode());
	}

	@Override
	public int hashCode() {
		return this.email.hashCode();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	
}
