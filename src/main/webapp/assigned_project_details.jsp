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
<title>Assigned Project Details</title>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Assigned Employee Details</h1>
<div class="container-fluid">
<a href="project.jsp" class="btn btn-success" style="color: #fff;"><span class="glyphicon glyphicon-home"> Home</span></a></button>
</div>
<table class="table table-hover">
    <thead>
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Desgination</th>
        <th>Email</th>
        <th>Phone number</th>
        <th>Salary</th>
      </tr>
    </thead>
    <tbody>
         
            <c:forEach items="${assignedEmployeeDetails}" var="value">
               <c:set var="assignedEmployee" value="${fn:split(value,',')}" />
                <tr>
                <td><c:out value="${assignedEmployee[0]}"></c:out></td>
                <td><c:out value="${assignedEmployee[1]}"></c:out></td>
                <td><c:out value="${assignedEmployee[2]}"></c:out></td>
                <td><c:out value="${assignedEmployee[3]}"></c:out></td>
                <td><c:out value="${assignedEmployee[4]}"></c:out></td>
                <td><c:out value="${assignedEmployee[5]}"></c:out></td>
                <td><c:out value="${assignedEmployee[6]}"></c:out></td>
                 </tr> 
            </c:forEach>  
    </tbody>
  </table>
  
</body>
</html>