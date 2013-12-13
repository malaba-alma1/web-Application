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

</div>
<div class="templatemo_sidebar_bottom"></div>
<!-- end of sidebar -->