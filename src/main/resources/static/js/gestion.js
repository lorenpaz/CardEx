function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev, col) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var org = document.getElementById(data);
    var newElement = document.createElement("tr");
    newElement.setAttribute("draggable","true");
    newElement.setAttribute("ondragstart","drag(event)");
    var newTd = document.createElement("td");
    if(col === 1){
    	newTd.innerHTML = "<button type='button' class='btn btn-link btn-xs' onclick='removeCard(event)'>X</button>";
    	newElement.appendChild(org);
    	newElement.appendChild(newTd);
    	var parent = ev.target.parentNode;
    	parent.parentNode.insertBefore(newElement, parent.nextSibling);
    }
    if(col === 2){
    	newTd.innerHTML =   "Saga de Urza";
    	var newTd2 = document.createElement("td");
    	newTd2.innerHTML =  "<select class='form-control input-sm'><option>Nueva</option><option>Jugada</option><option>Deteriorada</option></select>"
    	var newTd3 = document.createElement("td");
    	newTd3.innerHTML =  "<button type='button' class='btn btn-xs' onclick='decrSpinner(event)'>"+
    						"<span class='glyphicon glyphicon-minus'></span></button> "+
    						"<input  type='text' name='quantity' class='cantidad-carta' value='0'>"+
    						" <button type='button' class='btn btn-xs' onclick='incrSpinner(event)'>"+
    						"<span class='glyphicon glyphicon-plus'></span></button>";
    	var newTd4 = document.createElement("td");
    	newTd4.innerHTML =	"<button type='button' class='btn btn-link btn-xs' onclick='removeCard(event)' >X</button></td>";
    	newElement.appendChild(org);
    	newElement.appendChild(newTd);
    	newElement.appendChild(newTd2);
    	newElement.appendChild(newTd3);
    	newElement.appendChild(newTd4);
    	var parent = ev.target.parentNode;
    	parent.parentNode.insertBefore(newElement, parent.nextSibling);
    }
    if(col == 3){
    	newTd.innerHTML=org.innerHTML;
    }
}

function decrSpinner(e){
	var element = e.srcElement.parentNode.parentNode.getElementsByTagName("input")[0];
	var num = element.value;
	var id = element.id;
	id = id.substring(1);
	id = '#quantity'.concat(id);
	var idQ = $(id).html();
	if(num > 0){
		num--;
		element.value = num;
	}
	$(id).val(num);
}

function incrSpinner(e){
	var element = e.srcElement.parentNode.parentNode.getElementsByTagName("input")[0];
	var num = element.value;
	var id = element.id;
	num++;
	element.value = num;
	id = id.substring(1);
	id = '#quantity'.concat(id);
	var idQ = $(id).html();
	$(id).val(num);
}

/*MÉTODO QUE PONE EN EL HIDDEN EL VALOR DEL QUANTITY CADA VEZ QUER CAMBIE EL TEXT INPUT -- IMPORTANTEEEEEEEEE-HECHOO*/

function removeCard(e, table){
	var element = e.srcElement.parentNode.parentNode;
	var parent = element.parentNode;
	var removed = e.srcElement.parentNode.parentNode.id;
	var idR = removed.substring(4);
	
	if(table == 2){
		var nameSRow = "nameSRow"+idR;
		var edSRow = "edSRow"+idR;
		$(nameSRow).remove();
		$(edSRow).remove();
	}else{
		var nameORow = "nameORow"+idR;
		var edORow = "edORow"+idR;
		var quantityORow = "quantityORow"+idR;
		var stateORow = "stateORow"+idR;
		$(nameORow).remove();
		$(edORow).remove();
		$(quantityORow).remove();
		$(stateORow).remove();
	}
	
	var cont = 0;
	if(parent.parentNode.parentNode.id == "table_propias")
	{
		while(cont<4)
		{
			parent.removeChild(element.nextSibling);
			cont++;
		}
	}
	else{
		console.log(parent.nextSibling);
		while(cont<2)
		{
			parent.removeChild(element.nextSibling);
			cont++;
		}
	}
	parent.removeChild(element);

}

/*$( function() {
    $(".cantidad-carta").change( function(){
        var value = $(this).val();
        var valueAux = $(this).attr('id');
        var id = valueAux.substring(1);
        value += 1;
        console.log(valueAux);
        id = '#quantity'.concat(id);
        $(id).val(value);
    });
}
);*/

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
				'</td><td class="filterable-cell"><button type="button" class="btn btn-xs spinner" onclick="decrSpinner(event)">-</button> '+
				'<input  id="qORow'+ contTable1 +'" type="text" name="quantity" class="cantidad-carta" value="1">'+
				'<button type="button" class="btn btn-xs spinner" onclick="incrSpinner(event)">+</button></td><td class="filterable-cell text-right">'+
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

var selected;
var contTable1;
var contTable2;


$(document).ready(function() {
	contTable1 = $('tab2').children().length+1;
	contTable2 = $('tab3').children().length+1;
    $('#paginacionTabla').DataTable({
    	"lengthMenu": [5, 10, 25, 50, 75, 100 ],
    	"pageLength": 5,
    	"lengthChange": false
    });
    
    $("#tab1 tr").click(function(){
    	selected = $(this);
        $(this).addClass("info").siblings().removeClass("info");
    });
    
    $(".cantidad-carta").change(function(){
    	var changed = $(this);
    	var value = changed.val();
    	id = changed.attr('id');
    	id = id.substring(1);
    	id = "#quantity"+ id;
    	$(id).val(value);    	
    });
    
    /*$("#btnGuardar").click(function(){
    	$('propias') = $('#tab2 tbody').children();
    	for(int i = 0; i< )
    });*/
 } );
