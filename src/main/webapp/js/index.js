var cH;

//function goToElement(cur, taget) {
//	console.log("Header " + $("#header").height());
////	console.log(cur + " " + target);
//
//	$(cur).click(function() {
//		console.log("Top " + $(taget).offset().top);
//		$('body, #main-content').animate({
//			scrollTop : ($(taget).offset().top - 64)
//		}, 1000);
//	});
//}


$(document).ready(function() {
	
	$(document).keydown(function(e) {
		console.log(Math.abs($("#div0").offset().top) + 64);
		var current = Math.floor((Math.abs($("#div0").offset().top) + 64) /  $('#main-content').height());
		console.log("Current pos " + current);
	    switch(e.which) {

	        case 38: // up
	        	console.log("up");
	        	$('html, #main-content').animate({
	    			scrollTop : $('#main-content').height() * (current - 1)
	    		}, 1000);
	        break;

	        case 40: // down
	        	console.log("down");
	        	$('html, #main-content').animate({
	    			scrollTop : $('#main-content').height() * (current + 1)
	    		}, 1000);
	        break;

	        default: return; // exit this handler for other keys
	    }
	    e.preventDefault(); // prevent the default action (scroll / move caret)
	});

	cH = $('#main-content').height();
	
	$("#down_1").click(function() {
		$('html, #main-content').animate({
			scrollTop : $('#div1').height()
		}, 1000);
	});
	
	$("#down_2").click(function() {
		$('html, #main-content').animate({
			scrollTop : $('#div2').height() * 2
		}, 1000);
	});
	
	$("#down_3").click(function() {
		$('html, #main-content').animate({
			scrollTop : $('#div3').height() * 3
		}, 1000);
	});
	
	$("#up_1").click(function() {
		$('html, #main-content').animate({
			scrollTop : 0
		}, 1000);''
	});
	
	$("#up_2").click(function() {
		$('html, #main-content').animate({
			scrollTop : $('#main-content').height()
		}, 1000);
	});
	
	$("#up_3").click(function() {
		$('html, #main-content').animate({
			scrollTop : $('#main-content').height() * 2
		}, 1000);
	});

	$("#div0").height($('#main-content').height());
	$("#div1").height($('#main-content').height());
	$("#div2").height($('#main-content').height());
	$("#div3").height($('#main-content').height());

});