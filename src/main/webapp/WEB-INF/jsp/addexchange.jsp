<!DOCTYPE HTML>
<html>
<head>
<%@include file="header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<script src = "js/mainApp.js"></script>
</head>
<body>
	<%@include file="menu2.jsp"%>
	<div id="main">
		<div id="site_content">
			<div id="content">

				<h1>Add Exchange <br/> ${message}</h1>

				<form name="form" action="/saveexchange" method="post">

					<div class="form_settings">

						<p>
							<span>Name :</span><input class="contact" type="text" name="name" required="required"/>
						</p>
						
						<p>
							<span>Description:</span><textarea class="contact"
								name="description" required="required"></textarea>
						</p>
						
						<p>
							<span>Address:</span><textarea class="contact"
								name="address" required="required"></textarea>
						</p>
						
						<p>
							<span>Remarks:</span><textarea class="contact"
								name="remarks" required="required"></textarea>
						</p>
						
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Add Exchange"/>
						</p>

					</div>
				</form>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>