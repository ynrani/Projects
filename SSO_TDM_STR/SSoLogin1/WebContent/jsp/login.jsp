<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
    <title>Lc AI | Login</title>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="assestsNew/css/new/angular-material.min.css">
    <script src="assestsNew/js/angular.min.js"></script>
    <script src="assestsNew/js/angular-animate.min.js"></script>
    <script src="assestsNew/js/angular-aria.min.js"></script>
    <script src="assestsNew/js/angular-messages.min.js"></script>
    <script src="assestsNew/js/angular-material.min.js"></script>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  
    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    
    <!-- GOOGLE FONTS-->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css" />
         <script language="javascript">
         angular
            .module('firstApplication', ['ngMaterial'])
            .controller('inputController', inputController);

         function inputController ($scope) {
           $scope.project = {
              comments: 'Comments',    
           };
         }                 
      </script>     	
</head>
<body ng-app="firstApplication" style="background-color: white;">
  <div id="inputContainer" class="inputDemo" ng-controller="inputController as ctrl" ng-cloak>
    <div class="container">
        <div class="row text-center " style="padding-top:100px;">
            <div class="col-md-12">
                <img src="assets/img/symantec-logo-top.png" />
            </div>
        </div>
       	<div class="row ">      
	    	<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">        
	        	<div class="panel-body">
	        	  <md-content layout-padding>
	            	<form:form id="loginForm" name="loginForm" action="${pageContext.request.contextPath}/itaplogin" method="post" autocomplete="on"> 
						<h5>
							<% if(request.getParameter("auth")!=null && request.getParameter("auth").equals("fail")){
							 	out.println("Invalid Username or Password");
							}		
							else if(request.getParameter("logout")!=null && request.getParameter("logout").equals("true")){
								out.println("Logout Successfully");
							}
							else if(request.getParameter("session")!=null && request.getParameter("session").equals("expired")){
								out.println("Session Expired");
							}else if(request.getParameter("session")!=null && request.getParameter("session").equals("alreadyLogged")){
								out.println("User Already Logged In");
							}
							%>	
						</h5><br/>
	              <!--      	<div class="form-group input-group">
	                    	<span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
	                       	<input type="text" name="userid" required="required" class="form-control" placeholder="Your Username " />
	                    </div>
	                    <div class="form-group input-group">
	                          <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
	                          <input type="password" name="password" required="required" class="form-control"  placeholder="Your Password" />
	                    </div>
	                    <div class="form-group">
	                             <label class="checkbox-inline"><input type="checkbox" /> Remember me </label>
	                    </div>
	                    <div class="form-group">
	                    	<input type="submit" value="Login Now" class="btn btn-primary"/> 
	                    </div> -->


							<md-input-container class="md-block"> <label>User
								Name</label> <input required name="userid" ng-model="project.userName" />
							</md-input-container>
							<md-input-container class="md-block"> <label>Password</label>
							<input required type="password" name="password"
								ng-model="project.userEmail" /> </md-input-container>


							
 <md-button style="background-color:#385785;"  class="md-raised md-primary"  type="submit" >Login Now</md-button>

						</form:form>
	              	</md-content>
	        	</div>
	     	</div>
	 	</div>
  	</div>
</div>
<script type="text/javascript"> 
 //loginValidation();
 
 	window.history.forward();
	function noBack() {
		window.history.forward();
	} 
	 
</script>
</body>
</html>
