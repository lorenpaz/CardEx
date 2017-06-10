function filterUser(user){
	return allUsers.filter(function(u){
		return u.usuario.toLowerCase().includes(user.toLowerCase());
	});
}

function filterEdition(ed){
	return allEditions.filter(function(e){
		return e.name.toLowerCase().includes(ed.toLowerCase());
	});
}

function poblarListaUsuarios(){
	$('#tablaUsuarios tbody').empty();
	var i;
	for(i=0; i<filterUsers.length; i++){
		createRowUsuarios(filterUsers[i]);
	}
}

function poblarListaEdiciones(){
	$('#tablaEdiciones tbody').empty();
	var i;
	for(i=0; i<filterEditions.length; i++){
		createRowEdiciones(filterEditions[i]);
	}
}

function createRowUsuarios(usuario){
	var bloque;
	if(usuario.activo){
		bloque = `<td>Activo</td>
									<td></td>
									<td>
									<form method='post' action="../admin/deshabilitaUser">
		                  			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button>
		                  			    
		                  			  	<input type="hidden" name="id" value="`+usuario.id+`"/> 
	                 					`+csrf+`
		                  			  </form>
									</td>`;
	}else{
		bloque = `<td>Inactivo</td>
			<td>
				 <form method='post' action="../admin/habilitaUser">
      			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-ok"></span></button>
      			    
      			  	<input type="hidden" name="id" value="`+usuario.id+`"/> 
 					`+csrf+`
      			  </form>					
			</td>
			<td></td>`;
	}
	
	$('#tablaUsuarios tbody').append(`<tr>
		                      <td>`+usuario.usuario+`</td>
		                      <td>`+usuario.fechaAlta+`</td>
		                      	`+bloque+`
						    </tr>`);
}

function createRowEdiciones(edicion){
	var bloque;
	if(edicion.fechaUltimaActualizacion == null){
		bloque = `<td>Sin Actualizar</td>
      			  <td>
          			  <form method='post' action="../admin/updateCardSet">
          			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-refresh"></span></button>
          			    
          			  	<input type="hidden" name="code" value="`+edicion.code+`"/> 
						`+csrf+`
          			  </form>
      			  </td>
      			  <td></td>`;
	}else{
		bloque = `<td>`+edicion.fechaUltimaActualizacion+`</td>
      			  <td>
          			  <form method='post' action="../admin/updateCardSet">
          			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-refresh"></span></button>
          			    
          			  	<input type="hidden" name="code" value="`+edicion.code+`"/> 
						`+csrf+`
          			  </form>
      			  </td>
      			  <td><form method='post' action="../admin/removeCardSets">
          			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button>
          			    
          			    <input type="hidden" name="code" value="`+edicion.code+`"/> 
          			  	<input type="hidden" name="name" value="`+edicion.name+`"/> 
						`+csrf+`
          			  </form>
          		</td>`;
	}
	$('#tablaEdiciones tbody').append(`<tr>
				                      	<td>`+edicion.name+`</td>`+ bloque +`</tr>`);
}

var allUsers;
var allEditions;
var filterUsers;
var filterEditions;

$(function() {
	allUsers = usuariosJSON.usuarios;
	allEditions = edicionesJSON.ediciones;
    $("#filterUser").on('keyup', function(){
    	filterUsers = allUsers;
    	var user = $(this).val();
    	filterUsers = filterUser(user);
        poblarListaUsuarios();
     });
    $("#filterEdition").on('keyup', function(){
    	filterEditions = allEditions;
    	var ed = $(this).val();
    	filterEditions = filterEdition(ed);
        poblarListaEdiciones();
     });
});