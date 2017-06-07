
/*function createRow(name , edition){
	$('#table_cards tbody').append(`<tr>
			<td class="filterable-cell">`+ name +`</td>
			<td class="filterable-cell">`+ edition +`</td>
			</tr>`);
}*/

/*function poblarTablas(rows){
	var j;
	for(j=0; j<rows.length; j++){
		createRow(rows[j].name, rows[j].setName);
	}
}*/

function createRowUserFirst(exchange){
	user = exchange.usuarioRecibe == usuarioSesionJSON.id ? exchange.usuarioOfrece : exchange.usuarioRecibe;

	$('#intercambio-column .list-group').append(`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
			role="tab" data-toggle="tab" class="list-group-item active">
			`+user.usuario+`
			<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>
			</a>
			<c:set var="currentUser" value="`+`" /> `);
	addBehaviour();
}

function createRowUser(exchange){
	user = exchange.usuarioRecibe == usuarioSesionJSON.id ? exchange.usuarioOfrece : exchange.usuarioRecibe;
	$('#intercambio-column .list-group').append(`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
			role="tab" data-toggle="tab" class="list-group-item usuarios">
			`+`</a>`);
	addBehaviour();
}

function createRowCardPide(card){
	$('#orderTable tbody').append(`<td>`+card.carta.name+`</td>
			<td><span class="estadoCarta label label-success">`+card.estadoCarta+`</span></td>
			<td>`+card.cantidad+`</td>`);
}

function createRowCardOfrece(card){
	$('#offerTable tbody').append(`<td>`+card.carta.name+`</td>
			<td><span class="estadoCarta label label-success">`+card.estadoCarta+`</span></td>
			<td>`+card.cantidad+`</td>`);
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
            <tbody>
            </tr><tr>`);
		}else{
			$('#offerTable tbody').append(`<tr>`);
		}
		createRowCardOfrece(filterOfferCards[i]);
		if(i == filterOfferCards.length - 1)
		{
			$('#offerTable tbody').append(`</tr></tbody></table>`);
		}else{
			$('#offerTable tbody').append(`</tr>`);
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
             <tbody>
             </tr>`);
		}else{
			$('#orderTable tbody').append(`<tr>`);
		}
		createRowCardPide(filterOrderCards[i]);
		if(i== filterOfferCards.length - 1)
		{
			$('#orderTable tbody').append(`
			</tr>
			</tbody>
			</table>`);
		}else{
			$('#orderTable tbody').append(`</tr>`);
		}
	}
	$("#action").empty();
	if( filterExchanges.length > 0 && filterExchanges[0].estadoIntercambio == 'Pendiente')
	{
		$('#action').append(
				`<div class="list-group">`+
            	`<form method="post" action="../historial/aceptar" id="enviarPOST">
            	<button type="submit" class="list-group-item btn btn-primary">Aceptar</button>
            	<input type="hidden" name="intercambio" id="aceptar" />
          `+ csrf +  `
            </form>`+
           `<form method="post" action="../historial/rechazar" id="rechazarPOST">
                <button type="submit" class="list-group-item">Rechazar</button>
                <input type="hidden" name="intercambio"  id="rechazar" />
            	`+ csrf + `
                </form>`+
                `<form method="post" action="../historial/contraoferta" id="contraofertaPOST">
                <button type="submit" class="list-group-item">Hacer contraoferta</button>
                <input type="hidden" name="intercambio" id="contraoferta" />
               ` +csrf  +`
                </form>`+
		`</div>`);
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
		var n = this.usuarioOfrece;
		return (n == userRec || this.usuarioRecibe == userRec);
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
/*
function loadFilterUsersListInOwnCards(list){
	for(i = 0; i < list.length; i++) {
		var usu = filterByUser(list[i].usuarioPropietario);
		if(filterUsersList.length>0){
			if(isNotInside(usu)){
				filterUsersList.push(usu);
			}	
		}else{
			filterUsersList.push(usu);
		}
	}
}

function loadFilterUsersListInSearchCards(usu){
	if(filterUsersList.length>0){
		if(isNotInside(usu)){
			filterUsersList.push(usu);
		}	
	}else{
		filterUsersList.push(usu);
	}
}*/


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
	
	/*//Input de forma predeterminada
	var primerIntercambio = $(".active").attr('id');
	$("#aceptar").attr('value', primerIntercambio);
	$("#rechazar").attr('value', primerIntercambio);
	$("#contraoferta").attr('value', primerIntercambio);*/
	
	$("#offerFilter").change(function(){
    	let user = $('#userFilter').val();
    	let state = $('#offerFilter').val();
    	filterTable(state, user);
        poblarListasCartas();
        poblarListaUsuarios();
    	if(filterExchanges.length == 0)
    	{
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
	
    $("#usuarioFilter").on('keyup', function(){
    	
    	let user = $(this).val();
    	let state = $('#offerFilter').val();
    	filterTable(state, user);
        poblarListasCartas();
        poblarListaUsuarios(); 
    	if(filterExchanges.length == 0)
    	{
    		if(!$('#noHay').length)
    		{
    			$('#intercambio').append('<h2 id="noHay">No se han encontrado resultados para la busqueda realizada</h2>');
    		}else{
        		if($('#noHay').length)
        		{
        			$('#noHay').remove();
        		}
    		}
    	}
    	filterExchanges = intercambiosJSON.intercambios;
     });
    
    addBehaviour();
	
});
