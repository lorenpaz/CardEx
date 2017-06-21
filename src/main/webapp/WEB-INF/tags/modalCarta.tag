<%@ attribute name="carta" required="true" type="java.lang.Object"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">Cerrar</button>
		<h4 class="modal-title">${carta.name}</h4>
	</div>
	<div class="modal-body">
		<table id="MainTable">
			<tr>
				<td><img src="${carta.imageUrl}" alt="${carta.name}"
					width="200" height="300" /></td>
				<td>
					<div id="firstparagraph">
						<!--Detalles carta-->
						<table id="details2">
							<tr>
								<td class="left">Edici�n:</td>
								<td class="right">${carta.edicion.name}</td>
							</tr>
							<tr>
								<td class="left">Color:</td>
								<td class="right">${carta.colors[0]}</td>
							</tr>
							<tr>
								<td class="left">Tipo:</td>
								<td class="right">${carta.type}</td>
							</tr>
							<tr>
								<td class="left">Tipo de criatura:</td>
								<td class="right">${carta.types[0]}</td>
							</tr>
							<tr>
								<td class="left">Artista:</td>
								<td class="right">${carta.artist}</td>
							</tr>
							<tr>
								<td class="left">Fuerza:
								<td class="right">${carta.power}</td>
							</tr>
							<tr>
								<td class="left">Resistencia:</td>
								<td class="right">${carta.toughness}</td>
							</tr>
							<tr>
								<td class="left">Frecuencia:</td>
								<td class="right">${carta.rarity}</td>
							</tr>
							<tr>
								<td class="left">Coste de man�:</td>
								<td class="right">
								<c:forEach items="${carta.manaCost}" var="caracter">
									<c:choose>
									<c:when test="${caracter == 'B'}">
									<div id="blueball">&nbsp;</div>
									</c:when>
									<c:when test="${caracter == 'G'}">
									<div id="greenball">&nbsp;</div>
									</c:when>
									<c:when test="${caracter == 'W'}">
									<div id="blueball">&nbsp;</div>
									</c:when>
									<c:when test="${caracter == 'R'}">
									<div id="redball">&nbsp;</div>
									</c:when>									
									<c:otherwise>
									<div id="ball"></div>
									</c:otherwise>
									</c:choose>
								</c:forEach>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="modal-footer"></div>
</div>
