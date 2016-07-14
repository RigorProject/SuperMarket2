'use strict';

App.factory('GrnService', ['$http', '$q', function($http, $q){
	return {
			fetchAllGrns: function() {
					return $http.get('http://localhost:8080/SuperMarket/grn/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching grns');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createGrn: function(grn){
					return $http.post('http://localhost:8080/SuperMarket/grn/', grn)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating grn');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateGrn: function(grn, grnID){
					return $http.put('http://localhost:8080/SuperMarket/grn/'+grnID, grn)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating grn');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteGrn: function(grnID){
					return $http.delete('http://localhost:8080/SuperMarket/grn/'+grnID)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting grn');
										return $q.reject(errResponse);
									}
							);
			}
	};
}]);
