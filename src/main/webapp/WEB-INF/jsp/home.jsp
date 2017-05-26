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
				<c:forEach items="${usuario.cartasPropias}" var="carta">
					<li class="list-group-item r"><a class="popup-trigger nostyle">${carta.name}</a></li>
				</c:forEach>
				<li class="list-group-item r"><a class="popup-trigger nostyle">Carta1</a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="pide-column" class="offer-column">
			<h3>Pide</h3>
			<ul class="list-group">
				<c:forEach items="${usuarios}" var="usuario" varStatus="status">
				<c:forEach items="${usuario.cartasBuscadas}" var="carta">
					<li class="list-group-item r"><a class="popup-trigger nostyle">${carta.name}</a></li>
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

	<div class="popup">
		<!--Cartas -->
		<div id="carta1" title="Magic: The Gathering">
			<table id="MainTable">
				<td><img src="static/img/carta.png" alt="Fuerza de Voluntad"
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
										<p>Fuerza de Voluntad / Force of Will
								</strong>
								</p></td>
							</tr>
							<tr>
								<td class="blanco datacell"><strong>
										<p>Color:
								</strong>Azul
									</p></td>
							</tr>
							<tr>
								<td class="blanco datacell"><strong>
										<p>Tipo:
								</strong>Instant&aacute;neo
									</p></td>
							</tr>
							<tr>
								<td class="blanco datacell"><strong>
										<p>Tipo de criatura:
								</strong>N/A
									</p></td>
							</tr>
							<tr>
								<td class="blanco datacell"><strong>
										<p>Coste:
								</strong>
									</p></td>
							</tr>
							<tr>
								<td class="blanco datacell"><strong>
										<p>Fuerza:
								</strong>-
									</p></td>
							</tr>
							<tr>
								<td class="blanco datacell"><strong>
										<p>Resistencia:
								</strong>-
									</p></td>
							</tr>
							<tr>
								<td class="blanco datacell"><strong>
										<p>Frecuencia:
								</strong>Infrecuente
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
								<td class="blanco datacell"><p>Puedes pagar 1 vida y
										retirar del juego una carta azul de tu mano en vez de pagar el
										coste de invocacion de la Fuerza de voluntad.</p></td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td class="blanco datacell"><p>Precio: 85 $</p></td>
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
</div>

<%@ include file="../jspf/footer.jspf"%>