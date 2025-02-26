package com.customer.Dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

	public int id;

	public String contactNumber;

	public boolean status;

	public String customerName;

	public LocalDateTime dateTimeField;

	public Date deliveryDate;

	public Integer quantity;

	public Long grandTotal;

	public Long orderDue;

	public boolean paymentStatus;

}
