<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Specific Employee Details</title>
</head>
<body>
<div class="container">
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Individual Employee Details</h1>
<a href="employee.jsp" class="btn btn-success"><span class="glyphicon glyphicon-home"></span> Home</a>
<div class="alert alert-success">
    <strong class="text-center">${message}</strong> 
 </div>
<form action="employee?action=specific_employee" method="post">
  <div class="row">
  <div class="col-md-6">
  <label>Enter your Employee Id</label>
  </div>
  <div class="col-md-6 text-center">
  <input type="number" class="form-control" name="employee_id" required/> 
  </div>
  </div> 
  <br>
  <div class="text-center">
  <input type="submit" class="btn btn-primary" id="individual-emp-btn"/>
  </div>           
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Name</th>
        <th>Desgination</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Salary</th>
        <th>Date of Birth</th>
        <th>Permanent Address</th>
        <th>Temporary Address</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${employeeDetails.get("name")}</td> 
        <td>${employeeDetails.get("desgination")}</td>
        <td>${employeeDetails.get("email")}</td>
        <td>${employeeDetails.get("phoneNumber")}</td>
        <td>${employeeDetails.get("salary")}</td>
        <td>${employeeDetails.get("dob")}</td>
        <td>${employeeDetails.get("permanentAddress")}</td>
        <td>${employeeDetails.get("temporaryAddress")}</td>
      </tr>
    </tbody>
  </table>
  </form>
</div>

</body>
</html>