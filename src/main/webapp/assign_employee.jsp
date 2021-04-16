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
<title>Assign Employee</title>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Assign Employee To Project</h1>
<div class="container">
<div class="alert alert-success" role="alert">
         <strong class="text-center">${message}</strong> 
 </div>
<div class="row">
<form action="project?action=assign_employees" method="post">
<% String hidden = request.getParameter("id"); %>
<input type="hidden" name="id" value="<%=hidden %>"/>

<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">Employee To Assign Project</label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                 <c:forEach items="${employeeDetails}" var="value">
                    <c:set var="employee" value="${fn:split(value,',')}" />
                    <c:choose>
                 <c:when test="${employee[7] == 'true'}">
                  <input type="checkbox" name="employees" class="form-check-input" value="${employee[0]}" /> <span> ${employee[1]}</span><br>
                 </c:when>
                 </c:choose> 
                </c:forEach> 
                <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Select Your Employee</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>
<div class="text-center">
<a href="project.jsp" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> Home</a>
<input type="submit" class="btn btn-primary" name="submit" id="submit-btn" />
</div>
</form>
</div>
</div>
</body>
</html>