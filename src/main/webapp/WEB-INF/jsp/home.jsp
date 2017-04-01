<%@ include file="../jspf/header.jspf" %>

<div class="container">
	
		<form id="filter" class="form-inline form-search" method="get">
			<label>Carta
            <input type="search" class="form-control" value=""></label>
            <label>Usuario
            <input type="search" class="form-control" value=""></label>
			<label>Valoraci&oacute;n
			<input id="input-21e" value="3" type="text" class="rating" data-min=0 data-max=5 data-step=0.5 data-size="xs" displayOnly="true" title=""></label>
			<label><input type="submit" class="btn btn-primary" value="Buscar"></label>
        </form>
		    
        <div id="intercambio">
            <div id="intercambio-column" class="offer-column">
                <h3>Usuarios</h3>
                <div class="list-group">
                    <a href="#" class="list-group-item active">Pepe
						<span class="badge">
							<span class="glyphicon glyphicon-chevron-right"></span>
						</span>
					</a>
                    <a href="#" class="list-group-item">fadfasdf</a>
                    <a href="#" class="list-group-item">Jorge</a>
                    <a href="#" class="list-group-item">Rodrigo</a>
                </div>
            </div>

            <div id="ofrece-column" class="offer-column">
                <h3>Ofrece</h3>
                <ul class="list-group">
                    <li class="list-group-item r"><a class="popup-trigger nostyle">Carta1</a></li>
                    <li class="list-group-item r">Carta2</li>
                    <li class="list-group-item r">Carta3</li>
                    <li class="list-group-item r">Carta4</li>
                </ul>
            </div>
            <div id="pide-column" class="offer-column">
                <h3>Pide</h3>
                <ul class="list-group">
                    <li class="list-group-item">Carta6</li>
                    <li class="list-group-item">Carta7</li>
                    <li class="list-group-item">Carta8</li>
                    <li class="list-group-item"><br></li>
                </ul>
            </div>
			<div id="action" class="row">
				<a href="intercambio"><button class="btn-primary">Hacer oferta</button></a>
			</div>
		</div>
		
		<div class="popup">
					<!--Cartas -->
			<div id="carta1" title="Magic: The Gathering">
				<table id="MainTable">
					<td>
						<img src="static/img/carta.png" alt="Fuerza de Voluntad" width="200" height="300"/>
					</td>
					<td>
						<div id="firstparagraph">
							<!--Detalles carta-->
							<table id="details2">
								<tr>
									<td></td>
								</tr>
								<tr>
									<td class="amarillo datacell"><strong> <p>Fuerza de Voluntad / Force of Will</strong></p></td>
								</tr>
								<tr>
									<td class="blanco datacell"><strong> <p>Color:</strong>Azul</p></td>
								</tr>
								<tr>
									<td class="blanco datacell"><strong> <p>Tipo: </strong>Instant&aacute;neo</p></td>
								</tr>
								<tr>
									<td class="blanco datacell"><strong> <p>Tipo de criatura: </strong>N/A</p></td>
								</tr>
								<tr>
									<td class="blanco datacell"><strong> <p>Coste: </strong>
									</p></td>
								</tr>
								<tr>
									<td class="blanco datacell"><strong> <p>Fuerza: </strong>-</p></td>
								</tr>
								<tr>
									<td class="blanco datacell"><strong> <p>Resistencia: </strong>-</p></td>
								</tr>
								<tr>
									<td class="blanco datacell"><strong> <p>Frecuencia: </strong>Infrecuente</p></td>
								</tr>		
							</table>
						</div>
						<!--Detalles 2 Carta -->
						<div id="secondparagraph">
							<table id="details">
								<tr>
									<td class="blanco datacell"><strong> <p>Informaci&oacute;n: </strong></p></td>
								</tr>
								<tr>
									<td class="blanco datacell"><p>Puedes pagar 1 vida y retirar del juego una carta azul de tu mano en vez de pagar el coste de invocacion de la Fuerza de voluntad.</p></td>
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

<%@ include file="../jspf/footer.jspf" %>