/*$(document).ready(function() {
	
	$('.button').click(function() {
		
		type = $(this).attr('data-type');
		
		$('.overlay-container').fadeIn(function() {
			
			window.setTimeout(function(){
				$('.window-container.'+type).addClass('window-container-visible');
			}, 100);
			$(window).resize(function() {
				  // This will execute whenever the window is resized
				  $(window).height(); // New height
				  $(window).width(); // New width
				});
		});
	});
	
	$('.close').click(function() {
		$('.overlay-container').fadeOut().end().find('.window-container').removeClass('window-container-visible');
	});
	
});*/

		// Popup Window
		var scrollTop = '';
		var newHeight = '100';

		$(window).bind('scroll', function() {
		   scrollTop = $( window ).scrollTop();
		   newHeight = scrollTop + 100;
		});
		
		$('.popup-trigger').click(function(e) {
  		 e.stopPropagation();
		});
		
		$('html').click(function() {
		 $('.popup').hide();
		});

		$('.popup-btn-close').click(function(e){
		  $('.popup').hide();
		});

		$('.popup').click(function(e){
		  e.stopPropagation();
		});