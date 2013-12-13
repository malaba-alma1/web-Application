<%@page import="java.util.Date"%>
<%@page import="fr.nantes.event.bean.EventBean"%>
<%@page import="fr.nantes.event.util.Utility"%>
<%@page import="fr.nantes.event.dao.EventDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
/*String date = request.getParameter("date") !=null ? request.getParameter("date") : "";
String nom = request.getParameter("nom") !=null ? request.getParameter("nom") : "";
String sport = request.getParameter("sport") !=null ? request.getParameter("sport") : "";
String stadium = request.getParameter("stadium") !=null ? request.getParameter("stadium") : "";
String creator = request.getParameter("creator") !=null ? request.getParameter("creator") : "";*/

String date = request.getSession().getAttribute("date") !=null ? request.getSession().getAttribute("date").toString() : "";
String sport = request.getSession().getAttribute("sport") !=null ? request.getSession().getAttribute("sport").toString() : "";

String nom = ""; //request.getSession().getAttribute("nom") !=null ? request.getParameter("nom") : "";
String stadium = ""; //request.getSession().getAttribute("stadium") !=null ? request.getParameter("stadium") : "";
String creator = ""; //request.getSession().getAttribute("creator") !=null ? request.getParameter("creator") : "";

request.getSession().removeAttribute("date");
request.getSession().removeAttribute("sport");
//out.println("DATA:"+date+" SPORT:"+sport);
List<EventDao> listEvents = Utility.getUpComingEvents("", sport, date, nom, stadium, creator); 
%>
<c:set var="sport_frm" scope="page" value="<%=sport%>"/>
<c:set var="date" scope="page" value="<%=date%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Jquery -->
<%@ include file="header.jsp"%>
 <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=&sensor=false"></script>
    <!-- script src="js/map.js"></script-->
<!-- Table-Data -->
<link rel="stylesheet" href="js/data-table/css/demo_table.css" type="text/css" />
<link rel="stylesheet" href="js/data-table/css/demo_table_jui.css" type="text/css" />
<link rel="stylesheet" href="js/data-table/css/data-table.css" type="text/css" />

<script type="text/javascript" src="js/data-table/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/data-table/js/data-table-init.js"></script>
<!-- Table-Data -->

<script type="text/javascript">
function initialize() {
<% if(!listEvents.isEmpty() && listEvents.size()>0){%>
    var map;
    var bounds = new google.maps.LatLngBounds();
    var mapOptions = {
        mapTypeId: 'roadmap'
    };

    // Display a map on the page
    map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
    map.setTilt(45);

    // Display multiple markers on a map
    var infoWindow = new google.maps.InfoWindow(), marker;

    // Loop through our array of markers & place each one on the map

    var i = 0;
    "<c:forEach var='event' items='<%=listEvents%>'>"
        var position = new google.maps.LatLng("${event.latitude}", "${event.longitude}");
        bounds.extend(position);
        marker = new google.maps.Marker({
            position: position,
            map: map,
            title: "${event.stadium}"
        });

        // Allow each marker to have an info window
        google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
            	var cont = '<div class="info_content" style="width:150px;z-index: 1;"> <h4>${event.address}</h4> <p>Event: ${event.description}</p> </div>'
                infoWindow.setContent(cont);
                infoWindow.open(map, marker);
            }
        })(marker, i));

        // Automatically center the map fitting all markers on the screen
        map.fitBounds(bounds);
        i = i+1;
   "</c:forEach>"

    // Override our map zoom level once our fitBounds function runs (Make sure it only runs once)
    var boundsListener = google.maps.event.addListener((map), 'bounds_changed', function(event) {
        //this.setZoom(14);
        google.maps.event.removeListener(boundsListener);
    });
<%} else{%>
var myLatlng = new google.maps.LatLng(47.235313, -1.555735);

var mapOptions = {
  zoom: 16,
  center: myLatlng,
  mapMaker: true
};

var map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

var marker = new google.maps.Marker({
	    position: myLatlng,
	    map: map,
	    title: "Nantes University"
	});

// To add the marker to the ma	p, call setMap();
marker.setMap(map);
<%}%>

var w_main = $("#templatemo_content").width();
	//var h_main = $("#content_middle").height();
$("#map-canvas").width(w_main+"px").height("300");
}
 	window.onload = initialize;
</script>
    
<style type="text/css">
#map_wrapper {
    height: 300px;
}
</style>
<title>Your geolocalised event in Nantes</title>
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
					<c:set var="display_form" scope="page" value="yes"/>
					<%@ include file="left.jsp"%>
				</div>
				<!-- end of templatemo_sidebar_wrapper -->

				<div id="templatemo_content">

					<div id="banner">
						<!-- Carte de geolocalisation -->
						<script src="js/map.js"></script>
						<div id="map_wrapper">
						    <div id="map-canvas" class="mapping"></div>
						</div>
					</div>

					<div id="content_top"></div>
					<div id="content_middle">
						<h3>Upcoming Events</h3>
						
						<table cellpadding="0" cellspacing="0" border="0" class="display table-data" id="table-data" width="98%" style="font-size:11px;">
							<thead>
							<tr> <th>Date</th> <th>Event</th> <th>Registered</th> <th>Promotor</th> <th></th> </tr>
							</thead>
							
							<tbody>
							<c:forEach var="event" items="<%=listEvents%>">
								<c:set var="key" scope="request" value="${event.id}"/>
								<c:set var="date" scope="request" value="${event.date}"/>
								<%
									String keytoString = com.google.appengine.api.datastore.KeyFactory.keyToString((com.google.appengine.api.datastore.Key)request.getAttribute("key"));
								 %>
								<tr>
									<td><%=Utility.getDateToString((Date)request.getAttribute("date"), "MM/dd/yyyy HH:mm") %></td>
									<td>
										<a href="detailsEvent.jsp?key=<%=keytoString%>">
											<c:out value="${event.name}"/>
										</a>
									</td>
									<td><c:out value="${event.nbSubscrits}/${event.maxPeoples}"/></td>
									<td><c:out value="${event.userCreated}"/></td>
									<td>
										<%
										if (UserServiceFactory.getUserService().getCurrentUser() == null) {%>
											<img alt="subscribe ico" title="Subscription" src="/images/subscribe1.png" width="60" height="30" style="cursor: pointer;" onclick="alert('You must be log in to subscribe');">
										<%} else if(Utility.isSubcribed(keytoString, UserServiceFactory.getUserService().getCurrentUser().getEmail())){%>
											<b>subcribed</b>
									<%}else{%>
											<img alt="subscribe ico" title="Subscription" src="/images/subscribe1.png" width="60" height="30" style="cursor: pointer;" onclick="window.location.href='/event/subscribe.do?key=<%=keytoString%>'">
											
										<%}
										%>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>

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