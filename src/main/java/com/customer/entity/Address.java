package com.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	public String addressLine1;

	public String addressLine2;

	public String addressLine3;

	public String city;

	public int zipCode;

	public String state;

	public String country;

	public String addressType;

	public boolean primaryAddress;

	public boolean status;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	public Customer customer;

}
