package com.rigor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rigor.entity.Product;
import com.rigor.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	//-------------------Retrieve All Products--------------------------------------------------------
    
    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.findAllProducts();
        if(products.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    
    //-------------------Retrieve Single Product--------------------------------------------------------
     
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") int id) {
        System.out.println("Fetching Product with id " + id);
        Product product = productService.findById(id);
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
     
    //-------------------Create a Product--------------------------------------------------------
     
    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Product " + product.getProductName()); 
        productService.saveProduct(product); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{productId}").buildAndExpand(product.getProductId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
     
    //------------------- Update a Product --------------------------------------------------------
     
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") int id, @RequestBody Product product) {
        System.out.println("Updating Product " + id);
         
        Product currentProduct = productService.findById(id);
         
        if (currentProduct==null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        currentProduct.setProductName(product.getProductName());
        currentProduct.setBrand(product.getBrand());
        currentProduct.setMadeIn(product.getMadeIn());
        currentProduct.setPrice(product.getPrice());
         
        productService.updateProduct(currentProduct);
        return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
    }
    
    //------------------- Delete a Product --------------------------------------------------------
     
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") int id) {
        System.out.println("Fetching & Deleting Product with id " + id);
        
		productService.deleteProductById(id);
		Product currentProduct = productService.findById(id);
        if (currentProduct == null) {
            System.out.println("Unable to delete. Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        productService.findAllProducts();
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
    //------------------- Delete All Products --------------------------------------------------------
     
    @RequestMapping(value = "/product/", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteAllProducts() {
        System.out.println("Deleting All Products");

        productService.deleteAllProducts();
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
}
