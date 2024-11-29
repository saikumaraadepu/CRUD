package com.crud.crudapp.Security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SecurityController {

	private final AuthenticationManager authenticationManager;
	private final CustomUserRepository customUserRepository;
	private final BCryptPasswordEncoder encoder;

	public SecurityController(AuthenticationManager authenticationManager,
							  CustomUserRepository customUserRepository,
							  BCryptPasswordEncoder encoder) {
		this.authenticationManager = authenticationManager;
		this.customUserRepository = customUserRepository;
		this.encoder = encoder;
	}

	@PostMapping(path = "/login")
	public ResponseEntity login(@RequestBody LoginRequest request) {

		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					request.getUsername(),
					request.getPassword()
			);

			Authentication authenticate = authenticationManager.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(authenticate);

			String jwtToken = JwtUtil.generateToken(request.getUsername());
			return ResponseEntity.ok(new JwtResponse(jwtToken));

		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}
	}

	@PostMapping(path = "/createNewUser")
	public ResponseEntity<String> createNewUser(@RequestBody LoginRequest request) {
		Optional<CustomUser> customUserOptional = customUserRepository.findById(request.getUsername());
		if (customUserOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
		}

		CustomUser customUser = new CustomUser();
		customUser.setUsername(request.getUsername());
		customUser.setPassword(encoder.encode(request.getPassword()));
		customUserRepository.save(customUser);
		return ResponseEntity.status(HttpStatus.CREATED).body("User created");
	}
}
