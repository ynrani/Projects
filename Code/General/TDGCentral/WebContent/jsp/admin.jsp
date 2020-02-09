<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>TDG Central | Admin</title>
<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
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
	<!-- <script src="include/indexHeader.js"></script> -->
	<jsp:include page="indexHeader.jsp"></jsp:include>
	<div class="container tdm-central">
		<ol class="breadcrumb">
			<li><a href="./index">Home</a></li><li>&#x2f;</li><li class="active">TDG Life Cycle</li>
		</ol>
		<div class="">
			<!-- Thumbnail for Demand -->
			<diV class="thumbnail gutter demand">
				<h4>User</h4>
				<p>Users added by Admin</p>
				<ul>
					<li><a href="./testdaAdmin">Display Users</a></li>
					<li><a href="./tesdaCreateNewUser">Create User</a></li>
					<li><a href="./tesdaMasterDictionary">Upload TDG
							Dictionary</a></li>
					<li><a href="./tdgMasterDictionaryDashboard">TDG
							Dictionary Dashboard</a></li>
				</ul>
				<div class="bottom-arrow"></div>
			</diV>

		</div>
	</div>

	<script>
 	menu_highlight('tdm_command_center1');
 </script>
</body>
</html>