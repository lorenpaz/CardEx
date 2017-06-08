<%@ include file="../jspf/header.jspf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<div class="container">

	<div id="filter" class="zona-info form-inline form-search">
		<label>Carta <input type="search" class="form-control"
			id="cartaFilter" placeholder="Busca una carta" /></label> 
		<label
			for="usuarioFilter">Usuario <input type="search"
			id="usuarioFilter" class="form-control"
			placeholder="Busca un usuario" /></label>
	</div>
	<div id="intercambio">
		<div id="intercambio-column" class="offer-column">
			<h3>Usuarios</h3>
			<div class="list-group">
				<c:forEach items="${usuarios}" var="usuario" varStatus="status">
					<c:choose>
						<c:when test="${status.first}">
							<a href="${usuario.id}" aria-controls="tab-${usuario.id}"
								role="tab" data-toggle="tab" class="list-group-item active">
								<c:out value="${usuario.usuario}" /> <span class="badge">
									<span class="glyphicon glyphicon-chevron-right"></span>
							</span>
							</a>
							<c:set var="currentUser" value="${usuario.id}" />
						</c:when>
						<c:otherwise>
							<a href="${usuario.id}" aria-controls="tab-${usuario.id}"
								role="tab" data-toggle="tab" class="list-group-item usuarios">
								<c:out value="${usuario.usuario}" />
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<div id="ofrece-column" class="offer-column">
			<h3>Ofrece</h3>
			<ul class="list-group">
				<c:forEach items="${usuarios}" var="usuario" varStatus="status">
					<c:forEach items="${usuario.cartasPropias}" var="cartaPropia">
						<c:if test="${status.first}">
							<li class="list-group-item r">
								<div class="tab-pane fade in">

									<a class="nostyle" href="#" data-toggle="modal"
										data-target="#${cartaPropia.carta.id}"><c:out
											value="${cartaPropia.carta.name}" /></a>
									<!--Cartas -->
									<div class="modal fade" id="${cartaPropia.carta.id}"
										role="dialog">
										<div class="modal-dialog">
											<!-- Modal content-->
											<h:modalCarta carta="${cartaPropia.carta}" />
										</div>
									</div>

								</div>
							</li>
						</c:if>
					</c:forEach>
				</c:forEach>
			</ul>
		</div>
		<div id="pide-column" class="offer-column">
			<h3>Pide</h3>
			<ul class="list-group">
				<c:forEach items="${usuarios}" var="usuario" varStatus="status">
					<c:forEach items="${usuario.cartasBuscadas}" var="cartaBuscada">
						<c:if test="${status.first}">
							<li class="list-group-item r">
								<div class="tab-pane fade in">
									<a class="nostyle" href="#" data-toggle="modal"
										data-target="#${cartaBuscada.id}"><c:out
											value="${cartaBuscada.name}" /></a>
									<!--Cartas -->
									<div class="modal fade" id="${cartaBuscada.id}" role="dialog">
										<div class="modal-dialog">
											<!-- Modal content-->
											<h:modalCarta carta="${cartaBuscada}" />
										</div>
									</div>
								</div>
							</li>
						</c:if>
					</c:forEach>
				</c:forEach>
			</ul>
		</div>
		<div id="action" class="row">
			<form id="intercambioEnviar"
				action="${prefijo}intercambio/${currentUser}">
				<button class="btn btn-primary" onClick="enviarOferta(event)">Hacer
					oferta</button>
			</form>
		</div>
	</div>
</div>
<script>
	var usuariosJSON = ${usuariosJSON}
</script>
<%@ include file="../jspf/footer.jspf"%>
