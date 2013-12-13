<%@page import="fr.nantes.event.bean.EventBean"%>
<%@page import="fr.nantes.event.util.Utility"%>
<%@page import="fr.nantes.event.dao.EventDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
String creator = request.getParameter("usr") !=null ? request.getParameter("usr") : "###";
List<EventDao> listEvents = Utility.getListEventsBy("", "", "", "", "", creator); 
%>
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

<title>Event of <%=creator%></title>
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
						<h3>Events of <%=creator %></h3>
						
						<table cellpadding="0" cellspacing="0" border="0" class="display table-data" id="table-data" width="98%" style="font-size:11px;">
							<thead>
							<tr> <th>Date</th> <th>Event</th> <th>Registered</th> </tr>
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