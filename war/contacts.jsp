<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Jquery -->
<%@ include file="header.jsp"%>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=&sensor=false">
</script>

<script type="text/javascript">
	function initialize() {
	var myLatlng = new google.maps.LatLng(47.19822 , -1.58578);

	  var mapOptions = {
	    zoom: 16,
	    center: myLatlng,
	    mapMaker: true
	  };

	  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	  
	  var marker = new google.maps.Marker({
		    position: myLatlng,
		    map: map,
		    title: "43, Rue Bougainville"
		});
	  
	// To add the marker to the ma	p, call setMap();
	  marker.setMap(map);
	}

	function loadScript() {
	  	var script = document.createElement('script');
	  	script.type = 'text/javascript';
	  	script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' + 'callback=initialize';
	  	document.body.appendChild(script);
	  
	  	var w_main = $("#content_middle").width();
		var h_main = $("#content_middle").height();
     	$("#map-canvas").width(w_main*0.90).height("300").css("margin-left", w_main*0.05+"px");
	}
	window.onload = loadScript;
</script>
<title>Contact me</title>
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
				</div>
				<!-- end of templatemo_sidebar_wrapper -->

				<div id="templatemo_content"  style="margin: 0 18% 0 18%;">

					<div id="banner">
					</div>

					<div id="content_top"></div>
					<div id="content_middle">
						<h3>My contact details</h3>
						<table style="width: 80%; text-align: left;" align="center">
							<tr>
								<td>Name</td>
								<td>BA Mamadou Laime</td>
							</tr>
							
							<tr>
								<td>Adresse</td>
								<td>43, Rue Bougainville, 44100, Nantes</td>
							</tr>
							
							<tr>
								<td>Phone number</td>
								<td>07 51 30 38 63</td>
							</tr>
						</table>
						
						<div id="map-canvas"> </div>
						<div class="cleaner"></div>

					</div>
					<div id="content_bottom"></div>

				</div>

				<div class="templatemo_sidebar_wrapper float_r">
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