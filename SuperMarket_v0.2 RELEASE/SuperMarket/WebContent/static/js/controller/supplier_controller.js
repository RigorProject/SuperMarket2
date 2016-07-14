'use strict';

App.controller('SupplierController', ['$scope', 'SupplierService', function($scope, SupplierService) {
          var self = this;
          self.supplier={supplierId:null,supplierName:'',organization:''};
          self.suppliers=[];
              
          self.fetchAllSuppliers = function(){
        	  SupplierService.fetchAllSuppliers()
                  .then(
      					       function(d) {
      						        self.suppliers = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching All Supplier');
            					}
      			       );
          };
          
          self.fetchByID = function(supplier){
        	  
        	  SupplierService.getSupplier(supplier)
        	  .then(
					       function(d) {
 						        self.supplier = d;
 					       },
       					function(errResponse){
       						console.error('Error while fetching single Supplier');
       					}
 			       );
          };
           
          self.createSupplier = function(supplier){
        	  
        	  SupplierService.createSupplier(supplier)
		              .then(
                      self.fetchAllSuppliers, 
				              function(errResponse){
					               console.error('Error while creating Supplier.');
				              }	
                  );
          };

         self.updateSupplier = function(supplier, supplierId){
        	 SupplierService.updateSupplier(supplier, supplierId)
		              .then(
				              self.fetchAllSuppliers, 
				              function(errResponse){
					               console.error('Error while updating Supplier.');
				              }	
                  );
          };

         self.deleteSupplier = function(supplierId){
        	 SupplierService.deleteSupplier(supplierId)
		              .then(
				              self.fetchAllSuppliers, 
				              function(errResponse){
					               console.error('Error while deleting Supplier.');
				              }	
                  );
          };

          self.fetchAllSuppliers();

          self.submit = function() {        	  
              if(self.supplier.supplierId == null){
                  console.log('Saving New supplier', self.supplier);    
                  self.createSupplier(self.supplier);
                  
              }else{
                  self.updateSupplier(self.supplier, self.supplier.supplierId);
                  console.log('supplier updated with id ', self.supplier.supplierId);
                 
              }
              self.reset();
          };
              
          self.edit = function(supplierId){
              console.log('id to be edited', supplierId);
              for(var i = 0; i < self.suppliers.length; i++){
                  if(self.suppliers[i].supplierId == supplierId) {
                     self.supplier = angular.copy(self.suppliers[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(supplierId){        	
              console.log('id to be deleted', supplierId);
              if(self.supplier.supplierId === supplierId) {
                 self.reset();
              }
              self.deleteSupplier(supplierId);
          };

          
          self.reset = function(){        	
              self.supplier={supplierId:null,supplierName:'',organization:''};
              $scope.supplierForm.$setPristine(); 
          };
          
          self.search= function(supName){
        	  self.supplier.supplierName=supName;        	      	  
        	  self.getSupplier(self.supplier.supplierName);
          };

      }]);
