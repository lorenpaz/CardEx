function createRowUserFirst(user){
	$('#intercambio-column .list-group').append(`<form method="get" action="${prefijo}perfil/` + user.id + `">
	<button type="submit"><span class="badge">
	<span class="glyphicon glyphicon-chevron-down"></span>
</span></button></form>` + `<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
			role="tab" data-toggle="tab" class="list-group-item active">
			`+user.usuario+`
			<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>
			</a>
			<c:set var="currentUser" value="`+user.usuario+`" /> `);
	addBehaviour();
}

function createRowUser(user){
	$('#intercambio-column .list-group').append(`<form method="get" action="${prefijo}perfil/` + user.id + `">
			<button type="submit"><span class="badge">
			<span class="glyphicon glyphicon-chevron-down"></span>
		</span></button></form>` + `<a href="../perfil/`+user.id+`"></a>`+`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
			role="tab" data-toggle="tab" class="list-group-item usuarios">
			`+user.usuario+`</a>`);
	addBehaviour();
}

function getModal(card, index){
	var card;
	if(index==1){
		card = card.carta;
	}else{
		card = card;
	}
	if(card.name == undefined){
		card.name = '';
	}
	if(card.setName == undefined){
		card.setName = '';
	}
	if(card.imageUrl == undefined){
		card.imageUrl = '';
	}
	if(card.color == undefined){
		card.imageUrl = '';
	}
	if(card.type == undefined){
		card.type = '';
	}
	if(card.types[0] == undefined){
		card.types[0] = '';
	}
	if(card.artist == undefined){
		card.artist = '';
	}
	if(card.power == undefined){
		card.power = '';
	}
	if(card.toughness == undefined){
		card.toughness = '';
	}
	if(card.rarity == undefined){
		card.rarity = '';
	}
	if(card.manaCost == undefined){
		card.manaCost = '';
	}

	var modal = `<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">Cerrar</button>
		<h4 class="modal-title">`+card.name+`</h4>
	</div>
	<div class="modal-body">
		<table id="MainTable">
			<tr>
				<td><img src="`+card.imageUrl+`" alt="`+card.name+`"
					width="200" height="300" /></td>
				<td>
					<div id="firstparagraph">
						<!--Detalles carta-->
						<table id="details2">
							<tr>
								<td class="left">Edicion:</td>
								<td class="right">`+card.setName+`</td>
							</tr>
							<tr>
								<td class="left">Color:</td>
								<td class="right">`+card.color+`</td>
							</tr>
							<tr>
								<td class="left">Tipo:</td>
								<td class="right">`+card.type+`</td>
							</tr>
							<tr>
								<td class="left">Tipo de criatura:</td>
								<td class="right">`+card.types[0]+`</td>
							</tr>
							<tr>
								<td class="left">Artista:</td>
								<td class="right">`+card.artist+`</td>
							</tr>
							<tr>
								<td class="left">Fuerza:
								<td class="right">`+card.power+`</td>
							</tr>
							<tr>
								<td class="left">Resistencia:</td>
								<td class="right">`+card.toughness+`</td>
							</tr>
							<tr>
								<td class="left">Frecuencia:</td>
								<td class="right">`+card.rarity+`</td>
							</tr>
							<tr>
								<td class="left">Coste de mana:</td>
								<td class="right">`+manaTipo(card.manaCost)+`</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="modal-footer"></div>
</div>`;
	return modal;
}

function manaTipo(mana) {
	
	var manaText = ``;
	for (var i = 0, len = mana.length; i < len; i++) {
		 if(mana[i] == 'U')
		 {
			 manaText += `<div id="blueball">&nbsp;</div>`;
		 }
		 if(mana[i] == 'G')
		 {
			 manaText += `<div id="greenball">&nbsp;</div>`;
		 }
		 if(mana[i] == 'R')
		 {
			 manaText += `<div id="redball">&nbsp;</div>`;
		 }
		 if(mana[i] == 'W')
		 {
			 manaText += `<div id="whiteball">&nbsp;</div>`;
		 }
		 if(mana[i] == 'B')
		 {
			 manaText += `<div id="blackball">&nbsp;</div>`;
		 }
	}
	
	return manaText;
}

function createRowCardPide(card){
	if(cardInsideOfferList(card))
	{
	$('#pide-column .list-group').append(`<div class="tab-pane fade in">
			<li class="list-group-item r">
			<a class="nostyle bold" href="#" data-toggle="modal" data-target="#`
			+ card.id+`">`+card.name+`</a>
			<!--Cartas -->
			<div class="modal fade" id="`+card.id+`" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
			`+getModal(card, 2)+`
			</div>
			</div>
			</li>
	</div>`);
	}else{
		$('#pide-column .list-group').append(`<div class="tab-pane fade in">
				<li class="list-group-item r">
				<a class="nostyle" href="#" data-toggle="modal" data-target="#`
				+ card.id+`">`+card.name+`</a>
				<!--Cartas -->
				<div class="modal fade" id="`+card.id+`" role="dialog">
				<div class="modal-dialog">
				<!-- Modal content-->
				`+getModal(card, 2)+`
				</div>
				</div>
				</li>
		</div>`);	
	}
}

function createRowCardOfrece(card){
	
	if(cardInsideSearchCard(card)){
	$('#ofrece-column .list-group').append(`<div class="tab-pane fade in">
			<li class="list-group-item r">
			<a class="nostyle bold" href="#" data-toggle="modal" data-target="#` + card.carta.id+`">`+card.carta.name+`</a>
			<!--Cartas -->
			<div class="modal fade" id="`+card.carta.id+`" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
			`+getModal(card, 1)+`
			</div>
			</div>
			</li>
	</div>`);
	}else{
		$('#ofrece-column .list-group').append(`<div class="tab-pane fade in">
				<li class="list-group-item r">
				<a class="nostyle" href="#" data-toggle="modal" data-target="#` + card.carta.id+`">`+card.carta.name+`</a>
				<!--Cartas -->
				<div class="modal fade" id="`+card.carta.id+`" role="dialog">
				<div class="modal-dialog">
				<!-- Modal content-->
				`+getModal(card, 1)+`
				</div>
				</div>
				</li>
		</div>`);
	}
}

function cardInsideOfferList(card){
	
	var auxiliar = usuarioActualJSON.cartasPropias.filter( function(c){
		return card.id == c.carta.id;
	});
	return auxiliar.length == 0 ? false : true;
}

function cardInsideSearchCard(card){
	
	var auxiliar = usuarioActualJSON.cartasBuscadas.filter( function(c){
		return c.id == card.carta.id;
	});
	return auxiliar.length == 0 ? false : true;
}

function poblarListasCartas(){
	
	$('#ofrece-column .list-group').empty();
	for(i = 0; i < filterOwnCards.length; i++) {
		if (!filterOwnCards[i].inExchange){
			createRowCardOfrece(filterOwnCards[i]);
		}
	}
	$('#pide-column .list-group').empty();
	for(i = 0; i < filterSearchCards.length; i++) {
		createRowCardPide(filterSearchCards[i]);
	}
}

function poblarListaUsuarios(card){
	$('#intercambio-column .list-group').empty();
	var j;
	for(j=0; j<filterUsers.length; j++){
		if(j==0){
			createRowUserFirst(filterUsers[j]);
		}else{
			createRowUser(filterUsers[j]);
		}
	}
}


function filterByUser(user){
	filterUsers = $(usuariosJSON.usuarios).filter(function(){
		var n = this.usuario.toLowerCase();
		var u = user.toLowerCase();
		return (n.includes(u));
	});
	return filterUsers[0];
}

function getUser(user){
	var users = $(usuariosJSON.usuarios).filter(function(){
		var n = this.usuario.toLowerCase();
		var u = user.toLowerCase();
		return (n.includes(u));
	});
	return users[0];
}

function filterCardsByUser(user){
	var filterUser = filterByUser(user);
	if(filterUser != undefined){
		filterOwnCards = filterUser.cartasPropias;
		filterSearchCards = filterUser.cartasBuscadas;
		filterUsersList = filterUsers;
		currentUser = filterUsersList[0];
	}else{
		filterUsersList = [];
		filterSearchCards = [];
		filterOwnCards = [];
	}
}

function filterCardsByName(card){
	var filterAux = [];
	var filterOwnCardsAux = [];
	var filterSearchCardsAux = [];
	var usuarioAux = null;
	var usuarioTmp;
	card = card.toLowerCase();
	for(i=0; i<filterUsers.length; i++){
		var ownCardsAux = filterUsers[i].cartasPropias.filter(function(c){
			usuarioTmp = c.usuarioPropietario;
			cardName = c.carta.name.toLowerCase();
			return !c.inExchange && cardName.includes(card);
		});
		if(ownCardsAux.length > 0){
			filterOwnCardsAux.push(ownCardsAux);
			if(usuarioTmp != usuarioAux){
				filterAux.push(getUser(usuarioTmp));
				usuarioAux = usuarioTmp;
			}
		}
		usuarioTmp = filterUsers[i].usuario;
		var searchCardsAux = filterUsers[i].cartasBuscadas.filter(function(c){
			cardName = c.name.toLowerCase();
			return !c.inExchange && cardName.includes(card);
		});
		
		if(searchCardsAux.length > 0){
			filterSearchCardsAux.push(searchCardsAux);
			if(usuarioTmp != usuarioAux){
				filterAux.push(getUser(usuarioTmp));
				usuarioAux = usuarioTmp;
			}
		}
	}
	filterUsers = filterAux;
	filterUsersList = filterUsers;
	currentUser = filterUsersList[0];
	if(filterOwnCardsAux[0] == undefined){
		filterOwnCards=[];
	}else{
		filterOwnCards=filterOwnCardsAux[0];
	}
	if(filterSearchCardsAux[0] == undefined){
		filterSearchCards=[];
	}else{
		filterSearchCards=filterSearchCardsAux[0];
	}
}


function filterCardsByCardAndUser(user, card){
	if((card == undefined || card == "")&&(user== undefined || user == "")){	
		filterUsers = usuariosJSON.usuarios;
		filterUsersList = filterUsers;
		currentUser = filterUsers[0];
		filterOwnCards = currentUser.cartasPropias;
		filterSearchCards = currentUser.cartasBuscadas;
	}else{
		if(user!=""){
			filterCardsByUser(user);
		}
		if(card != ""){
			filterCardsByName(card);
		}
	}
}

function addBehaviour(){
	// Cambiar botón al pulsar en el usuario
	$("#intercambio-column a").click(function(){
	    if (!$(this).hasClass('active')){
	    		
	    	var user = $(this).text().replace(/\s/g,'');
	    	var card = $('#cartaFilter').val();
	        filterUsu = filterByUser(user);
	        currentUser = filterUsu;
	        filterCardsByCardAndUser(user, card);
	        poblarListasCartas();
	    	var anterior = $('.active').addClass('usuarios').removeClass('active');
	    	$('a .badge').remove();
	    	$('#usuarioQuieroIntercambio').remove();
	    	
	        $(this).addClass('active');
	        $(this).removeClass('usuarios');
	        
	        var cartasDelUsuario = $(this).attr('href');
	        cartasDelUsuario = "."+cartasDelUsuario;
	        
	        var id = filterByUser(user).id;
	        document.getElementById('intercambioEnviar').action = "../intercambio/"+id;
	        $(this).append('<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>');
	        filterUsers = usuariosJSON.usuarios;
	        filterUsersList = filterUsers;
	    }
	});
}


var filterUsers;
var filterUsersList;
var filterOwnCards;
var filterSearchCards;
var ownCardsFilterBy;
var serachCardsFilterBy;
var tab = false;
var currentUser;

$( function(){
	
	filterUsers = usuariosJSON.usuarios;
	filterUsersList = [];
	if(filterUsers.length>0){
		filterOwnCards = filterUsers[0].cartasPropias;
		filterSearchCards = filterUsers[0].cartasBuscadas;
		currentUser = filterUsers[0];
	}

    $("#cartaFilter").on('keyup', function(){	
    	filterUsers = usuariosJSON.usuarios;
	    var card = $(this).val();
        var user = $('#usuarioFilter').val();
        filterCardsByCardAndUser(user, card);
        poblarListasCartas();
        poblarListaUsuarios(card);
    });
    
    $("#usuarioFilter").on('keyup', function(){
    	filterUsers = usuariosJSON.usuarios;
    	var user = $(this).val();
        var card = $('#cartaFilter').val();
        filterCardsByCardAndUser(user, card);
        poblarListasCartas();
        poblarListaUsuarios(card);
     });
    
    addBehaviour();
});


// Enviar oferta
function enviarOferta(e) {

	var usuarioIntercambio = e.target;
	console.log(usuarioIntercambio);
	$('#intercambioEnviar').action = $('#intercambioEnviar').action + usuarioIntercambio;
	console.log($('#intercambioEnviar').action);
}