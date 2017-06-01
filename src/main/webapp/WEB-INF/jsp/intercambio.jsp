<%@ include file="../jspf/header.jspf" %>

	<div class="container">
        <div class="extended" id="intercambio">
        	<div class="centered text-center">
        		<form action="${prefijo}intercambio/ofrecer" action="post">
	            <div id="ofrece-column" class="offer-column col-intercambio">
	                <h3>Tú</h3>
	                <table class="table table-striped">
	                  <caption>Ofreces</caption>
	                  <tr>
	                      <th>Carta</th>
	                      <th>Estado</th>
	                      <th>Cantidad</th>
                      </tr>
                      <c:forEach items="${user.cartasPropias}" var="cartaPropia">
                      <tr id="spinner${cartaPropia.id}a">
                      	<td>${cartaPropia.carta.name}</td>
                      	<td><span class="estadoCarta label label-success">${cartaPropia.estadoCarta}</span></td>
                      	<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
						<input  type="hidden" name="cartasO[]" value="${cartaPropia.id}">
						<input  type="text" name="quantityO[]" class="cantidad-carta" value="1">
						<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td> 
                      </tr>  
                      </c:forEach>
                    </table>
	            </div> 
				
	            <div class="img-exchange">
	            	<img src="${prefijo}${prefix}img/exchange.png"/>
	            </div>

	            <div id="pide-column" class="offer-column col-intercambio">
	                <h3>${usuarioIntercambio.usuario}</h3>
	                <table class="table table-striped">
		                <caption>Pides</caption>
		                  <tr>
		                      <th>Carta</th>
		                      <th>Estado</th>
		                      <th>Cantidad</th>
	                      </tr> 
                      <c:forEach items="${usuarioIntercambio.cartasPropias}" var="cartaPropia">
                      <tr id="spinner${cartaPropia.id}b">
                      	<td>${cartaPropia.carta.name}</td>
                      	<td><span class="estadoCarta label label-success">${cartaPropia.estadoCarta}</span></td>
                      	<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
						<input  type="hidden" name="cartasP[]" value="${cartaPropia.id}">
						<input  type="text" name="quantityP[]" class="cantidad-carta" value="1">
						<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td> 
                      	<input	type="hidden" name="usuarioQuePido" value="${usuarioIntercambio.id}">
                      </tr>  
                      </c:forEach>
                    </table>
	            </div>
            	<button class="btn btn-primary" type="submit">Ofrecer</button>
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            	</form>
            </div>
        </div>
    </div>
    
    <%@ include file="../jspf/footer.jspf" %>