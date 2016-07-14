package com.rigor.controller;

import java.util.ArrayList;
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
import com.rigor.service.InvoiceItemService;

@RestController
public class InvoiceItemController {

	private List<InvoiceItem> invoiceList = new ArrayList<>();
	@Autowired
	private InvoiceItemService invoiceItemService;



	// create invoice
	@RequestMapping(value = "/invoice/", method = RequestMethod.POST)
	public ResponseEntity<Void> createInvoice(@RequestBody InvoiceItem invoiceItem, UriComponentsBuilder ucb) {
		System.out.println("method called" + invoiceItem.getAmount());
		invoiceItemService.createInvoice(invoiceItem);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/invoice/{id}").buildAndExpand(invoiceItem.getInvoiceID()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	// after load of create invoice
	@RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceItem> getInvoice(@PathVariable("id") int id) {
		InvoiceItem invoice = invoiceItemService.getInvoice(id);
		System.out.println("Invoice Id = " + id + " " + invoice.getAmount() + " " + invoice.getItemID());
		return new ResponseEntity<InvoiceItem>(invoice, HttpStatus.OK);
	}
	
	// load all invoices
	@RequestMapping(value = "/invoice/", method = RequestMethod.GET)
	public ResponseEntity<List<InvoiceItem>> listAllInvoices() {
		List<InvoiceItem> allinvoice = invoiceItemService.getAllInvoice();
		for (InvoiceItem items : allinvoice) {
			System.out.println("ID : " + items.getInvoiceID() + "- Amount : " + items.getAmount());
		}
		return new ResponseEntity<List<InvoiceItem>>(allinvoice, HttpStatus.OK);
	}

	// update invoice
	@RequestMapping(value = "/invoice/{id}", method = RequestMethod.PUT)
	public ResponseEntity<InvoiceItem> updateInvoice(@PathVariable("id") int id, @RequestBody InvoiceItem invoiceItem) {
		System.out.println("Updating Invoice " + id);
		invoiceItemService.editInvoice(invoiceItem);
		InvoiceItem updatedInvoice = invoiceItemService.getInvoice(id);
		return new ResponseEntity<InvoiceItem>(updatedInvoice, HttpStatus.OK);
	}

	// delete single invoice
	@RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<InvoiceItem> deleteInvoice(@PathVariable("id") int id) {
		invoiceItemService.cancelInvoice(id);
		return new ResponseEntity<InvoiceItem>(HttpStatus.NO_CONTENT);
	}

}
