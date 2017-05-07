<%@ include file="../jspf/header2.jspf" %>
<div class="container">
		<div class="zona-info col-sm-6">
			<h3 class="center">Ponte en contacto con otros jugadores</h3>
			<img src="static/img/intercambio.png" class="logo-intercambio">
			<h3 class="center">Intercambia tus cartas<br> Magic: The Gathering</h3>
			<a href="info"><br><h4 class="center">M&aacute;s informaci&oacute;n</h4></a>
		</div>

		<div class="zona-registro col-sm-6">
			<h3> ¿Aún no tienes cuenta? Regístrate.</h3>
			<div class="form-group">
				<form action="register" method="post">
					<label for="nameform">Nombre</label>
					<input id="nameform" class="form-control" name="nombre">
					<label for="surnameform">Apellidos</label>
					<input id="surnameform" class="form-control" name="apellidos">
					<label for="mailform">E-Mail</label>
					<input id="mailform" class="form-control">
					<label for="usernameform">Nombre de usuario</label>
					<input id="usernameform" class="form-control">
					<label for="passform">Contraseña</label>
					<input id="passform" class="form-control">
					<label for="repassform">Confirmar contraseña</label>
					<input id="repassform" class="form-control">
					<label for="provform">Provincia</label>
					<select id="provform" class="form-control">
						<option>A Coruña</option>
						<option>Álava</option>
						<option>Albacete</option>
						<option>Alicante</option>
						<option>Almería</option>
						<option>Asturias</option>
						<option>Ávila</option>
						<option>Badajoz</option>
						<option>Baleares</option>
						<option>Barcelona</option>
						<option>Burgos</option>
						<option>Cáceres</option>
						<option>Cádiz</option>
						<option>Cantabria</option>
						<option>Castellón</option>
						<option>Ciudad Real</option>
						<option>Córdoba</option>
						<option>Cuenca</option>
						<option>Girona</option>
						<option>Granada</option>
						<option>Guadalajara</option>
						<option>Gipuzkoa</option>
						<option>Huelva</option>
						<option>Huesca</option>
						<option>Jaén</option>
						<option>La Rioja</option>
						<option>Las Palmas</option>
						<option>León</option>
						<option>Lérida</option>
						<option>Lugo</option>
						<option>Madrid</option>
						<option>Málaga</option>
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
					<button type="submit" class="btn btn-default right">Registrar</button>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</div>

		</div>
</div>			<!-- /.container -->
<%@ include file="../jspf/footer.jspf" %>