package com.customer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Service.OrderService;
import com.customer.entity.Orders;

@RestController
@RequestMapping("/api/v2")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/createOrder")
	public ResponseEntity<String> createOrder(@RequestBody Orders orderRequest) {
		long customerId = orderRequest.getCustomerOrders().getId();
		orderService.createOrder(customerId, orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully!");
	}

//	@GetMapping("/order/{customerId}")
//	public ResponseEntity<List<OrderResponse>> getOrderByCustomerId(@PathVariable int customerId) {
//		List<OrderResponse> orderResponse = orderService.findOrderByCustomerId(customerId);
//		return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
//	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Orders> getOrderByOrderId(@PathVariable int orderId) {
		Orders orderResponse = orderService.getOrderByOrderId(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
	}
}
