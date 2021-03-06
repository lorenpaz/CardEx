<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>

	<%-- Nuestro header --%>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>CardEx - admin</title>
	<link rel="shortcut icon" href="${prefijo}${prefix}img/logo.ico" />
	  
	<!-- Bootstrap Core CSS -->
	<link href="${prefijo}${prefix}css/bootstrap.min.css" rel="stylesheet">
	<!-- MetisMenu CSS -->
	<link href="${prefijo}${prefix}css/metisMenu.min.css" rel="stylesheet">
	<!-- Custom Fonts -->
	<link href="${prefijo}${prefix}css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<%-- pageExtraCSS --%>
	<c:forEach var="i" items="${pageExtraCSS}" >
        <link rel="stylesheet" href="${prefijo}${prefix}css/${i}">
    </c:forEach>
	
	<%-- pageExtraScripts --%>
	<c:forEach var="i" items="${pageExtraScripts}" >
        <script src="${prefijo}${prefix}js/${i}"></script>
    </c:forEach>   
    
    <%-- Carga de Ediciones en desplegable --%>
    <%@page import="es.ucm.fdi.iw.model.*"%> 

</head>

<body>
	<div class="container">
		<div class="extended text-right">
		<p><h5>Bienvenido, <span class="text-primary">Administrador</span></h5>
		<form action="${prefijo}logout" id="sesionCerrar" method="post">
			<span class="badge"></span><input type="submit" id="cerrarSesion" value="Cerrar Sesi�n">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
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
	                  <input id="filterUser" type="search" class="form-control filters" placeholder="Usuario"/>
	                  <table id="tablaUsuarios" class="table table-striped table-hover">
	                  	<tr>
	                      <th>Usuario</th>
	                      <th>Fecha de Alta</th>
	                      <th>Estado</th>
	                      <th>Activar</th>
	                      <th>Desactivar</th>
	                    </tr>
	                    
	                    <c:forEach items="${usuarios}" var="usuario">
						    <tr>
		                      <td><c:out value="${usuario.usuario}"/></td>
		                      <td><c:out value="${usuario.fechaAlta}"/></td>
		                      	<c:if test= "${usuario.activo eq false}">
									<td>Inactivo</td>
									<td>
									
									 <form method='post' action="${prefijo}admin/habilitaUser">
		                  			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-ok"></span></button>
		                  			    
		                  			  	<input type="hidden" name="id" value="${usuario.id}"/> 
	                 					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		                  			  </form>
									
									
									</td>
									<td></td>
								</c:if>
								<c:if test= "${usuario.activo eq true}">
									<td>Activo</td>
									<td></td>
									<td>
									<form method='post' action="${prefijo}admin/deshabilitaUser">
		                  			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button>
		                  			    
		                  			  	<input type="hidden" name="id" value="${usuario.id}"/> 
	                 					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		                  			  </form>
									</td>
								</c:if>
						    </tr>
						</c:forEach>
	                    
	                  </table>     
	                </div>
	                
	                <div class="tab-pane fade in" id="tab2">
	                
	                		<input id="filterEdition" type="search" class="form-control filters" placeholder="Edicion"/>
		                	<form class="form-inline form-search" method="post" action="${prefijo}admin/updateSets">
		                     <button type="submit" class="btn btn-default">Actualizar Ediciones</button>
		                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		                  	</form>
		                  	
		                  	
		                  	
	                        <table id="tablaEdiciones" class="table table-striped table-hover">
		                        <caption>Ediciones</caption>
		                        <tr>
		                        <th>Colecci�n</th>
		                        <th>�ltima actualizaci�n</th>
		                        <th>Actualizar cartas</th>
		                        <th>Borrar</th>
		                        </tr>
		                        
		                        <c:forEach items="${ediciones}" var="edicion">
								    <tr>
				                      	<td>${edicion.name}</td>
							    		<c:choose>
							    		
							    		
										    <c:when test="${edicion.fechaUltimaActualizacion eq null}">
										          <td>Sin Actualizar</td>
					                  			  <td>
						                  			  <form method='post' action="${prefijo}admin/updateCardSet">
						                  			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-refresh"></span></button>
						                  			    
						                  			  	<input type="hidden" name="code" value="${edicion.code}"/> 
		                    							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						                  			  </form>
					                  			  </td>
					                  			  <td></td>
										    </c:when>    
										    
										    
										    <c:otherwise>
										          <td>${edicion.fechaUltimaActualizacion}</td>
					                  			  <td>
						                  			  <form method='post' action="${prefijo}admin/updateCardSet">
						                  			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-refresh"></span></button>
						                  			    
						                  			  	<input type="hidden" name="code" value="${edicion.code}"/> 
		                    							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						                  			  </form>
					                  			  </td>
					                  			  <td><form method='post' action="${prefijo}admin/removeCardSets">
						                  			    <button type="submit" class="btn btn-link btn-xs"><span class="glyphicon glyphicon-remove"></span></button>
						                  			    
						                  			    <input type="hidden" name="code" value="${edicion.code}"/> 
						                  			  	<input type="hidden" name="name" value="${edicion.name}"/> 
		                    							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						                  			  </form>
						                  		</td>
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
	var csrf = '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />';
	var usuariosJSON = ${usuariosJSON};
	var edicionesJSON = ${edicionesJSON};
</script>
    
 <%@ include file="../jspf/footer.jspf" %>
