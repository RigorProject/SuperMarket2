<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	// 	function selectType() {
	// 		var element = document.getElementById("searchType");
	// 		var val = element.options[element.selectedIndex].value;

	// 		var inputEle = document.getElementById("PATH");
	// 		if (val == "invoice") {
	// 			inputEle.setAttribute("path", "invoiceID");
	// 		}
	// 		if (val == "item") {
	// 			inputEle.setAttribute("path", "itemID");
	// 		}

	//}
</script>
<title>Invoice Search</title>
</head>
<body>

	<form:form commandName="invoiceItem">
		<label>Select search type</label>

		<label>Invoice ID :</label>
		<form:input path="invoiceID" id="PATH" />
		<input type="submit" value="Search" />


	</form:form>

	
		<table>
			<tr>
				<th>Invoice ID</th>
				<th>Item ID</th>
				<th>Amount</th>
			</tr>

			<tr>
				<td>${selectedInvoice.invoiceID}</td>
				<td>${selectedInvoice.itemID}</td>
				<td>${selectedInvoice.amount}</td>
			</tr>

		</table>
	

	


</body>
</html>