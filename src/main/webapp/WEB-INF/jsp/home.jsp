<%@ include file="../jspf/header.jspf"%>

<div class="container">

	<form id="filter" class="form-inline form-search" method="get"
		action="${prefijo}home">
		<label>Carta <input type="search" class="form-control"
			value=""></label> <label>Usuario <input type="search"
			class="form-control" value=""></label>
		<label><input type="submit" class="btn btn-primary"
			value="Buscar"></label>
	</form>
 
	<div id="intercambio">
		<div id="intercambio-column" class="offer-column">
			<h3>Usuarios</h3>
			<div class="list-group">
				<c:forEach items="${usuarios}" var="usuario" varStatus="status">
					<c:choose>
						<c:when test="${status.first}">
							<a href="#" class="list-group-item active">${usuario.usuario}
								<span class="badge"> <span
									class="glyphicon glyphicon-chevron-right"></span>
							</span>
							</a>
						</c:when>
						<c:otherwise>
							<a href="#" class="list-group-item usuarios">${usuario.usuario}</a>
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
					<li class="list-group-item r"><a class="popup-trigger nostyle">${cartaPropia.carta.name}</a></li>
					<div class="popup">
					<!--Cartas -->
					<div id="carta1" title="Magic: The Gathering">
						<table id="MainTable">
							<td><img src="${cartaPropia.carta.imageUrl}" alt="${cartaPropia.carta.name}"
								width="200" height="300" /></td>
							<td>
								<div id="firstparagraph">
									<!--Detalles carta-->
									<table id="details2">
										<tr>
											<td></td>
										</tr>
										<tr>
											<td class="amarillo datacell"><strong>
													<p>${cartaPropia.carta.name}</p>
											</strong>
											</td>
										</tr>
										<tr>
											<td class="blanco datacell"><strong>
													<p>Edición:
											</strong>${cartaPropia.carta.edicion.name}
												</p></td>
										</tr>
										<tr>
											<td class="blanco datacell"><strong>
													<p>Color: 
											</strong>
											<c:forEach items=" ${cartaPropia.carta.colors}" var="color" varStatus="status1">
											<c:if test="${!status1.first}">
											-
											</c:if>
											<c:out value="${color}" />
											</c:forEach>
											</td>
										</tr>
										<tr>
											<td class="blanco datacell"><strong>
													<p>Tipo:
											</strong>${cartaPropia.carta.type}
												</p></td>
										</tr>
										<tr>
											<td class="blanco datacell"><strong>
													<p>Tipo de criatura:
											</strong>
											<c:forEach items=" ${cartaPropia.carta.types}" var="criatura" varStatus="status2">
											<c:if test="${!status2.first}">
											-
											</c:if>
											<c:out value="${criatura}" />
											</c:forEach>
												</p></td>
										</tr>
										<tr>
											<td class="blanco datacell"><strong>
													<p>Artista:
											</strong>${cartaPropia.carta.artist}
												</p></td>
										</tr>
										<tr>
											<td class="blanco datacell"><strong>
													<p>Fuerza:
											</strong>${cartaPropia.carta.power}
												</p></td>
										</tr>
										<tr>
											<td class="blanco datacell"><strong>
													<p>Resistencia:
											</strong>${cartaPropia.carta.toughness}
												</p></td>
										</tr>
										<tr>
											<td class="blanco datacell"><strong>
													<p>Frecuencia:
											</strong>${cartaPropia.carta.rarity}
												</p></td>
										</tr>
									</table>
								</div> <!--Detalles 2 Carta -->
								<div id="secondparagraph">
									<table id="details">
										<tr>
											<td class="blanco datacell"><strong>
													<p>Informaci&oacute;n:
											</strong>
											</p></td>
										</tr>
										<tr>
											<td class="blanco datacell"><p>${cartaPropia.carta.text}</p></td>
										</tr>
										<tr>
											<td></td>
										</tr>
										<tr>
											<td class="blanco datacell"><p>Precio: ${cartaPropia.carta.manaCost} $</p></td>
										</tr>
										<tr>
											<td></td>
										</tr>
									</table>
								</div>
							<td>
						</table>
					</div>
					<span class="popup-btn-close">close</span>
				</div>
				</c:forEach>
				</c:forEach>
			</ul>
		</div>
		<div id="pide-column" class="offer-column">
			<h3>Pide</h3>
			<ul class="list-group">
				<c:forEach items="${usuarios}" var="usuario" varStatus="status">
				<c:forEach items="${usuario.cartasBuscadas}" var="cartaBuscada">
					<li class="list-group-item r"><a class="popup-trigger nostyle">${cartaBuscada.name}</a></li>
				</c:forEach>
				</c:forEach>
			</ul>
		</div>
		<div id="action" class="row">
			<form action="intercambio">
				<input type="hidden" value="" name="usuarioQuieroIntercambio"
					id="usuarioQuieroIntercambio" />
				<button class="btn-primary" type="submit">Hacer oferta</button>
			</form>
		</div>
	</div>
</div>

<%@ include file="../jspf/footer.jspf"%>