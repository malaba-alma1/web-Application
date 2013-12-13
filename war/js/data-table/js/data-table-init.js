var asInitVals = new Array();
			
$(document).ready(function() {
	var oTable = $('#table-data').dataTable({
        "aaSorting": "",
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"bFilter": true,
		
		"oLanguage": {
			//"sLengthMenu": "Afficher _MENU_ lignes par page",
			"sZeroRecords": "Nothing found - sorry",
			//"sInfo": "Affichage _START_ à _END_ sur _TOTAL_ enregistrements",
			//"sInfoEmpty": "Affichage 0 de 0 of 0 enregistrement",
			"sInfoFiltered": "(filtered from _MAX_ total records)",
			"sProcessing": "Bitte warten...",
			"sInfoPostFix": "",
			//"sSearch": "Rechercher tout",
			"sUrl": "",
		/*	"oPaginate": {
				"sFirst":    "Debut",
				"sPrevious": "Suiv",
				"sNext":     "Prec",
				"sLast":     "Fin"
			}*/
		}
	});
	
	$("tfoot input").keyup( function () {
		/* Filter on the column (the index) of this element */
		oTable.fnFilter( this.value, $("tfoot input").index(this) );
	} );
	
	
	
	/*
	 * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
	 * the footer
	 */
	$("tfoot input").each( function (i) {
		asInitVals[i] = this.value;
	} );
	
	$("tfoot input").focus( function () {
		if ( this.className == "search_init" )
		{
			this.className = "";
			this.value = "";
		}
	} );
	
	$("tfoot input").blur( function (i) {
		if ( this.value == "" )
		{
			this.className = "search_init";
			this.value = asInitVals[$("tfoot input").index(this)];
		}
	} );
} );