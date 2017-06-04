/*MÉTODO QUE PONE EN EL HIDDEN EL VALOR DEL QUANTITY CADA VEZ QUER CAMBIE EL TEXT INPUT -- IMPORTANTEEEEEEEEE-HECHOO*/

function removeCard(e, table){
	var element = e.srcElement.parentNode.parentNode;
	var parent = element.parentNode;
	var removed = e.srcElement.parentNode.parentNode.id;
	var idR = removed.substring(4);
	
	if(table == 2){
		var nameSRow = "#nameSRow"+idR;
		var edSRow = "#edSRow"+idR;
		$(nameSRow).remove();
		$(edSRow).remove();
	}else{
		var nameORow = "#nameORow"+idR;
		var edORow = "#edORow"+idR;
		var quantityORow = "#quantityORow"+idR;
		var stateORow = "#stateORow"+idR;
		$(nameORow).remove();
		$(edORow).remove();
		$(quantityORow).remove();
		$(stateORow).remove();
	}
	
	parent.removeChild(element);

}

function incrSearch(){
	if(selected != undefined){
		var childrens = $(selected).children();
		var searched = $('#tab3 tbody').append('<tr id="sRow'+ contTable2 +'"><td class="filterable-cell">'+childrens[0].innerHTML+'</td><td class="filterable-cell">'+childrens[1].innerHTML+'</td><td class="filterable-cell text-right"><button type="button" class="btn btn-link btn-xs" onclick="removeCard(event,2)">X</button></td></tr>');
		var hidden1 = $('#tab3 tbody').append('<input id="nameSRow'+ contTable2 +'" type="hidden" value="'+childrens[0].innerHTML+'" name="cardsS[]"></input>');
		var hidden2 = $('#tab3 tbody').append('<input id="edSRow'+ contTable2 +'" type="hidden" value="'+childrens[1].innerHTML+'" name="cardsSE[]"></input>');
		contTable2++;
	}else{
		alert('Pinche primero en una carta y después en una de las flechas');
	}
	
}

function incrOwner(){
	if(selected != undefined){
		var childrens = $(selected).children();
		var owner = $('#tab2 tbody').append('<tr id="oRow'+ contTable1 +'"><td class="filterable-cell">'+childrens[0].innerHTML+'</td>'+
				'<td class="filterable-cell">'+childrens[1].innerHTML+'</td><td class="filterable-cell"><select id="selORow'+ contTable1 +
				'"class="form-control input-sm selectorEstado" onchange="updateState(event)"><option>Nueva</option><option>Jugada</option><option>Deteriorada</option></select>'+
				'</td><td class="filterable-cell"> '+
				'<input  id="qORow'+ contTable1 +'" type="number" name="quantity" class="cantidad-carta" value="1" min="1" onchange="updateQ(event)">'+
				'</td><td class="filterable-cell text-right">'+
				'<button type="button" class="btn btn-link btn-xs" onclick="removeCard(event, 1)" >X</button></td></tr>');
		var hidden1 = $('#tab2 tbody').append('<input id="nameORow'+ contTable1 +'" type="hidden" value="'+childrens[0].innerHTML+'" name="cardsO[]"></input>');
		var hidden2 = $('#tab2 tbody').append('<input id="edORow'+ contTable1 +'" type="hidden" value="'+childrens[1].innerHTML+'" name="cardsOE[]"></input>');
		var hidden3 = $('#tab2 tbody').append('<input id="quantityORow'+contTable1+'" type="hidden" value="1" name="cardsOQ[]"></input>');
		var hidden4 = $('#tab2 tbody').append('<input id="stateORow'+contTable1+'" type="hidden" value="Nueva" name="cardsOS[]"></input>');
		contTable1++;
	}else{
		alert('Pinche primero en una carta y después en una de las flechas');
	}
}

function updateState(e){
	var selectorCambiado = e.srcElement;
	var estadoCambiado = selectorCambiado.value;
	var id = selectorCambiado.id;
	id = id.substring(3);
	id = '#state'.concat(id);
	$(id).val(estadoCambiado);
}

function updateQ(e){
	var changed = e.srcElement;
	var value = changed.value;
	id = changed.id;
	id = id.substring(1);
	id = "#quantity"+ id;
	$(id).val(value);
}

function createRow(name , edition){
	$('#table_cards tbody').append(`<tr onclick="selectRow(event)">
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

function selectRow(ev){
	selected = ev.srcElement.parentNode;
	$(selected).addClass("info").siblings().removeClass("info");
}

var selected;
var contTable1;
var contTable2;
var checks = [];
var filterCards;


window.onload = function() {
	contTable1 = $('#tab2').children('tbody').children('tr').length;
	contTable2 = $('#tab3').children('tbody').children('tr').length;
	filterCards = $(cards);
    $('#paginacionTabla').DataTable({
    	"lengthMenu": [5, 10, 25, 50, 75, 100 ],
    	"pageLength": 5,
    	"lengthChange": false
    });   
    
    $(".filter").change(function(){
    	let edition = $('#filterEdition').val();
    	let type = $('#filterType').val();
    	var i;
    	for(i=0; i<6; i++){
    		if($('.chk_filter')[i].checked){
    			let checkbox = $('.chk_filter')[i];
    			checks.push(checkbox.value);
    		}
    	}
    	filterTable(edition, type, checks);
    });
    
 }


