<div id="templatemo_footer">
	Copyright � 2013-2014 <b>BA Mamadou Lamine - E13D192L</b> MASTER 1 - ALMA At <a href="http://univ-nantes.fr">Nantes University</a> 
	<div class="cleaner"></div>
</div>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script src="js/jquery-ui-timepicker-addon.js"></script>
<script>
$(document).ready(function(){
    $(".datepicker").datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: 'images/icon_calendar.png', dateFormat: "dd/mm/yy" });
    $('#datetimepickerff').datetimepicker({	showOn: 'button', buttonImageOnly: true, buttonImage: 'images/icon_calendar.png',
									    	addSliderAccess: true,
									    	sliderAccessArgs: { touchonly: false }
									    });
   $('#datetimepicker').datetimepicker({
	   showOn: 'button', buttonImageOnly: true, buttonImage: 'images/icon_calendar.png'   
   });
});
</script>