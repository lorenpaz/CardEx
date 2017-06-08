<%@ include file="../jspf/header2.jspf" %>
<div class="container">
		<div class="zona-info col-sm-6">
			<h3 class="text-center">Ponte en contacto con otros jugadores</h3>
			<img src="static/img/intercambio.png" class="logo-intercambio">
			<h3 class="text-center">Intercambia tus cartas<br> Magic: The Gathering</h3>
			<h4 class="text-center"><a href="info"><br>M&aacute;s informaci&oacute;n</a></h4>
		</div>

		<div class="zona-registro col-sm-6">
			<h3> A˙n no tienes cuenta? RegÌstrate ahora!</h3>
			<div class="form-group">
				<form action="register" method="post">
					<label for="nameform">Nombre</label>
					<input id="nameform" class="form-control" name="nombre" required>
					<label for="surnameform">Apellidos</label>
					<input id="surnameform" class="form-control" name="apellidos" required>
					<label for="mailform">E-Mail</label>
					<input id="mailform" class="form-control" name="email" required>
					<label for="usernameform">Nombre de usuario</label>
					<input id="usernameform" class="form-control" name="usuario" required>
					<label for="passform">ContraseÒa</label>
					<input id="passform" class="form-control" type="password" name="contrasena" required>
					<label for="repassform">Confirmar contraseÒa</label>
					<input id="repassform" class="form-control" type="password" name="contrasenaR" required>
					<label for="provform">Provincia</label>
					<select id="provform" class="form-control" name="provincia" required>
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
					<button type="submit" class="btn btn-default center-block margin-top10">Registrar</button>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</div>

		</div>
</div>			<!-- /.container -->
<%@ include file="../jspf/footer.jspf" %>
