<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Supermarket System</title>  
    <style>
      .supplierName.ng-valid {
          background-color: lightgreen;
      }
      .supplierName.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .supplierName.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="GrnController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Place GRN</span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.grn.grnID" />
                      <div class="form-group"> 
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Supplier Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.grn.supplierName" name="supplierName" class="supplierName form-control input-sm" placeholder="Supplier Name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.supplierName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.supplierName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.supplierName.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Product Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.grn.productName" class="form-control input-sm" placeholder="Product Name"  required/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Product Quantity</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.grn.quantity" name="quantity" class="quantity form-control input-sm" placeholder="Product Quantity"  required/>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Unit Price (buying)</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.grn.unitPrice" name="unitPrice" class="unitPrice form-control input-sm" placeholder="Unit Price" required/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.grn.grnID ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
          <div class="panel-heading">
          	<span><img id="search" alt="image" src="<c:url value="/static/img/search.jpg" />"><input class="search" type="text" ng-model="searchText" placeholder="Search With Supplier Name" /></span>
          </div>
		  </div>
          
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of GRN</span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>GRN ID</th>
                              <th>Supplier Name</th>
                              <th>Product Name</th>
                              <th>Quantity</th>
                              <th>Unit Price</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.grns | filter:searchText">
                              <td><span ng-bind="u.grnID"></span></td>
                              <td><span ng-bind="u.supplierName"></span></td>
                              <td><span ng-bind="u.productName"></span></td>
                              <td><span ng-bind="u.quantity"></span></td>
                              <td><span ng-bind="u.unitPrice"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.grnID)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.grnID)" class="btn btn-danger custom-width">Delete</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/grn_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/grn_controller.js' />"></script>
  </body>
</html>