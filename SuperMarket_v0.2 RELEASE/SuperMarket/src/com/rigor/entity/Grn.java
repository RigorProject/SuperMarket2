package com.rigor.entity;



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
@Table(name = "grn")
public class Grn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GrnID")
	private int grnID;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name = "SupplierName")
	private String supplierName;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name = "ProductName")
	private String productName;
	
	@Column(name = "Quantity")
	private int quantity;

	@Column(name = "UnitPrice")
	private double unitPrice;
	
/*	@OneToMany(mappedBy="invoiceItem")
	private Set<Product> products;*/
	
	/* @ManyToOne
	 @JoinColumn(name="supplier_id")
	 private Supplier suppliers;*/

	public int getGrnID() {
		return grnID;
	}

	public void setGrnID(int grnID) {
		this.grnID = grnID;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}


}
