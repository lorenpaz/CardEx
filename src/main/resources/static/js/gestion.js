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
		var childrens = selected.children();
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
		var childrens = selected.children();
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

var selected;
var contTable1;
var contTable2;


window.onload = function() {
	contTable1 = $('#tab2').children('tbody').children('tr').length;
	contTable2 = $('#tab3').children('tbody').children('tr').length;
    $('#paginacionTabla').DataTable({
    	"lengthMenu": [5, 10, 25, 50, 75, 100 ],
    	"pageLength": 5,
    	"lengthChange": false
    });
    
    $("#tab1 tr").click(function(){
    	selected = $(this);
        $(this).addClass("info").siblings().removeClass("info");
    });   
    
 }


