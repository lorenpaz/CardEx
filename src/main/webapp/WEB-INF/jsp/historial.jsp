<%@ include file="../jspf/header.jspf" %>
<div class="container">
	
        <form id="filter" class="form-inline form-search" method="post">
            <label>Tipo de oferta:</label>
            <select class="input-large form-control">
                <option selected="">Pendientes</option>
                <option>Aceptadas</option>
                <option>Rechazadas</option>
                <option>Finalizadas</option>
            </select>&nbsp;&nbsp;
            <label>Usuario:</label>
            <input type="search" class="form-control" value="Nombre del usuario">
            <input type="submit" class="btn btn-primary" value="Buscar">
        </form>
		
        <div id="intercambio">
            <div id="intercambio-column" class="offer-column">
                <h3>Intercambios</h3>
                <div class="list-group">
                    <a href="#" class="list-group-item active">Pepe
                        <span class="badge">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </span>
                    </a>
					
                    <a href="#" class="list-group-item">Rafael</a>
                    <a href="#" class="list-group-item">Jorge</a>
                    <a href="#" class="list-group-item">Rodrigo</a>
                </div>
            </div>

            <div id="ofrece-column" class="offer-column">
                <h3>Ofrece</h3>
                
                <table class="table table-striped">
                    <tr>
                        <th>Carta</th>
                        <th>Estado</th>
                        <th>Cantidad</th>
                    </tr>
                      <tr>
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-success">Nueva</span></td>
                      	<td>3</td>
                      </tr>
                      <tr>
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-warning">Jugada</span></td>
                      	<td>2</td>
                      </tr>
                      <tr>
                      	<td>Carta 2</td>
                      	<td><span class="estadoCarta label label-danger">Deteriorada</span></td>
                      	<td>1</td>
                      </tr>
                </table>
            </div>
			
            <div id="pide-column" class="offer-column">
                <h3>Pide</h3>
                <table class="table table-striped">
                    <tr>
                        <th>Carta</th>
                        <th>Estado</th>
                        <th>Cantidad</th>
                    </tr>
                      <tr>
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-success">Nueva</span></td>
                      	<td>3</td>
                      </tr>
                      <tr>
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-warning">Jugada</span></td>
                      	<td>2</td>
                      </tr>
                      <tr>
                      	<td>Carta 2</td>
                      	<td><span class="estadoCarta label label-danger">Deteriorada</span></td>
                      	<td>1</td>
                      </tr>
                      <tr>
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-danger">Deteriorada</span></td>
                      	<td>1</td>
                      </tr>
                </table>
            </div>
			
            <div id="action" class="offer-column">
                <div class="list-group">
                    <button class="list-group-item btn btn-primary">Aceptar</button>
                    <button class="list-group-item">Rechazar</button>
                    <button class="list-group-item">Hacer controferta</button>
                </div>
            </div>
        </div>
    </div>

<%@ include file="../jspf/footer.jspf" %>