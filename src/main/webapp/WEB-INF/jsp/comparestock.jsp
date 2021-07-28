<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.example.demo.model.Stock"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<%@include file="header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<script src = "js/mainApp.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%@include file="menu2.jsp"%>
	<div id="main">
		<div id="site_content">
			<div id="content">

				${message}

				<form name="form" action="/displaycomparestock">

					<div class="form_settings">

						<p>
							<span>Select Company 1:</span> <select name="company1"
								required="required">
								<option value="">--select--</option>
								<c:forEach items="${companys}" var="company">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select> <span>Select Stock Exchange 1:</span> <select name="exchange1"
								required="required">
								<option value="">--select--</option>
								<c:forEach items="${exchanges}" var="exchange">
									<option value="${exchange.id}">${exchange.name}</option>
								</c:forEach>
							</select>
						</p>

						<p>
							<span>Select Company 2:</span> <select name="company2"
								required="required">
								<option value="">--select--</option>
								<c:forEach items="${companys}" var="company">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select> <span>Select Stock Exchange 2:</span> <select name="exchange2"
								required="required">
								<option value="">--select--</option>
								<c:forEach items="${exchanges}" var="exchange">
									<option value="${exchange.id}">${exchange.name}</option>
								</c:forEach>
							</select>
						</p>

						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Compare Stock'S" />
						</p>

					</div>
				</form>
				
				<%
					List<Stock> stocks1 = (List<Stock>) session.getAttribute("stocks1");
					List<Stock> stocks2 = (List<Stock>) session.getAttribute("stocks2");

					if(stocks1 != null && stocks2 != null)
					{
				
						Gson gsonObj = new Gson();
						Map<Object, Object> map = null;
						
						List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
						List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
						
						for(Stock stock : stocks1)
						{
							map = new HashMap(); map.put("label",stock.getTime()); map.put("y",stock.getPrice()); list1.add(map);
						}
						
						for(Stock stock : stocks2)
						{
							map = new HashMap(); map.put("label",stock.getTime()); map.put("y",stock.getPrice()); list2.add(map);
						}
						
						String dataPoints1 = gsonObj.toJson(list1);
						String dataPoints2 = gsonObj.toJson(list2);
					%>
					
					<script type="text/javascript">
						window.onload = function() {
					
							var chart = new CanvasJS.Chart("chartContainer", {
								animationEnabled : true,
								theme : "light2",
								title : {
									text : "Stock Prices"
								},
								axisX : {
									crosshair : {
										enabled : true,
										snapToDataPoint : true
									}
								},
								axisY : {
									title : "Price",
									crosshair : {
										enabled : true,
										snapToDataPoint : true,
										valueFormatString : "##0.00",
									}
								},
								data : [ {
									yValueFormatString : "##0.00 Price",
									dataPoints :
									<%out.print(dataPoints1);%>
								},
								{
									yValueFormatString : "##0.00 Price",
									dataPoints :
									<%out.print(dataPoints2);%>
								} ]
							});
							chart.render();
					
						}
					</script>
					
					<div id="chartContainer" style="height: 370px; width: 100%;"></div>
					<script src="js/canvasjs.min.js"></script>
				<%
						session.removeAttribute("stocks1");
						session.removeAttribute("stocks2");
					} 
				%>
				
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>