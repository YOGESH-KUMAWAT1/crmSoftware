package com.customer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByFirstName(String firstName);

	@Query(value = "SELECT * FROM user WHERE " + "LOWER(salutation) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "CAST(contact_number AS CHAR) LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
	List<Customer> searchByKeyword(@Param("keyword") String keyword);

	List<Customer> findByContactNumber(String contactNumber);

}
