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
    var mf = top.frames["mainframe"];
    mf.location = url;
    $(mf).focus();
}

function paginate(i){
    $("#page").val(i);
    $("#listform").submit();
}
