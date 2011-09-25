<%@page contentType="application/x-javascript" %>
<%@include file="../inc/tag-libs.jsp" %>
var pmid = "${param.pmid}";
var searchable = "${param.searchable}" == "true";
var oTable;
var n = 0;
	
PM_register(function() {
    oTable = $('#list').dataTable({
        "bProcessing": true,
        "oLanguage": {
            "sSearch": "",
            "oPaginate": {
                "sFirst": "<pmfn:message key='list.first' />",
                "sLast": "<pmfn:message key='list.last' />",
                "sNext": "<pmfn:message key='list.next' />",
                "sPrevious": "<pmfn:message key='list.previous' />"
            },
            "sInfo": "<pmfn:message key='list.info' />",
            "sInfoEmpty": "<pmfn:message key='list.info.empty' />",
            "sInfoFiltered": "<pmfn:message key='list.info.filtered' />",
            "sLengthMenu": "<pmfn:message key='pm.struts.list.rpp' />",
            "sProcessing": "<pmfn:message key='list.processing' />",
            "sZeroRecords": "<pmfn:message key='list.zero.records' />"
        },
        "bSort": false,
        "bFilter": searchable,
        "bPaginate": false,
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
                this.value = "<pmfn:message key='list.input.search'/>";
            }
        });

        $("#list_filter input").blur( function (i) {
            if ( this.value == "" )	{
                this.className = "search_init";
                this.value = "<pmfn:message key='list.search.all'/>";
            }
        });
    }
    
    $("#page").keydown(function(event){
        if(event.keyCode == 13)
            this.form.submit();
    });
    
    $('#operationsort')
        .unbind('click')
        .addClass('jqModal');
    
    $('#sort_page').jqm({
        onShow:function(hash){
            hash.w.css('opacity',0.88).show();
        }
    });
    
    $('#first_footer').append( $('#list_wrapper>#list_filter') );
    
    $('#list_filter input')
        .val("<pmfn:message key='list.search.all' />")
        .addClass("search_init")
        .attr("size","7");
});

function selectItem(i){
    $.ajax({ url: "${es.context_path}/selectItem.do?pmid=${param.pmid}&idx="+i});
}
