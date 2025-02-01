package com.customer.Service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.Dto.CustomerResponse;
import com.customer.Exception.AddressNotFoundException;
import com.customer.Exception.CustomerNotFoundException;
import com.customer.Repository.AddressRepository;
import com.customer.Repository.CustomerRepository;
import com.customer.entity.Address;
import com.customer.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	public CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

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

	// Add a new customer
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	// Retrieve all customers
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	// Add an address to an existing customer
	public Customer addAddress(int customerId, Address address) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + customerId + " not found"));

		address.setCustomer(customer);
		customer.getAddress().add(address);
		return customerRepository.save(customer);
	}

	// Update an address
	public Address updateAddress(int addressId, Address newAddress) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException("Address with ID " + addressId + " not found"));

		address.setCountry(newAddress.getCountry());
		address.setCity(newAddress.getCity());
		address.setState(newAddress.getState());
		address.setZipCode(newAddress.getZipCode());
		address.setAddressLine1(newAddress.getAddressLine1());
		address.setAddressLine2(newAddress.getAddressLine2());
		address.setAddressLine3(newAddress.getAddressLine3());
		address.setZipCode(newAddress.getZipCode());
		address.setZipCode(newAddress.getZipCode());
		address.setState(newAddress.getState());
		address.setAddressType(newAddress.getAddressType());
		address.setPrimaryAddress(newAddress.isPrimaryAddress());

		return addressRepository.save(address);
	}

	// Delete an address
	public void deleteAddress(int addressId) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException("Address with ID " + addressId + " not found"));

		address.setStatus(false);
	}
}
