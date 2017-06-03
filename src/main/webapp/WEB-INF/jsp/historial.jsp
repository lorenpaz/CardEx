<%@ include file="../jspf/header.jspf" %>
<div class="container">
	
    <form id="filter" class="form-inline form-search" method="post">
        <label>Tipo de oferta:</label>
        <select class="input-large form-control">
            <option selected="">Pendientes</option>
            <option>Aceptadas</option>
            <option>Rechazadas</option>
            <option>Finalizadas</option>
        </select>&nbsp;&nbsp;
        <label>Usuario:</label>
        <input type="search" class="form-control" value="Nombre del usuario">
        <input type="submit" class="btn btn-primary" value="Buscar">
    </form>
	<c:forEach items="${intercambioConjunto}" var="intercambios" varStatus="statusConjunto">
	<c:choose>
	<c:when test="${not empty intercambios}">
    <div id="intercambio">
    	<c:forEach items="${intercambios}" var="intercambio" varStatus="status">
		<div id="intercambio-column" class="offer-column">
		<c:choose>
		<c:when test="${statusConjunto.first}">
			<h3>Ofertas recibidas</h3>
		</c:when>
		<c:otherwise>
			<h3>Ofertas enviadas</h3>
		</c:otherwise>
		</c:choose>
			<div class="list-group">
					<c:choose>
						<c:when test="${statusConjunto.first}">
							<c:choose>
								<c:when test="${status.first}">
									<a href="#tab-${intercambio.usuarioOfrece.id}" aria-controls="tab-${intercambio.usuarioOfrece.id}"
										role="tab" data-toggle="tab" class="list-group-item active"><c:out value="${intercambio.usuarioOfrece.usuario}"/>
										<span class="badge"> <span
											class="glyphicon glyphicon-chevron-right"></span>
									</span>
									</a>
									<c:set var="currentUser" value="${intercambio.usuarioOfrece.usuario}" />
								</c:when>
								<c:otherwise>
									<a href="#tab-${intercambio.usuarioOfrece.id}" aria-controls="tab-${intercambio.usuarioOfrece.id}"
										role="tab" data-toggle="tab" class="list-group-item usuarios"><c:out value="${intercambio.usuarioOfrece.usuario}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${status.first}">
									<a href="#tab-${intercambio.usuarioRecibe.id}" aria-controls="tab-${intercambio.usuarioRecibe.id}"
										role="tab" data-toggle="tab" class="list-group-item active"><c:out value="${intercambio.usuarioRecibe.usuario}"/>
										<span class="badge"> <span
											class="glyphicon glyphicon-chevron-right"></span>
									</span>
									</a>
									<c:set var="currentUser" value="${intercambio.usuarioRecibe.usuario}" />
								</c:when>
								<c:otherwise>
									<a href="#tab-${intercambio.usuarioRecibe.id}" aria-controls="tab-${intercambio.usuarioRecibe.id}"
										role="tab" data-toggle="tab" class="list-group-item usuarios"><c:out value="${intercambio.usuarioRecibe.usuario}"/></a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
			</div>
		</div>
        <div id="ofrece-column" class="offer-column">
            <h3>Ofrece</h3>
            <table class="table table-striped">
                <tr>
                    <th>Carta</th>
                    <th>Estado</th>
                    <th>Cantidad</th>
               	</tr>
            <c:forEach items="${intercambio.cartasOfrecidas}" var="cartaOfrecida">
                <tr>
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
            </table>
        </div>

        <div id="pide-column" class="offer-column">
            <h3>Pide</h3>
            <table class="table table-striped">
                <tr>
                    <th>Carta</th>
                    <th>Estado</th>
                    <th>Cantidad</th>
                </tr>
            <c:forEach items="${intercambio.cartasRecibidas}" var="cartaRecibida">
                <tr>
                <td>${cartaRecibida.carta.name}</td>
                <c:choose>
	                <c:when test="${cartaRecibida.estadoCarta} == 'Nueva' ">
	                <td><span class="estadoCarta label label-success">Nueva</span></td>
	                </c:when>
	                <c:when test="${cartaRecibida.estadoCarta} == 'Jugada' ">
	                <td><span class="estadoCarta label label-warning">Jugada</span></td>
	                </c:when>
	                <c:when test="${cartaRecibida.estadoCarta} == 'Deteriorada' ">
	                <td><span class="estadoCarta label label-danger">Deteriorada</span></td>
	                </c:when>                
                </c:choose>
                <td>${cartaRecibida.cantidad}</td>
                </tr>
             </c:forEach>               
            </table>
        </div>
		<c:if test="${statusConjunto.first}">
        <div id="action" class="offer-column">
            <div class="list-group">
            	<form method="post" action="${prefijo}historial/aceptar">
                	<button type="submit" class="list-group-item btn btn-primary">Aceptar</button>
                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <form method="post" action="${prefijo}historial/rechazar">
                <button type="submit" class="list-group-item">Rechazar</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <form method="post" action="${prefijo}historial/contraoferta">
                <button type="submit" class="list-group-item">Hacer controferta</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </div>
        </div>
        </c:if>
        </c:forEach>
    </div>
    </c:when>
	    <c:otherwise>
	    No hay ofertas 
		    <c:choose>
		    <c:when test="${statusConjunto.first}">
		    	recibidas
		    </c:when>
		    <c:otherwise>
		    	enviadas
		    </c:otherwise>
		    </c:choose>
	    </c:otherwise>
    </c:choose>
    </c:forEach>
</div>

<%@ include file="../jspf/footer.jspf" %>