<%@ include file="../jspf/header.jspf"%>
<div class="container">
	<div class="zona-info form-inline form-search">
		<label>Edici&oacute;n</label> <select class="form-control">
			<option>Todas</option>
			<option>Saga de Urza</option>
			<option>Tempestad</option>
			<option>Fortaleza</option>
		</select> <label>Tipo de carta</label> <select class="form-control">
			<option>Todos</option>
			<option>Tierra</option>
			<option>Criatura</option>
			<option>Encantamiento</option>
		</select> <label>Color</label> <label class="checkbox-inline"> <input
			type="checkbox" id="checkboxEnLinea1" value="opcion_1"> Rojo
		</label> <label class="checkbox-inline"> <input type="checkbox"
			id="checkboxEnLinea2" value="opcion_2"> Negro
		</label> <label class="checkbox-inline"> <input type="checkbox"
			id="checkboxEnLinea3" value="opcion_3"> Azul
		</label> <label class="checkbox-inline"> <input type="checkbox"
			id="checkboxEnLinea1" value="opcion_1"> Verde
		</label> <label class="checkbox-inline"> <input type="checkbox"
			id="checkboxEnLinea2" value="opcion_2"> Blanco
		</label> <label class="checkbox-inline"> <input type="checkbox"
			id="checkboxEnLinea3" value="opcion_3"> Incoloro
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
							<tr>
								<td class="filterable-cell"><c:out value="${carta.name}"/></td>
								<td class="filterable-cell"><c:out value="${carta.setName}"/></td>
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
					<table id="tab2" class="table table-striped ">
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
								<tr id="oRow${countO}">
									<td class="filterable-cell"><c:out value="${cartaPropia.carta.name}"/></td>
									<td class="filterable-cell"><c:out value="${cartaPropia.carta.edicion.name}"/></td>
									<td class="filterable-cell">
									<select
										id="selORow${countO}"
										class="form-control input-sm selectorEstado"
										onchange="updateState(event)"
										value="${cartaPropia.estadoCarta}">
											<option>Nueva</option>
											<option>Jugada</option>
											<option>Deteriorada</option>
									</select></td>
									<td class="filterable-cell">
										<input
										id="qORow${countO}" type="number" name="quantity"
										class="cantidad-carta" max="${cartaPropia.cantidad}" min="1" value="${cartaPropia.cantidad}" onchange="updateQ(event)">		
									</td>
									<td class="filterable-cell text-right">
										<button type="button" class="btn btn-link btn-xs"
											onclick="removeCard(event, 1)">X</button>
									</td>
								</tr>
								<input id="nameORow${countO}" type="hidden"
									value="${cartaPropia.carta.name}" name="cardsO[]"></input>
								<input id="edORow${countO}" type="hidden"
									value="${cartaPropia.carta.edicion.name}" name="cardsOE[]"></input>
								<input id="quantityORow${countO}" type="hidden"
									value="${cartaPropia.cantidad}" name="cardsOQ[]" ></input>
								<input id="stateORow${countO}" type="hidden"
									value="${cartaPropia.estadoCarta}" name="cardsOS[]"></input>
								<c:set var="countO" value="${countO + 1}" scope="page"/>
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
									<td class="filterable-cell">${cartaBuscada.edicion.name}</td>
									<td class="filterable-cell text-right">
										<button type="button" class="btn btn-link btn-xs"
											onclick="removeCard(event,2)">X</button>
									</td>
								</tr>
								<input id="nameSRow${countS}" type="hidden" value="${cartaBuscada.name}"
									name="cardsS[]"></input>
								<input id="edSRow${countS}" type="hidden" value="${cartaBuscada.edicion.name}"
									name="cardsSE[]"></input>
								<c:set var="countS" value="${countS + 1}" scope="page"/>
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
<!-- /.container -->
<%@ include file="../jspf/footer.jspf"%>