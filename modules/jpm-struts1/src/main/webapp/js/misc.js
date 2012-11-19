var PM_onLoadFunctions = new Array();
var ajaxRequests = [];

String.prototype.trim = function() {
    return this.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,"");
}

/**
 * Register a function to excecut on page load
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
    alert("Popup not implemented! Redirecting");
    loadPage(url);
/*$("#jpm-popup-ajax").load(url, function(){
        $( "#jpm-popup" ).dialog({
            modal: true, 
            resizable: true,
            bgiframe: true,
            width: 600,
            height: 400,
            autoOpen: true
        });
    });*/
}

/**
 * Loads a page on main frame asking first
 **/
function loadPageConfirm(url){
    $("#confirmationDialog").dialog('option', 'buttons', {
        "Confirm" : function() {
            loadPage(url);
        },
        "Cancel" : function() {
            $(this).dialog("close");
        }
    });
    $("#confirmationDialog").dialog("open");
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
