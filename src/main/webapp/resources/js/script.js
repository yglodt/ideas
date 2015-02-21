
window.onerror = function(e) {
	alert("JavaScript-Error:\n\n" + e);
};

$(document).ready(function() {

});

$(document).ajaxStart(function() {
	// console.log("ajaxStart");
	// $("#spinner").addClass("fa fa-spinner fa-spin fa-lg");
});

$(document).ajaxStop(function() {
	// console.log("ajaxStop");
	// $("#spinner").removeClass("fa fa-spinner fa-spin fa-large");
});
