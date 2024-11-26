package com.crud.crudapp.Customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

	private final CustomerRepository customerRepository;
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
		return ResponseEntity.ok(customerRepository.findById(id).orElse(null));
	}

	@PostMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		return ResponseEntity.ok(customerRepository.save(customer));
	}
}
