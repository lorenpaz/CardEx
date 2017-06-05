
function createRow(name , edition){
	$('#table_cards tbody').append(`<tr>
			<td class="filterable-cell">`+ name +`</td>
			<td class="filterable-cell">`+ edition +`</td>
			</tr>`);
}

function poblarTablas(rows){
	var j;
	for(j=0; j<rows.length; j++){
		createRow(rows[j].name, rows[j].setName);
	}
}

function filterByState(state){
	filterInter = $(filterInter).filter(function(){
		var n = this.setName;
		return (n == edition);
	});
}

function filterByName(userRec){
	filterInter = $(filterInter).filter(function(){
		var n = this.type;
		return (n.includes(type));
	});
}


function filterTable(state, userRec){
	filterByState(state);
	
	if(userRec != ''){
		filterByName(userRec);
	}
	$('#table_cards tbody>tr').remove();
	poblarTablas(filterInter);
	checks = [];
	filterInter = $(interJson);
}

var filterInter;

$( function(){
	
	filterInter = $(interJson);
	
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
	    	
	    	$(".filter").change(function(){
	        	let name = $('#filterState').val();
	        	let state = $('#filterName').val();
	        	filterTable(state, name);
	        });
	    }
	});
	
});
