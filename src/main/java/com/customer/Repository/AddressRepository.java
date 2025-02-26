package com.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
