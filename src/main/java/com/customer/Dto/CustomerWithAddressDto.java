package com.customer.Dto;

import com.customer.entity.Address;
import com.customer.entity.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerWithAddressDto {
	private Customer customer;
	private Address address;
}
