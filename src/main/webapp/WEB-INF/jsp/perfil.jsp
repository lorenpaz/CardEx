<%@ include file="../jspf/header.jspf" %>

<div class="container">

		<div class="card hovercard">
			<div class="card-info"> <span class="card-title">Lorem Ipsum</span>
			</div>
		</div>
		<div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
			<div class="btn-group" role="group">
				<button type="button" id="favorites" class="btn btn-primery" href="#tab1" data-toggle="tab"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					<div class="hidden-xs">Información Personal</div>
				</button>
			</div>
			<div class="btn-group" role="group">
				<button type="button" id="stars" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-star" aria-hidden="true"></span>
					<div class="hidden-xs">Valoración</div>
				</button>
			</div>
			<div class="btn-group" role="group">
				<button type="button" id="following" class="btn btn-default" href="#tab3" data-toggle="tab"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
					<div class="hidden-xs">Ajustes</div>
				</button>
			</div>
		</div>

		<div class="well margin-top30 fg-form margin">
			 <div class="tab-content">
				<div class="tab-pane fade in active" id="tab1">
					 <table class="table table-sm">
						<tr>
						  <th scope="row">Nombre</th>
						  <td>Mark</td>
						</tr>
						<tr>
						  <th scope="row">Apellidos</th>
						  <td>Jacob</td>
						</tr>
						<tr>
						  <th scope="row">E-Mail</th>
						  <td>ejemplo@ucm.es</td>
						</tr>
						<tr>
						  <th scope="row">Provincia</th>
						  <td>Madrid</td>
						</tr>
					</table>        
				</div>
				<div class="tab-pane fade in" id="tab2">
				  Valoración media del usuario<input id="input-21e" value="3" type="text" class="rating" readonly data-min=0 data-max=5 data-step=0.5 data-size="sm" readonly:true title="">
					<table class="table table-sm">
						<td>
							<form action="" method="POST">
							Valora al usuario (este apartado no aparece a veces)
							<textarea id="opinion" rows="4" cols="50" class="center"></textarea>
							<input id="input-21e" value="3" type="text" class="rating" data-min=0 data-max=5 data-step=0.5 data-size="xs" rating-disabled title="">
							<button type="submit" class="btn btn-sm btn-success right">Valorar</button>
							</form>
						</td>
						<tr>
						  <th scope="row">Pepe</th>
						  <td>Me has intercambiado tu mejor cromo. ¡Gracias! <br>
							<input id="input-21e" value="4" type="text" class="rating" readonly data-min=0 data-max=5 data-step=0.5 data-size="xs" rating-disabled title="">
						  </td>
						</tr>
						<tr>
						  <th scope="row">Ignacio</th>
						  <td>No me ha gustado que hayas llegado tarde a la quedada
						  	<input id="input-21e" value="1" type="text" class="rating" readonly data-min=0 data-max=5 data-step=0.5 data-size="xs" rating-disabled title="">
						  </td>
						</tr>
						<tr>
						  <th scope="row">Lorenzo</th>
						  <td>Muy buen coleccionista de cromos
							<input id="input-21e" value="5" type="text" class="rating" readonly data-min=0 data-max=5 data-step=0.5 data-size="xs" rating-disabled title="">		  
						  </td>
						</tr>
					</table>   
				  
				</div>
				<div class="tab-pane fade in" id="tab3">
				  <h3>Ajustes</h3>
					
					<div class="form-group margin-top30 fg-form">
						<label>Cambiar nombre usuario</label>
						<input class="form-control">
						<label>Cambiar Contrase&ntilde;a</label>
						<input class="form-control">
						<label>Confirmar contrase&ntilde;a</label>
						<input class="form-control">
						<label>Cambiar Nombre</label>
						<input class="form-control">
						<label>Cambiar Apellidos</label>
						<input class="form-control">
						<label>Cambiar E-Mail</label>
						<input class="form-control">
						<label>Cambiar Provincia</label>
						<select class="form-control">
							<option>Madrid</option>
							<option>Murcia</option>
							<option>Barcelona</option>
							<option>Cuenca</option>
							<option>Guadalajara</option>
							<option>Toledo</option>
						</select>
					</div>
					<button type="submit" class="btn btn-default">Guardar cambios</button>
				</div>
			</div>
		</div>
		
</div>

		<!--  <link href="static/css/star-rating.min.css" rel="stylesheet" type="text/css">
		<link href="static/css/perfilEstilo.css" rel="stylesheet" type="text/css">
		<script src="static/js/jquery-3.1.1.min.js"></script>
		<script src="static/js/bootstrap.min.js" ></script>
		<script src="static/js/star-rating.min.js" ></script>
		<script src="static/js/perfil.js" ></script> -->



	 <%@ include file="../jspf/footer.jspf" %>