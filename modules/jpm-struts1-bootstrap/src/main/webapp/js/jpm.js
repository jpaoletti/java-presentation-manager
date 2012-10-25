var PM_onLoadFunctions = new Array();
var msg_system = new Array();
var msg_entity = new Array();
var msg_field = new Array();

String.prototype.trim = function() {
    return this.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,"");
}

/**
 * Register a function to excecute on page load
 */
function PM_register(func){
    PM_onLoadFunctions.push(func);
}

/**
 * Loads a page on main frame
 **/
function loadPage(url){
    window.location = url;
}

function popup(url){
    $("#popup-load-iframe").attr("src", url);
    var $this = $("#popup-load-iframe");
    var modal = $('#popup-modal');
    var text = modal.find(".modal-header > .loading-text").html();
    modal.find(".modal-header > h3").html(text);
    modal.find(".modal-body").html("<div class='center'><img alt='"+text+"' src='loading.gif' /></div>");
    modal.modal('show');
    setTimeout(function(){
        var body = $this.contents().find('body');
        body.find("#popup-load-iframe").remove();
        body.find("hr:last").remove();
        body.find("footer").remove();
        body.find("#confirmation-dlg").remove();
        body.find("#navigation_bar").remove();
        body.find("#operations_bar").parent().remove();
        modal.find(".modal-header > h3").html(body.find("h2.title").html());
        body.find("h2.title").remove();
        body.find("script").remove();
        body.find(".btn-danger").attr("data-dismiss","modal").attr("onclick","");
        $("#modal-body").html(body.html());
    }, 2000);
//loadPage(url);
}

/**
 * Loads a page on main frame asking first
 **/
function loadPageConfirm(url){
    $('#confirmation-dlg-ok').click(function(){
        loadPage(url);
    });
    $('#confirmation-dlg').modal('show');
}

function paginate(i){
    $("#page").val(i);
    $("#listform").submit();
}

function enable_disable(inputid, v){
    var f = $('#'+inputid);
    if (v){
        f.val("");
        f.attr('disabled', 'disabled');
    }else{
        f.removeAttr('disabled');
    }
}

function validatePassword(entity, param){
    var f = $("#f_"+param+"_id").val();
    var d = $("#d_"+param);
    var sub = $("#"+entity+"_submit");
    if( f != $("#r_"+param).val()){
        d.addClass("error");
        d.find(".help-inline").show();
        sub.attr('disabled', 'disabled');
    }else{
        d.removeClass("error");
        d.find(".help-inline").hide();
        sub.removeAttr('disabled');
    }
}

var delay = (function(){
    var timer = 0;
    return function(callback, ms){
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    };
})();


/* SYSTEM STARTUP */
$().ready(function() {
    try{
        $("button.submit").click(function(){
            this.form.submit();
        });
        $("button.reset").click(function(){
            this.form.reset();
            return false;
        });
        jQuery.each(PM_onLoadFunctions, function(){
            try{
                this();
            }catch(e){
                alert("Error: "+e);
            }
        });
        // Menu Fixes
        $(".menu-button-bar").remove();
        var m = $("#menu");
        m.removeClass("jqueryslidemenu");
        m.children("script").remove();
        m.children("ul").addClass("nav");
        m.children("ul").children("li").addClass("dropdown");
        m.children("ul").children("li").each(function(){
            if($(this).children().size()>1){
                $(this).children("ul").addClass("dropdown-menu");
                $(this).children("a")
                .addClass("dropdown-toggle")
                .attr('data-toggle', 'dropdown')
                .append("<b class='caret'></b>");
            }
        });
        //Form fixes
        $(".form-horizontal").find(".cell").each(function(){
            var text = $(this).html();
            if(text.indexOf("<") == -1){
                if(!$(this).parent().is("td")){
                    $(this).html("<input type='text' disabled='disabled' value='"+text+"'/>");
                }
            }
        });
        //Login form fixes
        $("#username").keypress(function(e){
            if (e.keyCode == '13') {
                e.preventDefault();
                $("#password").focus();
            }
        });
        $("#username").focus();
        $("input[type='file']").closest("form").attr("enctype","multipart/form-data");
        //Message Initialization
        jQuery.each(msg_system, function(){
            var cl = ".message_container";
            jQuery(cl).addClass("alert alert-"+this.type.toLowerCase());
            jQuery(cl).removeClass("hide");
            jQuery(cl).html(this.text);
        });
        jQuery.each(msg_entity, function(){
            var cl = ".entity_message_container_"+this.entity;
            jQuery(cl).addClass("alert alert-"+this.type.toLowerCase());
            jQuery(cl).html(this.text);
        });
        jQuery.each(msg_field, function(){
            var cl = ".field_message_container_"+this.entity+"_"+this.field;
            jQuery(cl).addClass("pm_message_"+this.type).addClass("help-inline");
            jQuery(cl).parent().addClass("pm_message_"+this.type);
            jQuery(cl).parents(".control-group").addClass(this.type.toLowerCase());
            jQuery(cl).html(this.text);
        });
    }finally{
        $("#loading-div").hide();
        $("#page-container").show();
    }
});