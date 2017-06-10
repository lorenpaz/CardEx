function createRowUserFirst(user){
	$('#intercambio-column .list-group').append(`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
			role="tab" data-toggle="tab" class="list-group-item active">
			`+user.usuario+`
			<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>
			</a>
			<c:set var="currentUser" value="`+user.usuario+`" /> `);
	addBehaviour();
}

function createRowUser(user){
	$('#intercambio-column .list-group').append(`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
			role="tab" data-toggle="tab" class="list-group-item usuarios">
			`+user.usuario+`</a>`);
	addBehaviour();
}

function createRowCardPide(card){
	$('#pide-column .list-group').append(`<div class="tab-pane fade in">
			<li class="list-group-item r">
			<a class="nostyle" href="#" data-toggle="modal" data-target="#`
			+ card.id+`">`+card.name+`</a>
			<!--Cartas -->
			<div class="modal fade" id="`+card.id+`" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
			<h:modalCarta cartaPropia="`+card+`" />
			</div>
			</div>
			</li>
	</div>`);
}

function createRowCardOfrece(card){
	$('#ofrece-column .list-group').append(`<div class="tab-pane fade in">
			<li class="list-group-item r">
			<a class="nostyle" href="#" data-toggle="modal" data-target="#` + card.carta.id+`">`+card.carta.name+`</a>
			<!--Cartas -->
			<div class="modal fade" id="`+card.carta.id+`" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
			<h:modalCarta cartaPropia="`+card+`" />
			</div>
			</div>
			</li>
	</div>`);
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
	    	$('.badge').remove();
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