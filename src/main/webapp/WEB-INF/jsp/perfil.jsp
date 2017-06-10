<%@ include file="../jspf/header.jspf"%>

<div class="container">
	<div class="card hovercard">
		<div class="card-info">
			<span class="card-title"><c:out value="${usuario.usuario}" /></span>
		</div>
	</div>

	<div class="btn-pref btn-group btn-group-justified btn-group-lg">
		<a class="btn btn-primary" href="#tab1" data-toggle="tab"> 
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span><br>
			<span class="hidden-xs">Informaci&oacute;n Personal</span>
		</a> <a class="btn btn-default" href="#tab2" data-toggle="tab">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span><br>
			<span class="hidden-xs">Valoraci&oacute;n</span>
		</a>
		<c:if test="${empty visitante}">
			<a class="btn btn-default" href="#tab3" data-toggle="tab"> 
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span><br>
				<span class="hidden-xs">Ajustes</span>
			</a>
		</c:if>
	</div>

	<div class="well fg-form">
		<div class="tab-content">
			<div class="tab-pane fade in active" id="tab1">
				<table class="table table-sm">
					<tr>
						<th scope="row">Nombre</th>
						<td><c:out value="${usuario.nombre}" /></td>
					</tr>
					<tr>
						<th scope="row">Apellidos</th>
						<td><c:out value="${usuario.apellidos}" /></td>
					</tr>
					<tr>
						<th scope="row">E-Mail</th>
						<c:choose>
							<c:when test="${visitante eq null || cuantosIntercambiosAceptados > 0}">
								<td><c:out value="${usuario.email}" /></td>
							</c:when>
							<c:otherwise>
								<td>Hay que hacer un intercambio con este usuario para acceder a esta informaci√≥n</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th scope="row">Provincia</th>
						<td><c:out value="${usuario.provincia}" /></td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade in" id="tab2">
				Valoraci&oacute;n media del usuario (Cantidad de valoraciones:
				<c:out value="${cuantosUsuariosValoraron}" />
				)<input id="input-21e"
					value="<c:out value="${usuario.valoracionMedia}"/>" type="text"
					class="rating" readonly data-min=0 data-max=5 data-step=0.5
					data-size="sm" title="">
				<table class="table table-sm">
					<tbody>
						<c:choose>
						
							<c:when test="${visitante ne null && cuantosIntercambios > 0 && cuantasValoraciones == 0}">
								<td>
									<form action="${prefijo}perfil/valorarUsuario"
										id="valorarUsuario" method="POST">
										Valora al usuario
										<textarea id="opinion" rows="4" cols="50" class="center"
											form="valorarUsuario" name="descripcion"></textarea>
										<input id="input-21e" value="3" type="text" class="rating"
											data-min=0 data-max=5 data-step=1 data-size="xs" name="valor">
										<button type="submit" class="btn btn-sm btn-success right">Valorar</button>
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input type="hidden"
											name="usuarioValorado" value="${usuario.usuario}" />
									</form>
								</td>
							</c:when>
							<c:when test="${visitante ne null && (cuantosIntercambios == 0)}">
								<td>
									Finaliza un intercambio para valorar.
								</td>
							</c:when>
							<c:when test="${visitante ne null && (cuantosIntercambios > 0 && cuantasValoraciones > 0)}">
								<td>
									Se puede valorar solo una vez.
								</td>
							</c:when>
						</c:choose>
						<c:forEach items="${usuario.valoracionesRecibidas}"
							var="valoracion">
							<tr>
								<th scope="row"><c:out
										value="${valoracion.usuarioQueValora.usuario}" /></th>
								<td><c:out value="${valoracion.comentario}" /><br> <input
									id="input-21e" value="${valoracion.valor}" type="text"
									class="rating" readonly data-min=0 data-max=5 data-step=0.5
									data-size="xs" title="valoracion"></td>
							
							<c:if test="${valoracion.usuarioQueValora.id == usuarioLogeado.id}">
								<td>
									<form action="${prefijo}perfil/eliminarValoracion" method="post">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
										<input type="hidden" name="valoracionId" value="${valoracion.id}" />
										<input type="hidden" name="otroUsuario" value="${usuario.usuario}" />
										<button type="submit" class="close" aria-label="Close">
	  										<span aria-hidden="true">&times;</span>
										</button>
										
									</form>
								</td>
							</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="tab-pane fade in" id="tab3">
				<div class="form-group margin-top30 fg-form">
					<form action="${prefijo}perfil/cambiarAjustes" method="post">
						<label for="usernameform">Cambiar nombre</label> <input
							id="usernameform" class="form-control" name="nombre"
							value="${usuario.nombre}" /> <label for="surnameform">Cambiar
							apellidos</label> <input id="surnameform" class="form-control"
							name="apellidos" value="${usuario.apellidos}" /> <label
							for="passform">Cambiar contrase&ntilde;a</label> <input id="passform"
							class="form-control" name="password" type="password" /> <label
							for="repassform">Confirmar contrase&ntilde;a</label> <input
							id="repassform" class="form-control" name="passwordConfirm"
							type="password" /> <label for="mailform">E-Mail</label> <input
							id="mailform" class="form-control" name="email"
							value="${usuario.email}" /> <label for="provform">Provincia</label>
						<select id="provform" class="form-control" name="provincia">
							<option selected>${usuario.provincia}</option>
							<option>A CoruÒa</option>
							<option>√Ålava</option>
							<option>Albacete</option>
							<option>Alicante</option>
							<option>Almeria</option>
							<option>Asturias</option>
							<option>√Åvila</option>
							<option>Badajoz</option>
							<option>Baleares</option>
							<option>Barcelona</option>
							<option>Burgos</option>
							<option>C·ceres</option>
							<option>C·diz</option>
							<option>Cantabria</option>
							<option>CastellÛn</option>
							<option>Ciudad Real</option>
							<option>CÛrdoba</option>
							<option>Cuenca</option>
							<option>Girona</option>
							<option>Granada</option>
							<option>Guadalajara</option>
							<option>Gipuzkoa</option>
							<option>Huelva</option>
							<option>Huesca</option>
							<option>JaÛn</option>
							<option>La Rioja</option>
							<option>Las Palmas</option>
							<option>LeÛn</option>
							<option>LÈrida</option>
							<option>Lugo</option>
							<option>Madrid</option>
							<option>M·laga</option>
							<option>Murcia</option>
							<option>Navarra</option>
							<option>Orense</option>
							<option>Palencia</option>
							<option>Pontevedra</option>
							<option>Salamanca</option>
							<option>Segovia</option>
							<option>Sevilla</option>
							<option>Soria</option>
							<option>Tarragona</option>
							<option>Santa Cruz de Tenerife</option>
							<option>Teruel</option>
							<option>Toledo</option>
							<option>Valencia</option>
							<option>Valladolid</option>
							<option>Vizcaya</option>
							<option>Zamora</option>
							<option>Zaragoza</option>
						</select>
						<button id="settingsButton" type="submit" class="btn btn-default">Guardar
							cambios</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<form action="${prefijo}perfil/eliminarCuenta" method="post">
						<button id="eliminarButton" type="submit" class="btn btn-danger"
							title="Elimina tu cuenta sin posibilidad de volver a usarla">Eliminar
							cuenta</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>

			</div>
		</div>
	</div>
</div>
<%@ include file="../jspf/footer.jspf"%>
