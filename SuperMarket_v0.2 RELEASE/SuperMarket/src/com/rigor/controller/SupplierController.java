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

import com.rigor.entity.InvoiceItem;
import com.rigor.entity.Supplier;
import com.rigor.service.SupplierService;

@RestController
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	// load all suppliers
	@RequestMapping(value = "/supplier/", method = RequestMethod.GET)
	public ResponseEntity<List<Supplier>> listAllSuppliers() {
		List<Supplier> allsupplier = supplierService.getAllSuppliers();

		for (Supplier supplier : allsupplier) {
			System.out
					.println("name : " + supplier.getSupplierName() + "- organization : " + supplier.getOrganization());
		}
		return new ResponseEntity<List<Supplier>>(allsupplier, HttpStatus.OK);

	}

	// create Supplier
	@RequestMapping(value = "/supplier/", method = RequestMethod.POST)
	public ResponseEntity<Void> createSupplier(@RequestBody Supplier supplier, UriComponentsBuilder ucb) {
		System.out.println("method called" + supplier.getSupplierName());
		supplierService.saveSupplier(supplier);
		supplierService.indexinit();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/supplier/{id}").buildAndExpand(supplier.getSupplierId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	// load supplier after create supplier
	@RequestMapping(value = "/supplier/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> getSingleSupplier(@PathVariable("id") int id) {
		Supplier supplier = supplierService.getSupplier(id);
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
	}

	// delete single supplier
	@RequestMapping(value = "/supplier/{supid}", method = RequestMethod.DELETE)
	public ResponseEntity<Supplier> deleteSupplier(@PathVariable("supid") int id) {
		supplierService.deleteSupplier(id);
		return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
	}

	// update supplier
	@RequestMapping(value = "/supplier/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Supplier> updateInvoice(@PathVariable("id") int id, @RequestBody Supplier supplier) {
		System.out.println("Updating Supplier " + id);
		supplierService.update(supplier);
		Supplier updatedSupplier = supplierService.getSupplier(id);
		return new ResponseEntity<Supplier>(updatedSupplier, HttpStatus.OK);
	}

	// load a supplier
	// @RequestMapping(value = "/supplier/{name}", method = RequestMethod.GET)
	// public ResponseEntity<List<Supplier>> searchedSuppliers(@RequestBody
	// Supplier sup) {
	// List<Supplier> allsupplier =
	// supplierService.searchForSupplier(sup.getSupplierName());
	// System.out.println("load Search supplier");
	// for (Supplier supplier : allsupplier) {
	// System.out
	// .println("name : " + supplier.getSupplierName() + "- organization : " +
	// supplier.getOrganization());
	// }
	// return new ResponseEntity<List<Supplier>>(allsupplier, HttpStatus.OK);
	//
	// }

}
