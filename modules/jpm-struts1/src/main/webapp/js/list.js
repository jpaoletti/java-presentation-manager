var asInitVals = new Array();
var oTable;
var n = 0;
	
PM_register(function() {
    oTable = $('#list').dataTable({
        "bProcessing": true,
        "oLanguage": {
            "sSearch": texts[0],
            "oPaginate": {
                "sFirst": texts[1],
                "sLast": texts[2],
                "sNext": texts[3],
                "sPrevious": texts[4]
            },
            "sInfo": texts[5],
            "sInfoEmpty": texts[6],
            "sInfoFiltered": texts[7],
            "sLengthMenu": texts[8],
            "sProcessing": texts[9],
            "sZeroRecords": texts[10]
        },
        "bSort": sortable,
        "bFilter": searchable,
        "bPaginate": paginable,
        "bInfo":false,
        "bAutoWidth":false
    });
    
    if(searchable){
        $("tfoot input").keyup( function () {
            /* Filter on the column (the index) of this element */
            oTable.fnFilter( this.value, $("tfoot input").index(this)+n );
        });
			
        $("tfoot input, #list_filter input").focus( function () {
            if ( this.className == "search_init" ){
                this.className = "";
                this.value = "";
            }
        });
			
        $("tfoot input").blur( function (i) {
            if ( this.value == "" )	{
                this.className = "search_init";
                this.value = asInitVals["search"];
            }
        });

        $("#list_filter input").blur( function (i) {
            if ( this.value == "" )	{
                this.className = "search_init";
                this.value = asInitVals["searchall"];
            }
        });
    }
			
} );