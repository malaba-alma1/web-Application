<%@page import="fr.nantes.event.dao.EventDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="Event" class="fr.nantes.event.bean.EventBean" scope="request"/>
<% 
String key = request.getParameter("key");
System.out.println("KEY: "+key);
//get event by key from appi
EventDao event = Utility.getEventById(key);

if(event != null){ %>
<jsp:setProperty property="sport" value="<%=event.getSport()%>" name="Event"/>
<jsp:setProperty property="date" value='<%=Utility.getDateToString(event.getDate(), "MM/dd/yyyy HH:mm")%>' name="Event"/>
<jsp:setProperty property="name" value="<%=event.getName()%>" name="Event"/>
<jsp:setProperty property="description" value="<%=event.getDescription()%>" name="Event"/>
<jsp:setProperty property="stadium" value="<%=event.getStadium()%>" name="Event"/>
<jsp:setProperty property="address" value="<%=event.getAddress()%>" name="Event"/>
<jsp:setProperty property="longitude" value="<%=event.getLongitude()%>" name="Event"/>
<jsp:setProperty property="maxPeoples" value="<%=event.getMaxPeoples()%>" name="Event"/>
<jsp:setProperty property="nbSubscrits" value="<%=event.getNbSubscrits()%>" name="Event"/>
<% } %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.jsp"%>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=&sensor=false"></script>

    <script type="text/javascript">
    function initialize() {
    	var lat = parseFloat("<%=event.getLatitude()%>");
    	var lon = parseFloat("<%=event.getLongitude()%>");
    	var myLatlng = new google.maps.LatLng(lat, lon);

    	  var mapOptions = {
    	    zoom: 16,
    	    center: myLatlng,
    	    mapMaker: true
    	  };

    	  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    	  
    	  var marker = new google.maps.Marker({
    		    position: myLatlng,
    		    map: map,
    		    title: "${Event.stadium}"
    		});
    	  
    	// To add the marker to the ma	p, call setMap();
    	  marker.setMap(map);
    	}

    	function loadScript() {
    	  var script = document.createElement('script');
    	  script.type = 'text/javascript';
    	  script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' +
    	      'callback=initialize';
    	  document.body.appendChild(script);
    	  
    	  	var w_main = $("#content_middle").width();
	  		var h_main = $("#content_middle").height();
	        $("#map-canvas").width(w_main*0.90).height("300").css("margin-left", w_main*0.05+"px");
    	}
    	window.onload = loadScript;
    </script>
    <title>${Event.name}</title>
</head>
<body>
	<div id="templatemo_body_wrapper">
		<div id="templatemo_wrapper">
			<div id="templatemo_header">
				<%@ include file="banner.jsp"%>
			</div>
			<!-- end of templatemo_header -->

			<div id="templatemo_menu">
				<%@ include file="menu-search.jsp"%>
			</div>
			<!-- end of templatemo_menu -->
			<div id="templatemo_content_wrapper">

				<div class="templatemo_sidebar_wrapper float_l">
					<%@ include file="left.jsp"%>
				</div>
				<!-- end of templatemo_sidebar_wrapper -->

				<div id="templatemo_content">

					<div id="banner">
					</div>

					<div id="content_top"></div>
					<div id="content_middle">
						<h3>${Event.name}</h3>
						<div style="float: right; margin-right: 20px;">
							<%
								if (UserServiceFactory.getUserService().getCurrentUser() == null) {%>
									<img alt="subscribe ico" title="Subscription" src="/images/subscribe1.png" width="60" height="30" style="cursor: pointer;" onclick="alert('You must be log in to subscribe');">
								<%} else if(Utility.isSubcribed(key, UserServiceFactory.getUserService().getCurrentUser().getEmail())){%>
									<div class="block" id="thoughtbot"><button onclick="window.location.href='/event/unsubscribe.do?key=<%=key%>'" style="cursor: pointer;font-size: 13px; width: 100px; height: 25px; padding: 5px 0 6px 0;">Unsubscribe</button></div>
							<%}else{%>
									<img alt="subscribe ico" title="Subscription" src="/images/subscribe1.png" width="60" height="30" style="cursor: pointer;" onclick="window.location.href='/event/subscribe.do?key=<%=key%>'">
									
								<%}
								%>
						</div>
						<table style="width: 98%; text-align: left;" align="center">
							<tr>
								<td>Sport</td>
								<td>${Event.sport}</td>
							</tr>
							
							<tr>
								<td>Date</td>
								<td>${Event.date}</td>
							</tr>
							
							<tr>
								<td>Name</td>
								<td>${Event.name}</td>
							</tr>
							
							<tr>
								<td>Description</td>
								<td>${Event.description}</td>
							</tr>
							
							<tr>
								<td>Stadium</td>
								<td>${Event.stadium}</td>
							</tr>
							
							<tr>
								<td>Address</td>
								<td>${Event.address}</td>
							</tr>
							
							<tr>
								<td>Max guests</td>
								<td>${Event.maxPeoples}</td>
							</tr>
							
							<tr>
								<td>Subscribed</td>
								<td>${Event.nbSubscrits}</td>
							</tr>
						</table>
						
						<br> <br>
						
						<div id="map-canvas"> </div>
						
						<br> <br>
						<div class="cleaner"></div>

					</div>
					<div id="content_bottom"></div>

				</div>

				<div class="templatemo_sidebar_wrapper float_r">
					<%@ include file="right.jsp"%>
					<!-- end of sidebar -->
				</div>
				<!-- end of templatemo_sidebar_wrapper -->


				<div class="cleaner"></div>

			</div>
			<!-- end of templatemo_content_wrapper -->

		</div>
		<!-- end of templatemo_wrapper -->

		<div class="cleaner"></div>
	</div>
	<!-- end of templatemo_body_wrapper -->

	<div id="templatemo_footer_wrapper"> <!-- mlb:footer -->
		<%@ include file="footer.jsp"%>		
		<!-- end of footer -->
	</div> <!-- mlb:footer end -->
</body>
</html>