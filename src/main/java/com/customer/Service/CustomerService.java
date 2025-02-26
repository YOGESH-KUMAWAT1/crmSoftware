package com.customer.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.Exception.AddressNotFoundException;
import com.customer.Exception.CustomerNotFoundException;
import com.customer.Exception.OrderNotFoundException;
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

	public Optional<Customer> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	public Customer addCustomer(Customer customer) {
		if (customer.getAddress() != null) {
			for (Address address : customer.getAddress()) {
				address.setCustomer(customer);
			}
		}
		return customerRepository.save(customer);
	}

	public Customer deleteCustomer(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

		customer.setStatus(false);
		return customerRepository.save(customer);
	}

	public List<Customer> fetchCustomerByName(String name) {
		return customerRepository.findByFirstName(name);
	}

	public Customer fetchCustomerByOrderId(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order not found with this order ID: " + id));
	}

	public List<Customer> fetchCustomerByMobileNumber(String contactNumber) {
		return customerRepository.findByContactNumber(contactNumber);
	}

	public List<Customer> fetchCustomerByAnyDetail(String keyword) {
		return customerRepository.searchByKeyword(keyword);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer addAddress(Long customerId, Address address) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + customerId + " not found"));

		address.setCustomer(customer);
		customer.getAddress().add(address);
		return customerRepository.save(customer);
	}

	public Address updateAddress(Long addressId, Address newAddress) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException("Address with ID " + addressId + " not found"));

		address.setCountry(newAddress.getCountry());
		address.setCity(newAddress.getCity());
		address.setState(newAddress.getState());
		address.setZipCode(newAddress.getZipCode());
		address.setAddressLine1(newAddress.getAddressLine1());
		address.setAddressLine2(newAddress.getAddressLine2());
		address.setAddressLine3(newAddress.getAddressLine3());
		address.setAddressType(newAddress.getAddressType());
		address.setPrimaryAddress(newAddress.isPrimaryAddress());

		return addressRepository.save(address);
	}

	public void deleteAddress(Long addressId) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException("Address with ID " + addressId + " not found"));

		address.setStatus(false);
		addressRepository.save(address);
	}
}
