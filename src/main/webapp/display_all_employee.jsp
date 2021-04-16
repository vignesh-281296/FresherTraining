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
</style>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Employee Details</h1>
<div class="container-fluid">
<a href="employee.jsp" class="btn btn-success" style="color: #fff;"><span class="glyphicon glyphicon-home"></span> Home</a>
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
        <th>UnAssign Employee</th>
        <th>Assigned Project Details</th>
      </tr>
    </thead>
    <tbody>
            <c:forEach items="${employees}" var="value">
               <c:set var="empoyeeDetails" value="${fn:split(value,',')}" />
                <tr>
                <td><c:out value="${empoyeeDetails[0]}"></c:out></td>
                <td><c:out value="${empoyeeDetails[1]}"></c:out></td>
                <td><c:out value="${empoyeeDetails[2]}"></c:out></td>
                <td><c:out value="${empoyeeDetails[3]}"></c:out></td>
                <td><c:out value="${empoyeeDetails[4]}"></c:out></td>
                <td><c:out value="${empoyeeDetails[5]}"></c:out></td>
                <td><c:out value="${empoyeeDetails[6]}"></c:out></td>
                <c:choose>
                 <c:when test="${empoyeeDetails[7] == 'true'}">
                  <td><a href="employee?id=${empoyeeDetails[0]}&action=delete_employee" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</a></td>
                 </c:when>
                 <c:otherwise>
                  <td><a href="employee?id=${empoyeeDetails[0]}&action=restore_employee" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span> Restore</a></td>
                  </c:otherwise>
                 </c:choose>
                 <c:choose>
                 <c:when test="${empoyeeDetails[7] == 'true'}">
                   <td><a href="employee?id=${ empoyeeDetails[0]}&action=update_employee_details" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> edit</a></td>
                 </c:when>
                 <c:otherwise>
                  <td><a href="#" class="btn btn-primary disabled-link"><span class="glyphicon glyphicon-edit"></span> edit</a></td>
                  </c:otherwise>
                 </c:choose>
                 <c:choose>
                 <c:when test="${empoyeeDetails[7] == 'true'}">
                   <td><a href="employee?id=${ empoyeeDetails[0]}&action=assign_project_details" class="btn btn-primary">Assign</a></td>
                   <td><a href="employee?id=${ empoyeeDetails[0]}&action=get_assigned_employee_details" class="btn btn-danger">UnAssign</a></td>
                 </c:when>
                 <c:otherwise>
                  <td><a href="#" class="btn btn-primary disabled-link">Assign</a></td>
                  <td><a href="#" class="btn btn-danger disabled-link">UnAssign</a></td>
                  </c:otherwise>
                 </c:choose>
                 <c:choose>
                 <c:when test="${empoyeeDetails[7] == 'true'}">
                   <td><a href="employee?id=${ empoyeeDetails[0]}&action=assigned_employee_details" class="btn btn-primary">Assign Project Details</a></td>
                 </c:when>
                 <c:otherwise>
                  <td><a href="#" class="btn btn-primary disabled-link">Assign Project Details</a></td>
            
                  </c:otherwise>
                 </c:choose>
                 </tr> 
            </c:forEach>  
    </tbody>
  </table>
</body>
</html>