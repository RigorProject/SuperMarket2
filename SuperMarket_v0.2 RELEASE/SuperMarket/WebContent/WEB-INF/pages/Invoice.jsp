<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Invoice Create Page</title>  
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
      <div class="generic-container" ng-controller="InvoiceController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Invoice Creation Portal </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="invoiceForm" class="form-horizontal">
                      
                      <input type="hidden" ng-model="ctrl.invoice.invoiceID" />
                     
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Item ID</label>
                              
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.invoice.itemID" ng-pattern="/^[0-9]+$/" name="itemID" class="itemID form-control input-sm" placeholder="Enter Item ID" required />
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="invoiceForm.itemID.$error.required">This is a required field</span>                                      
                                      <span ng-show="invoiceForm.itemID.$invalid">This field is invalid </span>
                                      <span ng-show="invoiceForm.itemID.$error.pattern">invalid entry for Item ID</span>
                                  </div>
                              </div>
                          </div>
                      </div>                      
                                          
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Item Amount</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.invoice.amount" ng-pattern="/^(\d*([.,](?=\d{3}))?\d+)+((?!\2)[.,]\d\d)?$/" name="amount" class="amount form-control input-sm" placeholder="Enter your amount" required/>
                                  <div class="has-error" ng-show="invoiceForm.$dirty">
                                      <span ng-show="invoiceForm.amount.$error.required">This is a required field</span>
                                      <span ng-show="invoiceForm.amount.$invalid">This field is invalid </span>
                                      <span ng-show="invoiceForm.amount.$error.pattern">invalid entry for amount</span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.invoice.invoiceID ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="invoiceForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="invoiceForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          
          <div class="panel panel-default">
	          <div class="panel-heading"><span class="lead">Invoice Search by ID</span></div>
	          <div class="formcontainer">
	                  <form  name="searchForm" class="form-horizontal">
	                    <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Invoice ID</label>
                               <div class="col-md-7">
                                  <input  type="text" ng-model="ctrl.invoice.id" ng-pattern="/^[0-9]+$/" name="searchid" class="searchID form-control input-sm"  required />
                                  <div class="has-error" ng-show="searchForm.$dirty">                                                                      
                                      <span ng-show="searchForm.searchid.$error.pattern">invalid entry for Invoice search ID</span><br> 
                                  </div>
                              </div>
                              
                               
                              
                          </div>
                       </div>
	                  
	                  </form>
	          </div>
          </div>
          
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Invoices </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>Invoice ID</th>
                              <th>Item ID</th>
                              <th>Item Amount</th>                              
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
<!--                       we dont want searchBy ID function instead for that used filter directives for searching -->
                          <tr ng-repeat="In in ctrl.invoices | filter: ctrl.invoice.id ">
                              <td><span ng-bind="In.invoiceID"></span></td>
                              <td><span ng-bind="In.itemID"></span></td>
                              <td><span ng-bind="In.amount"></span></td>                              
                              <td>
                              <button type="button" ng-click="ctrl.edit(In.invoiceID)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(In.invoiceID)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/invoice_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/invoice_controller.js' />"></script>
  </body>
</html>