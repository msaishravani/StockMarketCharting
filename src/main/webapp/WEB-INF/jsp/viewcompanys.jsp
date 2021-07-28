<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<script src = "js/mainApp.js"></script>

</head>
<body ng-app="CompanyManagement" ng-controller="CompanyController" n>

	<%@include file="menu2.jsp"%>
	<div id="main">
		<div id="site_content">
			<div id="content">

				<h1>View Company's</h1>

				<table align="center" id="customers">
					<tr>
						<th>Company ID</th>
						<th>Name</th>
						<th>Turnover</th>
						<th>CEO</th>
						<th>Director's</th>
						<th>Sector</th>
						<th>Description</th>
						<th>Status</th>
						<%
							if (role.equals("admin")) {
						%>
								<th>Activate/Deactivate</th>
						<%
							}
						%>

					</tr>
					<c:forEach items="${companys}" var="company">
						<tr>
							<td><c:out value="${company.id}" /></td>
							<td><c:out value="${company.name}" /></td>
							<td><c:out value="${company.turnover}" /></td>
							<td><c:out value="${company.ceo}" /></td>
							<td><c:out value="${company.directors}" /></td>
							<td><c:out value="${company.sector}" /></td>
							<td><c:out value="${company.description}" /></td>
							<td><c:out value="${company.status}" /></td>
							<%
								if (role.equals("admin")) {
							%>
									<c:if test="${company.status=='yes'}">
										<td><a
											href="/activatecompany?companyid=${company.id}&status=no">deactivate</a></td>
									</c:if>
									<c:if test="${company.status=='no'}">
										<td><a
											href="/activatecompany?companyid=${company.id}&status=yes">activate</a></td>
									</c:if>
							<%
								}
							%>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>