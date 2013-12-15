<%@page import="fr.nantes.event.util.EventUtility"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="fr.nantes.event.util.Utility"%>
<%@page import="fr.nantes.event.dao.EventDao"%>
<%@page import="java.util.List"%>
<%
String creator = "";
if (UserServiceFactory.getUserService().getCurrentUser() != null) {
	creator = UserServiceFactory.getUserService().getCurrentUser().getEmail();
}
List<EventDao> listEvents = EventUtility.getListEventsBy("", "", "", "", "", creator); 
%>
<h3>My Events</h3>
						
	<table cellpadding="0" cellspacing="0" border="0" class="display table-data" id="table-data" width="98%" style="font-size:11px;">
		<thead>
		<tr> <th>Date</th> <th>Event</th> <th>Registered</th> <th></th> </tr>
		</thead>
		
		<tbody>
		<c:forEach var="event" items="<%=listEvents%>">
			<c:set var="key" scope="request" value="${event.id}"/>
			<%
				String keytoString = com.google.appengine.api.datastore.KeyFactory.keyToString((com.google.appengine.api.datastore.Key)request.getAttribute("key"));
			 %>
			<tr>
				<td><c:out value="${event.date}"/></td>
				<td>
					<a href="detailsEvent.jsp?key=<%=keytoString%>">
						<c:out value="${event.name}"/>
					</a>
				</td>
				<td><c:out value="${event.nbSubscrits}/${event.maxPeoples}"/></td>
				<td></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<div class="cleaner"></div>