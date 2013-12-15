<%@page import="java.util.SortedMap"%>
<%@page import="java.util.TreeMap"%>
<%@page import="fr.nantes.event.util.XmlParser"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
UserService userService = UserServiceFactory.getUserService();
User oUser = userService.getCurrentUser();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.jsp"%>
<!-- script src="js/jquery-ui-timepicker-addon.js"></script>
<link type="text/css" rel="stylesheet" href="css/jquery-ui-timepicker-addon.css"-->
<title>Adding new event</title>
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
						<h3>New event form</h3>
						<% if(request.getSession(true).getAttribute("errorMsg") != null && request.getSession(true).getAttribute("errorMsg").toString().length()> 0){%>
								<p class="errors-msg">
									<%=request.getSession(true).getAttribute("errorMsg")%>
								</p>
							<% 
								request.getSession(true).setAttribute("errorMsg", "");
							} %>
						<jsp:useBean id="Event" class="fr.nantes.event.bean.EventBean" scope="session"/>
						<jsp:setProperty property="*" name="Event"/>
						<form action="/event/add.do" method="POST">
							<table align="center" width="98%" style="text-align: left;">
								<tr>
									<td>Sport</td>
									<td>
										<select name="sport">
											<%=Utility.getOptionsSports("${Event.sport}") %>
										</select>
									</td>
								</tr>
								
								<tr>
									<td>Date</td>
									<td><input type="text" name="date" readonly="readonly" id="datetimepicker" size="20" value="${Event.date}"></td>
								</tr>
								
								<tr>
									<td>Name</td>
									<td><input type="text" name="name" value="${Event.name}" style="width: 400px;"></td>
								</tr>
								
								<tr>
									<td>Description</td>
									<td><textarea rows="3" name="description" style="width: 400px;">${Event.description}</textarea></td>
								</tr>
								
								<tr>
									<td>Stadium</td>
									<td>
										<select name="stadium">
											<%=Utility.getOptionsStadiums("${Event.stadium}")%>
										</select>
									</td>
								</tr>
								
								<tr>
									<td>Max guests</td>
									<td>
										<input type="text" name="maxPeoples" size="2" value="${Event.maxPeoples}" maxlength="3">
									</td>
								</tr>
								
								<tr>
									<td colspan="2" align="center">
										<div class="block" id="clean-gray">
											<button type="submit" style="cursor: pointer">Save event</button>
										</div>
									</td>
								</tr>
							</table>
							<% 
								request.getSession(true).setAttribute("Event", null);
							 %>
						</form>
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