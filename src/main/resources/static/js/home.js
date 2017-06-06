	
function poblarListasCartas(usuarios){
		var j;
		for(j=0; j<usuarios.length; j++){
			for(i = 0; i < usuarios[j].cartasPropias.length; i++) {
				createRowCardOfrece(usuarios[j].cartasPropias[i]);
			}
			for(i = 0; i < usuarios[j].cartasBuscadas.length; i++) {
				createRowCardPide(usuarios[j].cartasBuscadas[i]);
			}
		}
}

function poblarListaUsuarios(usuarios){
	var j;
	for(j=0; j<usuarios.length; j++){
		if(j==0)
		{
			createRowUserFirst(usuarios[j]);
		}else{
		createRowUser(usuarios[j]);
		}
	}
}

function createRowUserFirst(user){
	$('#intercambio-column .list-group').append(`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
	role="tab" data-toggle="tab" class="list-group-item active">
	`+user.usuario+`
	<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>
	</a>
	<c:set var="currentUser" value="`+user.usuario+`" /> `);
}

function createRowUser(user){
	$('#intercambio-column .list-group').append(`<a href="`+user.id+`" aria-controls="tab-`+user.id+`"
	role="tab" data-toggle="tab" class="list-group-item usuarios">
	`+user.usuario+`</a>`);
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

function filterListas(card,user,tab) {
	
	if(tab == false)
	{
		filterByUser(user);
		filterByCard(card,user);
		$('.list-group').empty();
		poblarListaUsuarios(filterUsers);
	}else{
		$('#ofrece-column .list-group').empty();
		$('#pide-column .list-group').empty();
		filterByCard(card);	
	}
	poblarListasCartas(filterUsers);
	filterUsers = usuariosJSON.usuarios;
}

function filterByUser(user){
	filterUsers = $(filterUsers).filter(function(){
		var n = this.usuario;
		return (n.includes(user));
	});
}

function filterByCard(card){
	filterUsers = $(filterUsers).cartasPropias.filter(function(user){
			return this.carta.name.includes(card);
	});
	filterUsers += $(filterUsers).cartasBuscadas.filter(function(user) {
			return this.name.includes(card);
		});
} 


var filterUsers;
var tab = false;

$( function(){
	
	filterUsers = usuariosJSON.usuarios;

    $("#cartaFilter").on('keyup', function(){	
	    var card = $(this).val();
        var user = $('#usuarioFilter').val();
        
		filterListas(card,user);
    });
    
    $("#usuarioFilter").on('keyup', function(){
    	
    	var user = $('#usuariosFilter').val();
        var card = $('#cartaFilter').val();
        
        filterListas(card,user);
     });
    
    
	//Cambiar botón al pulsar en el usuario
	$("#intercambio-column .list-group-item").click(function(){
	    if (!$(this).hasClass('active')){
	    		
	    	var usuario = $(this).text().replace(/\s/g,'');
	    	
	        //Filtro
	        var user = usuario;
	        tab = true;
	        var card = $('#cartaFilter').val();
	        filterListas(card,user,tab);
	        
	    	var anterior = $('.active').addClass('usuarios').removeClass('active');
	    	$('.badge').remove();
	    	$('#usuarioQuieroIntercambio').remove();
	    	
	        $(this).addClass('active');
	        $(this).removeClass('usuarios');
	        
	        var cartasDelUsuario = $(this).attr('href');
	        cartasDelUsuario = "."+cartasDelUsuario;
	        
	        
	        document.getElementById('intercambioEnviar').action = "../intercambio/"+usuario;
	        $(this).append('<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>');
	        
	    }
	});
	
});


//Enviar oferta
function enviarOferta(e) {

	var usuarioIntercambio = e.target;
	console.log(usuarioIntercambio);
	$('#intercambioEnviar').action = $('#intercambioEnviar').action + usuarioIntercambio;
	console.log($('#intercambioEnviar').action);
}