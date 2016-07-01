package com.rigor.entity;

/** 

* Entity Class for Product. 
* 
* Copyright (c) Virtusa Corporation 2016, All Rights Reserved.
* 
* This class is the entity class for Supplier which is  
* use to generate table name item and create relevant columns for the table  . 
* 
* @author MadhukaraK 
* 
* @version 1.0
* 
* @see see also SupplierController.java, 
* SupplierDAO.java, SupplierDAOImpl.java, 
* SupplierDTO.java, SupplierService.java, 
* SupplierServiceImpl.java classes

*/ 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "supplier")
public class Supplier {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "SupplierID")
	private int supplierId;

@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
@Column(name = "SupName")
	private String supplierName;


@Column(name = "Organization")
	private String organization;

public int getSupplierId() {
	return supplierId;
}
public void setSupplierId(int supplierId) {
	this.supplierId = supplierId;
}
public String getSupplierName() {
	return supplierName;
}
public void setSupplierName(String supplierName) {
	this.supplierName = supplierName;
}
public String getOrganization() {
	return organization;
}
public void setOrganization(String organization) {
	this.organization = organization;
}


}
