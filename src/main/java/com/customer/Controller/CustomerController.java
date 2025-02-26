package com.customer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.customer.Service.CustomerService;
import com.customer.entity.Address;
import com.customer.entity.Customer;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/getCustomerById/{id}")
	public ResponseEntity<?> getCustomerDetails(@PathVariable Long id) {
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

		Customer savedCustomer = customerService.addCustomer(customer);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedCustomer);
	}

	@GetMapping("/search/name")
	public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam String name) {
		List<Customer> customers = customerService.fetchCustomerByName(name);
		return customers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customers);
	}

	@GetMapping("/search/contact")
	public ResponseEntity<List<Customer>> getCustomerByContactNumber(@RequestParam String contactNumber) {
		List<Customer> customers = customerService.fetchCustomerByMobileNumber(contactNumber);
		return customers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customers);
	}

	@GetMapping("/searchByCustomerDetails/{keyword}")
	public ResponseEntity<List<Customer>> getCustomerByDetail(@PathVariable String keyword) {
		List<Customer> customers = customerService.fetchCustomerByAnyDetail(keyword);
		return customers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customers);
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@PostMapping("/addAddressToCustomer/{customerId}")
	public ResponseEntity<Customer> addAddressToCustomer(@PathVariable Long customerId, @RequestBody Address address) {
		return ResponseEntity.ok(customerService.addAddress(customerId, address));
	}

	@PutMapping("/updateAddress/{addressId}")
	public ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
		return ResponseEntity.ok(customerService.updateAddress(addressId, address));
	}

	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
		customerService.deleteAddress(addressId);
		return ResponseEntity.noContent().build();
	}

}
