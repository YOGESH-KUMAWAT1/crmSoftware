package com.customer.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	Optional<Orders> findById(int orderId);

	Optional<Orders> findByCustomerName(String customerName);

//	List<Orders> findByCustomerId(@Param("customerId") long customerId);
}
