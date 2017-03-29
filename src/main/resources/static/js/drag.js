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
    var newTd = document.createElement("td");
    if(col === 1){
    	newTd.innerHTML = "<span class='glyphicon glyphicon-remove'></span>";
    	newElement.appendChild(org);
    	newElement.appendChild(newTd);
    	var parent = ev.target.parentNode;
    	parent.parentNode.insertBefore(newElement, parent);
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
    	newTd4.innerHTML =	"<span class='glyphicon glyphicon-remove'></span></td>";
    	newElement.appendChild(org);
    	newElement.appendChild(newTd);
    	newElement.appendChild(newTd2);
    	newElement.appendChild(newTd3);
    	newElement.appendChild(newTd4);
    	var parent = ev.target.parentNode;
    	parent.parentNode.insertBefore(newElement, parent);
    }
}

function decrSpinner(e){
	var element = e.srcElement.parentNode.parentNode.getElementsByTagName("input")[0];
	var num = element.value;
	if(num > 0){
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

