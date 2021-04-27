<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Specific Project Details</title>
</head>
<body>
<div class="container">
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Individual Employee Details</h1>
<a href="project.jsp" class="btn btn-success"><span class="glyphicon glyphicon-home"></span> Home</a>
<div class="alert alert-success">
    <strong class="text-center">${message}</strong> 
 </div>
<form action="project?action=specific_project" method="post">
  <div class="row">
      <div class="col-md-6">
          <label>Enter your project Id</label>
      </div>
      <div class="col-md-6 text-center">
          <input type="number" class="form-control" name="project_id" required/> 
      </div>
  </div> 
  <br>
  <div class="text-center">
      <input type="submit" class="btn btn-primary" id="individual-project-btn"/>
  </div>
   <c:if test="${projectDetails != null}">            
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
      <tr>
        <td>${projectDetails.getId()}</td> 
        <td>${projectDetails.getName()}</td>
        <td>${projectDetails.getManagerName()}</td>
        <td>${projectDetails.getStartDate()}</td>
        <td>${projectDetails.getEndDate()}</td>
      </tr>
    </tbody>
  </table>
  </c:if>
  </form>
</div>

</body>
</html>