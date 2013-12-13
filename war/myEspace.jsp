<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Jquery -->
<%@ include file="header.jsp"%>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/themes/ui-lightness/jquery.ui.theme.css" rel="stylesheet">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/themes/ui-lightness/jquery.ui.tabs.css">

 <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=&sensor=false">
    </script>
    <!-- script src="js/map.js"></script-->
<!-- Table-Data -->
<link rel="stylesheet" href="js/data-table/css/demo_table.css" type="text/css" />
<link rel="stylesheet" href="js/data-table/css/demo_table_jui.css" type="text/css" />
<link rel="stylesheet" href="js/data-table/css/data-table.css" type="text/css" />

<script type="text/javascript" src="js/data-table/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/data-table/js/data-table-init.js"></script>
<!-- Table-Data -->
<title>My Espace</title>
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
					<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
					<%@page import="fr.nantes.event.util.Utility"%>
					<div class="templatemo_sidebar_top"></div>
					<div class="templatemo_sidebar">
					
						<div class="sidebar_box">
					
							<div class="sidebar_box_content">
								
								<%if (UserServiceFactory.getUserService().getCurrentUser() != null) {%>
									<button onclick="window.location.href='addEvent.jsp'">Add new event</button>
								<%} else{%>
									<button onclick="alert('You must login to post an event')">Add an event</button>
								<%}
								%>
							</div>
					
						</div>
					
						<!-- displayed only at the index page -->
						<div class="sidebar_box">
					
							<h2>User Menu</h2>
					
							<div class="sidebar_box_content" style="text-align:left; font-size: 12px; font-weight: bold;">
								<a href="myEspace.jsp?page=ev">My events</a> <br>
								<a href="myEspace.jsp?page=sub">My subcriptions</a> <br>
								<div class="cleaner"></div>
							</div>
						</div>	
					</div>
					<div class="templatemo_sidebar_bottom"></div>
					<!-- end of sidebar -->
				</div>
				<!-- end of templatemo_sidebar_wrapper -->

				<div id="templatemo_content">

					<div id="banner">
						<!-- Carte de geolocalisation -->
						<script src="js/map.js"></script>
						<div id="map_wrapper">
						    <div id="map_canvas" class="mapping"></div>
						</div>
					</div>

					<div id="content_top"></div>
					<div id="content_middle">
						<% if(request.getParameter("page") != null && request.getParameter("page").toString().equals("sub")){%>
								<%@ include file="pages/mySubcriptions.jsp"%>
						<%} else{%>
								<%@ include file="pages/myEvents.jsp"%>						
						<%} %>
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