String.prototype.trim = function() {
    return this.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,"");
}

var PM_onLoadFunctions = new Array();

/**
 * Register a function to excecut on page load
 */
function PM_register(func){
    PM_onLoadFunctions.push(func);
}
