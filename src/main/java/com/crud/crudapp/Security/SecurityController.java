package com.crud.crudapp.Security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

//	@GetMapping(path = "/open")
//	public String open() {
//		return "No Login Required";
//	}
//
//	@GetMapping(path = "/closed")
//	public String closed() {
//		return "Login is Required";
//	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/special")
	public String special() {
		return "SPECIAL";
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@GetMapping(path = "/basic")
	public String basic() {
		return "BASIC";
	}
}
