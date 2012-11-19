if(typeof updateObjectConverter == "undefined"){
    var updateObjectConverter = function($this, settings){
        $this.find(".object-converter-loading").show();
        $this.find(".object-converter-items").hide();
        var select = $this.find("select");
        select.contents().remove();
        var finishSelect = function(){
            var sid = settings["selected_id"];
            var noSelected = select.find("option[value='"+sid+"']").length==0;
            var nullable = settings["with_null"];
            var nullValue = sid == "-1";
            if(noSelected){
                if((!nullable && !nullValue) || nullable){
                    select.prepend("<option value='"+sid+"'>"+settings["selected_value"]+"</option>");                    
                }
            }
            select.val(sid);
            if(nullable && !nullValue){
                select.prepend("<option value='-1'></option>");
            }
            select.find("option[value='undefined']").remove();
            $this.find(".object-converter-loading").hide();
            $this.find(".object-converter-items").show();
        }
        var filter = $this.find("input").val();
        if(filter.length >= settings["min_search_size"]){
            var call = $.getJSON(settings["url"]+"?filter=" + filter + "&relatedFieldValue=" + $("#f_"+settings["related"]).val(),function(list){
                $.each(list, function (i, item){
                    select.append("<option value='"+list[i].key+"'>"+list[i].value+"</option>");
                });
                finishSelect();
            });
            ajaxRequests.push(call);
        }else{
            finishSelect();
        }
    };
}

(function( $ ){
    $.fn.objectConverter = function( method ) {
        var settings;
        var $this = this;
        var methods = {
            init : function( options ) {
                settings = $.extend( {
                    "field"          : "",
                    "selected_value" : "",
                    "selected_id"    : "0",
                    "min_search_size": 0,
                    "search"         : false,
                    "related"        : "",
                    "related_entity" : "",
                    "add"            : "",
                    "with_null"      : true
                }, options);
                if(settings["min_search_size"] == 0 && !settings["search"]){
                    $this.find(".object-converter-search").hide();
                }
                if(settings["add"] != ""){
                    $this.find("select").addClass("with-add");
                    $this.find("button").click(function(){
                        popup(settings["add"]);
                        setTimeout(function(){
                            $("#form_"+settings["related_entity"]+"_add").ajaxForm(function(){
                                $('#popup-modal').modal('hide');
                                updateObjectConverter($this, settings);
                            });
                        }, 2100);
                    });
                }else{
                    $this.find("button").remove();
                }
                $this.find("input").live("keyup", function() {
                    delay(function(){
                        updateObjectConverter($this, settings);
                    }, 1000 );
                });
                if(settings["related"]!=""){
                    $("#f_"+settings["related"]).live('change', function(){
                        updateObjectConverter($this, settings);
                    });
                }
                updateObjectConverter($this, settings);
                return $this;
            },
            reload : function( ) {
                updateObjectConverter($this, settings);
            }
        };
        // Method calling logic
        if ( methods[method] ) {
            return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof method === 'object' || ! method ) {
            return methods.init.apply( this, arguments );
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.objectConverter' );
        }
        return this;
    };
})( jQuery );