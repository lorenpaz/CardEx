<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>

	<%-- Nuestro header --%>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>CardEx - admin</title>
	<link rel="shortcut icon" href="${prefijo}${prefix}img/logo.ico" />
	  
	<!-- Bootstrap Core CSS -->
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<!-- MetisMenu CSS -->
	<link href="static/css/metisMenu.min.css" rel="stylesheet">
	<!-- Custom CSS -->
	<link href="static/css/sb-admin-2.min.css" rel="stylesheet">
	<!-- Custom Fonts -->
	<link href="static/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="static/css/main.css" rel="stylesheet" type="text/css">
	
	<%-- pageExtraCSS --%>
	<c:forEach var="i" items="${pageExtraCSS}" >
        <link rel="stylesheet" href="static/css/${i}">
    </c:forEach>
	
	<%-- pageExtraScripts --%>
	<c:forEach var="i" items="${pageExtraScripts}" >
        <script src="static/js/${i}"></script>
    </c:forEach>   
    
    <%-- Carga de Ediciones en desplegable --%>
    <%@page import="es.ucm.fdi.iw.model.*"%> 

</head>

<body>
	<div class="container">
		<div class="extended text-right">
		<p><h5>Bienvenido, <span class="text-primary">Administrador</span></h5> <a href="logout"> <span class="badge"> Cerrar sesión</span></a></p>
		</div>
	    <div class="principal">
	        <div class="col-lg-6 col-sm-6 extended">
	            <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
	                <div class="btn-group" role="group">
	                    <button type="button" id="favorites" class="btn btn-default" href="#tab1" data-toggle="tab"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
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
	                  	<tr>
	                      <th>Usuario</th>
	                      <th>Fecha de Alta</th>
	                      <th>Estado</th>
	                      <th>Activar</th>
	                      <th>Desactivar</th>
	                    </tr>
	                    
	                    <c:forEach items="${usuarios}" var="usuario">
						    <tr>
		                      <td>${usuario.nombre}</td>
		                      <td>${usuario.fechaAlta}</td>
		                      	<c:if test= "${usuario.activo eq false}">
									<td>Inactivo</td>
									<td><form method="post"><button type="button" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-ok"></span></button></form></td>
									<td></td>
								</c:if>
								<c:if test= "${usuario.activo eq true}">
									<td>Activo</td>
									<td></td>
									<td><form method="post"><button type="button" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button></form></td>
								</c:if>
						    </tr>
						</c:forEach>
	                    
	                  </table>     
	                </div>
	                
	                <div class="tab-pane fade in" id="tab2">
	                
	                
		                	<form class="form-inline form-search" method="post" action="../admin/updateSets">
		                     <button type="submit" class="btn btn-default">Actualizar Ediciones</button>
		                  	</form>
		                  	
		                  	
		                  	
	                        <table class="table table-striped table-hover">
		                        <caption>Ediciones</caption>
		                        <tr>
		                        <th>Colección</th>
		                        <th>Última actualización</th>
		                        <th>Actualizar cartas</th>
		                        <th>Borrar</th>
		                        </tr>
		                        
		                        <c:forEach items="${ediciones}" var="edicion">
								    <tr>
				                      	<td>${edicion.name}</td>
							    		<c:choose>
										    <c:when test="${edicion.fechaUltimaActualizacion eq null}">
										          <td>Sin Actualizar</td>
					                  			  <td><form method='post'><span class='glyphicon glyphicon-refresh'></span></form></td>
					                  			  <td></td>
										    </c:when>    
										    <c:otherwise>
										          <td>${edicion.fechaUltimaActualizacion}</td>
					                  			  <td><form method='post'><span class='glyphicon glyphicon-refresh'></span></form></td>
					                  			  <td><form method='post'><span class='glyphicon glyphicon-remove'></span></form></td>
										    </c:otherwise>
										</c:choose>
								    </tr>
								</c:forEach>
		                       
		                        
	                      	</table>
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
