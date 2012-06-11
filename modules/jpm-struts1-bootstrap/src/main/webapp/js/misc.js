var PM_onLoadFunctions = new Array();

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
