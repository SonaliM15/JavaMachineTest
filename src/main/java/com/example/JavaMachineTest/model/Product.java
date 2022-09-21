package com.example.JavaMachineTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer prodId;
	@Column
	private String prodName;
	@Column
	private Float prodPrice;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "catId" )
	private Category prodCategory;
	

}
