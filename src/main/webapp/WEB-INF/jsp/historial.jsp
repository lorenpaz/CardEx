<%@ include file="../jspf/header.jspf" %>
<div class="container">
	<div id="filter" class="zona-info form-inline form-search">
        <label for="offerFilter">Tipo de oferta:</label>
        <select class="input-large form-control filter" id="offerFilter">
            <option selected value="Pendiente">Pendientes</option>
            <option value="Aceptado">Aceptadas</option>
            <option value="Rechazado">Rechazadas</option>
            <option value="Finalizado">Finalizadas</option>
        </select>&nbsp;&nbsp;
        <label for="userFilter">Usuario:</label>
        <input type="search" class="form-control filter" id="userFilter" placeholder="Busca un usuario">
	 </div>
	<c:forEach items="${intercambios}" var="intercambio" varStatus="status">
	<c:if test="${status.first}">
    <div id="intercambio">
		<div id="intercambio-column" class="offer-column">
			<div class="list-group">
			<c:forEach items="${intercambios}" var="intercambi" varStatus="status2">
			<c:if test="${intercambi.estadoIntercambio == 'Pendiente'}">
			<c:choose>
			<c:when test="${status2.first}">
				<c:choose>
				<c:when test="${user.id == intercambi.usuarioOfrece.id}">
				<a href="${intercambi.id}" aria-controls="tab-${intercambi.id}"
				role="tab" data-toggle="tab" id="${intercambi.id}" class="list-group-item active"><c:out value="${intercambi.usuarioRecibe.usuario}"/>
				<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span>
				</span>
				</a>
				<c:set var="currentUser" value="${intercambi.usuarioRecibe.usuario}" />
				</c:when>
				<c:otherwise>
				<a href="${intercambi.id}" aria-controls="tab-${intercambi.id}"
				role="tab" data-toggle="tab" id="${intercambio.id}" class="list-group-item active"><c:out value="${intercambi.usuarioOfrece.usuario}"/>
				<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span>
				</span>
				</a>
				<c:set var="currentUser" value="${intercambi.usuarioOfrece.usuario}" />
				</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
				<c:when test="${user.id == intercambi.usuarioOfrece.id}">
				<a href="${intercambi.usuarioOfrece.id}" aria-controls="tab-${intercambi.usuarioRecibe.id}"
				role="tab" data-toggle="tab" id="${intercambio.id}" class="list-group-item usuarios"><c:out value="${intercambi.usuarioRecibe.usuario}"/></a>
				</c:when>
				<c:otherwise>
				<a href="${intercambi.usuarioOfrece.id}" aria-controls="tab-${intercambi.usuarioOfrece.id}"
				role="tab" data-toggle="tab" id="${intercambio.id}" class="list-group-item usuarios"><c:out value="${intercambi.usuarioOfrece.usuario}"/></a>
				</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
			</c:if>
			</c:forEach>
			</div>
		</div>
        <div id="ofrece-column" class="offer-column">
        	<h3><c:out value="${intercambio.usuarioOfrece.usuario}"/> ofrece</h3>
            <table class="table table-striped" id="offerTable">
            <thead>
                <tr>
                    <th>Carta</th>
                    <th>Estado</th>
                    <th>Cantidad</th>
               	</tr>
            </thead>
            <tbody>
            <c:forEach items="${intercambio.cartasOfrecidas}" var="cartaOfrecida">
                <tr class="cartas ${intercambio.id}">
                <td>${cartaOfrecida.carta.name}</td>
                <c:choose>
                <c:when test="${cartaOfrecida.estadoCarta == 'Nueva' }">
                <td><span class="estadoCarta label label-success">Nueva</span></td>
                </c:when>
                <c:when test="${cartaOfrecida.estadoCarta == 'Jugada' }">
                <td><span class="estadoCarta label label-warning">Jugada</span></td>
                </c:when>
                <c:when test="${cartaOfrecida.estadoCarta == 'Deteriorada' }">
                <td><span class="estadoCarta label label-danger">Deteriorada</span></td>
                </c:when>                
                </c:choose>
                <td>${cartaOfrecida.cantidad}</td>
                </tr>
             </c:forEach>
             </tbody>
            </table>
        </div>

        <div id="pide-column" class="offer-column">
        	<h3><c:out value="${intercambio.usuarioRecibe.usuario}"/> ofrece</h3>
            <table class="table table-striped" id="orderTable">
            <thead>
                <tr>
                    <th>Carta</th>
                    <th>Estado</th>
                    <th>Cantidad</th>
                </tr>
             </thead>
             <tbody>
            <c:forEach items="${intercambio.cartasRecibidas}" var="cartaRecibida">
                <tr class="cartas ${intercambio.id}">
                <td>${cartaRecibida.carta.name}</td>
                <c:choose>
	                <c:when test="${cartaRecibida.estadoCarta == 'Nueva' }">
	                <td><span class="estadoCarta label label-success">Nueva</span></td>
	                </c:when>
	                <c:when test="${cartaRecibida.estadoCarta == 'Jugada' }">
	                <td><span class="estadoCarta label label-warning">Jugada</span></td>
	                </c:when>
	                <c:when test="${cartaRecibida.estadoCarta == 'Deteriorada' }">
	                <td><span class="estadoCarta label label-danger">Deteriorada</span></td>
	                </c:when>                
                </c:choose>
                <td>${cartaRecibida.cantidad}</td>
                </tr>
             </c:forEach>
             </tbody>            
            </table>
        </div>
		<c:if test="${intercambio.estadoIntercambio == 'Pendiente' && intercambio.usuarioRealizaUltimaAccion.id != user.id}">
        <div id="action" class="offer-column">
            <div class="list-group">
            	<form method="post" action="${prefijo}historial/aceptar" id="enviarPOST">
                	<button type="submit" class="list-group-item btn btn-primary">Aceptar</button>
                	<input type="hidden" name="intercambio" id="aceptar" value="${intercambio.id}" />
                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <form method="post" action="${prefijo}historial/rechazar" id="rechazarPOST">
                <button type="submit" class="list-group-item">Rechazar</button>
                <input type="hidden" name="intercambio"  id="rechazar" value="${intercambio.id}" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <form method="post" action="${prefijo}historial/contraoferta" id="contraofertaPOST">
                <button type="submit" class="list-group-item">Hacer contraoferta</button>
                <input type="hidden" name="intercambio" id="contraoferta" value="${intercambio.id}" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </div>
        </div>
        </c:if>
        <c:if test="${intercambio.estadoIntercambio == 'Pendiente' && intercambio.usuarioRealizaUltimaAccion.id == user.id}">
        	<div class="list-group waiting"> Esperando respuesta del otro usuario </div>
        </c:if>
    </div>
    </c:if>
    </c:forEach>
</div>
<script>
var usuarioActual = '<c:out value = "${currentUser}"/>';
var usuarioSesionJSON = ${usuarioSesionJSON};

var csrf = '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />';
var intercambiosJSON = ${intercambiosJSON};
</script>
<%@ include file="../jspf/footer.jspf" %>