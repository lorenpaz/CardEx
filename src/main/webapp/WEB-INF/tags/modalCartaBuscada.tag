<%@ attribute name="cartaBuscada" required="true" type="java.lang.Object" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal-content">
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">Cerrar</button>
	<h4 class="modal-title">${cartaBuscada.name}</h4>
</div>
<div class="modal-body">
	<table id="MainTable">
		<td><img src="${cartaBuscada.imageUrl}"
			alt="${cartaBuscada.name}" width="200" height="300" /></td>
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
						</strong></td>
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
						</strong> <c:forEach items=" ${cartaBuscada.colors}"
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
						</strong>${cartaBuscada.type}
							</p></td>
					</tr>
					<tr>
						<td class="blanco datacell"><strong>
								<p>Tipo de criatura:
						</strong> <c:forEach items=" ${cartaBuscada.types}"
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
						<td class="blanco datacell"><p>Precio:
								${cartaBuscada.manaCost} $</p></td>
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
