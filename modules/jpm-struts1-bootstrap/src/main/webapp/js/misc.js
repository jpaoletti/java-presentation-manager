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

function popup(url){
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
    loadPage(url);
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

var delay = (function(){
    var timer = 0;
    return function(callback, ms){
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    };
})();
