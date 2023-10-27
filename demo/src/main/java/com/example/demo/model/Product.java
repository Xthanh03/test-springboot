package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public  class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Name may not be null")
	@Column(name="name")
	private String name;
	
	@NotNull(message = "Price may not be empty")
	@Column(name="price")
	private String price;
	
	@NotNull(message = "Description may not be empty")
	@Column(name="description")
	private String description;
	
	@CreationTimestamp
    private Date created_time;

}
