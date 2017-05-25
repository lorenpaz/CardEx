<%@page import="java.util.List"%>
<%@page import="es.ucm.fdi.iw.controller.GestionController"%>
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
			<div class="cont_gestion">
				<img class="flecha" src="static/img/left_arrow.png" onclick="incrOwner()"/>
				<!-- AQUÍ FLECHA A LA IZDA -->
				<div id="table_cards">
					<table id="tab1" class="table table-striped">
					    <thead>
						    <tr>
						        <th>Nombre</th>
						    </tr>
					    </thead>
					    <tbody>
							<c:forEach items="${cartas}" var="carta">
								<tr>
						        	<td class="filterable-cell">${carta.name}</td>
						    	</tr>
							</c:forEach>
					    </tbody>
					</table>
				</div>
				<!-- AQUÍ FLECHA A LA DERECHA -->
				<img class="flecha" src="static/img/right_arrow.png" onclick="incrSearch()"/>
			</div>
				
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
						    <tr>
						    	<td class="filterable-cell">Ford</td>
						        <td class="filterable-cell">Edicion</td>
						        <td class="filterable-cell"><select class='form-control input-sm'><option>Nueva</option><option>Jugada</option><option>Deteriorada</option></select></td>
						        <td class="filterable-cell">
						        	<button type='button' class='btn btn-xs spinner' onclick='decrSpinner(event)'>-</button> 
		    						<input  type='text' name='quantity' class='cantidad-carta' value='0'>
		    						<button type='button' class='btn btn-xs spinner' onclick='incrSpinner(event)'>+</button>
		    					</td>
						        <td class="filterable-cell text-right"><button type='button' class='btn btn-link btn-xs' onclick='removeCard(event)' >X</button></td>
						    </tr>
						    
					    </tbody>
					</table>
				</div>
				
				
				<div id="table_buscadas" class="tabla_gestion">
					<table id = "tab3" class="table table-striped">
					<caption>Buscadas</caption>
					    <thead>
						    <tr>
						        <th>Nombre</th>
						        <th></th>
						    </tr>
					    </thead>
					    <tbody>
						    <tr>
						        <td class="filterable-cell">Ford</td>
						        <td class="filterable-cell text-right"><button type='button' class='btn btn-link btn-xs' onclick='removeCard(event)'>X</button></td>
						    </tr>
						    
					    </tbody>
					</table>
				</div>
			</div>
			<button id="btnGuardar" class="btn btn-primary btn-lg btn-block">Guardar</button>
		</div>
            

            
</div>	<!-- /.container -->
<%@ include file="../jspf/footer.jspf" %>