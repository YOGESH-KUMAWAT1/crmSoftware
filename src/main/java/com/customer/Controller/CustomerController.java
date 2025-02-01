package com.customer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Dto.CustomerResponse;
import com.customer.Dto.CustomerWithAddressDto;
import com.customer.Service.CustomerService;
import com.customer.entity.Address;
import com.customer.entity.Customer;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/getCustomerById/{id}")
	public ResponseEntity<CustomerResponse> getCustomerDetails(@PathVariable int id) {
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerWithAddressDto customerWithAddressDto) {

		Customer customer = customerWithAddressDto.getCustomer();
		Address address = customerWithAddressDto.getAddress();

		Customer savedCustomer = customerService.addCustomer(customer);
		address.setCustomer(savedCustomer);
		customerService.addAddress(savedCustomer.getId(), address);
		return ResponseEntity.status(201).body(savedCustomer);
	}

	@GetMapping("/search/by-name")
	public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam String name) {
		List<Customer> customers = customerService.fetchCustomerByName(name);
		return customers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customers);
	}

	@GetMapping("/search/by-contact")
	public ResponseEntity<List<Customer>> getCustomerByContactNumber(@RequestParam String contactNumber) {
		List<Customer> customers = customerService.fetchCustomerByMobileNumber(contactNumber);
		return customers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customers);
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<Customer>> getCustomerByDetail(@PathVariable String keyword) {
		List<Customer> customers = customerService.fetchCustomerByAnyDetail(keyword);
		return customers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customers);
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@PostMapping("/addresses/{customerId}")
	public ResponseEntity<Customer> addAddressToCustomer(@PathVariable int customerId, @RequestBody Address address) {
		return ResponseEntity.ok(customerService.addAddress(customerId, address));
	}

	@PutMapping("/addresses/{addressId}")
	public ResponseEntity<Address> updateAddress(@PathVariable int addressId, @RequestBody Address address) {
		return ResponseEntity.ok(customerService.updateAddress(addressId, address));
	}

	@DeleteMapping("/addresses/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable int addressId) {
		customerService.deleteAddress(addressId);
		return ResponseEntity.noContent().build();
	}

}
