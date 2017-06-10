
function createRowUserFirst(exchange){
	user = exchange.usuarioRecibe.id == usuarioSesionJSON.id ? exchange.usuarioOfrece : exchange.usuarioRecibe;

	$('#intercambio-column .list-group').append(`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
			role="tab" data-toggle="tab" class="list-group-item active">
			`+user.usuario+`
			<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>
			</a>
			<c:set var="currentUser" value="`+`" /> `);
	addBehaviour();
}

function createRowUser(exchange){
	user = exchange.usuarioRecibe.id == usuarioSesionJSON.id ? exchange.usuarioOfrece : exchange.usuarioRecibe;
	$('#intercambio-column .list-group').append(`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
			role="tab" data-toggle="tab" class="list-group-item usuarios">`+ user.usuario
			+`</a>`);
	addBehaviour();
}

function createRowCardPide(card){
	
	var estado;
	if(card.estadoCarta == 'Nueva')
	{
		estado = `<td><span class="estadoCarta label label-success">`+card.estadoCarta+`</span></td>`;
	}else if(card.estadoCarta == 'Jugada')
	{
		estado = `<td><span class="estadoCarta label label-warning">`+card.estadoCarta+`</span></td>`;
	}else{
		estado = `<td><span class="estadoCarta label label-danger">`+card.estadoCarta+`</span></td>`;
	}
	$('#orderTable tbody').append(`<tr><td>`+card.carta.name+`</td>`+ estado + `<td>`+card.cantidad+`</td></tr>`);
}

function createRowCardOfrece(card){
	var estado;
	if(card.estadoCarta == 'Nueva')
	{
		estado = `<td><span class="estadoCarta label label-success">`+card.estadoCarta+`</span></td>`;
	}else if(card.estadoCarta == 'Jugada')
	{
		estado = `<td><span class="estadoCarta label label-warning">`+card.estadoCarta+`</span></td>`;
	}else{
		estado = `<td><span class="estadoCarta label label-danger">`+card.estadoCarta+`</span></td>`;
	}
	
	$('#offerTable tbody').append(`<tr><td>`+card.carta.name+`</td>`+ estado + `<td>`+card.cantidad+`</td></tr>`);
}

function poblarListaUsuarios(){
	$('#intercambio-column .list-group').empty();
	var j;
	for(j=0; j<filterExchanges.length; j++){
		if(j==0){
			createRowUserFirst(filterExchanges[j]);
		}else{
			createRowUser(filterExchanges[j]);
		}
	}
}

function poblarListasCartas(){
	
	$('#ofrece-column').empty();
	for(i = 0; i < filterOfferCards.length; i++) {
		if(i==0)
		{
			$('#ofrece-column').append(`<h3>Ofrece</h3>
            <table class="table table-striped" id="offerTable">
            <thead> 
                <tr>
                    <th>Carta</th>
                    <th>Estado</th>
                    <th>Cantidad</th>
               	</tr>
            </thead>
            <tbody>`);
		}
		createRowCardOfrece(filterOfferCards[i]);
		if(i == filterOfferCards.length - 1)
		{
			$('#offerTable tbody').append(`</tbody></table>`);
		}
	}
	$('#pide-column').empty();
	for(i = 0; i < filterOrderCards.length; i++) {
		if(i==0)
		{
			$('#pide-column').append(`<h3>Pide</h3>
            <table class="table table-striped" id="orderTable">
            <thead>
                <tr>
                    <th>Carta</th>
                    <th>Estado</th>
                    <th>Cantidad</th>
                </tr>
             </thead>
             <tbody>`);
		}
		createRowCardPide(filterOrderCards[i]);
		if(i== filterOfferCards.length - 1)
		{
			$('#orderTable tbody').append(`
			</tbody>
			</table>`);
		}
	}
	$("#action").empty();
	if(filterExchanges.length > 0)
	{
		if(filterExchanges[0].estadoIntercambio == 'Pendiente' && filterExchanges[0].usuarioRealizaUltimaAccion.id != usuarioSesionJSON.id)
		{
		$('#action').append(
				`<div class="list-group">`+
            	`<form method="post" action="../historial/aceptar" id="enviarPOST">
            	<button type="submit" class="list-group-item btn btn-primary">Aceptar</button>
            	<input type="hidden" name="intercambio" id="aceptar" value="`+ filterExchanges[0].id+`" />
          `+ csrf +  `
            </form>`+
           `<form method="post" action="../historial/rechazar" id="rechazarPOST">
                <button type="submit" class="list-group-item">Rechazar</button>
                <input type="hidden" name="intercambio"  id="rechazar" value="`+ filterExchanges[0].id +`" />
            	`+ csrf + `
                </form>`+
                `<form method="post" action="../historial/contraoferta" id="contraofertaPOST">
                <button type="submit" class="list-group-item">Hacer contraoferta</button>
                <input type="hidden" name="intercambio" id="contraoferta" value="`+ filterExchanges[0].id +`" />
               ` +csrf  +`
                </form>`+
		`</div>`);
		}else if(filterExchanges[0].estadoIntercambio == 'Pendiente' && filterExchanges[0].usuarioRealizaUltimaAccion.id == usuarioSesionJSON.id)
		{
			if($("#action").length)
			{
			$('#action').append(`<div class="list-group"> Esperando respuesta del otro usuario </div>`);
			}else{
				var info = `<div id="action" class="offer-column"><div class="list-group waiting"> Esperando respuesta del otro usuario </div>`;
				$(botonFinalizar).insertAfter('#pide-column');
			}
		}
		else if(filterExchanges[0].estadoIntercambio == 'Finalizado' && filterExchanges[0].usuarioRealizaUltimaAccion.id == usuarioSesionJSON.id) {
			if($("#action").length)
			{
			$('#action').append(`<div class="list-group"> Esperando que finalice el intercambio el otro usuario </div>`);
			}else{
				$('#action').append(`<div id="action" class="offer-column"><div class="list-group"> Esperando que finalice el intercambio el otro usuario </div></div>`);
			}
		}
		else if(filterExchanges[0].estadoIntercambio == 'Aceptado')
		{
			if($("#action").length)
			{
			$('#action').append(
					`<div class="list-group">`+
	                `<form method="post" action="../historial/finalizar" id="finalizarPOST">
	                <button type="submit" class="list-group-item">Finalizar</button>
	                <input type="hidden" name="intercambio" id="finalizar" value="`+ filterExchanges[0].id +`/>
	               ` +csrf  +`
	                </form>`+
			`</div>`);
			}else{
				var botonFinalizar =`<div id="action" class="offer-column"><div class="list-group">`+
            	`<form method="post" action="../historial/finalizar" id="finalizarPOST">
            	<button type="submit" class="list-group-item btn btn-primary">Finalizar</button>
            	<input type="hidden" name="intercambio" id="finalizar" value="`+ filterExchanges[0].id+`" />
          `+ csrf +  `
            </form></div>`;
				$(botonFinalizar).insertAfter('#pide-column');
			}
		}else if(filterExchanges[0].estadoIntercambio == 'Finalizado' && filterExchanges[0].usuarioRealizaUltimaAccion.id != usuarioSesionJSON.id){
			if($("#action").length)
			{
			$('#action').append(
					`<div class="list-group">`+
	                `<form method="post" action="../historial/finalizar" id="finalizarPOST">
	                <button type="submit" class="list-group-item">Finalizar</button>
	                <input type="hidden" name="intercambio" id="finalizar" value="`+ filterExchanges[0].id +`/>
	               ` +csrf  +`
	                </form>`+
			`</div>`);
			}else{
				var botonFinalizar =`<div id="action" class="offer-column"><div class="list-group">`+
            	`<form method="post" action="../historial/finalizar" id="finalizarPOST">
            	<button type="submit" class="list-group-item btn btn-primary">Finalizar</button>
            	<input type="hidden" name="intercambio" id="finalizar" value="`+ filterExchanges[0].id+`" />
          `+ csrf +  `
            </form></div>`;
				$(botonFinalizar).insertAfter('#pide-column');
			}
		}
	}
	
}

function filterByState(state){
	filterExchanges = $(intercambiosJSON.intercambios).filter(function(){
		var n = this.estadoIntercambio;
		return (n == state);
	});
}

function filterByName(userRec){
	
	
	filterExchanges = $(intercambiosJSON.intercambios).filter(function(){

		var n = this.usuarioRecibe.id == usuarioSesionJSON.id ? this.usuarioOfrece.usuario : this.usuarioRecibe.usuario;
		return n.startsWith(userRec);
	});
}


function filterTable(state, userRec){
	filterCardsByState(state);
	
	if(userRec != ''){
		filterUserByName(userRec);
	}
}

function filterCardsByState(state) {
	filterByState(state);
	if(filterExchanges.length > 0) {
	filterOfferCards = filterExchanges[0].cartasOfrecidas;
	filterOrderCards = filterExchanges[0].cartasRecibidas;
	filterUsersList = filterExchanges;
	}else{
		filterUsersList = [];
		filterOfferCards=[];
		filterOrderCards=[];
	}
}


function filterUserByName(userRec){
	filterByName(userRec);
	if(filterExchanges.length > 0){
		filterOfferCards = filterExchanges[0].cartasOfrecidas;
		filterOrderCards =  filterExchanges[0].cartasRecibidas;
		filterUsersList = filterExchanges;
		currentUser = filterUsersList[0];
	}else{
		filterUsersList = [];
		filterOfferCards=[];
		filterOrderCards=[];		
	}
}

function addBehaviour(){
	// Cambiar botón al pulsar en el usuario
	$("#intercambio-column a").click(function(){
	    if (!$(this).hasClass('active')){
	    		
	    	var user = $(this).text().replace(/\s/g,'');
	    	var state = $('#offerFilter').val();
	        filterUsu = filterByName(user);
	        currentUser = filterUsu;
	        filterTable(state, user);
	        poblarListasCartas();
	    	var anterior = $('.active').addClass('usuarios').removeClass('active');
	    	$('.badge').remove();
	    	
	        $(this).addClass('active');
	        $(this).removeClass('usuarios');        
	        
	        $(this).append('<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>');
	        filterExchanges = intercambiosJSON.intercambios;
	        filterUsersList = filterExchanges;
	    }
	});
}

var filterExchanges;
var filterUsersList;
var filterOfferCards;
var filterOrderCards;

$( function(){
	
	filterExchanges = intercambiosJSON.intercambios;

	filterUsersList = [];
	if(filterExchanges.length>0){
		filterOfferCards = filterExchanges[0].cartasOfrecidas;
		filterOrderCards = filterExchanges[0].cartasRecibidas;
		currentUser = usuarioActual;
	}
	
	$("#offerFilter").change(function(){
    	let user = $('#userFilter').val();
    	let state = $('#offerFilter').val();
    	filterTable(state, user);
        poblarListasCartas();
        poblarListaUsuarios();
    	if(filterExchanges.length == 0)
    	{
    		$('.waiting').remove();
    		if(!$('#noHay').length)
    		{
    			$('#intercambio').append('<h2 id="noHay">No se han encontrado resultados para la busqueda realizada</h2>');
    		}	
    	}else{
    		if($('#noHay').length)
    		{
    			$('#noHay').remove();
    		}
    	}
    	filterExchanges = intercambiosJSON.intercambios;
    });
	
    $("#userFilter").on('keyup', function(){
    	
    	let user = $(this).val();
    	let state = $('#offerFilter').val();
    	filterTable(state, user);
        poblarListasCartas();
        poblarListaUsuarios(); 
    	if(filterExchanges.length == 0)
    	{
    		$('.waiting').remove();
    		if(!$('#noHay').length)
    		{
    			$('#intercambio').append('<h2 id="noHay">No se han encontrado resultados para la busqueda realizada</h2>');
    		}else{
        		if($('#noHay').length)
        		{
        			$('#noHay').remove();
        		} 
    		}
    	}else{
    		if($('#noHay').length)
    		{
    			$('#noHay').remove();
    		}
    	}
    	filterExchanges = intercambiosJSON.intercambios;
     });
    
    addBehaviour();
	
});
