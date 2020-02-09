<header>
	<div class="top-blue-nav">
		<div class="container">
			<div class="primary-nav">
				<nav>
					<ul>
						<li><a href="./index"><img src="images/home-icon.png"
								width="20" height="20" alt="" /> Home</a></li>
						<li><a href="http://www.capgemini.com/about-capgemini"
							TARGET="_NEW"><img src="images/about-icon.png" width="20"
								height="20" alt="" /> About Us</a></li>
						<li><a href="http://www.in.capgemini.com/contact-capgemini"
							TARGET="_NEW"><img src="images/contact-icon.png" width="20"
								height="20" alt="" /> Contact Us</a></li>
						<li><a href="./logout?logout=true"><img
								src="images/logout-icon.png" width="20" height="20" alt="" />
								Logout</a></li>
					</ul>
				</nav>
			</div>
			<div class="welcome">
				<h5>
					Welcome
					<%out.println((String)session.getAttribute("UserId"));%>
				</h5>
			</div>
			<div class="clearfloat">&nbsp;</div>
		</div>
	</div>
	<div class="navigation">
		<div class="container">
			<div class="logo">
				<img src="images/logo-cap.jpg" width="197" height="48" alt="" />
			</div>
			<div class="main-nav">
				<nav>
					<ul>
						<li id="tdg_life_cycle"><a id="tdg_life_cycle1"
							href="./index">TDG Life Cycle</a></li>
						<li id="tdm_command_center"><a id="tdm_command_center1"
							href="./indexCmdCtr">Command Center</a></li>
					</ul>
				</nav>
			</div>
			<div class="clearfloat">&nbsp;</div>
		</div>
	</div>
	<div class="title-band">
		<div class="container">
			<div class="title">
				<h3>TDG Central</h3>
			</div>
			<div class="advanced-search"></div>
			<div class="clearfloat">&nbsp;</div>
		</div>
	</div>
</header>