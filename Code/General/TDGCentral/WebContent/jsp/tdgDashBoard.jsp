<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>TDG Central | DashBoard</title>
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
<script type="text/javascript" src="js/additional-methods.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if("${tdgRequestListDTO.messageConstant}" == 'SUCCESS'){
			$('#errors').html('');
			 $('#success').html('${tdgRequestListDTO.message}');
				$('#success').show(); 
		 }else if("${tdgRequestListDTO.messageConstant}" == 'FAILED'){
			 $('#success').html('');
				$('#success').hide();
				$('#errors').html('${tdgRequestListDTO.message}');
				$('#errors').show();
		 }
	});
	
	</script>
</head>

<body>
	<div id="main" class="wrapper">
		<!--  <script src="include/header.js"></script> -->
		<jsp:include page="header.jsp"></jsp:include>
		<div id="tabs-1" class="container">
			<ol class="breadcrumb">
				<li><a href="./index">Home</a></li>
				<li>&#x2f;</li>
				<li><a href="./index">TDG Life Cycle</a></li>
				<li>&#x2f;</li>
				<li class="active">DashBoard</li>
			</ol>
			<h2 style="color: #0098cc">TDG Dashboard</h2>
			<form:form id="tdgDashBoardForm" name="tdgDashBoardForm"
				action="${pageContext.request.contextPath}/tdgDashBoardDetails"
				modelAttribute="tdgRequestListDTO">
				<div id="errors" class="errorblock" style="display: none"></div>
				<div id="success" class="successblock" style="display: none"></div>
				<input type="hidden" name="userId" value="${sessionScope.userId}" />
				<table style="width: 100%; border: 0; font-size: 13px;"
					cellpadding="4">
					<tbody>

						<tr>
							<td class="lable-title" width="35%" align="left" valign="middle">Request
								ID :</td>
							<td class="flied-title" width="20%" align="left" valign="middle"><form:input
									path="requestid" class="form-control" /></td>
						</tr>
					</tbody>
				</table>

				<table style="width: 100%; border: 0; font-size: 13px;"
					cellpadding="4">
					<tbody>
						<tr>
							<td colspan="4" align="center" valign="middle"><input
								type="submit" name="search" id="Search"
								class="btn-primary btn-cell"
								value="<spring:message code="button.serch"/>"> <input
								type="reset" value="Reset" id="reset"
								class="btn-primary btn-cell"
								onClick="clearFields('./tdgDashBoardDetails');"></td>
						</tr>
					</tbody>
				</table>
				<br />
				<br />
				<div id="searchResult">
					<c:choose>
						<c:when
							test="${tdgRequestListDTO ne null  && tdgRequestListDTO.listTdgRequestListDTO ne null }">

							<%
									int currentPage = (Integer) request.getAttribute("currentPage");
									int count1 = currentPage - 1;
									count1 = count1 * 10;
					 
								%>

							<div class="nav" id="myid">
								<table id="search_output_table" class="hoverTable" border="0"
									cellpadding="0" cellspacing="1"
									style="width: 100%; font-size: 13px;">
									<thead>
										<tr>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Request Id</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Dictionary Name</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Records Generated</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Conditions</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Created By</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Data File</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tdgRequestListDTO.listTdgRequestListDTO}"
											var="tdgRequestListDTOs" varStatus="status">
											<tr>
												<td align="left">${tdgRequestListDTOs.requestid}</td>
												<td align="left">${tdgRequestListDTOs.schemaname}</td>
												<td align="left">${tdgRequestListDTOs.requestCount}</td>
												<td align="left">${tdgRequestListDTOs.conditions}</td>
												<td align="left">${tdgRequestListDTOs.userid}</td>
												<td align="left"><form:select path="dataFile"
														id="dataFile${status.index}">
														<form:option value=".xls">xls</form:option>
														<form:option value=".pdf">pdf</form:option>
														<form:option value=".csv">csv</form:option>
													</form:select><a id="dhref" href="#"
													onclick="performTdgRequestDownload('${tdgRequestListDTOs.conditions}','${tdgRequestListDTOs.requestid}','${status.index}')"><img
														src="./images/download.png" alt="download" height="25"
														width="25"></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<ul class="grdPagination">
								<%
			                  				int noOfPages = (Integer) request.getAttribute("noOfPages");
			                  				int startPage = (Integer) request.getAttribute("startPage");
			                  				int lastPage = (Integer) request.getAttribute("lastPage");
			                  		  
											if (currentPage != 1) {
			   							%>
								<li><a href="tdgDashBoardDetails?page=<%= 1 %>">First</a>
									<div>First</div></li>
								<li><a href="tdgDashBoardDetails?page=<%= currentPage-1 %>">&lt;
										Prev</a>
									<div>&lt; Prev</div> <%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
								<li class="disable"><a
									href="tdgDashBoardDetails?page=<%= 1 %>">First</a>
									<div>First</div></li>
								<li class="disable"><a
									href="tdgDashBoardDetails?page=<%= currentPage-1 %>">&lt;
										Prev</a>
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
								<li><a href="tdgDashBoardDetails?page=<%= i %>"
									id="employeeLink"><u><%= i %></u></a></li>
								<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
								<li><a href="tdgDashBoardDetails?page=<%= currentPage+1 %>">Next
										&gt;</a>
									<div>Next &gt;</div></li>
								<li><a href="tdgDashBoardDetails?page=<%= noOfPages %>">Last</a>
									<div>Last</div></li>
								<%
											} else {
											    if(noOfPages > 1) {
										%>
								<li class="disable"><a
									href="tdgDashBoardDetails?page=<%= currentPage+1 %>">Next
										&gt;</a>
									<div>Next &gt;</div></li>
								<li class="disable"><a
									href="tdgDashBoardDetails?page=<%= noOfPages %>">Last</a>
									<div>Last</div></li>
								<%
											    }
											}
										%>
							</ul>
						</c:when>
					</c:choose>
				</div>
			</form:form>
		</div>
		<script src="include/footer.js"></script>
	</div>

	<script>
    menu_highlight('tdm_command_center1');    
    $("#search_output_table").tablesorter({
 	    widgets: ['zebra']
 	  });
    $(".table tr:odd").css('background-color', '#ffffff');
    $(".table tr:even").addClass('even'); 
    
    var performTdgRequestDownload=function(conditions,reqId,index){
       	  if(conditions == ''){
 		  	$('#errors').html('Conditions are absent unable to perform the export');
 			$('#errors').show();
 			 return;
    	}else{
	    	var selected=$("#dataFile"+index).val();
	    	if(selected == ".csv"){
	         	document.location.href='${pageContext.request.contextPath}/downloadTdgCSVRequest?dataFile='+selected+'&reqId='+reqId;
	    	}
	    	else{
	         	document.location.href='${pageContext.request.contextPath}/downloadTdgRequest?dataFile='+selected+'&reqId='+reqId;
	    	}
    	}
     }
</script>
</body>
</html>
