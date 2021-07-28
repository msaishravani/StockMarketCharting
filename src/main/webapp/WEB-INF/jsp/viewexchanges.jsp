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

				<h1>View Exchange's</h1>

				<table align="center" id="customers">
					<tr>
						<th>Exchange ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Address</th>
						<th>Remarks</th>
					</tr>
					<c:forEach items="${exchanges}" var="exchange">
						<tr>
							<td><c:out value="${exchange.id}" /></td>
							<td><c:out value="${exchange.name}" /></td>
							<td><c:out value="${exchange.description}" /></td>
							<td><c:out value="${exchange.address}" /></td>
							<td><c:out value="${exchange.remarks}" /></td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>