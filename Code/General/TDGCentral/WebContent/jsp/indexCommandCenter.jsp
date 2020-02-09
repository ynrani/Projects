<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>TDG Central | Index</title>
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
<body>

	<jsp:include page="indexHeader.jsp"></jsp:include>

	<div class="container tdm-central">
		<ol class="breadcrumb">
			<li><a href="./index">Home</a></li>
			<li>/</li>
			<li>Command Center</li>
		</ol>
		<div class="">

			<security:authorize access="hasRole('ROLE_ADMIN')">
				<!-- Thumbnail for Admin -->
				<diV class="thumbnail admin">
					<h4>Admin</h4>
					<p>TDM Admin to use this option for access management and Auto
						Scheduling</p>
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/testdisplayAdmin">Manage
								More</a></li>
					</ul>
				</diV>
				<!-- /Thumbnail for Admin -->
			</security:authorize>
			<div class="clearfloat">&nbsp;</div>
		</div>
	</div>
	<script>
 	menu_highlight('tdm_command_center1');
 </script>
</body>
</html>
