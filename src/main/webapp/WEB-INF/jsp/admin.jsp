<%@ include file="../jspf/header.jspf" %>
<div class="container">
	
    <div class="principal">
        <div class="col-lg-6 col-sm-6 extended">
            <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
                <div class="btn-group" role="group">
                    <button type="button" id="favorites" class="btn btn-primary" href="#tab1" data-toggle="tab"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        <div class="hidden-xs">Usuarios</div>
                    </button>
                </div>
                <div class="btn-group" role="group">
                    <button type="button" id="stars" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-th" aria-hidden="true"></span>
                        <div class="hidden-xs">Cartas</div>
                    </button>
                </div>
            </div>
        
            <div class="well">
              <div class="tab-content">
                <div class="tab-pane fade in active" id="tab1">
                 <form class="form-inline form-search" method="post">
                    <input type="search" class="form-control" placeholder="Usuario"/>
                    <input type="submit" class="btn btn-primary" value="Buscar"/>
                  </form>
                  <table class="table table-striped table-hover">
                      <th>#</th>
                      <th>Usuario</th>
                      <th>Fecha de Alta</th>
                      <th>Estado</th>
                      <th>Activar</th>
                      <th>Desactivar</th>
                      <tr>
                        <td>1</td>
                        <td>Jack</td>
                        <td>15/02/2014</td>
                        <td>Activo</td>
                        <td></td>
                        <td><form method="post"><span class="glyphicon glyphicon-remove"></span></form></td>
                      </tr>
                      <tr>
                        <td>2</td>
                        <td>Paul</td>
                        <td>23/05/2015</td>
                        <td>Inactivo</td>
                        <td><form method="post"><span class="glyphicon glyphicon-ok"></span></form></td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>3</td>
                        <td>Abraham</td>
                        <td>31/03/16</td>
                        <td>Activo</td>
                        <td></td>
                        <td><form method="post"><span class="glyphicon glyphicon-remove"></span></form></td>
                      </tr>
                    </table>     
                </div>
                <div class="tab-pane fade in" id="tab2">
                  <div>
                        <form class="form-inline form-search" method="post">
                            <select class="input-large form-control">
                              <option selected="selected">Elige una colecci�n</option>
                              <option >Magic Or�genes</option>
                              <option >B�sica Magic 2015</option>
                              <option >Theros</option>
                            </select>
                          <input type="search" class="form-control" placeholder="Carta"/>
                          <input type="submit" class="btn btn-primary" value="Buscar"/>
                        </form>
                        <table class="table table-striped table-hover">
                        <caption>Todas las cartas</caption>
                        <th>#</th>
                        <th>Carta</th>
                        <th>Colecci�n</th>
                        <th>Borrar</th>
                        <tr>
                          <td>1</td>
                          <td>Jack</td>
                          <td>Magic Or�genes</td>
                          <td><form method="post"><span class="glyphicon glyphicon-remove"></span></form></td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Daisy</td>
                          <td>B�sica Magic 2015</td>
                          <td><form method="post"><span class="glyphicon glyphicon-remove"></span></form></td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Thor</td>
                          <td>Magic Or�genes</td>
                          <td><form method="post"><span class="glyphicon glyphicon-remove"></span></form></td>
                        </tr>
                      </table>
                    </div>
                    <div>
                        <table class="table table-striped table-hover">
                         <caption>Todas las colecciones</caption>
                        <th>#</th>
                        <th>Colecci�n</th>
                        <th>�ltima actualizaci�n</th>
                        <th>Actualizar</th>
                        <th>Borrar</th>
                        <tr>
                          <td>1</td>
                          <td>Magic Or�genes</td>
                          <td>02/02/17</td>
                          <td><form method="post"><span class="glyphicon glyphicon-refresh"></span></form></td>
                          <td><form method="post"><span class="glyphicon glyphicon-remove"></span></form></td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>B�sica Magic 2015</td>
                          <td>13/10/16</td>
                          <td><form method="post"><span class="glyphicon glyphicon-refresh"></span></form></td>
                          <td><form method="post"><span class="glyphicon glyphicon-remove"></span></form></td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Theros</td>
                          <td>Sin actualizar</td>
                          <td><form method="post"><span class="glyphicon glyphicon-refresh"></span></form></td>
                          <td></td>
                        </tr>
                      </table>
                    </div>
                </div>
              </div>
            </div>     
        </div>
    </div>
</div>

	<script>
		$(function() {
			$( "#tabs" ).tabs();
	  } );
	 </script>    
	 <%@ include file="../jspf/footer.jspf" %>
