package com.customer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Dto.CustomerResponse;
import com.customer.Service.CustomerService;
import com.customer.entity.Customer;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/customer/getCustomers/{id}")
	private ResponseEntity<?> getCustomerDetails(@PathVariable int id) {
		CustomerResponse employee = customerService.getCustomerById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@PostMapping("/addCustomer")
	private ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		Customer customers = customerService.addCustomer(customer);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customers);
	}

	@GetMapping("/getCustomerByName")
	private ResponseEntity<?> getCustomerBy(@RequestBody String name) {
		List<Customer> customer = customerService.fetchCustomerByName(name);

		if (customer != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(customer);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(name);
		}
	}

	@GetMapping("/getCustomerByContactNumber")
	private ResponseEntity<?> getCustomerByContactNumber(@RequestBody String contactNumber) {
		List<Customer> customer = customerService.fetchCustomerByMobileNumber(contactNumber);

		if (customer != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(customer);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(contactNumber);
		}
	}

	@GetMapping("/getCustomerDetail")
	private List<Customer> getCustomerDetail(@RequestBody String keyword) {
		List<Customer> customer = customerService.fetchCustomerByAnyDetail(keyword);
		return customer;
	}

}
