<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>TDG Central | Display Users</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script src="js/html5.js"></script>
<link href="css/theme.default.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<div id="main" class="wrapper">
		<!-- <script src="include/indexHeader.js"></script> -->
		<jsp:include page="indexHeader.jsp"></jsp:include>

		<div id="tabs-1" class="container">
			<ol class="breadcrumb">
				<li><a href="./index">Home</a></li><li>&#x2f;</li>
				<li><a href="./testdisplayAdmin">Admin</a></li><li>&#x2f;</li>
				<li class="active">TDG Life Cycle</li>
			</ol>

			<form:form
				action="${pageContext.request.servletContext.contextPath}/tesdaCreateNewUser">
				<c:choose>
					<c:when test="${!empty displayUser}">

						<%
						int currentPage = (Integer) request
											.getAttribute("currentPage");
									int count1 = currentPage - 1;
									count1 = count1 * 10;
					%>

						<table id="displayUsers" class="table tablesorter" border="0"
							cellpadding="0" cellspacing="1"
							style="width: 100%; font-size: 13px;">
							<thead>
								<tr>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">User
										Id</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">User
										Name</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Active
										?</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Email
										Id</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Mobile
										No.</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Role</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Edit
										Action</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Delete
										Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${displayUser}" var="item">
									<tr>
										<td>${item.userId}</td>

										<td>${item.username}</td>

										<td>${item.enabled}</td>

										<td>${item.emailId}</td>

										<td>${item.mobileNo}</td>
										<td><c:if
												test="${item.tdmUserAuthDTO.role == 'ROLE_ADMIN' }">
                                                 Admin
                                      </c:if> <c:if
												test="${item.tdmUserAuthDTO.role == 'ROLE_USER' }">
                                                 User
                                         </c:if></td>
										<td><a
											href="${pageContext.request.contextPath}/editUser?userId=${item.userId}">Edit</a></td>
										<td><a href="#"
											onClick="getDeleteUsers('${item.userId}')">delete</a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>


						<!-- Pagination Starts -->
						<ul class="grdPagination">
							<%
			                  				int noOfPages = (Integer) request.getAttribute("noOfPages");
			                  				int startPage = (Integer) request.getAttribute("startPage");
			                  				int lastPage = (Integer) request.getAttribute("lastPage");
			                  		  
											if (currentPage != 1) {
			   							%>
							<li><a href="testdaAdmin?page=<%= 1 %>">First</a>
								<div>First</div></li>
							<li><a href="testdaAdmin?page=<%= currentPage-1 %>">&lt;
									Prev</a>
								<div>&lt; Prev</div> <%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
							<li class="disable"><a href="testdaAdmin?page=<%= 1 %>">First</a>
								<div>First</div></li>
							<li class="disable"><a
								href="testdaAdmin?page=<%= currentPage-1 %>">&lt; Prev</a>
								<div>&lt; Prev</div> <%
			   								 	}
			   								}
											if(noOfPages > 1) {
			    								for (int i=startPage; i<=lastPage; i++) {
			    									if(currentPage == i) {
			   			 				%>
							<li class="active"><a href="#"><%= i %></a>
								<div><%= i %></div></li>
							<%
			    									} else {
			    						%>
							<li><a href="testdaAdmin?page=<%= i %>" id="employeeLink"><u><%= i %></u></a></li>
							<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
							<li><a href="testdaAdmin?page=<%= currentPage+1 %>">Next
									&gt;</a>
								<div>Next &gt;</div></li>
							<li><a href="testdaAdmin?page=<%= noOfPages %>">Last</a>
								<div>Last</div></li>
							<%
											} else {
											    if(noOfPages > 1) {
										%>
							<li class="disable"><a
								href="testdaAdmin?page=<%= currentPage+1 %>">Next &gt;</a>
								<div>Next &gt;</div></li>
							<li class="disable"><a
								href="testdaAdmin?page=<%= noOfPages %>">Last</a>
								<div>Last</div></li>
							<%
											    }
											}
										%>
						</ul>

						<!-- Pagination Ends -->



						<table style="width: 50%; border: 0; font-size: 13px;"
							cellpadding="4">
							<tbody>
								<tr>
									<td colspan="4" align="center" valign="middle"><input
										type="submit" name="newUser" id="newUser"
										class="btn-primary btn-cell" value="Create New User">
									</td>
								</tr>
							</tbody>
						</table>

					</c:when>
					<c:otherwise>
						<h3 align="center">
							<font color="red">No USER !Please Add USER</font>
						</h3>

						<table style="width: 50%; border: 0; font-size: 13px;"
							cellpadding="4">
							<tbody>
								<tr>
									<td colspan="4" align="center" valign="middle"><input
										type="submit" name="newUser" id="newUser"
										class="btn-primary btn-cell" value="Create New User">
									</td>
								</tr>
							</tbody>
						</table>
					</c:otherwise>

				</c:choose>

			</form:form>
		</div>
	</div>
</body>
<script>


	menu_highlight('tdm_command_center1');


function getDeleteUsers(userId){
	if (confirm('Are you sure to delete the user?')) {
	   document.location.href="./deleteUser?userId="+userId;
	}
}
   $("#displayUsers").tablesorter({
	    widgets: ['zebra']
	  });
   $(".table tr:odd").css('background-color', '#ffffff');
   $(".table tr:even").addClass('even'); 
   
  
</script>
</html>