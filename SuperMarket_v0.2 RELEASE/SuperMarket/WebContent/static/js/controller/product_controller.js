'use strict';

App.filter('filter', function(){
	return function(arr, searchText){
		if(!searchText){
			return arr;
		}
		var result = [];
		searchText = searchText.toLowerCase();
		// Using the forEach helper method to loop through the array
		angular.forEach(arr, function(product){
			if(product.productName.toLowerCase().indexOf(searchText) !== -1){
				result.push(product);
			}
		});
		return result;
	};
});

App.controller('ProductController', ['$scope', 'ProductService', function($scope, ProductService) {
          var self = this;
          self.product={productId:null,productName:'',madeIn:'',brand:'',price:''};
          self.products=[];
                  
          self.fetchAllProducts = function(){
              ProductService.fetchAllProducts()
                  .then(
      					       function(d) {
      						        self.products = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.createProduct = function(product){
              ProductService.createProduct(product)
		              .then(
                      self.fetchAllProducts, 
				              function(errResponse){
					               console.error('Error while creating Product.');
				              }	
                  );
          };

         self.updateProduct = function(product, productId){
              ProductService.updateProduct(product, productId)
		              .then(
				              self.fetchAllProducts, 
				              function(errResponse){
					               console.error('Error while updating Product.');
				              }	
                  );
          };

         self.deleteProduct = function(productId){
              ProductService.deleteProduct(productId)
		              .then(
				              self.fetchAllProducts, 
				              function(errResponse){
					               console.error('Error while deleting Product.');
				              }	
                  );
          };

          self.fetchAllProducts();

          self.submit = function() {
              if(self.product.productId==null){
                  console.log('Saving New Product', self.product);    
                  self.createProduct(self.product);
              }else{
                  self.updateProduct(self.product, self.product.productId);
                  console.log('Product updated with productId ', self.product.productId);
              }
              self.reset();
          };
              
          self.edit = function(productId){
              console.log('productId to be edited', productId);
              for(var i = 0; i < self.products.length; i++){
                  if(self.products[i].productId == productId) {
                     self.product = angular.copy(self.products[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(productId){
              console.log('productId to be deleted', productId);
              if(self.product.productId === productId) {//clean form if the product to be deleted is shown there.
                 self.reset();
              }
              self.deleteProduct(productId);
          };

          
          self.reset = function(){
              self.product={productId:null,productName:'',madeIn:'',brand:'',price:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);