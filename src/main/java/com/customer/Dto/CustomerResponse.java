package com.customer.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

	public int id;

	public String salutation;

	public String firstName;

	public String lastName;

	public String contactNumber;

	public String countryCode;

	public String AlternateContactNumber;

	public String email;

	public boolean status;

}
