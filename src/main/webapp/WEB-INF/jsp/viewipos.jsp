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

				<h1>View IPO's</h1>
				
				<form name="form" action="/displayipos">

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
						
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="View IPO'S"/>
						</p>

					</div>
				</form>

				<table align="center" id="customers">
					<tr>
						<th>ID</th>
						<th>Company ID</th>
						<th>Exchange ID</th>
						<th>Share Price</th>
						<th>Total Shares</th>
						<th>Open Date</th>
						<th>Remarks</th>
					</tr>
					<c:forEach items="${ipos}" var="ipo">
						<tr>
							<td><c:out value="${ipo.id}" /></td>
							<td><c:out value="${ipo.companyId}" /></td>
							<td><c:out value="${ipo.exchangeId}" /></td>
							<td><c:out value="${ipo.sharePrice}" /></td>
							<td><c:out value="${ipo.totalShares}" /></td>
							<td><c:out value="${ipo.openDate}" /></td>
							<td><c:out value="${ipo.remarks}" /></td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>