var PM_onLoadFunctions = new Array();
var msg_system = new Array();
var msg_entity = new Array();
var msg_field = new Array();
var ajaxRequests = [];
var popupNewClass;

String.prototype.trim = function() {
    return this.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g, "");
};

/**
 * Register a function to excecute on page load.
 * @param {function} func the function
 */
function PM_register(func) {
    PM_onLoadFunctions.push(func);
}

/**
 * Loads a page.
 * @param {String} url Url to load.
 */
function loadPage(url) {
    window.location = url;
}

function disable(field, dis) {
    if (dis) {
        $('#f_' + field).val("");
        $('#f_' + field).attr('disabled', 'disabled');
    } else {
        $('#f_' + field).removeAttr('disabled');
    }
}

function popup(url) {
    var fullUrl = url + ((url.indexOf("?") !== -1) ? "&" : "?") + "popup=true";
    var modal = $('#popup-modal');
    modal.removeClass(popupNewClass);
    modal.find(".modal-header > h3").html("...");
    modal.find(".modal-body").html("");
    modal.find(".modal-footer").html("");
    $.ajax({
        url: fullUrl,
        success: function(data) {
            var tmp = $("<div />");
            tmp.html(data);
            var d = tmp.find("#jpm-internal-body");
            popupNewClass = "entity-" + d.attr("data-entity") + " operation-" + d.attr("data-operation");
            modal.addClass(popupNewClass);
            //var text = modal.find(".modal-header > .loading-text").html();
            var text = tmp.find(".breadcrumb > li.active").html();
            var footer = d.find(".form-actions").html();
            d.find(".std-header").remove();
            d.find(".form-actions").remove();
            modal.find(".modal-header > h3").html(text);
            modal.find(".modal-body").html("<div class='hide to-show'>" + d.html() + "</div><div class='center to-hide'><img alt='" + text + "' src='loading.gif' /></div>");
            modal.find(".modal-footer").html(footer).find("button[type='submit']").click(function() {
                modal.find(".modal-body").find("form").submit()
            })
            modal.find(".modal-footer").find("button[type='reset']").click(function() {
                modal.find(".modal-body").find("form")[0].reset()
            });
            modal.find(".btn-cancel").click(function(e) {
                e.preventDefault();
            }).attr("data-dismiss", "modal");
            modal.on("keydown", "input", function(e) {
                if (e.which === 13) {
                    e.preventDefault();
                    $(this).parents("form").submit();
                }
            });
            modal.modal('show');
            ajaxRequests = [];
            startupFunctions();
            modal.find(".to-hide").hide();
            modal.find(".to-show").show();
            tmp.remove();
        },
        error: function(e) {
            alert("Error loading popup: " + e);
        }
    });
}

/**
 * Loads a page on main frame asking first
 **/
function loadPageConfirm(url) {
    $('#confirmation-dlg-ok').click(function() {
        loadPage(url);
    });
    $('#confirmation-dlg').modal('show');
}

function paginate(i) {
    $("#page").val(i);
    $("#listform").submit();
}

function enable_disable(inputid, v) {
    var f = $('#' + inputid);
    if (v) {
        f.val("");
        f.attr('disabled', 'disabled');
    } else {
        f.removeAttr('disabled');
    }
}

function validatePassword(entity, param) {
    var f = $("#f_" + param + "_id").val();
    var d = $("#d_" + param);
    var sub = $("#" + entity + "_submit");
    if (f != $("#r_" + param).val()) {
        d.addClass("error");
        d.find(".help-inline").show();
        sub.attr('disabled', 'disabled');
    } else {
        d.removeClass("error");
        d.find(".help-inline").hide();
        sub.removeAttr('disabled');
    }
}

var delay = (function() {
    var timer = 0;
    return function(callback, ms) {
        clearTimeout(timer);
        timer = setTimeout(callback, ms);
    };
})();

function startupFunctions() {
    PM_onLoadFunctions = $.grep(PM_onLoadFunctions, function(func, i) {
        try {
            func();
        } catch (e) {
            console.error("The following function raised an error: " + func);
            console.error(e);
            alert("Error: " + e);
        }
        return false;
    });
}

/* SYSTEM STARTUP */
$().ready(function() {
    try {
        $("button.submit").click(function() {
            this.form.submit();
        });
        $("button.reset").click(function() {
            this.form.reset();
            return false;
        });
        startupFunctions();
        // Menu Fixes
        $(".menu-button-bar").remove();
        var m = $("#menu");
        m.removeClass("jqueryslidemenu");
        m.children("script").remove();
        m.children("ul").addClass("nav");
        m.children("ul").children("li").addClass("dropdown");
        m.children("ul").children("li").each(function() {
            if ($(this).children().size() > 1) {
                $(this).children("ul").addClass("dropdown-menu");
                $(this).children("a")
                        .addClass("dropdown-toggle")
                        .attr('data-toggle', 'dropdown')
                        .append("<b class='caret'></b>");
            }
        });
        //Form fixes
        $(".form-horizontal").find(".cell").each(function() {
            var text = $(this).html();
            if (text.indexOf("<") == -1) {
                if (!$(this).parent().is("td")) {
                    $(this).html("<input type='text' disabled='disabled' value='" + text + "'/>");
                }
            }
        });
        //Login form fixes
        $("#username").keypress(function(e) {
            if (e.keyCode == '13') {
                e.preventDefault();
                $("#password").focus();
            }
        });
        $("#username").focus();
        $("input[type='file']").closest("form").attr("enctype", "multipart/form-data");
        //Message Initialization
        jQuery.each(msg_system, function() {
            var cl = ".message_container";
            jQuery(cl).addClass("alert alert-" + this.type.toLowerCase());
            jQuery(cl).removeClass("hide");
            jQuery(cl).html(this.text);
        });
        jQuery.each(msg_entity, function() {
            var cl = ".entity_message_container_" + this.entity;
            jQuery(cl).addClass("alert alert-" + this.type.toLowerCase());
            jQuery(cl).html(this.text);
        });
        jQuery.each(msg_field, function() {
            var cl = ".field_message_container_" + this.entity + "_" + this.field;
            jQuery(cl).addClass("pm_message_" + this.type).addClass("help-inline");
            jQuery(cl).parent().addClass("pm_message_" + this.type);
            jQuery(cl).parents(".control-group").addClass(this.type.toLowerCase());
            jQuery(cl).html(this.text);
        });
        if ($(location).attr('href').indexOf("popup=true") !== -1) {
            var defer = $.when.apply($, ajaxRequests);
            defer.done(function(args) {
                var body = $('body');
                body.on("keydown", "input", function(e) {
                    if (e.which === 13) {
                        e.preventDefault();
                        $(this).parents("form").submit();
                    }
                });
                var title = body.find("#navigation_bar > .breadcrumb > .active").html();
                body.find("#popup-load-iframe").remove();
                body.find("hr:last").remove();
                body.find("footer").remove();
                body.find("#confirmation-dlg").remove();
                body.find("#navigation_bar").remove();
                body.find("#operations_bar").parent().remove();
                body.find("h2.title").remove();
                body.find(".navbar").remove();
                body.find("#loading-div").remove();
                body.find("#page-container").show();
                body.find(".btn-danger").attr("data-dismiss", "modal").attr("onclick", "");
                var modal = $(top.parent.document).find("#popup-modal");
                modal.find(".modal-header > h3").html(title);
                modal.find(".modal-body").html(body.html());
            });
        }
    } finally {
        $("#loading-div").hide();
        $("#page-container").show();
    }
});