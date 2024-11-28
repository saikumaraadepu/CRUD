package com.crud.crudapp.Security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping(path = "/open")
	public String open() {
		return "No Login Required";
	}

	@GetMapping(path = "/closed")
	public String closed() {
		return "Login is Required";
	}

	@GetMapping(path = "/special")
	public String special() {
		return "SPECIAL";
	}

	@GetMapping(path = "/basic")
	public String basic() {
		return "BASIC";
	}
}
