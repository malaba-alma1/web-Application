<%@page import="fr.nantes.event.util.EventUtility"%>
<%@page import="java.util.Date"%>
<%@page import="fr.nantes.event.dao.EventDao"%>
<%@page import="java.util.List"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="fr.nantes.event.util.Utility"%>
<div class="templatemo_sidebar_top"></div>
<div class="templatemo_sidebar">

	<div class="sidebar_box">

		<div class="sidebar_box_content">
			<div class="block" id="clean-gray">
			<%if (UserServiceFactory.getUserService().getCurrentUser() != null) {%>
				<button onclick="window.location.href='addEvent.jsp'" style="cursor: pointer">Add new event</button>
			<%} else{%>
				<button onclick="alert('You must login to post an event')" style="cursor: pointer">Add an event</button>
			<%}
			%>
			</div>
		</div>

	</div>

	<!-- displayed only at the index page -->
	<c:if test="${display_form=='yes'}">
		<div class="sidebar_box">
	
			<h2>Discounts</h2>
	
			<div class="sidebar_box_content" style="text-align:left;">
				<form action="/index/search.do" method="POST">
					 Date <br> <input type="text" id="date" class="datepicker" name="date" size="15" value="${date}" onchange="form.submit();" /> <br>
					 Sport <br> 
					<select name="sport" onchange="form.submit();">
						<%=Utility.getOptionsSports("${sport_frm}") %>
					</select>
					  <br>
					 <!--input type="submit" value="Check"-->
				</form>
				<div class="cleaner"></div>
			</div>
		</div>	
	</c:if>
	
	<div class="sidebar_box">
		<h2>Past events</h2>
			<% 
			List<EventDao> pastEvents = EventUtility.getPastEvents(0, 3); 
			%>
			<div class="sidebar_box_content">
				<c:forEach var="event" items="<%=pastEvents%>">
					<c:set var="key" scope="request" value="${event.id}"/>
					<c:set var="date" scope="request" value="${event.date}"/>
					<%
						String keytoString = com.google.appengine.api.datastore.KeyFactory.keyToString((com.google.appengine.api.datastore.Key)request.getAttribute("key"));
					 %>
					<div class="news_box">
						<h4>
							<a href="detailsEvent.jsp?key=<%=keytoString%>">${event.name}</a>
						</h4>
						<p style="text-align: left;">
							At <b><%=Utility.getDateToString((Date)request.getAttribute("date"), "MM/dd/yyyy HH:mm")%></b> <br>
							By <b>${event.userCreated}</b><br>
							Subcribed <b>${event.nbSubscrits}</b>
						</p>
					</div>
				</c:forEach>			
			</div>
		</div>
</div>
<div class="templatemo_sidebar_bottom"></div>
<!-- end of sidebar -->