package com.customer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.Dto.OrderResponse;
import com.customer.Repository.CustomerRepository;
import com.customer.Repository.OrderRepository;
import com.customer.entity.Customer;
import com.customer.entity.Orders;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	public Orders createOrder(long customerId, Orders order) {

		// Fetch the customer from the database using customerId
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		// Set the customer for the order
		order.setCustomerOrders(customer);

		// Save the order (cascade will handle saving the relationship)
		Orders savedOrder = orderRepository.save(order);
		return savedOrder;
	}

	public Orders getOrderByOrderId(int orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new IllegalArgumentException("Order not found for ID: " + orderId));
	}

//	public List<OrderResponse> findOrderByCustomerId(long customerId) {
//		// Fetch customer details from the customer repository
//		Customer customer = customerRepository.findById(customerId)
//				.orElseThrow(() -> new IllegalArgumentException("Customer not found for ID: " + customerId));
//
//		// Fetch orders by customer ID
////		List<Orders> orders = orderRepository.findByCustomerId(customerId);
//
//		// Map OrdersEntity to OrderResponse
////		return orders.stream().map(order -> mapToOrderResponse(order, customer)).toList();
//	}

	private OrderResponse mapToOrderResponse(Orders order, Customer customer) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setId(order.getId());
		orderResponse.setContactNumber(customer.getContactNumber());
		orderResponse.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
		orderResponse.setDateTimeField(order.getDateTimeField());
		orderResponse.setDeliveryDate(order.getDeliveryDate());
		orderResponse.setQuantity(order.getQuantity());
		orderResponse.setGrandTotal(order.getGrandTotal());
		orderResponse.setOrderDue(order.getOrderDue());
		orderResponse.setPaymentStatus(order.isPaymentStatus());
		return orderResponse;
	}
}
