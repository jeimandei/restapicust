package com.customers.restapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data

public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@CreationTimestamp
	@Column(nullable = false)
	private Date createOn;
	
	@CreationTimestamp
	@Column(nullable = false)
	private Date updateOn;
	
}
