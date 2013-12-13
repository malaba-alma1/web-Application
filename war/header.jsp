<%@page import="fr.nantes.event.util.UserUtility"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"-->

<link type="text/css" rel="stylesheet" href="css/common.css">
<link type="text/css" rel="stylesheet" href="css/templatemo.css"/>
<link type="text/css" rel="stylesheet" href="css/button.css"/>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/themes/ui-lightness/jquery.ui.theme.css" rel="stylesheet">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/themes/ui-lightness/jquery.ui.tabs.css">

<link type="text/css" rel="stylesheet" href="css/jquery-ui-timepicker-addon.css"/>
<!-- Adding current user if not registered  -->
<%
	// Add current user if connected and not regitered
	UserUtility.addUserIfNew();
%>