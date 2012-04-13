var PM_onLoadFunctions = new Array();

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

var delay = (function(){
    var timer = 0;
    return function(callback, ms){
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    };
})();
