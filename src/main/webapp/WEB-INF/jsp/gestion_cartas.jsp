<%@ include file="../jspf/header.jspf" %>
<div class="container">
	
	
		<div class="zona-info form-inline form-search">
				<label>Edici&oacute;n</label>
				<select class="form-control">
					<option>Todas</option>
					<option>Saga de Urza</option>
					<option>Tempestad</option>
					<option>Fortaleza</option>
				</select>
				
				<label>Tipo de carta</label>
				<select class="form-control">
					<option>Todos</option>
					<option>Tierra</option>
					<option>Criatura</option>
					<option>Encantamiento</option>
				</select>
			
				<label>Color</label>
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
		<div id="intercambio">
            <div id="cartas-column" class="offer-column">
				<h3>Cartas</h3>
				<!-- <div class="list-group">
					<a href="#" class="list-group-item active">Force of will</a>
					<a href="#" class="list-group-item">Masticore</a>
					<a href="#" class="list-group-item">Wild mongrel</a>
					<a href="#" class="list-group-item">River boa</a>
				</div> -->
				<table class="table table-striped">
					<tr>
						<td id="card1" draggable="true" ondragstart="drag(event)">Force of will</td>
						<td id="card2" draggable="true" ondragstart="drag(event)">Masticore</td>
						<td id="card3" draggable="true" ondragstart="drag(event)">Wild mongrel</td>
						<td id="card4" draggable="true" ondragstart="drag(event)">River boa</td>
					</tr>
					<tr>
						<td id="card5" draggable="true" ondragstart="drag(event)">Zombie</td>
						<td id="card6" draggable="true" ondragstart="drag(event)">Troll</td>
						<td id="card7" draggable="true" ondragstart="drag(event)">Random Pig</td>
						<td id="card8" draggable="true" ondragstart="drag(event)">God Snake</td>
					</tr>
					<tr>
						<td id="card9" draggable="true" ondragstart="drag(event)">Green Elephant</td>
						<td id="card10" draggable="true" ondragstart="drag(event)">Software Artifact</td>
						<td id="card11" draggable="true" ondragstart="drag(event)">Air Lion</td>
						<td id="card12" draggable="true" ondragstart="drag(event)">Earth Dolphin</td>
					</tr>
				</table>
            </div>

            <div id="buscadas-column" class="offer-column">
				<h3>Buscadas</h3>
	                <table class="table table-striped">
	                  <tr>
		                  <th>Carta</th>
						  <th></th>
					  </tr>
					  <tr>
						<td>Counterspell</td>
						<td><button type="button" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>
					  </tr>
					  <tr>
						<td>Mana leak</td>
						<td><button type="button" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>
					  </tr>
					  <tr>
						<td>Stifle</td>
						<td><button type="button" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>
					  </tr>
					  <tr class="drop-cell drop info" id="trp1" ondrop="drop(event, 1)" ondragover="allowDrop(event)">
						<td colspan="2" class="td-table-add"> Arrastra aquí </td>
					  </tr>
	                </table>
            </div>
            <div id="cambiar-column" class="offer-column">
 				<h3>Para cambiar</h3>
				<table class="table table-striped">  
				  <tr>  
					  <th>Carta</th>
					  <th>Edición</th>
					  <th>Estado</th>
					  <th>Cantidad</th>
					  <th></th>
				  </tr>
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
					<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
						<input  type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td>
					<td><button type="button" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>
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
					<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
						<input  type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td>
					<td><button type="button" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>
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
					<td><button type="button" class="btn btn-xs" onclick="decrSpinner(event)"><span class="glyphicon glyphicon-minus"></span></button>
						<input  type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs" onclick="incrSpinner(event)"><span class="glyphicon glyphicon-plus"></span></button></td>
					<td><button type="button" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>
				  </tr>
				  <tr class="drop-cell drop info" id="trp1" ondrop="drop(event, 2)" ondragover="allowDrop(event)">
						<td colspan="5" class="td-table-add"> Arrastra aquí </td>
			      </tr>
				</table>
			</div>
        </div>
</div>	<!-- /.container -->
<%@ include file="../jspf/footer.jspf" %>