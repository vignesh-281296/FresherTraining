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
<title>Display All Project</title>
<style>
.disabled-link{
 pointer-events: none;
}
</style>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Project Details</h1>
<div class="container-fluid">
<a href="project.jsp" class="btn btn-success" style="color: #fff;"><span class="glyphicon glyphicon-home"></span> Home</a>
</div>
<table class="table table-hover">
    <thead>
      <tr>
        <th>Id</th>
        <th>Project Name</th>
        <th>Project Manager Name</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Status</th>
        <th>Edit</th>
        <th>Assign Employee</th>
        <th>Unassign Project</th>
        <th>Assigned Employee</th>
      </tr>
    </thead>
    <tbody>
         
            <c:forEach items="${projects}" var="value">
               <c:set var="projectDetails" value="${fn:split(value,',')}" />
                <tr>
                <td><c:out value="${projectDetails[0]}"></c:out></td>
                <td><c:out value="${projectDetails[1]}"></c:out></td>
                <td><c:out value="${projectDetails[2]}"></c:out></td>
                <td><c:out value="${projectDetails[3]}"></c:out></td>
                <td><c:out value="${projectDetails[4]}"></c:out></td>
                <c:choose>
                 <c:when test="${projectDetails[5] == 'true'}">
                  <td><a href="project?id=${projectDetails[0]}&action=delete_project" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</a></td>
                 </c:when>
                 <c:otherwise>
                  <td><a href="project?id=${projectDetails[0]}&action=restore_project" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span> Restore</a></td>
                  </c:otherwise>
                 </c:choose>
                 <c:choose>
                 <c:when test="${projectDetails[5] == 'true'}">
                   <td><a href="project?id=${ projectDetails[0]}&action=update_project_detail" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> edit</a></td>
                 </c:when>
                 <c:otherwise>
                  <td><a href="#" class="btn btn-primary disabled-link"><span class="glyphicon glyphicon-edit"></span> edit</a></td>
                  </c:otherwise>
                 </c:choose>
                 <c:choose>
                 <c:when test="${projectDetails[5] == 'true'}">
                   <td><a href="project?id=${ projectDetails[0]}&action=assign_employee_details" class="btn btn-primary">Assign</a></td>
                   <td><a href="project?id=${ projectDetails[0]}&action=get_assigned_project_details" class="btn btn-danger">UnAssign</a></td>
                 </c:when>
                 <c:otherwise>
                  <td><a href="#" class="btn btn-primary disabled-link">Assign</a></td>
                  <td><a href="#" class="btn btn-danger disabled-link">UnAssign</a></td>
                  </c:otherwise>
                 </c:choose>
                 <c:choose>
                 <c:when test="${projectDetails[5] == 'true'}">
                   <td><a href="project?id=${ projectDetails[0]}&action=assigned_employee_details" class="btn btn-primary">Assigned Employee Details</a></td>
                 </c:when>
                 <c:otherwise>
                  <td><a href="#" class="btn btn-primary disabled-link">Assigned Employee Details</a></td>
                  </c:otherwise>
                 </c:choose>
                 </tr> 
            </c:forEach>  
    </tbody>
  </table>
</body>
</html>