<%@page import="fr.nantes.event.util.EventUtility"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<div id="site_title">
	<a href="" target="_parent"> <img
		src="images/nantes-metropole-logo.svg" alt="Nantes University logo" width="200" height="70" /> 
		<span>Your Geolocalised Sports events in Nantes</span>
	</a>
</div>

<div id="shopping_cart_box">
	<%
	if (UserServiceFactory.getUserService().getCurrentUser() != null) {%>
		Welcome<h3><%=UserServiceFactory.getUserService().getCurrentUser().getNickname()%></h3>
	<%} else{%>
		You're not connected
	<%}
	%>
	
	<p style="padding-top: 45px;">
		Total <span><%=EventUtility.getNbEvents()%> event(s)</span> added
	</p>
</div>