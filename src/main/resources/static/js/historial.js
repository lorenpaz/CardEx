
function createRow(name , edition){
	$('#table_cards tbody').append(`<tr>
			<td class="filterable-cell">`+ name +`</td>
			<td class="filterable-cell">`+ edition +`</td>
			</tr>`);
}

function poblarTabla(rows){
	var j;
	for(j=0; j<rows.length; j++){
		createRow(rows[j].name, rows[j].setName);
	}
}

function filterByEdition(edition){
	filterCards = $(filterCards).filter(function(){
		var n = this.setName;
		return (n == edition);
	});
}

function filterByType(type){
	filterCards = $(filterCards).filter(function(){
		var n = this.type;
		return (n.includes(type));
	});
}

function filterByColor(color){
	filterCards = $(filterCards).filter(function(){
		var colors = this.colorIdentity;
		var filterBoolean = true;
		var z;
		for(z=0; z<color.length; z++){
			if(colors != undefined){
				filterBoolean = filterBoolean && (colors.includes(color[z]));
			}else{
				filterBoolean = false;
			}
		}
		return filterBoolean;
	});
}

function filterTable(edition, type, color){
	if(edition != 'Todas'){
		filterByEdition(edition);
	}
	
	if(type != 'Todos'){
		var t;
		if(type=='Tierra'){
			t='Land';
		}else{
			if(type=='Criatura'){
				t='Creature';
			}else{
				if(type=='Encantamiento'){
					t='Enchantment';
				}else{
					if(type=='Instantáneo'){
						t='Instant';
					}else{
						if(type=='Artefacto'){
							t='Artifact';
						}else{
							if(type=='Hechizo'){
								t='Sorcery';
							}
						}
					}
				}
			}
		}
		filterByType(t);
	}
	
	var transColor = [];
	if(color.length>0){
		let j;
		for(j=0; j<color.length; j++){
			if(color[j] == 'azul'){
				transColor.push('U');
			}
			if(color[j] == 'verde'){
				transColor.push('G');
			}
			if(color[j] == 'negro'){
				transColor.push('B');
			}
			if(color[j] == 'blanco'){
				transColor.push('W');
			}
			if(color[j] == 'rojo'){
				transColor.push('R');
			}
			if(color[j] == 'incoloro'){
				
			}
		}
		filterByColor(transColor);
	}
	$('#table_cards tbody>tr').remove();
	poblarTabla(filterCards);
	checks = [];
	filterCards = $(cards);
}



$( function(){
	
	//Cambiar visibilidad de forma predeterminada al primer usuario
	var idUsuario = $(".list-group-item.active").attr('href');
	$('.'+idUsuario).css("display","unset");
	
	//Input de forma predeterminada
	var primerIntercambio = $(".active").attr('id');
	$("#aceptar").attr('value', primerIntercambio);
	$("#rechazar").attr('value', primerIntercambio);
	$("#contraoferta").attr('value', primerIntercambio);
	
	//Cuando tocas a otro usuario
	$("#intercambio-column .list-group-item").click(function(){
	    if (!$(this).hasClass('active')){
	  
	    	//Mover el icono de la flecha junto con las clases active y usuarios
	    	var anterior = $('.active').addClass('usuarios').removeClass('active');
	    	$('.badge').remove();
	    	
	        $(this).addClass('active');
	        $(this).removeClass('usuarios');
	        
	        //Cambiar el href
	        var cartasDelUsuario = $(this).attr('href');
	        cartasDelUsuario = "."+cartasDelUsuario;
	        
	    /*    //Cambiar visibilidad
	        $(cartasDelUsuario).css("display","inline");
	         
*/
	        $(this).append('<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span></span>');
	        
	        //Cambiar input de los post
	    	var primerIntercambio = $(".active").attr('id');
	    	$("#aceptar").attr('value', primerIntercambio);
	    	$("#rechazar").attr('value', primerIntercambio);
	    	$("#contraoferta").attr('value', primerIntercambio);
	    }
	});
	
});
