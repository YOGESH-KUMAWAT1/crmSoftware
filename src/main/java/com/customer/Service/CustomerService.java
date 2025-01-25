package com.customer.Service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.Dto.CustomerResponse;
import com.customer.Repository.CustomerRepository;
import com.customer.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	public CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	public CustomerResponse getCustomerById(int id) {
		Optional<Customer> employee = customerRepository.findById(id);
		CustomerResponse customerResponse = mapper.map(employee, CustomerResponse.class);
		return customerResponse;
	}

	public Customer addCustomer(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}

	public Customer deleteCustomer(int id) {
		Optional<Customer> customer = customerRepository.findById(id); // Fetch the customer by ID

		if (customer.isPresent()) {
			Customer customerObject = customer.get();
			customerObject.setStatus(false);
			customerRepository.save(customerObject);
			return customerObject;
		} else {
			return null;
		}
	}

	public List<Customer> fetchCustomerByName(String name) {
		List<Customer> customer = customerRepository.findByFirstName(name);

		if (!customer.isEmpty()) {
			return customer;
		} else {
			return null;
		}
	}

	public ResponseEntity<?> fetchCustomerByOrderId(int id) {
		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isPresent()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(customer);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	public List<Customer> fetchCustomerByMobileNumber(String contactNumber) {
		List<Customer> customer = customerRepository.findByContactNumber(contactNumber);

		if (!customer.isEmpty()) {
			return customer;
		} else {
			return null;
		}
	}

	public List<Customer> fetchCustomerByAnyDetail(String keyword) {
		List<Customer> customer = customerRepository.searchByKeyword(keyword);

		if (!customer.isEmpty()) {
			return customer;
		} else {
			return null;
		}

	}
}
