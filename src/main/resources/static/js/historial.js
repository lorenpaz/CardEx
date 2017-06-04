$( function(){
	
	var idUsuario = $(".list-group-item.active").attr('href');
	$('.'+idUsuario).css("display","unset");
	
	$("#intercambio-column .list-group-item").click(function(){
	    if (!$(this).hasClass('active')){
	  
	    	var anterior = $('.active').addClass('usuarios').removeClass('active');
	    	$('.badge').remove();
	    	
	        $(this).addClass('active');
	        $(this).removeClass('usuarios');
	        
	        var cartasDelUsuario = $(this).attr('href');
	        cartasDelUsuario = "."+cartasDelUsuario;
	        
	        $(cartasDelUsuario).css("display","inline");
	        
	       // $("#row_id").css("display", "none");  // hides the row
	        
	        
	     //   var usuario = $(this).text().replace(/\s/g,'');
		//	$('#intercambioEnviar').append('<input type="hidden" value="'+ usuario + '" name="usuarioIntercambio" id="usuarioQuieroIntercambio" />');
	       // document.getElementById('intercambioEnviar').action = "../intercambio/"+usuario;
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