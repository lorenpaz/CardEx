<%@ include file="../jspf/header.jspf" %>
<div class="container">
	
	
		<div class="zona-info">
			<div class="col-lg-3">
				<label>Edici&oacute;n</label>
				<select class="form-control">
					<option>Todas</option>
					<option>Saga de Urza</option>
					<option>Tempestad</option>
					<option>Fortaleza</option>
				</select>
			</div>
				
			<div class="col-lg-3">
				<label>Tipo de carta</label>
				<select class="form-control">
					<option>Todos</option>
					<option>Tierra</option>
					<option>Criatura</option>
					<option>Encantamiento</option>
				</select>
			</div>
			
			
			<div class="col-lg-6">
				<label>Color</label>
				<div>
					<label class="checkbox-inline">
					  <input type="checkbox" id="checkboxEnLinea1" value="opcion_1"> Rojo
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="checkboxEnLinea2" value="opcion_2"> Negro
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="checkboxEnLinea3" value="opcion_3"> Azul
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="checkboxEnLinea1" value="opcion_1"> Verde
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="checkboxEnLinea2" value="opcion_2"> Blanco
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="checkboxEnLinea3" value="opcion_3"> Incoloro
					</label>
				</div>
			</div>
		</div>	
		
		<div class="">
			<div class="offer-column">
				<h3>Cartas</h3>
				<div class="list-group">
					<a href="#" class="list-group-item active">Force of will</a>
					<a href="#" class="list-group-item">Masticore</a>
					<a href="#" class="list-group-item">Wild mongrel</a>
					<a href="#" class="list-group-item">River boa</a>
				</div>
			</div>
			
			<div class="offer-column">
				<h3>Buscadas</h3>
				<table class="table table-striped">    
				  <th>Carta</th>
				  <th></th>
				  <tr>
					<td>Counterspell</td>
					<td><span class="glyphicon glyphicon-remove"></span></td>
				  </tr>
				  <tr>
					<td>Mana leak</td>
					<td><span class="glyphicon glyphicon-remove"></span></td>
				  </tr>
				  <tr>
					<td>Stifle</td>
					<td><span class="glyphicon glyphicon-remove"></span></td>
				  </tr>
				</table>
			</div>
		</div>
		
		<div class="offer-column">
			<h3>Para cambiar</h3>
			<table class="table table-striped">    
			  <th>Carta</th>
			  <th>Edici&oacute;n</th>
			  <th>Estado</th>
			  <th>Cantidad</th>
			  <th></th>
			  <tr>
				<td>Mountain</td>
				<td>Tempestad</td>
				<td>
					<select class="form-control input-sm">
						<option>Nueva</option>
						<option>Jugada</option>
						<option>Deteriorada</option>
					</select>
				</td>
				<td><input class="cantidad-carta input-sm" type="number" name="quantity" min="0"/></td>
				<td><span class="glyphicon glyphicon-remove"></span></td>
			  </tr>
			  <tr>
				<td>Island</td>
				<td>Fortaleza</td>
				<td>
					<select class="form-control input-sm">
						<option>Nueva</option>
						<option>Jugada</option>
						<option>Deteriorada</option>
					</select>
				</td>
				<td><input class="cantidad-carta input-sm" type="number" name="quantity" min="0"/></td>
				<td><span class="glyphicon glyphicon-remove"></span></td>
			  </tr>
			  <tr>
				<td>Rancor</td>
				<td>Saga de Urza</td>
				<td>
					<select class="form-control input-sm">
						<option>Nueva</option>
						<option>Jugada</option>
						<option>Deteriorada</option>
					</select>
				</td>
				<td><input class="cantidad-carta input-sm" type="number" name="quantity" min="0"/></td>
				<td><span class="glyphicon glyphicon-remove"></span></td>
			  </tr>
			</table>
		</div>
	
	</div><!-- /.container -->
<%@ include file="../jspf/footer.jspf" %>