<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Supermarket System</title>  
    <style>
      .productname.ng-valid {
          background-color: lightgreen;
      }
      .productname.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .productname.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="ProductController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Product Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.product.productId" />
                      <div class="form-group"> 
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Product Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.product.productName" name="productName" class="productName form-control input-sm" placeholder="Product Name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.productName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.productName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.productName.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Brand Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.product.brand" class="form-control input-sm" placeholder="Brand"  required/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Selling Price</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.product.price" name="price" class="price form-control input-sm" placeholder="Price"  required/>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Made-In</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.product.madeIn" name="madeIn" class="madeIn form-control input-sm" placeholder="Made-In" required/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.product.productId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
          <div class="panel-heading">
          	<span><img id="search" alt="image" src="<c:url value="/static/img/search.jpg" />"><input class="search" type="text" ng-model="searchText" placeholder="Search With Product Name" /></span>
          </div>
		  </div>
          
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Products </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Product Name</th>
                              <th>Brand</th>
                              <th>Price</th>
                              <th>Made In</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.products | filter:searchText">
                              <td><span ng-bind="u.productId"></span></td>
                              <td><span ng-bind="u.productName"></span></td>
                              <td><span ng-bind="u.brand"></span></td>
                              <td><span ng-bind="u.price"></span></td>
                              <td><span ng-bind="u.madeIn"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.productId)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.productId)" class="btn btn-danger custom-width">Delete</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/product_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/product_controller.js' />"></script>
  </body>
</html>