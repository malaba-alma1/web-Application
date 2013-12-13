<%@page import="java.util.Date"%>
<%@page import="fr.nantes.event.util.Utility"%>
<%@page import="fr.nantes.event.dao.EventDao"%>
<%@page import="java.util.List"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<div class="templatemo_sidebar_top"></div>
<div class="templatemo_sidebar">

	<div class="sidebar_box">

		<h2>Last events added</h2>
<% 
List<EventDao> lastEvents = Utility.getLastEvents(0, 3); 
%>
		<div class="sidebar_box_content">
			<c:forEach var="event" items="<%=lastEvents%>">
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
						For <b><%=Utility.getDateToString((Date)request.getAttribute("date"), "MM/dd/yyyy HH:mm")%></b> <br>
						By <b>${event.userCreated}</b>
					</p>
				</div>
			</c:forEach>			
		</div>
		<!-- end of sidebar_box_content -->
	</div>
	<!-- end of sidebar_box ( news ) -->

	<div class="sidebar_box">
	<%if (UserServiceFactory.getUserService().getCurrentUser() == null) {%>
		<h2>Newsletter</h2>
		<div class="sidebar_box_content">
			<div id="newsletter_box">
				<form action="/index/newsletter.do" method="POST">
					<label>Please enter your email address to subscribe
						our newsletter.</label> <input type="text" value="" name="email" size="10" class="input_field" title="usernmae" />
						<div class="block" id="clean-gray">
							<button type="submit" style="cursor: pointer">Subscribe</button>
						</div>
				</form>
			</div>
			<div class="cleaner"></div>
		</div>
	<%}%>

	</div>
	<!-- end of sidebar_box ( newsletter ) -->

</div>
<div class="templatemo_sidebar_bottom"></div>