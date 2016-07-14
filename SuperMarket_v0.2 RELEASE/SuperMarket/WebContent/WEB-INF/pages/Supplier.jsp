<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Supplier Create Page</title>  
    <style>
      .itemID.ng-valid {
          background-color: lightgreen;
      }
      .itemID.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .itemID.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .amount.ng-valid {
          background-color: lightgreen;
      }
      .amount.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .amount.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="SupplierController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Supplier Creation Portal </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="supplierForm" class="form-horizontal">
                      
                      <input type="hidden" ng-model="ctrl.supplier.supplierId" />
                     
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Supplier Name</label>
                              
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.supplier.supplierName"  name="supplierName" class="supplierName form-control input-sm" placeholder="Enter Supplier Name" required />
                                  <div class="has-error" ng-show="supplierForm.$dirty">
                                      <span ng-show="supplierForm.supplierName.$error.required">This is a required field</span>                                      
                                      <span ng-show="supplierForm.supplierName.$invalid">This field is invalid </span>                                     
                                  </div>
                              </div>
                          </div>
                      </div>                      
                                          
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Supplier Organization</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.supplier.organization"  name="organization" class="organization form-control input-sm" placeholder="Enter your Organization" required/>
                                  <div class="has-error" ng-show="supplierForm.$dirty">
                                      <span ng-show="supplierForm.supplier.$error.required">This is a required field</span>
                                      <span ng-show="supplierForm.supplier.$invalid">This field is invalid </span>                                     
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.supplier.supplierId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="supplierForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="supplierForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          
          <div class="panel panel-default">
	          <div class="panel-heading"><span class="lead">Supplier Search by ID</span></div>
	          <div class="formcontainer">
	                  <form  name="searchForm" class="form-horizontal">
	                    <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Supplier ID</label>
                               <div class="col-md-7">
                                  <input  type="text" ng-model="ctrl.supplier.id" name="searchid" class="searchID form-control input-sm"  required />
                                  <div class="has-error" ng-show="searchForm.$dirty">                                                                      
                                      <span ng-show="searchForm.searchid.$error.pattern">invalid entry for Supplier search ID</span><br> 
                                  </div>
                              </div>
                              
                               
                              
                          </div>
                       </div>
	                  
	                  </form>
	          </div>
          </div>
          
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Suppliers </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>Supplier ID</th>
                              <th>Supplier Name</th>
                              <th>Supplier Organization</th>                              
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
<!--                       we dont want searchBy ID function -->
                          <tr ng-repeat="sup in ctrl.suppliers | filter: ctrl.supplier.id ">
                              <td><span ng-bind="sup.supplierId"></span></td>
                              <td><span ng-bind="sup.supplierName"></span></td>
                              <td><span ng-bind="sup.organization"></span></td>                              
                              <td>
                              <button type="button" ng-click="ctrl.edit(sup.supplierId)" class="btn btn-success custom-width">Edit</button>  
<!--                              <button type="button" ng-click="ctrl.remove(sup.supplierId)" class="btn btn-danger custom-width">Remove</button> -->
                              </td>
                          </tr>
                      
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/supplier_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/supplier_controller.js' />"></script>
  </body>
</html>