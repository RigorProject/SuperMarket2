
App.controller('InvoiceController', ['$scope', 'InvoiceService', function($scope, InvoiceService) {
          var self = this;
          self.invoice={invoiceID:null,itemID:'',amount:''};
          self.invoices=[];
              
          self.fetchAllInvoices = function(){
        	  InvoiceService.fetchAllInvoices()
                  .then(
      					       function(d) {
      						        self.invoices = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
          
          self.findByID = function(invoiceID){        	 
        	  InvoiceService.fetchByID(invoiceID)
              .then(
            		  function(da) {               			 
					        self.invoice = da;	
					        self.invoice.id=self.invoice.invoiceID;
				       }, 
		              function(errResponse){				    	  
			               console.error('Error while deleting invoice.');
		              }	
          );
          };
           
          self.createInvoice = function(invoice){
        	  InvoiceService.createInvoice(invoice)
		              .then(
                      self.fetchAllInvoices, 
				              function(errResponse){
					               console.error('Error while creating invoice.');
				              }	
                  );
          };

         self.updateInvoice = function(invoice, invoiceID){
        	 InvoiceService.updateInvoice(invoice, invoiceID)
		              .then(
				              self.fetchAllInvoices, 
				              function(errResponse){
					               console.error('Error while updating invoice.');
				              }	
                  );
          };

         self.deleteInvoice = function(invoiceID){
        	 InvoiceService.deleteInvoice(invoiceID)
		              .then(
				              self.fetchAllInvoices, 
				              function(errResponse){
					               console.error('Error while deleting invoice.');
				              }	
                  );
          };

          self.fetchAllInvoices();

          self.submit = function() {
              if(self.invoice.invoiceID==null){
                  console.log('Saving New invoice', self.invoice);    
                  self.createInvoice(self.invoice);
              }else{
                  self.updateInvoice(self.invoice, self.invoice.invoiceID);
                  console.log('invoice updated with id ', self.invoice.invoiceID);
              }
              
              self.reset();
              
          };
              
          self.edit = function(invoiceID){
              console.log('id to be edited', invoiceID);
              for(var i = 0; i < self.invoices.length; i++){
                  if(self.invoices[i].invoiceID == invoiceID) {
                     self.invoice = angular.copy(self.invoices[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(invoiceID){
              console.log('id to be deleted', invoiceID);
              if(self.invoice.invoiceID === invoiceID) {
                 self.reset();
              }
              self.deleteInvoice(invoiceID);
          };

          
          self.reset = function(){
              self.invoice={invoiceID:null,itemID:'',amount:'',id:null};
              $scope.invoiceForm.$setPristine(); 
          };
          
         
          
          self.searchByID = function(){        	 
        	  self.invoice.invoiceID=self.invoice.id;
        	  self.findByID(self.invoice.invoiceID);
        	  
          };

      }]);
