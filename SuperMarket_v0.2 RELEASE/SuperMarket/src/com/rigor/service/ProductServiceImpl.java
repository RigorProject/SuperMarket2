package com.rigor.service;
/** 

* Product Service  Implementation for product. 
* 
* Copyright (c) Virtusa Corporation 2016, All Rights Reserved.
* 
* Basically help to perform CRUD operations with the help of ProductDAO
* 
* @author Nadeesha 
* 
* @version 1.0
* 
* @see see also ProductDAOImpl.java, ProductDAO.java, ProductServiceImpl.java

*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rigor.dao.ProductDAOImpl;
import com.rigor.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAOImpl productDAO;
	private static List<Product> products;

	public void saveProduct(Product product) {
		productDAO.save(product);
	}

	public void updateProduct(Product product) {
		productDAO.update(product);
	}
	
	public void deleteAllProducts(){
		products.clear();
	}
	
	public Product findById(int id) {
		Product product = productDAO.findById(id);
		return product;
	}

	public void deleteProductById(int id) {
		productDAO.deleteById(id);
	}
	
	public List<Product> findAllProducts() {
		List<Product> products = productDAO.findAll();
		return products;
	}
}
