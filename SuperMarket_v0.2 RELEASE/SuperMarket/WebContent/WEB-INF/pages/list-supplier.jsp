<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="col-md-8">
			<div>
				<form action="SupplierSearch" method="post">
					<fieldset>

					<!-- Form Name -->
					<legend>Supplier Search</legend>

					<!-- Appended Input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="appendedtext">Enter Here Supplier Name</label>
					  <div class="col-md-4">
						<div class="input-group">
						  <input type="text" name="searchText" /><a href="<c:url value='searchSupplier.htm'/>"> <button id="searchSupplier" name="searchSupplier" class="btn btn-success">Search</button></a>
						</div>
					  </div>
					</div>					
					</fieldset>
				</form>
			</div>
			<div>
				<div class="panel panel-default">
				  <!-- Default panel contents -->
  
			<table  class="table">
				<tr>
					<th>Supplier Name</th>
					<th>Organization</th>
				</tr>
				<c:forEach items="${foundSupplier}" var="Supplier">		  				  
				<tr bgcolor="#E1E1E1">
						<td>${Supplier.supplierName}</td>
						<td>${Supplier.organization}</td>
						<td><a href="<c:url value='editSupplier.htm?id=${Supplier.supplierId}'/>"> <button id="editSupplier" name="editSupplier" class="btn btn-success">Edit</button></a>
							<a href="<c:url value='deleteSupplier.htm?id=${Supplier.supplierId}'/>"><button id="deleteSupplier" name="deleteSupplier" class="btn btn-danger">Delete</button></a>
							</td>
				</tr>
				</c:forEach>
				<c:if test="${!empty suppliers}">
				<c:forEach items="${suppliers}" var="supplier">
					<tr bgcolor="#E1E1E1">
						<td>${supplier.supplierName}</td>
						<td>${supplier.organization}</td>
						<td><a href="<c:url value='editSupplier.htm?id=${supplier.supplierId}'/>"> <button id="editSupplier" name="editSupplier" class="btn btn-success">Edit</button></a>
							<a href="<c:url value='deleteSupplier.htm?id=${supplier.supplierId}'/>"><button id="deleteSupplier" name="deleteSupplier" class="btn btn-danger">Delete</button></a>
							</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<hr>
			<p>
				<a href="supplier.htm"><button id="addMore" name="addMore" class="btn btn-success">Add More Suppliers</button></a> <input type="button" value="Go Back From Where You Came!" onclick="history.back(-1)" class="btn btn-success"/>
			</p>
				</div>		
			</div>
		</div>
	</body>
</html>