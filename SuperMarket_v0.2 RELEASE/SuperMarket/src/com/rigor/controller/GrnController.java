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

import com.rigor.entity.Grn;
import com.rigor.service.GrnService;

@RestController
public class GrnController {

	@Autowired
	private GrnService grnService;

	//-------------------Retrieve All Grns--------------------------------------------------------
    
    @RequestMapping(value = "/grn/", method = RequestMethod.GET)
    public ResponseEntity<List<Grn>> listAllGrns() {
        List<Grn> grns = grnService.findAllGrns();
        if(grns.isEmpty()){
            return new ResponseEntity<List<Grn>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Grn>>(grns, HttpStatus.OK);
    }
    
    //-------------------Retrieve Single Grn--------------------------------------------------------
     
    @RequestMapping(value = "/grn/{grnID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Grn> getGrn(@PathVariable("grnID") int id) {
        System.out.println("Fetching Grn with id " + id);
        Grn grn = grnService.findById(id);
        if (grn == null) {
            System.out.println("Grn with id " + id + " not found");
            return new ResponseEntity<Grn>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Grn>(grn, HttpStatus.OK);
    }
     
    //-------------------Create a Grn--------------------------------------------------------
     
    @RequestMapping(value = "/grn/", method = RequestMethod.POST)
    public ResponseEntity<Void> createGrn(@RequestBody Grn grn, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Grn " + grn.getSupplierName()); 
        grnService.saveGrn(grn); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/grn/{grnID}").buildAndExpand(grn.getGrnID()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
     
    //------------------- Update a Grn --------------------------------------------------------
     
    @RequestMapping(value = "/grn/{grnID}", method = RequestMethod.PUT)
    public ResponseEntity<Grn> updateGrn(@PathVariable("grnID") int id, @RequestBody Grn grn) {
        System.out.println("Updating Grn " + id);
         
        Grn currentGrn = grnService.findById(id);
         
        if (currentGrn==null) {
            System.out.println("Grn with id " + id + " not found");
            return new ResponseEntity<Grn>(HttpStatus.NOT_FOUND);
        }
        currentGrn.setSupplierName(grn.getSupplierName());
        currentGrn.setProductName(grn.getProductName());
        currentGrn.setQuantity(grn.getQuantity());
        currentGrn.setUnitPrice(grn.getUnitPrice());
         
        grnService.updateGrn(currentGrn);
        return new ResponseEntity<Grn>(currentGrn, HttpStatus.OK);
    }
    
    //------------------- Delete a Grn --------------------------------------------------------
     
    @RequestMapping(value = "/grn/{grnID}", method = RequestMethod.DELETE)
    public ResponseEntity<Grn> deleteGrn(@PathVariable("grnID") int id) {
        System.out.println("Fetching & Deleting Grn with id " + id);
        
		grnService.deleteGrnById(id);
		Grn currentGrn = grnService.findById(id);
        if (currentGrn == null) {
            System.out.println("Unable to delete. Grn with id " + id + " not found");
            return new ResponseEntity<Grn>(HttpStatus.NOT_FOUND);
        }
        grnService.findAllGrns();
        return new ResponseEntity<Grn>(HttpStatus.NO_CONTENT);
    }
    
    //------------------- Delete All Grns --------------------------------------------------------
     
    @RequestMapping(value = "/grn/", method = RequestMethod.DELETE)
    public ResponseEntity<Grn> deleteAllGrns() {
        System.out.println("Deleting All Grns");

        grnService.deleteAllGrns();
        return new ResponseEntity<Grn>(HttpStatus.NO_CONTENT);
    }
}
