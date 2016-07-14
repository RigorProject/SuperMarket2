package com.rigor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String getIndexPage() {
		return "Product";
	}

	@RequestMapping(value = "/grn", method = RequestMethod.GET)
	public String getGRNPage() {
		return "Grn";
	}

	@RequestMapping(value = "/invoice", method = RequestMethod.GET)
	public String getInvoicePage() {
		return "Invoice";
	}

	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	public String getSupplierPage() {
		return "Supplier";
	}

}