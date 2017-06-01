<%@ attribute name="cartaPropia" required="true" type="java.lang.Object" %>

<div class="modal-content">
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">Cerrar</button>
	<h4 class="modal-title">${cartaPropia.carta.name}</h4>
</div>
<div class="modal-body">
	<table id="MainTable">
		<td><img src="${cartaPropia.carta.imageUrl}"
			alt="${cartaPropia.carta.name}" width="200" height="300" /></td>
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
						</strong></td>
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
						</strong> <c:forEach items=" ${cartaPropia.carta.colors}"
								var="color" varStatus="status1">
								<c:if test="${!status1.first}">
	-
	</c:if>
								<c:out value="${color}" />
							</c:forEach></td>
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
						</strong> <c:forEach items=" ${cartaPropia.carta.types}"
								var="criatura" varStatus="status2">
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
						<td class="blanco datacell"><p>Precio:
								${cartaPropia.carta.manaCost} $</p></td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
			</div>
		<td>
	</table>
</div>
<div class="modal-footer"></div>
</div>
