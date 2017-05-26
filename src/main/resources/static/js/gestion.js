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
	if(num > 0){10
		num--;
		element.value = num;
	}
	
}

function incrSpinner(e){
	var element = e.srcElement.parentNode.parentNode.getElementsByTagName("input")[0];
	var num = element.value;
	num++;
	element.value = num;
	
}

function removeCard(e, table){
	if(table == 1){
		contTable1--;
	}else{
		contTable2--;
	}
	var element = e.srcElement.parentNode.parentNode;
	var parent = element.parentNode;
	parent.removeChild(element);
}

function incrSearch(){
	//var searched = $('#tab3 tbody').append('<tr id="oRow'+ contTable2 +'"><td class="filterable-cell">'+selected.children().html()+'</td><td class="filterable-cell text-right"><button type="button" class="btn btn-link btn-xs" onclick="removeCard(event, 2)">X</button></td></tr>');
	var anadido = $('#tab3 tbody').append('<input type="text" value="'+selected.children().html()+'" name="cardsO[]"></input>');
	contTable2++;
}

function incrOwner(){
	var childrens = selected.children();
	//var searched = $('#tab2 tbody').append('<tr id="sRow'+ contTable2 +'"><td class="filterable-cell">'+childrens[0].innerHTML+'</td><td class="filterable-cell">'+childrens[1].innerHTML+'</td><td class="filterable-cell"><select class="form-control input-sm"><option>Nueva</option><option>Jugada</option><option>Deteriorada</option></select></td><td class="filterable-cell"><button type="button" class="btn btn-xs spinner" onclick="decrSpinner(event)">-</button> <input  type="text" name="quantity" class="cantidad-carta" value="0"><button type="button" class="btn btn-xs spinner" onclick="incrSpinner(event)">+</button></td><td class="filterable-cell text-right"><button type="button" class="btn btn-link btn-xs" onclick="removeCard(event, 1)" >X</button></td></tr><input type="text" value="'+childrens[0].innerHTML+'" name="cardsS['+contTable1+']"/>');
	var anadido = $('#tab2 tbody').append('<input type="text" value="'+selected.children().html()+'" name="cardsS[]"></input>');
	console.log(searched);
	contTable1++;
}

var selected;
var contTable1;
var contTable2;


$(document).ready(function() {
	contTable1 = 0;
	contTable2 = 0;
    $('#paginacionTabla').DataTable({
    	"lengthMenu": [5, 10, 25, 50, 75, 100 ],
    	"pageLength": 5,
    	"lengthChange": false
    });
    
    $("#tab1 tr").click(function(){
    	selected = $(this);
        $(this).addClass("info").siblings().removeClass("info");
    });
 } );
