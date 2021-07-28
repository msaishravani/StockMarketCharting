
<%
	String role = (String) request.getSession().getAttribute("role");
	Integer userid = (Integer) request.getSession().getAttribute("userid");

	if (role == null || userid == null) {
		RequestDispatcher rd = request.getRequestDispatcher("/logout");
		rd.forward(request, response);//method may be include or forward  
		return;
	}
%>

<div id="header">
	<div id="logo">
		<div id="logo_text">
			<!-- class="logo_colour", allows you to change the colour of the text -->
			<h3>
				<a href="#"><font color="white">Stock Market Management</font></a>
			</h3>
			<p align="right"><a href="/logout"><font color="red" size="5">Logout</font></a></p>
			<br /> <br />
		</div>
	</div>
	<div id="menubar">
		<%
			if (role.equals("admin")) {
		%>
				<ul id="menu">
					<li role="presentation"><a href="/addcompany">Add Company</a></li>
					<li role="presentation"><a href="/viewcompanys">Company's</a></li>
					<li role="presentation"><a href="/addexchange">Add Exchange</a></li>
					<li role="presentation"><a href="/viewexchanges">Exchanges</a></li>
					<li role="presentation"><a href="/addipo">Add IPO</a></li>
					<li role="presentation"><a href="/viewipos">IPO'S</a></li>
					<li role="presentation"><a href="/addstock">Add Stock</a></li>
					<li role="presentation"><a href="/viewstocks">Stock'S</a></li>
					
				</ul>
		<%
			} else if (role.equals("user")) {
		%>
				<ul id="menu">
					<li role="presentation"><a href="/viewcompanys">View Company's</a></li>
					<li role="presentation"><a href="/viewipos">View IPO'S</a></li>
					<li role="presentation"><a href="/viewstocks">View Stock'S</a></li>
				</ul>
		<%
			}
		%>
	</div>
</div>
<div id="content_header"></div>