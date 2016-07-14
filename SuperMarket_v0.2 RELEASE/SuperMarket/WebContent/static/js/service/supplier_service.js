'use strict';

App.factory('SupplierService', ['$http', '$q', function($http, $q){

	return {
		
		fetchAllSuppliers: function() {
					return $http.get('http://localhost:8080/SuperMarket/supplier/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching Suppliers');
										return $q.reject(errResponse);
									}
							);
			},
			
			getSupplier: function(name){
				
				return $http.get('http://localhost:8080/SuperMarket/supplier/'+name)
				.then(
						function(response){							
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while fetch supplier');
							return $q.reject(errResponse);
						}
				);
			},
		    
			createSupplier: function(supplier){
				
					return $http.post('http://localhost:8080/SuperMarket/supplier/', supplier)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating supplier');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateSupplier: function(supplier, id){
					return $http.put('http://localhost:8080/SuperMarket/supplier/'+id, supplier)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating supplier');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteSupplier: function(supid){
					return $http.delete('http://localhost:8080/SuperMarket/supplier/'+supid)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting supplier');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
