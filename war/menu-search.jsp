<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<ul>
	<li><a href="/" class="current">Home</a></li>
	<!--li><a href="/listEvents.jsp" target="_parent">Events</a></li-->
	<li><a href="/members.jsp">Members</a></li>
	<li><a href="/contacts.jsp">Contacts</a></li>
	<%
	if (UserServiceFactory.getUserService().getCurrentUser() != null) {%>
		<li><a href="myEspace.jsp">My Space</a></li>
		<li><a href="#" onclick="window.location.href='<%=UserServiceFactory.getUserService().createLogoutURL("/")%>'">Logout</a></li>
	<%} else{%>
		<li><a href="#" onclick="window.location.href='<%=UserServiceFactory.getUserService().createLoginURL(request.getRequestURI())%>'">Login</a></li>
	<%}
	%>
</ul>