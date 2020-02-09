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
			<li class="active">TDG Life Cycle</li>
		</ol>
		<div class="">

			<!-- /Thumbnail for Prepare -->
			<!-- Thumbnail for Provision -->
			<diV class="thumbnail gutter demand">
				<h3>Data Generation</h3>
				<p>Generates the required data which is being given as criteria
					for respective schema's</p>
				<ul>
					<li><a href="./tdgOperationsDetails">Test Data Generation</a></li>
					<li><a href="./tdgDashBoardDetails">DashBoard</a></li>
					<li><a href="./tdgDataConditional">Data Conditioner</a></li>
					<li><a href="./tdgDataConditionalDashboard">Data Conditioner DashBoard</a></li>
					<li></li>
				</ul>
				 <div class="bottom-arrow"></div>
			</diV>
			<!-- /Thumbnail for Provision -->


			<div class="clearfloat">&nbsp;</div>
		</div>
	</div>
	<script>
 	menu_highlight('tdg_life_cycle1');
 
 </script>
</body>
</html>
