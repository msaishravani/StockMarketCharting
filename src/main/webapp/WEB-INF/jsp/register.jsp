<!DOCTYPE HTML>
<html>
<head>
<%@include file="header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<script src = "js/mainApp.js"></script>
</head>
<body>
	<%@include file="menu1.jsp"%>
	<div id="main">
		<div id="site_content">
			<div id="content">
			
				<h1>Registration Status : ${message}</h1>

				<form name="form" action="/regaction" method="post">

					<input type="hidden" name="role" value="user" />

					<div class="form_settings">

						<p>
							<span>Name :</span><input class="contact" type="text" name="name"
								value="" />
						</p>

						<p>
							<span>User Name :</span><input class="contact" type="text"
								name="username" value="" />
						</p>

						<p>
							<span>Password :</span><input class="contact" type="password"
								name="password" value="" />
						</p>

						<p>
							<span>Email :</span><input class="contact" type="text"
								name="email" value="" />
						</p>

						<p>
							<span>Mobile :</span><input class="contact" type="text"
								name="mobile" value="" />
						</p>

						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Register" />
						</p>

					</div>
				</form>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>