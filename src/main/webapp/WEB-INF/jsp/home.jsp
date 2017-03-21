<%@ include file="../jspf/header.jspf" %>

<div class="container">
	
		<form id="filter" class="form-inline form-search" method="get">
           	<label>Filtros de b&uacute;squeda</label>
			<label>Carta
            <input type="search" class="form-control" value=""></label>
            <label>Usuario
            <input type="search" class="form-control" value=""></label>
			<label>Valoraci&oacute;n
			<input type="submit" class="btn btn-primary" value="Buscar"></label>
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
                    <li class="list-group-item r">Carta1</li>
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
			
           <!-- <div id="action" class="offer-column">
                <div class="list-group">
                    <button class="list-group-item btn btn-primary">Aceptar</button>
                    <button class="list-group-item">Rechazar</button>
                    <button class="list-group-item">Hacer controferta</button>
                </div>
            </div>-->
			<div id="action" class="row">
				<button class="btn-primary">Hacer oferta</button>
			</div>
		</div>
</div>

<%@ include file="../jspf/footer.jspf" %>