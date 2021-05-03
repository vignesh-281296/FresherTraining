<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Display All Employee</title>
<style>
.disabled-link{
 pointer-events: none;
}

.btn.btn-primary[disabled] {
    background-color: #4169E1;
}
</style>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Employee Details</h1>
<div class="container-fluid">
<a href="employee" class="btn btn-success" style="color: #fff;"><span class="glyphicon glyphicon-home"></span> Home</a>
</div>
<table class="table table-hover">
    <thead>
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Desgination</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Date of Birth</th>
        <th>Salary</th>
        <th>Status</th>
        <th>Edit</th>
        <th>Assign Project</th>
        <!-- <th>UnAssign Employee</th> -->
        <th>Assigned Project Details</th>
      </tr>
    </thead>
		<tbody>
			<c:forEach items="${employees}" var="employees">
				<tr>
					<c:choose>
						<c:when test="${employees.getIsDelete() == 'true'}">
							<td><a href="single_employee_detail/${employees.getId()}"
								class="btn btn-primary">${employees.getId()}</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="#" class="btn btn-primary" disabled>${employees.getId()}</a></td>
						</c:otherwise>
					</c:choose>
					<td><c:out value="${employees.getName()}"></c:out></td>
					<td><c:out value="${employees.getDesgination()}"></c:out></td>
					<td><c:out value="${employees.getEmail()}"></c:out></td>
					<td><c:out value="${employees.getPhoneNumber()}"></c:out></td>
					<td><c:out value="${employees.getDob()}"></c:out></td>
					<td><c:out value="${employees.getSalary()}"></c:out></td>
					<c:choose>
						<c:when test="${employees.getIsDelete() == 'true'}">
							<td><a href="delete_employee/${employees.getId()}"
								class="btn btn-danger"><span
									class="glyphicon glyphicon-trash"></span> Delete</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="restore_employee/${employees.getId()}"
								class="btn btn-warning"><span
									class="glyphicon glyphicon-refresh"></span> Restore</a></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${employees.getIsDelete() == 'true'}">
							<td><a href="get_employee_detail/${employees.getId()}"
								class="btn btn-primary"><span
									class="glyphicon glyphicon-edit"></span> edit</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="#" class="btn btn-primary" disabled><span
									class="glyphicon glyphicon-edit"></span> edit</a></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${employees.getIsDelete() == 'true'}">
							<td><a href="get_assign_project/${employees.getId()}"
								class="btn btn-primary">Assign/UnAssign</a></td>
							<!-- <td><a href="employee?id=${employees.getId()}&action=get_assigned_employee_details" class="btn btn-danger">UnAssign</a></td>-->
						</c:when>
						<c:otherwise>
							<td><a href="#" class="btn btn-primary" disabled>Assign/UnAssign</a></td>
							<!-- <td><a href="#" class="btn btn-danger disabled-link">UnAssign</a></td> -->
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${employees.getIsDelete() == 'true'}">
							<td><a
								href="get_assigned_project_details/${employees.getId()}"
								class="btn btn-primary">Assigned Project Details</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="#" class="btn btn-primary" disabled>Assigned
									Project Details</a></td>

						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>