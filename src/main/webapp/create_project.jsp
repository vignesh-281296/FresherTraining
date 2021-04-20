<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<title>Create Project</title>
<meta charset="ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${null == projectDetails.getId()}">
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Create Project</h1>
</c:if>

<c:if test="${null != projectDetails.getId()}">
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Update Project</h1>
</c:if>

<div class="container">
<form  method="post">
<% String hidden = request.getParameter("id"); %>
<input type="hidden" name="id" value="<%=hidden %>"/>
<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">Project Name<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="project_name" class="form-control" value='${projectDetails.getName()}' required="This is mandatory"/>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your project name</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>

<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">Project Manager Name <span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="project_manager_name" class="form-control" value="${projectDetails.getManagerName()}" required="This is mandatory"/>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your project manager name</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>

<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">Start Date<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="date" name="start_date" class="form-control" value="${projectDetails.getStartDate()}" required="This is mandatory"/>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your start date</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>

<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">End Date<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="date" name="end_date" class="form-control" value="${projectDetails.getEndDate()}" required="This is mandatory"/>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your end date</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>

</div>

<div class="text-center">
    <a href="project.jsp" class="btn btn-default">Home</a>
    
    <c:if test="${null == projectDetails.getId()}">
    <input type="submit" class="btn btn-primary" name="create" id="create-btn" formaction="project?action=create_project"/>
    </c:if>
    <c:if test="${null != projectDetails.getId()}">
    <input type="submit" class="btn btn-primary" name="update" id="create-btn" formaction="project?action=update_project"/>
    </c:if>
</div>
</form>

</body>
</html>