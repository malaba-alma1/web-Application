<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<div id="search_box" style="font-size: 13px; padding-top: 6px;">
  Realized By <b>BA Mamadou Lamine - E13D192L</b> MASTER 1 Computer Sciences / ALMA (Nantes University)
</div>
<ul style="margin-top: -6px;">
	<li><a href="/" class="current">Home</a></li>
	<!--li><a href="/listEvents.jsp" target="_parent">Events</a></li-->
	<li><a href="/members.jsp">Members</a></li>
	<li><a href="/contacts.jsp">Contacts</a></li>
	<%
	if (UserServiceFactory.getUserService().getCurrentUser() != null) {%>
		<li><a href="myEspace.jsp">My Space</a></li>
		<li><a href="#" onclick="window.location.href='<%=UserServiceFactory.getUserService().createLogoutURL("/")%>'">Logout</a></li>
		<li><a href="https://github.com/malaba-alma1/web-Application" target="_blank"><b style="color: blue;">Sources Code</b></a></li>
		<li><a href="/images/rapport_BA-Mamadou-Lamine.pdf"><b style="color: blue;">Project Report</b></a></li>
	<%} else{%>
		<li><a href="#" onclick="window.location.href='<%=UserServiceFactory.getUserService().createLoginURL(request.getRequestURI())%>'">Login</a></li>
	<%}
	%>
</ul>