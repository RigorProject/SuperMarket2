'use strict';

App.filter('filter', function(){
	return function(arr, searchText){
		if(!searchText){
			return arr;
		}
		var result = [];
		searchText = searchText.toLowerCase();
		// Using the forEach helper method to loop through the array
		angular.forEach(arr, function(grn){
			if(grn.supplierName.toLowerCase().indexOf(searchText) !== -1){
				result.push(grn);
			}
		});
		return result;
	};
});

App.controller('GrnController', ['$scope', 'GrnService', function($scope, GrnService) {
          var self = this;
          self.grn={grnID:null,supplierName:'',productName:'',quantity:'',unitPrice:''};
          self.grns=[];
                  
          self.fetchAllGrns = function(){
              GrnService.fetchAllGrns()
                  .then(
      					       function(d) {
      						        self.grns = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching grn');
            					}
      			       );
          };
           
          self.createGrn = function(grn){
              GrnService.createGrn(grn)
		              .then(
                      self.fetchAllGrns, 
				              function(errResponse){
					               console.error('Error while creating Grn.');
				              }	
                  );
          };

         self.updateGrn = function(grn, grnID){
              GrnService.updateGrn(grn, grnID)
		              .then(
				              self.fetchAllGrns, 
				              function(errResponse){
					               console.error('Error while updating Grn.');
				              }	
                  );
          };

         self.deleteGrn = function(grnID){
              GrnService.deleteGrn(grnID)
		              .then(
				              self.fetchAllGrns, 
				              function(errResponse){
					               console.error('Error while deleting Grn.');
				              }	
                  );
          };

          self.fetchAllGrns();

          self.submit = function() {
              if(self.grn.grnID==null){
                  console.log('Saving New Grn', self.grn);    
                  self.createGrn(self.grn);
              }else{
                  self.updateGrn(self.grn, self.grn.grnID);
                  console.log('Grn updated with grnID ', self.grn.grnID);
              }
              self.reset();
          };
              
          self.edit = function(grnID){
              console.log('grnID to be edited', grnID);
              for(var i = 0; i < self.grns.length; i++){
                  if(self.grns[i].grnID == grnID) {
                     self.grn = angular.copy(self.grns[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(grnID){
              console.log('grnID to be deleted', grnID);
              if(self.grn.grnID === grnID) {//clean form if the grn to be deleted is shown there.
                 self.reset();
              }
              self.deleteGrn(grnID);
          };

          
          self.reset = function(){
              self.grn={grnID:null,supplierName:'',productName:'',quantity:'',unitPrice:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);