package com.customers.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customers.restapi.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
	
}
