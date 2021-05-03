<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Specific Employee Details</title>
</head>
<body>
	<div class="container">
		<h1 class="jumbotron"
			style="text-align: center; background-color: #00A693; color: #fff">Individual
			Employee Details</h1>
		<a href="/employee" class="btn btn-success"><span
			class="glyphicon glyphicon-home"></span> Home</a>
		<div class="alert alert-success">
			<strong class="text-center">${message}</strong>
		</div>

		<c:if test="${employee != null}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Name</th>
						<th>Desgination</th>
						<th>Email</th>
						<th>Phone Number</th>
						<th>Salary</th>
						<th>Date of Birth</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${employee.getName()}</td>
						<td>${employee.getDesgination()}</td>
						<td>${employee.getEmail()}</td>
						<td>${employee.getPhoneNumber()}</td>
						<td>${employee.getSalary()}</td>
						<td>${employee.getDob()}</td>
					</tr>
				</tbody>
			</table>

			<c:if test="${!employee.getAddressess().isEmpty()}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Door No</th>
							<th>Street Name</th>
							<th>City</th>
							<th>District</th>
							<th>State</th>
							<th>Country</th>
							<th>Address Type</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${employee.getAddressess()}" var="address">
							<tr>
								<td>${address.getDoorNo()}</td>
								<td>${address.getStreetName()}</td>
								<td>${address.getCity()}</td>
								<td>${address.getDistrict()}</td>
								<td>${address.getState()}</td>
								<td>${address.getCountry()}</td>
								<td>${address.getAddressMode()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</c:if>

	</div>

</body>
</html>