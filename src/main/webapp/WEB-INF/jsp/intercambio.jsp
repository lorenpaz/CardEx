<%@ include file="../jspf/header.jspf" %>

	<div class="container">
        <div class="extended" id="intercambio">
        	<div class="centered text-center">
        		<form action="${prefijo}intercambio/ofrecer" method="POST">
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
                      	<td><c:out value="${cartaPropia.carta.name}"/></td>
                      	<td><span class="estadoCarta label label-success"><c:out value="${cartaPropia.estadoCarta}"/></span></td>
                      	<td>
						<input  type="hidden" name="cartasO[]" value="${cartaPropia.id}">
						<input  type="number" name="quantityO[]" class="cantidad-carta" value="0" min="0" max="${cartaPropia.cantidad}" >
						</td> 
                      </tr>  
                      </c:forEach>
                    </table>
	            </div> 
				
	            <div class="img-exchange">
	            	<img src="${prefijo}${prefix}img/exchange.png"/>
	            </div>

	            <div id="pide-column" class="offer-column col-intercambio">
	                <h3><c:out value="${usuarioIntercambio.usuario}"/></h3>
	                <table class="table table-striped">
		                <caption>Pides</caption>
		                  <tr>
		                      <th>Carta</th>
		                      <th>Estado</th>
		                      <th>Cantidad</th>
	                      </tr> 
                      <c:forEach items="${usuarioIntercambio.cartasPropias}" var="cartaPropia">
                      <tr id="spinner${cartaPropia.id}b">
                      	<td><c:out value="${cartaPropia.carta.name}"/></td>
                      	<td><span class="estadoCarta label label-success"><c:out value="${cartaPropia.estadoCarta}"/></span></td>
                      	<td>
						<input  type="hidden" name="cartasP[]" value="${cartaPropia.id}">
						<input  type="number" name="quantityP[]" class="cantidad-carta" value="0" min="0" max="${cartaPropia.cantidad}">
						</td> 
                      </tr>  
                      </c:forEach>
                    </table>
	            </div>
            	<button class="btn btn-primary" type="submit">Ofrecer</button>
            	<input	type="hidden" name="usuarioQuePido" value="${usuarioIntercambio.usuario}">
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            	<c:choose>
            	<c:when test="${contraOferta}">
            	<input type="hidden" name="intercambioID" value="${intercambio.id}" />
            	</c:when>
            	<c:otherwise>
            	<input type="hidden" name="intercambioID" value="0" />
            	</c:otherwise>
            	</c:choose>
            	</form>
            </div>
        </div>
    </div>
    
    <%@ include file="../jspf/footer.jspf" %>