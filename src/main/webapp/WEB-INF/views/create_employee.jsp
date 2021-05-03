<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Create Employee</title>
<meta charset="ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
  .label-style {
      margin-left: 10px; 
      font-weight: bold;
  
  }
  </style>
</head>
<body>
<c:set var="addressess" value="${employee.addressess}"/>
<c:set var="size" value="${addressess.size()}"/>
<c:set var="permanent_address" value="${addressess[0]}"/>
<c:if test="${0 == employee.getId()}">
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Create Employee</h1>
</c:if>

<c:if test="${0 != employee.getId()}">
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Update Employee</h1>
</c:if> 
<div class="container">  
<form:form method="POST"  modelAttribute="employee">
           
	<form:hidden path="id" value="${employee.getId()}"></form:hidden>	
<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                 <form:label path="name" cssClass="label-style">Name:</form:label><span style="color: red; font-size: 20px;">*</span>
                     <!--<label  style="margin-left: 10px; font-weight: bold;">Name</label>  -->
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                    <form:input path="name" cssClass="form-control" value="${employee.name}" required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your name</span>
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
                     <form:label path="desgination" cssClass="label-style">desgination:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                      <form:input path="desgination" cssClass="form-control" value="${employee.desgination}" required="This field is required" />
                     <span id="err_candiate_name" style="color: red;"></span>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your desgination</span>
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
                     <form:label path="email" cssClass="label-style">email:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                      <form:input path="email" type="email" cssClass="form-control" value="${employee.email}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Email</span>
                     <span id="email_err_message"></span>
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
                     <form:label path="phoneNumber" cssClass="label-style">Phone Number:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="phoneNumber" cssClass="form-control" pattern="[1-9]{1}[0-9]{9}" value="${employee.phoneNumber}"  required="This field is required" /> 	
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Phone Number</span>
                     <span id="phone_err_message"></span>
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
                    <form:label path="salary" cssClass="label-style">Salary:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input type="number" path="salary" cssClass="form-control" value="${employee.salary}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your salary</span>
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
                    <form:label path="dob" cssClass="label-style"  type="date">Dob:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input type="date" path="dob" cssClass="form-control" value="${employee.dob}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Date of birth</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>
<form:input type="hidden" path="isDelete" value="1" />
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Address</h1>
<div class="permanent_address">
<form:input type="hidden" path="addressess[0].id" value="${permanent_address.id}" />
<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <form:label path="addressess[0].doorNo" cssClass="label-style">Door No:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[0].doorNo" cssClass="form-control" value="${permanent_address.doorNo}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Door no</span>
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
                     <form:label path="addressess[0].streetName" cssClass="label-style">Street Name:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                      <form:input path="addressess[0].streetName" cssClass="form-control" value="${permanent_address.streetName}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Street Name</span>
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
                    <form:label path="addressess[0].city" cssClass="label-style">City:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                      <form:input path="addressess[0].city" cssClass="form-control" value="${permanent_address.city}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your City</span>
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
                     <form:label path="addressess[0].district" cssClass="label-style">District:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[0].district" cssClass="form-control" value="${permanent_address.district}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your district</span>
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
                    <form:label path="addressess[0].state" cssClass="label-style">State:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[0].state" cssClass="form-control" value="${permanent_address.state}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your state</span>
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
                     <form:label path="addressess[0].country" cssClass="label-style">Country:</form:label><span style="color: red; font-size: 20px;">*</span>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[0].country" cssClass="form-control" value="${permanent_address.country}"  required="This field is required" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your country</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>
</div>
<form:input type="hidden" path="addressess[0].isDelete" value="1" />
<form:input type="hidden" path="addressess[0].addressMode" value="permanent" />
<ul class="list-group list">
                 <li class="list-group-item" style="background-color: #fffff;">
                     
                      <div class="form-group">
                    
                    <div class="row">
                        <div class="col-sm-6">
                        <c:if test="${0 == employee.getId()}">
                        <label style="margin-left: 10px; font-weight: bold;">Would you like to add Temporary address? <span style="color: red; font-size: 20px;">*</span></label>
                       </c:if>
                        <c:if test="${0 != employee.getId()}">
                        <label style="margin-left: 10px; font-weight: bold;">Would you like to Update Temporary address? <span style="color: red; font-size: 20px;">*</span></label>
                       </c:if>
                    </div>
                    
                    <div class="col-sm-3" style="margin-top: 5px;">
                        <input type="radio" name="temporary" value="1" required="This is required"> <span style=" margin-left: 10px; color: #444444; font-family: Open Sans;font-weight: 700;font-style: normal;">Yes</span><br>
                       
                    </div>
                    <div class="col-sm-3" style="margin-top: 5px;">
                        <input type="radio" name="temporary" value="0"> <span style=" margin-left: 10px; color: #444444; font-family: Open Sans;font-weight: 700;font-style: normal;">No</span>
                      
                    </div>
                  
                  </div>
                </div>
                     
                 </li>
             </ul>
               
<div class="temporary_address">
<c:set var="temporary_address" value="${addressess[1]}"/>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Temporay Address</h1>
<form:input type="hidden" path="addressess[1].id" value="${temporary_address.id}"/>
<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                    <form:label path="addressess[1].doorNo" cssClass="label-style">Door No:</form:label>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[1].doorNo" id="temporary_door_no" cssClass="form-control" value="${temporary_address.doorNo}" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Door no</span>
                     <span id="err_temporary_door_no"></span>
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
                     <form:label path="addressess[1].streetName" cssClass="label-style">Street Name:</form:label>
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[1].streetName" id="temporary_street_name" cssClass="form-control" value="${temporary_address.streetName}" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Street Name</span>
                     <span id="err_temporary_street_name"></span>
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
                      <form:label path="addressess[1].city" cssClass="label-style">City:</form:label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[1].city" id="temporary_city" cssClass="form-control" value="${temporary_address.city}" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your City</span>
                     <span id="err_temporary_city"></span>
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
                     <form:label path="addressess[1].district" cssClass="label-style">District:</form:label>    
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[1].district" id="temporary_district" cssClass="form-control" value="${temporary_address.district}" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your district</span>
                     <span id="err_temporary_district"></span>
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
                     <form:label path="addressess[1].state" cssClass="label-style">State:</form:label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <form:input path="addressess[1].state" id="temporary_state"  cssClass="form-control" value="${temporary_address.state}" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your state</span>
                     <span id="err_temporary_state"></span>
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
                     <form:label path="addressess[1].country" cssClass="label-style">Country:</form:label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                    <form:input path="addressess[1].country" id="temporary_country" cssClass="form-control" value="${temporary_address.country}" />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your country</span>
                     <span id="err_temporary_country"></span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>
<form:input type="hidden" path="addressess[1].addressMode" value="temporary"/>
<form:input type="hidden" path="addressess[1].isDelete" value="1" />

</div>

 <div class="alert alert-success" role="alert">
         <strong class="text-center">${message}</strong> 
 </div>
</div> 
<div class="text-center">
<c:if test="${0 == employee.getId()}">
<a href="/employee" class="btn btn-default">Cancel</a>
<input type="submit" class="btn btn-primary" id="create-btn" formaction="/create_employee" />
</c:if>
<c:if test="${0 != employee.getId()}">
<a href="/employee" class="btn btn-default">Cancel</a>
<input type="submit" class="btn btn-primary"  id="update-btn" formaction="/update_employee" />
</c:if>
</div>
</form:form>

<script>
$(document).ready(function(){
	$('.temporary_address').hide();
	$('.alert-success').hide();
	$("input[name='temporary']").click(function(){
		var radioValue = $("input[name='temporary']:checked").val();
		if (1 == radioValue) {
			$('.temporary_address').show();
		} else {
			$('.temporary_address').hide();
		}
	});
	
	$('#create-btn').click(function(){
		var radioValue = $("input[name='temporary']:checked").val();
		if (1 == radioValue) {
			var temporary_door_no = $("#temporary_door_no").val();
			var temporary_street_name = $("#temporary_street_name").val();
			var temporary_city = $("#temporary_city").val();
			var temporary_district = $("#temporary_district").val();
			var temporary_state = $("#temporary_state").val();
			var temporary_country = $("#temporary_country").val();
			var count = 0;
			if (temporary_door_no == '') {
				$('#err_temporary_door_no').text("Mandatory").css("color","red")
				count++;
			} else {
				$('#err_temporary_door_no').text("")
			}
			
			if (temporary_street_name == '') {
				$('#err_temporary_street_name').text("Mandatory").css("color","red")
				count++;
			} else {
				$('#err_temporary_street_name').text("")
			}
			
			if (temporary_city == '') {
				$('#err_temporary_city').text("Mandatory").css("color","red")
				count++;
			} else {
				$('#err_temporary_city').text("")
			}
			
			if (temporary_district == '') {
				$('#err_temporary_district').text("Mandatory").css("color","red")
				count++;
			}else {
				$('#err_temporary_district').text("")
			}
			
			if (temporary_state == '') {
				$('#err_temporary_state').text("Mandatory").css("color","red")
				count++;
			} else {
				$('#err_temporary_state').text("")
			}
			
			if (temporary_country == '') {
				$('#err_temporary_country').text("Mandatory").css("color","red")
				count++;
			} else{
				$('#err_temporary_country').text("")
			}

			if (count != 0) {
				event.preventDefault();
				alert("Please enter temporary address");
			}
		}
	});
	
	$('#update-btn').click(function(){
		var radioValue = $("input[name='temporary']:checked").val();
		if (1 == radioValue) {
			var temporary_door_no = $("#temporary_door_no").val();
			var temporary_street_name = $("#temporary_street_name").val();
			var temporary_city = $("#temporary_city").val();
			var temporary_district = $("#temporary_district").val();
			var temporary_state = $("#temporary_state").val();
			var temporary_country = $("#temporary_country").val();
			var count = 0;
			if (temporary_door_no == '') {
				$('#err_temporary_door_no').text("Mandatory").css("color","red")
				count++;
			} else {
				$('#err_temporary_door_no').text("")
			}
			
			if (temporary_street_name == '') {
				$('#err_temporary_street_name').text("Mandatory").css("color","red")
				count++;
			} else {
				$('#err_temporary_street_name').text("")
			}
			
			if (temporary_city == '') {
				$('#err_temporary_city').text("Mandatory").css("color","red")
				count++;
			} else {
				$('#err_temporary_city').text("")
			}
			
			if (temporary_district == '') {
				$('#err_temporary_district').text("Mandatory").css("color","red")
				count++;
			}else {
				$('#err_temporary_district').text("")
			}
			
			if (temporary_state == '') {
				$('#err_temporary_state').text("Mandatory").css("color","red")
				count++;
			} else {
				$('#err_temporary_state').text("")
			}
			
			if (temporary_country == '') {
				$('#err_temporary_country').text("Mandatory").css("color","red")
				count++;
			} else{
				$('#err_temporary_country').text("")
			}

			if (count != 0) {
				event.preventDefault();
				alert("Please enter temporary address");
			}
		}
	});
	
	$("#phoneNumber").keyup(function(){
		var phone_number = $("#phoneNumber").val();
		if (phone_number.length != 10) {
			$("#phone_err_message").text("Phone Number must match 10 digit").css("color","red");
		} else {
			$("#phone_err_message").text("valid phone number").css("color","green");
		}
	});
	
	$("#email").keyup(function(){
		var email = $("#email").val();
		var email_regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!email_regex.test(email)) {
			$("#email_err_message").text("Invalid email id").css("color","red");
		} else {
			$("#email_err_message").text("valid email id").css("color","green");
		}
	});
});
</script>
</body>
</html>