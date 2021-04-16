<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Employee</title>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Employee</h1>
<div class="container">
<div class="add-section text-center">
<a href="create_employee.jsp" style="color: #fff;" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span> Create Employee</a>
<a href="employee?action=display_all_employee" style="color: #fff;" class="btn btn-primary">Display All Employee</a>
<a href="specific_employee.jsp" style="color: #fff;" class="btn btn-primary">Display Specific Employee</a>
<!-- <button type ="button" class="btn btn-danger"><a href="delete_employee.jsp" style="color: #fff;">Delete Employee</a>>
<a href="restore_employee.jsp" style="color: #fff;">restore Employee</a></button>-->
</div>
<br>
<br>
 <div class="alert alert-success" role="alert">
         <strong class="text-center">${message}</strong> 
    </div>
</div> 

<div class="text-center">
<a href="index.jsp" class="btn btn-success">Welcome Page</a>
</div>
</div>
</body>
</html>