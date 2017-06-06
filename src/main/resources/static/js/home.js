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
		createRowCardOfrece(filterOwnCards[i]);
	}
	$('#pide-column .list-group').empty();
	for(i = 0; i < filterSearchCards.length; i++) {
		createRowCardPide(filterSearchCards[i]);
	}
}

function poblarListaUsuarios(){
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
		var n = this.usuario;
		return (n.includes(user));
	});
	return filterUsers[0];
}

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
	}
}

function filterCardsByName(card){
	var filterAux = filterUsers;
	var i;
	for(i=0; i<filterUsers.length; i++){
		filterOwnCards = filterUsers[i].cartasPropias.filter(function(c){
			return c.carta.name.includes(card);
		});
		if(filterOwnCards>0){
			loadFilterUsersListInOwnCards(filterOwnCards);
			filterAux[i].cartasPropias = filterUsers[i].cartasPropias;
		}
		filterSearchCards = filterUsers[i].cartasBuscadas.filter(function(c){
			return c.name.includes(card);
		});
		if(filterSearchCards>0){
			loadFilterUsersListInSearchCards(filterUser);
			filterAux[i].cartasBuscadas = filterUsers[i].cartasBuscadas;
		}
	}
	filterUsers = filterAux;
	currentUser = filterUsersList[0];
}

function isNotInside(user){
	var res = $(filterUsersList).filter(function(){
		var n = this.usuario;
		return (n.includes(user.usuario));
	});
	return res.length == 0;
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
	        
	        
	        document.getElementById('intercambioEnviar').action = "../intercambio/"+user;
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
	    var card = $(this).val();
        var user = $('#usuarioFilter').val();
        filterCardsByCardAndUser(user, card);
        poblarListasCartas();
        poblarListaUsuarios();
    });
    
    $("#usuarioFilter").on('keyup', function(){
    	
    	var user = $(this).val();
        var card = $('#cartaFilter').val();
        filterCardsByCardAndUser(user, card);
        poblarListasCartas();
        poblarListaUsuarios();
     });
    
    addBehaviour();/*
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
	        
	        
	        document.getElementById('intercambioEnviar').action = "../intercambio/"+user;
	        $(this).append('<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>');
	        filterUsers = usuariosJSON.usuarios;
	        filterUsersList = filterUsers;
	    }
	});*/
	
});


// Enviar oferta
function enviarOferta(e) {

	var usuarioIntercambio = e.target;
	console.log(usuarioIntercambio);
	$('#intercambioEnviar').action = $('#intercambioEnviar').action + usuarioIntercambio;
	console.log($('#intercambioEnviar').action);
}