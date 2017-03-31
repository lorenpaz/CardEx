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
				<table class="table table-striped" id="paginacionTabla">
				  <thead>
				  	<tr>
				  		<th></th>
				  		<th></th>
				  		<th></th>
				  		<th></th>
				  	</tr>
				  </thead>
				  <tbody>
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
					
					<tr>
						<td id="card13" draggable="true" ondragstart="drag(event)">Pithing Needle</td>
						<td id="card14" draggable="true" ondragstart="drag(event)">Arcbound Ravager</td>
						<td id="card15" draggable="true" ondragstart="drag(event)">Blinkmoth Nexus</td>
						<td id="card16" draggable="true" ondragstart="drag(event)">Brain Freeze</td>
					</tr>
					
					<tr>
						<td id="card17" draggable="true" ondragstart="drag(event)">Browbeat</td>
						<td id="card18" draggable="true" ondragstart="drag(event)">Cabal Coffers</td>
						<td id="card19" draggable="true" ondragstart="drag(event)">Chalice of the Void</td>
						<td id="card20" draggable="true" ondragstart="drag(event)">Isochron Scepter</td>
					</tr>
					
					<tr>
						<td id="card21" draggable="true" ondragstart="drag(event)">Counterbalance</td>
						<td id="card22" draggable="true" ondragstart="drag(event)">Cranial Plating</td>
						<td id="card23" draggable="true" ondragstart="drag(event)">Crucible of Worlds</td>
						<td id="card24" draggable="true" ondragstart="drag(event)">Dark Confidant</td>
					</tr>
					
					<tr>
						<td id="card25" draggable="true" ondragstart="drag(event)">Lorem ipsum Mega</td>
						<td id="card26" draggable="true" ondragstart="drag(event)">Lorem ipsum 7</td>
						<td id="card27" draggable="true" ondragstart="drag(event)">Lorem ipsum</td>
						<td id="card28" draggable="true" ondragstart="drag(event)">Lorem ipsum3</td>
					</tr>
					
					<tr>
						<td id="card29" draggable="true" ondragstart="drag(event)">Lorem ipsum4</td>
						<td id="card30" draggable="true" ondragstart="drag(event)">Lorem ipsum5</td>
						<td id="card31" draggable="true" ondragstart="drag(event)">Lorem ipsum6</td>
						<td id="card32" draggable="true" ondragstart="drag(event)">Lorem ipsum7</td>
					</tr>
				  </tbody>
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