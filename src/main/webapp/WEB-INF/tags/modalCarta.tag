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
								<td class="left">Edición:</td>
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
								<td class="left">Coste de maná:</td>
								<td class="right manaRight">
								<c:forTokens items="${carta.manaCost}" delims = "," var = "caracter">
									<c:set var="letra" value="${false}"></c:set>
									
									
									<c:if test="${caracter eq 'B'}">
									<c:set var="letra" value="${true}"></c:set>
									<div id="blackball">&nbsp;</div>
									</c:if>
									<c:if test="${caracter eq 'G'}">
									<c:set var="letra" value="${true}"></c:set>
									<div id="greenball">&nbsp;</div>
									</c:if>
									<c:if test="${caracter eq 'W'}">
									<c:set var="letra" value="${true}"></c:set>
									<div id="whiteball">&nbsp;</div>
									</c:if>
									<c:if test="${caracter eq 'R'}">
									<c:set var="letra" value="${true}"></c:set>
									<div id="redball">&nbsp;</div>
									</c:if>	
									<c:if test="${caracter eq 'U'}">
									<c:set var="letra" value="${true}"></c:set>
									<div id="blueball">&nbsp;</div>
									</c:if>									
									<c:if test="${letra == false}">
									<div id="ball"><c:out value="${caracter}"></c:out></div>
									</c:if>							
								</c:forTokens>
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
