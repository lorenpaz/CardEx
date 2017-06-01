<%@ include file="../jspf/header.jspf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<div class="container">

	<form id="filter" class="form-inline form-search" method="get"
		action="${prefijo}home">
		<label>Carta <input type="search" class="form-control"
			value=""></label> <label>Usuario <input type="search"
			class="form-control" value=""></label> <label><input
			type="submit" class="btn btn-primary" value="Buscar"></label>
	</form>
	<div id="intercambio">
		<div id="intercambio-column" class="offer-column">
			<h3>Usuarios</h3>
			<div class="list-group">
				<c:forEach items="${usuarios}" var="usuario" varStatus="status">
					<c:choose>
						<c:when test="${status.first}">
							<a href="#tab-${usuario.id}" aria-controls="tab-${usuario.id}"
								role="tab" data-toggle="tab" class="list-group-item active">${usuario.usuario}
								<span class="badge"> <span
									class="glyphicon glyphicon-chevron-right"></span>
							</span>
							</a>
							<c:set var="currentUser" value="${usuario.usuario}" />
						</c:when>
						<c:otherwise>
							<a href="#tab-${usuario.id}" aria-controls="tab-${usuario.id}"
								role="tab" data-toggle="tab" class="list-group-item usuarios">${usuario.usuario}</a>
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
						<c:choose>
							<c:when test="${!status.first}">
								<div class="tab-pane fade active in"
									id="#${cartaPropia.carta.id}">
									<li class="list-group-item r"><a class="nostyle" href="#"
										data-toggle="modal" data-target="#${cartaPropia.carta.id}">${cartaPropia.carta.name}</a>
										<!--Cartas -->
										<div class="modal fade" id="${cartaPropia.carta.id}"
											role="dialog">
											<div class="modal-dialog">
												<!-- Modal content-->
												<h:modalCarta cartaPropia="${cartaPropia}" />
											</div>
										</div></li>
								</div>
							</c:when>
							<c:otherwise>
								<div class="tab-pane fade in" id="#${cartaPropia.carta.id}">
									<li class="list-group-item r"><a class="nostyle" href="#"
										data-toggle="modal" data-target="#${cartaPropia.carta.id}">${cartaPropia.carta.name}</a>
										<!--Cartas -->
										<div class="modal fade" id="${cartaPropia.carta.id}"
											role="dialog">
											<div class="modal-dialog">
												<!-- Modal content-->
												<h:modalCarta cartaPropia="${cartaPropia}" />
											</div>
										</div></li>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:forEach>
			</ul>
		</div>
		<div id="pide-column" class="offer-column">
			<h3>Pide</h3>
			<ul class="list-group">
				<c:forEach items="${usuarios}" var="usuario" varStatus="status">
					<c:forEach items="${usuario.cartasBuscadas}" var="cartaBuscada">
						<c:choose>
							<c:when test="${!status.first}">
								<div class="tab-pane fade active in" id="#${cartaBuscada.id}">
									<li class="list-group-item r"><a class="nostyle" href="#"
										data-toggle="modal" data-target="#${cartaBuscada.id}">${cartaBuscada.name}</a>
										<!--Cartas -->
										<div class="modal fade" id="${cartaBuscada.id}" role="dialog">
											<div class="modal-dialog">
												<!-- Modal content-->
												<h:modalCarta cartaPropia="${cartaPropia}" />
											</div>
										</div></li>
								</div>
							</c:when>
							<c:otherwise>
								<div class="tab-pane fade in" id="#${cartaBuscada.id}">
									<li class="list-group-item r"><a class="nostyle" href="#"
										data-toggle="modal" data-target="#${cartaBuscada.id}">${cartaBuscada.name}</a>
										<!--Cartas -->
										<div class="modal fade" id="${cartaBuscada.id}" role="dialog">
											<div class="modal-dialog">
												<!-- Modal content-->
												<h:modalCarta cartaPropia="${cartaPropia}" />
											</div>
										</div></li>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:forEach>
			</ul>
		</div>
		<div id="action" class="row">
			<form id="intercambioEnviar"
				action="${prefijo}intercambio/${currentUser}">
				<button class="btn-primary" onClick="enviarOferta(event)">Hacer
					oferta</button>
			</form>
		</div>
	</div>
</div>

<%@ include file="../jspf/footer.jspf"%>