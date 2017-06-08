<%@ include file="../jspf/header.jspf"%>
<div class="container">
	<div class="zona-info form-inline form-search">
		<label>Edici&oacute;n <select id="filterEdition"
			class="form-control filter">
					<option>Todas</option>
				<c:forEach items="${sets}" var="set">
					<option>${set.name}</option>
				</c:forEach>
		</select>
		</label> <label>Tipo de carta <select id="filterType"
			class="form-control filter">
				<option>Todos</option>
				<option>Tierra</option>
				<option>Criatura</option>
				<option>Encantamiento</option>
				<option>Instant&aacute;neo</option>
		</select>
		</label> <label>Color <label class="checkbox-inline"> Rojo <input
				type="checkbox" class="filter chk_filter" name="chk_group"
				id="checkboxEnLinea1" value="rojo">
		</label> <label class="checkbox-inline"> Negro <input type="checkbox"
				class="filter chk_filter" name="chk_group" id="checkboxEnLinea2"
				value="negro">
		</label> <label class="checkbox-inline"> Azul <input type="checkbox"
				class="filter chk_filter" name="chk_group" id="checkboxEnLinea3"
				value="azul">
		</label> <label class="checkbox-inline"> Verde <input type="checkbox"
				class="filter chk_filter" name="chk_group" id="checkboxEnLinea4"
				value="verde">
		</label> <label class="checkbox-inline"> Blanco <input type="checkbox"
				class="filter chk_filter" name="chk_group" id="checkboxEnLinea5"
				value="blanco">
		</label> <label class="checkbox-inline"> Incoloro <input
				type="checkbox" class="filter chk_filter" name="chk_group"
				id="checkboxEnLinea6" value="incoloro">
		</label>
		</label>
	</div>
	<div id="intercambio">
		<div class="cont_gestion">
			<img class="flecha" src="${prefijo}${prefix}img/left_arrow.png"
				onclick="incrOwner()" />
			<!-- AQUÍ FLECHA A LA IZDA -->
			<div id="table_cards">
				<table id="tab1" class="table table-striped">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>Edicion</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cards}" var="carta">
							<tr onclick="selectRow(event)">
								<td class="filterable-cell"><c:out value="${carta.name}" /></td>
								<td class="filterable-cell"><c:out value="${carta.setName}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- AQUÍ FLECHA A LA DERECHA -->
			<img class="flecha" src="${prefijo}${prefix}img/right_arrow.png"
				onclick="incrSearch()" />
		</div>
		<form action='${prefijo}gestion_cartas/registrarCartasUsuario'
			method='post'>
			<div class="cont_gestion">
				<div id="table_propias" class="tabla_gestion">
					<table id="tab2" class="table">
						<caption>Propias</caption>
						<thead>
							<tr>
								<th>Carta</th>
								<th>Edición</th>
								<th>Estado</th>
								<th>Cantidad</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<input type="hidden" value="0" name="cardsO[]"></input>
							<input type="hidden" value="0" name="cardsOE[]"></input>
							<input type="hidden" value="0" name="cardsOQ[]"></input>
							<input type="hidden" value="0" name="cardsOS[]"></input>
							<c:set var="countO" value="0" scope="page" />
							<c:forEach items="${user.cartasPropias}" var="cartaPropia">
									<c:choose>
										<c:when test="${cartaPropia.inExchange == true}">
											<tr id="oRow${countO}" class="exchange">
												<td class="filterable-cell"><c:out
														value="${cartaPropia.carta.name}" /></td>
												<td class="filterable-cell"><c:out
														value="${cartaPropia.carta.setName}" /></td>
												<td class="filterable-cell"><c:out
													value="${cartaPropia.estadoCarta}" /></td>
												<td class="filterable-cell"><c:out
													value="${cartaPropia.cantidad}" /></td>
												<td class="filterable-cell"> </td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr id="oRow${countO}" class="notExchange">
												<td class="filterable-cell"><c:out
														value="${cartaPropia.carta.name}" /></td>
												<td class="filterable-cell"><c:out
														value="${cartaPropia.carta.setName}" /></td>
												<td class="filterable-cell"><select
													id="selORow${countO}"
													class="form-control input-sm selectorEstado"
													onchange="updateState(event)">
														<c:choose>
															<c:when test="${cartaPropia.estadoCarta == 'Nueva'}">
																<option selected>Nueva</option>
																<option>Jugada</option>
																<option>Deteriorada</option>
															</c:when>
															<c:when test="${cartaPropia.estadoCarta == 'Jugada'}">
																<option>Nueva</option>
																<option selected>Jugada</option>
																<option>Deteriorada</option>
															</c:when>
															<c:otherwise>
																<option>Nueva</option>
																<option>Jugada</option>
																<option selected>Deteriorada</option>
															</c:otherwise>
														</c:choose>
	
												</select></td>
												<td class="filterable-cell"><input id="qORow${countO}"
													type="number" name="quantity" class="cantidad-carta" min="1"
													value="${cartaPropia.cantidad}" onchange="updateQ(event)">
												</td>
												<td class="filterable-cell text-right">
													<button type="button" class="btn btn-link btn-xs"
														onclick="removeCard(event, 1)">X</button>
												</td>
											</tr>
											<input id="nameORow${countO}" type="hidden"
												value="${cartaPropia.carta.name}" name="cardsO[]"></input>
											<input id="edORow${countO}" type="hidden"
												value="${cartaPropia.carta.setName}" name="cardsOE[]"></input>
											<input id="quantityORow${countO}" type="hidden"
												value="${cartaPropia.cantidad}" name="cardsOQ[]"></input>
											<input id="stateORow${countO}" type="hidden"
												value="${cartaPropia.estadoCarta}" name="cardsOS[]"></input>
										</c:otherwise>
								</c:choose>


								<c:set var="countO" value="${countO + 1}" scope="page" />
							</c:forEach>
						</tbody>
					</table>
				</div>


				<div id="table_buscadas" class="tabla_gestion">
					<table id="tab3" class="table table-striped">
						<caption>Buscadas</caption>
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Edición</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<input type="hidden" value="0" name="cardsS[]"></input>
							<input type="hidden" value="0" name="cardsSE[]"></input>
							<c:set var="countS" value="0" scope="page" />
							<c:forEach items="${user.cartasBuscadas}" var="cartaBuscada">
								<tr id="sRow${countS}">
									<td class="filterable-cell">${cartaBuscada.name}</td>
									<td class="filterable-cell">${cartaBuscada.setName}</td>
									<td class="filterable-cell text-right">
										<button type="button" class="btn btn-link btn-xs"
											onclick="removeCard(event,2)">X</button>
									</td>
								</tr>
								<input id="nameSRow${countS}" type="hidden"
									value="${cartaBuscada.name}" name="cardsS[]"></input>
								<input id="edSRow${countS}" type="hidden"
									value="${cartaBuscada.setName}" name="cardsSE[]"></input>
								<c:set var="countS" value="${countS + 1}" scope="page" />
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button type="submit" id="btnGuardar"
				class="btn btn-primary btn-lg btn-block">Guardar</button>
		</form>
	</div>

</div>
<script>
	var cards = eval(${jsonCards})
</script>
<!-- /.container -->
<%@ include file="../jspf/footer.jspf"%>