package com.crud.crudapp.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

	private final OrderRepository orderRepository;
	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@PostMapping
	public ResponseEntity<Order> createOrder() {
		Order order = new Order();
		order.setId(UUID.randomUUID());
		order.setTotal(19.99);
		orderRepository.save(order);
		return ResponseEntity.ok().body(order);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable String id) {
		Optional<Order> order = orderRepository.findById(UUID.fromString(id));
		return ResponseEntity.ok().body(order.orElse(null));
	}
}
