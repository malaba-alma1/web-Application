<%@page import="fr.nantes.event.util.EventUtility"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.nantes.event.dao.GuestDao"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="fr.nantes.event.util.Utility"%>
<%@page import="fr.nantes.event.dao.EventDao"%>
<%@page import="java.util.List"%>
<%
String userMail = "";
List<EventDao> listEvents = new ArrayList<EventDao>();
if (UserServiceFactory.getUserService().getCurrentUser() != null) {
	userMail = UserServiceFactory.getUserService().getCurrentUser().getEmail();
	
	//Get all events subcribed
	List<GuestDao> subcriptions = EventUtility.getSubcriptions(userMail, "", "");
	for(GuestDao guest : subcriptions){
		String evenId = guest.getKeyEvent();
		EventDao event = EventUtility.getEventById(evenId);
		listEvents.add(event);
	}
}
//
%>

<h3>My subcriptions</h3>
	<table cellpadding="0" cellspacing="0" border="0" class="display table-data" id="table-data" width="98%" style="font-size:11px;">
		<thead>
		<tr> <th>Date</th> <th>Event</th> <th>Subcribe on</th> <th></th> </tr>
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
				<td><c:out value="${event.userCreated}"/></td>
				<td>
					<div class="block" id="thoughtbot"><button onclick="window.location.href='/event/unsubscribe.do?key=<%=keytoString%>'" style="cursor: pointer;font-size: 13px; width: 100px; height: 25px; padding: 5px 0 6px 0;">Unsubscribe</button></div>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="cleaner"></div>