$( function(){
	
	$("#intercambio-column .list-group-item").click(function(){
	    if (!$(this).hasClass('active')){
	  
	    	$('.in').removeClass('.in');
	    	
	    	
	    	var anterior = $('.active').addClass('usuarios').removeClass('active');
	    	$('.badge').remove();
	    	$('#usuarioQuieroIntercambio').remove();
	    	
	    	$('.in').removeClass('in');
	        $(this).addClass('in');
	        $(this).addClass('active');
	        $(this).removeClass('usuarios');
	        
	        var cartasDelUsuario = $(this).attr('href');
	        cartasDelUsuario = "."+cartasDelUsuario;
	        $(cartasDelUsuario).addClass('in');
	        
	        var usuario = $(this).text().replace(/\s/g,'');
		//	$('#intercambioEnviar').append('<input type="hidden" value="'+ usuario + '" name="usuarioIntercambio" id="usuarioQuieroIntercambio" />');
	        document.getElementById('intercambioEnviar').action = "../intercambio/"+usuario;
	        $(this).append('<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>');
	    }
	});
	
});

function enviarOferta(e) {

	var usuarioIntercambio = e.target;
	console.log(usuarioIntercambio);
	$('#intercambioEnviar').action = $('#intercambioEnviar').action + usuarioIntercambio;
	console.log($('#intercambioEnviar').action);
}