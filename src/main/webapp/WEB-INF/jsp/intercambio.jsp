<%@ include file="../jspf/header.jspf" %>

<div class="container">
        <div class="extended" id="intercambio">
        	<div class="centered text-center">
	            <div id="ofrece-column" class="offer-column col-intercambio">
	                <h3>Tú</h3>
	                <table class="table table-striped">
	                <caption>Ofreces</caption>
                      <th>Carta</th>
                      <th>Estado</th>
                      <th>Cantidad</th>
                      <tr>
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-success">Nueva</span></td>
                      	<td><button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
						<input type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-plus"></span></button></td>
                      </tr>
                      <tr>
                      	<td>Carta 1</td>
                      	<td><span class="estadoCarta label label-warning">Jugada</span></td>
                      	<td><button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
						<input type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-plus"></span></button></td>
                      </tr>
                      <tr>
                      	<td>Carta 2</td>
                      	<td><span class="estadoCarta label label-danger">Deteriorada</span></td>
                      	<td><button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
						<input type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-plus"></span></button></td>
                      </tr>
                    </table>
	            </div>
				
	            <div class="img-exchange">
	            	<image src="static/img/exchange.png"/>
	            </div>

	            <div id="pide-column" class="offer-column col-intercambio">
	                <h3>Pepe</h3>
	                <table class="table table-striped">
	                <caption>Pides</caption>
                      <th>Carta</th>
                      <th>Estado</th>
                      <th>Cantidad</th>
                      <tr>
                      	<td>Carta 5</td>
                      	<td><span class="estadoCarta label label-success">Nueva</span></td>
                      	<td><button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
						<input type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-plus"></span></button></td>
                      </tr>
                      <tr>
                      	<td>Carta 6</td>
                      	<td><span class="estadoCarta label label-success">Nueva</span></td>
                      	<td><button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
						<input type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-plus"></span></button></td>
                      </tr>
                      <tr>
                      	<td>Carta 6</td>
                      	<td><span class="estadoCarta label label-danger">Deteriorada</span></td>
                      	<td><button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-minus"></span></button>
						<input type="text" name="quantity" class="cantidad-carta" value="0">
						<button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-plus"></span></button></td>
                      </tr>
                    </table>
	            </div>
            	<button class="btn btn-primary">Ofrecer</button>
            </div>
        </div>
    </div>
	

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<!--   <link href="static/css/intercambioStyles.css" rel="stylesheet" type="text/css">
    <script src="static/js/jquery-3.1.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script> -->
    
    <%@ include file="../jspf/footer.jspf" %>