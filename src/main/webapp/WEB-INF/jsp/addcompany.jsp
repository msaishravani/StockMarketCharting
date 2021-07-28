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

				<h1>Add Company <br/> ${message}</h1>

				<form name="form" action="/savecompany" method="post">

					<input type="hidden" name="status" value="yes" />

					<div class="form_settings">

						<p>
							<span>Name :</span><input class="contact" type="text" name="name" required="required"/>
						</p>

						<p>
							<span>Turn Over:</span><input class="contact" type="text"
								name="turnover" required="required"/>
						</p>
						
						<p>
							<span>CEO:</span><input class="contact" type="text"
								name="ceo" required="required"/>
						</p>
						
						<p>
							<span>Directors:</span><input class="contact" type="text"
								name="directors" required="required"/>
						</p>
						
						<p>
							<span>Sector:</span>
							<select name="sector" required="required">
								<option value="">--select--</option>
								<option value="Banking">Banking</option>
								<option value="Finance">Finance</option>
							</select>
						</p>
						
						<p>
							<span>Description:</span><textarea class="contact"
								name="description" required="required"></textarea>
						</p>
						
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Add Company" />
						</p>

					</div>
				</form>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>