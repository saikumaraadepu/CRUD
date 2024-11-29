package com.crud.crudapp.Security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

	private final CustomUserRepository customUserRepository;
	public CustomeUserDetailsService(CustomUserRepository customUserRepository) {
		this.customUserRepository = customUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUser customUser = customUserRepository.findById(username).orElse(null);
		assert customUser != null;
		return User
				.withUsername(customUser.getUsername())
				.password(customUser.getPassword())
				.build();
	}
}
