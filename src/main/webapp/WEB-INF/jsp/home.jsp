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
				<a href="#tab-${usuario.id}" aria-controls="tab-${usuario.id}" role="tab" data-toggle="tab" class="list-group-item active">${usuario.usuario}
					<span class="badge"> <span class="glyphicon glyphicon-chevron-right"></span>
					</span>
				</a>
					 	<c:set var = "usuarioNow" value = "${usuario.id}"/>
						</c:when>
						<c:otherwise>
				<a href="#tab-${usuario.id}" aria-controls="tab-${usuario.id}" role="tab" data-toggle="tab" class="list-group-item usuarios">${usuario.usuario}</a>
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
						<c:if test="${usuario.id == usuarioNow}">
						<li class="list-group-item r"><a class="nostyle" href="#" data-toggle="modal" data-target="#${cartaPropia.carta.id}">${cartaPropia.carta.name}</a>
						<!--Cartas -->
						<div class="modal fade" id="${cartaPropia.carta.id}" role="dialog">
							<div class="modal-dialog">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">Cerrar</button>
										<h4 class="modal-title">${cartaPropia.carta.name}</h4>
									</div>
									<div class="modal-body">
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
									<div class="modal-footer">
									</div>
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
				<c:if test="${usuario.id == usuarioNow}">
					<li class="list-group-item r"><a class="nostyle" href="#" data-toggle="modal" data-target="#${cartaBuscada.id}">${cartaBuscada.name}</a>
					<!--Cartas -->
					<div class="modal fade" id="${cartaBuscada.id}" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">Cerrar</button>
									<h4 class="modal-title">${cartaBuscada.name}</h4>
								</div>
								<div class="modal-body">
								<table id="MainTable">
									<td><img src="${cartaBuscada.imageUrl}" alt="${cartaBuscada.name}"
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
															<p>${cartaBuscada.name}</p>
													</strong>
													</td>
												</tr>
												<tr>
													<td class="blanco datacell"><strong>
															<p>Edición:
													</strong>${cartaBuscada.edicion.name}
														</p></td>
												</tr>
												<tr>
													<td class="blanco datacell"><strong>
															<p>Color: 
													</strong>
													<c:forEach items=" ${cartaBuscada.colors}" var="color" varStatus="status1">
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
													</strong>${cartaBuscada.type}
														</p></td>
												</tr>
												<tr>
													<td class="blanco datacell"><strong>
															<p>Tipo de criatura:
													</strong>
													<c:forEach items=" ${cartaBuscada.types}" var="criatura" varStatus="status2">
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
													</strong>${cartaBuscada.artist}
														</p></td>
												</tr>
												<tr>
													<td class="blanco datacell"><strong>
															<p>Fuerza:
													</strong>${cartaBuscada.power}
														</p></td>
												</tr>
												<tr>
													<td class="blanco datacell"><strong>
															<p>Resistencia:
													</strong>${cartaBuscada.toughness}
														</p></td>
												</tr>
												<tr>
													<td class="blanco datacell"><strong>
															<p>Frecuencia:
													</strong>${cartaBuscada.rarity}
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
													<td class="blanco datacell"><p>${cartaBuscada.text}</p></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td class="blanco datacell"><p>Precio: ${cartaBuscada.manaCost} $</p></td>
												</tr>
												<tr>
													<td></td>
												</tr>
											</table>
										</div>
									<td>
								</table>
								</div>
								<div class="modal-footer">
								</div>
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
			<form action="intercambio">
				<input type="hidden" value="" name="usuarioQuieroIntercambio"
					id="usuarioQuieroIntercambio" />
				<button class="btn-primary" type="submit">Hacer oferta</button>
			</form>
		</div>
</div>

<%@ include file="../jspf/footer.jspf"%>