<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>update Employee</title>
<meta charset="ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Update Employee</h1>
<div class="container">  
<form action="employee?action=update_employee" method="post">
<% String hidden = request.getParameter("id"); %>
<input type="hidden" name="id" value="<%=hidden %>"/>
<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">Name<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="name" class="form-control" value='${employeeDetails.get("name")}' required="This is mandatory"/>
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
                     <label  style="margin-left: 10px; font-weight: bold;">Desgination<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="desgination" class="form-control" value='${employeeDetails.get("desgination")}' required="This is mandatory"/>
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
                     <label  style="margin-left: 10px; font-weight: bold;">Email<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="email" class="form-control"  value='${employeeDetails.get("email")}' required="This is mandatory"/>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Email</span>
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
                     <label  style="margin-left: 10px; font-weight: bold;">Phone Number<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="number" name="phone_number" class="form-control" value='${employeeDetails.get("phoneNumber")}' required="This is mandatory"/>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Phone Number</span>
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
                     <label  style="margin-left: 10px; font-weight: bold;">Salary<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="number" name="salary" class="form-control" value='${employeeDetails.get("salary")}' required="This is mandatory"/>
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
                     <label  style="margin-left: 10px; font-weight: bold;">Date of Birth<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="date" name="dob" class="form-control" value='${employeeDetails.get("dob")}' required="This is mandatory"/>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your Date of birth</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>

<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Address</h1>
<div class="permanent_address">
<input type="hidden" name="permanent_address_id" value="${employeeDetails.get("permanentAddressId")}" />
<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">Door No<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="door_no" class="form-control" value='${employeeDetails.get("permanentAddressDoorNo")}' required="This is mandatory"/>
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
                     <label  style="margin-left: 10px; font-weight: bold;">Street Name<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" class="form-control" name="street_name" value='${employeeDetails.get("permanentAddressStreetName")}' required="This is mandatory" />
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
                     <label  style="margin-left: 10px; font-weight: bold;">City<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="city" class="form-control" value='${employeeDetails.get("permanentAddressCity")}' required="This is mandatory"/>
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
                     <label  style="margin-left: 10px; font-weight: bold;">District<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="district" class="form-control" value='${employeeDetails.get("permanentAddressDistrict")}' required="This is mandatory"/>
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
                     <label  style="margin-left: 10px; font-weight: bold;">State<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="state" class="form-control" value='${employeeDetails.get("permanentAddressState")}' required="This is mandatory"/>
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
                     <label  style="margin-left: 10px; font-weight: bold;">Country<span style="color: red; font-size: 20px;">*</span></label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="country" class="form-control" value='${employeeDetails.get("permanentAddressCountry")}' required="This is mandatory"/>
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your country</span>
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
                        <label style="margin-left: 10px; font-weight: bold;">Would you like to add Temporary address? <span style="color: red; font-size: 20px;">*</span></label>
                       
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
<!-- <button type="button" class="btn btn-primary" id="temporary_btn">Add Address</button>-->
</div>
<c:choose>
 <c:when test="${11 < employeeDetails.size()}">
<div class="temporary_address">
<h1 class="jumbotron" style="text-align: center; background-color: #00A693; color: #fff">Temporary Address</h1>
<input type="hidden" name="temporary_address_id" value="${employeeDetails.get("temporaryAddressId")}" />
<ul class="list-group list">
     <li class="list-group-item" style="background-color: #fffff;">                  
         <div class="form-group">        
             <div class="row">
                 <div class="col-sm-6">
                     <label  style="margin-left: 10px; font-weight: bold;">Door No</label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="temporary_door_no" class="form-control" value='${employeeDetails.get("temporaryAddressDoorNo")}' />
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
                     <label  style="margin-left: 10px; font-weight: bold;">Street Name</label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" class="form-control" name="temporary_street_name" value='${employeeDetails.get("temporaryAddressStreetName")}' />
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
                     <label  style="margin-left: 10px; font-weight: bold;">City</label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="temporary_city" class="form-control" value='${employeeDetails.get("temporaryAddressCity")}' />
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
                     <label  style="margin-left: 10px; font-weight: bold;">District</label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="temporary_district" class="form-control" value='${employeeDetails.get("temporaryAddressDistrict")}' />
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
                     <label  style="margin-left: 10px; font-weight: bold;">State</label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="temporary_state" class="form-control" value='${employeeDetails.get("temporaryAddressState")}' />
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
                     <label  style="margin-left: 10px; font-weight: bold;">Country</label>  
                 </div>
                 <div class="col-sm-6" style="margin-top: 5px;">
                     <input type="text" name="temporary_country" class="form-control" value='${employeeDetails.get("temporaryAddressCountry")}' />
                     <span  style="font-family: Open Sans; font-size: 11px;color: #939393;font-style: italic;font-weight: 400;">Enter your country</span>
                 </div>       
             </div>
         </div>
                    
       </li>
</ul>
</div>
</c:when>
</c:choose>
 
<div class="text-center">
<a href="employee.jsp" class="btn btn-default">Cancel</a>
<input type="submit" class="btn btn-primary" name="submit" id="create-btn" />
</div>
</form>

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
});
</script>
</body>
</html>