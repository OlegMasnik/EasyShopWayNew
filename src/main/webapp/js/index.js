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

	cH = $('#main-content').height();
	
	$("#down_1").click(function() {
		console.log("Top " + $("#div2").offset().top);
		$('html, #main-content').animate({
			scrollTop : $('#div1').height()
		}, 1000);
	});
	
	$("#down_2").click(function() {
		console.log("div3 " + $("#div3").offset().top);
		console.log("div2 " + $("#div2").offset().top);
		$('html, #main-content').animate({
			scrollTop : $('#div2').height() * 2
		}, 1000);
	});
	
	$("#down_3").click(function() {
		console.log("div3 " + $("#div3").offset().top);
		console.log("div2 " + $("#div2").offset().top);
		$('html, #main-content').animate({
			scrollTop : $('#div3').height() * 3
		}, 1000);
	});
	
	$("#up_1").click(function() {
		console.log("Top " + $("#div1").offset().top);
		$('html, #main-content').animate({
			scrollTop : 0
		}, 1000);''
	});
	
	$("#up_2").click(function() {
		console.log("Top " + ($("#div3").offset().top * 2));
		$('html, #main-content').animate({
			scrollTop : $('#main-content').height()
		}, 1000);
	});
	
	$("#up_3").click(function() {
		console.log("Top " + ($("#div3").offset().top * 2));
		$('html, #main-content').animate({
			scrollTop : $('#main-content').height() * 2
		}, 1000);
	});
	

	console.log($('#div1').height());
	$("#div0").height($('#main-content').height());
	$("#div1").height($('#main-content').height());
	$("#div2").height($('#main-content').height());
	$("#div3").height($('#main-content').height());

});