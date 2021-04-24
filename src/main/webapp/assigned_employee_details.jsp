<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Assigned Employee Details</title>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Assigned project Details</h1>
<div class="container-fluid">
<a href="employee.jsp" class="btn btn-success" style="color: #fff;"><span class="glyphicon glyphicon-home"> Home</span></a>
</div>
<c:choose>
<c:when test="${assignedEmployeeDetails.size() > 0 }">
<table class="table table-hover">
    <thead>
      <tr>
        <th>Id</th>
        <th>Project Name</th>
        <th>Project Manager Name</th>
        <th>Start Date</th>
        <th>End Date</th>
      </tr>
    </thead>
    <tbody>
         
            <c:forEach items="${assignedEmployeeDetails}" var="value">
                <tr>
                <td><c:out value="${value.getId()}"></c:out></td>
                <td><c:out value="${value.getName()}"></c:out></td>
                <td><c:out value="${value.getManagerName()}"></c:out></td>
                <td><c:out value="${value.getStartDate()}"></c:out></td>
                <td><c:out value="${value.getEndDate()}"></c:out></td>
                 </tr> 
            </c:forEach>  
    </tbody>
  </table>
  </c:when>
  <c:otherwise>
  <div class="container">
  <div class="alert alert-danger" role="alert">
     <strong class="text-center">No Records</strong> 
 </div>
 </div>
  </c:otherwise>
  </c:choose>
</body>
</html>