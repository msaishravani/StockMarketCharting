<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

				<h1>Add IPO <br/> ${message}</h1>

				<form name="form" action="/saveipo" method="post">

					<div class="form_settings">

						<p>
							<span>Select Company:</span>
							<select name="companyId" required="required">
								<option value="">--select--</option>
								<c:forEach items="${companys}" var="company">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select>
						</p>
						
						<p>
							<span>Select Stock Exchange:</span>
							<select name="exchangeId" required="required">
								<option value="">--select--</option>
								<c:forEach items="${exchanges}" var="exchange">
									<option value="${exchange.id}">${exchange.name}</option>
								</c:forEach>
							</select>
						</p>
	
						<p>
							<span>Share Price :</span><input class="contact" type="text" name="sharePrice" required="required"/>
						</p>
						
						<p>
							<span>Total Shares :</span><input class="contact" type="text" name="totalShares" required="required"/>
						</p>
						
						<p>
							<span>Open Date :</span><input class="contact" type="date" name="openDate" required="required"/>
						</p>
						
						<p>
							<span>Remarks:</span><textarea class="contact"
								name="remarks" required="required"></textarea>
						</p>
						
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Add IPO"/>
						</p>

					</div>
				</form>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>