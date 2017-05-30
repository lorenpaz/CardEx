$( function(){
	
	$("#intercambio-column .list-group-item").click(function(){
	    if (!$(this).hasClass('active')){
	  
	    	$('.active').addClass('usuarios').removeClass('active');
	    	$('.badge').remove();
	        $(this).addClass('active');
	        $(this).removeClass('usuarios');
	        $(this).append('<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>');
	    }
	})
});