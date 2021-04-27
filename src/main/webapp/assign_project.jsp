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
<title>Assign Project</title>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Assign Employee To Project</h1>
<div class="container">
<div class="alert alert-success" role="alert">
         <strong class="text-center">${message}</strong> 
 </div>
<div class="row">
<form action="employee?action=assign_projects" method="post">
    <% String hidden = request.getParameter("id"); %>
    <input type="hidden" name="id" value="<%=hidden %>"/>


<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">Projects To Assign Employee</label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                 
                 <c:forEach items="${projectDetails}" var="value">
                    <c:set var="flag" value="true" />
                    <c:forEach items="${assignProjectDetails}" var="assigned">
                          <c:if test="${value.getIsDelete() == 'true'}">
                             <c:choose>
                               <c:when test="${value.getId() == assigned.getId()}">
                                  <input type="checkbox" name="projects" class="form-check-input" value="${value.getId()}" checked/> <span> ${value.getName()}</span><br>
                                  <c:set var="flag" value="false" />
                                  </c:when>
                              </c:choose>    
                          </c:if>
                       </c:forEach>
                       <c:if test="${value.getIsDelete() == 'true'}">
                           <c:if test="${flag == true }">
                              <input type="checkbox" name="projects" class="form-check-input" value="${value.getId()}" /> <span> ${value.getName()}</span><br>
                           </c:if>
                       </c:if>  
                 </c:forEach> 
                <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Select Your Projects</span>
                <span style="color: red;" id="err_msg"></span> 
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>

 
<div class="text-center">
    <a href="employee.jsp" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> Home</a>
    <input type="submit" class="btn btn-primary" name="submit" id="submit-btn" />
</div>
</form>
</div>
</div>
</body>
</html>