<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>

</head>
<body>
	<div class="col-md-8">
		<div>
			<form:form commandName="invoiceItem"
				action="http://localhost:8080/SuperMarket/listInvoice.htm">
				<fieldset>

					<!-- Form Name -->
					<legend>Invoice Search</legend>

					<!-- Appended Input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="appendedtext">Search
							Invoice</label> <a style="margin-left: 205px;" href="listInvoice"><span
							class="badge">Show All</span></a>
						<div class="col-md-4">
							<label>Invoice ID :</label>
							<form:input path="invoiceID" id="PATH"
								class="form-control input-md" />
							<form:errors path="invoiceID" cssClass="error" />
							<input type="submit" value="Search" class="btn btn-success"
								style="margin: 5px; margin-left: 190px;" />
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
		<div>
			<div class="panel panel-default">
				<!-- Default panel contents -->

				<c:choose>
					<c:when test="${ empty selectedInvoice && empty invoices}">

						<div class="alert alert-warning">
							<strong>Invoice box is empty !</strong>No any existing Invoices
						</div>
					</c:when>
					<c:otherwise>

						<table class="table">
							<tr>
								<th>Invoice ID</th>
								<th>Item ID</th>
								<th>Amount</th>
							</tr>
							<c:if test="${!empty invoices}">
								<c:forEach items="${invoices}" var="invoice_list">
									<tr bgcolor="#E1E1E1">
										<td>${invoice_list.invoiceID}</td>
										<td>${invoice_list.itemID}</td>
										<td>${invoice_list.amount}</td>

										<td><a
											href="<c:url value='/editInvoice/${invoice_list.invoiceID}'/>">
												<button id="editInvoice" name="editInvoice"
													class="btn btn-success">Edit</button>
										</a> <a
											href="<c:url value='/deleteInvoice/${invoice_list.invoiceID}'/>"><button
													id="deleteInvoice" name="deleteInvoice"
													class="btn btn-danger">Delete</button></a></td>
									</tr>

								</c:forEach>
							</c:if>
							<c:if test="${!empty selectedInvoice }">
								<tr>
									<td>${selectedInvoice.invoiceID}</td>
									<td>${selectedInvoice.itemID}</td>
									<td>${selectedInvoice.amount}</td>
									<td><a
										href="<c:url value='/editInvoice/${selectedInvoice.invoiceID}'/>">
											<button id="editInvoice" name="editInvoice"
												class="btn btn-success">Edit</button>
									</a> <a
										href="<c:url value='/deleteInvoice/${selectedInvoice.invoiceID}'/>"><button
												id="deleteInvoice" name="deleteInvoice"
												class="btn btn-danger">Delete</button></a></td>
								</tr>
							</c:if>



						</table>
					</c:otherwise>
				</c:choose>
				<hr>
				<p>
					<a href="http://localhost:8080/SuperMarket/addInvoice.htm"><button
							id="addMore" name="addMore" class="btn btn-success">Add
							More Invoice</button></a>
				</p>




			</div>
		</div>
	</div>
</body>
</html>