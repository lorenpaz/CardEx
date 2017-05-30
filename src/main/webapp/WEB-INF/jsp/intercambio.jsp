<%@ include file="../jspf/header.jspf" %>

	<div class="container">
        <div class="extended" id="intercambio">
        	<div class="centered text-center">
	            <div id="ofrece-column" class="offer-column col-intercambio">
	                <h3>Tú</h3>
	                <table class="table table-striped">
	                  <caption>Ofreces</caption>
	                  <tr>
	                      <th>Carta</th>
	                      <th>Estado</th>
	                      <th>Cantidad</th>
                      </tr>
                      <tr id="spinner0a">
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-success">Nueva</span></td>
                      	<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
						<input  type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td> 
                      </tr>
                      <tr id="spinner1a">
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-warning">Jugada</span></td>
                      	<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
						<input  type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td>
                      </tr>
                      <tr id="spinner2a">
                      	<td>Carta 2</td>
                      	<td><span class="estadoCarta label label-danger">Deteriorada</span></td>
                      	<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
						<input  type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td>
                      </tr>
                    </table>
	            </div> 
				
	            <div class="img-exchange">
	            	<img src="${prefijo}${prefix}img/exchange.png"/>
	            </div>

	            <div id="pide-column" class="offer-column col-intercambio">
	                <h3>Pepe</h3>
	                <table class="table table-striped">
		                <caption>Pides</caption>
		                  <tr>
		                      <th>Carta</th>
		                      <th>Estado</th>
		                      <th>Cantidad</th>
	                      </tr>
	                      <tr id="spinner0b">
	                      	<td>Carta 5</td>
	                      	<td><span class="estadoCarta label label-success">Nueva</span></td>
	                      	<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
							<input  type="text" name="quantity" class="cantidad-carta" value="0">
							<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td>
	                      </tr>
	                      <tr id="spinner1b">
	                      	<td>Carta 6</td>
	                      	<td><span class="estadoCarta label label-success">Nueva</span></td>
	                      	<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
							<input type="text" name="quantity" class="cantidad-carta" value="0">
							<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td>
	                      </tr>
	                      <tr id="spinner2b">
	                      	<td>Carta 6</td>
	                      	<td><span class="estadoCarta label label-danger">Deteriorada</span></td>
	                      	<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
							<input  type="text" name="quantity" class="cantidad-carta" value="0">
							<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td>
	                      </tr>
                    </table>
	            </div>
	            <form action="${prefijo}intercambio/ofrecer">
            	<button class="btn btn-primary" type="submit">Ofrecer</button>
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            	</form>
            </div>
        </div>
    </div>
    
    <%@ include file="../jspf/footer.jspf" %>