package com.rigor.service;
/** 

* Product Service Interface for product. 
* 
* Copyright (c) Virtusa Corporation 2016, All Rights Reserved.
* 
* Basically help to perform CRUD operations with the help of ProductDAO
* 
* @author Nadeesha 
* 
* @version 1.0
* 
* @see see also ProductDAOImpl.java, ProductDAO.java, ProductServiceImpl

*/
import java.util.List;

import com.rigor.entity.Product;

public interface ProductService {
	
	public void saveProduct(Product product);
	
	public void updateProduct(Product product);
	
	public Product findById(int id);
	
	public void deleteProductById(int id);

	public List<Product> findAllProducts(); 
	
	public void deleteAllProducts();
	
}
