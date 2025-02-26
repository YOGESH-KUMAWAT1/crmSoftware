package com.customer.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	enum Package {
		Jaipur25, Jaipur50, Jaipur1l;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String customerName;

	private LocalDateTime dateTimeField;

	private Date deliveryDate;

	private Integer quantity;

	private Long grandTotal;

	private Long orderDue;

	private boolean paymentStatus;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonBackReference
	public Customer customerOrders;

}
