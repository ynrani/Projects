<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
	<div class="navbar-header page-scroll">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<img src="assets/img/symantec-logo-top.png" class="logo">
	</div>
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav navbar-right">
			<li class="hidden"><a href="#page-top"></a></li>
			<c:if test="${sessionScope.ROLE eq 'ROLE_ADMIN'}">
				<li class="dropdown" style="margin-right: 7px">
					<a class="dropdown-toggle font-style" data-toggle="dropdown" href="#">Services <span class="caret"></span></a>
					<ul class="main_nav dropdown-menu header-dropdown header-dropdownServices">
						<li style="margin-top: 10px"><a href="#">Issue Troubleshoot</a></li>
						<li class="dropdown-li-margin"><a href="#">Use Full Queries</a></li>
					</ul></li>
			</c:if>
		 
			<li style="margin-right: 7px"><a class="page-scroll font-style" href="#">REPORTS</a></li>
			<!--<li style="margin-right:7px"> <a class="page-scroll font-style" href="#about">ABOUT US</a></li>-->
			<li style="margin-right: 4px">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<a href="javascript:document.getElementById('logout').submit();" title="Logout">LOGOUT</a>
				</c:if>
				<c:url value="/logout?logout=true" var="logoutUrl" />
				<form id="logout" action="${logoutUrl}" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</li>
			<div class="clearfix"></div>
		</ul>
	</div><!-- /.navbar-collapse -->
</div>
<div id="ajaxloader" class="modal" style="display: none">
	<div class="center">
		<img alt="" src="assets/img/loader.gif" />
	</div>
</div>