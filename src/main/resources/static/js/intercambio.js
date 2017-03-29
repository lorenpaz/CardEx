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
