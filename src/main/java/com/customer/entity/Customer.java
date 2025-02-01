package com.customer.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;

	public String salutation;

	@NotBlank(message = "{firstname should not be null}")
	@Size(min = 1, max = 50, message = "{firstname.size}")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "{firstname.pattern}")
	public String firstName;

	@NotBlank(message = "{lastname should not be null}")
	@Size(min = 1, max = 50, message = "{lastname.size}")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "{lastname.pattern}")
	public String lastName;

	@NotNull(message = "{contactNumber should not be nul}")
	@Size(min = 10, max = 10, message = "{mobile.size}")
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message = "{mobile.pattern}")
	public String contactNumber;

	public String countryCode;

	public String AlternateContactNumber;

	@Email(message = "{email.invalid}")
	public String email;

	public boolean status;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Address> address = new ArrayList<>();
}
