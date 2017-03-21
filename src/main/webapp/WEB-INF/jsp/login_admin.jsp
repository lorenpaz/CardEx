<%@ include file="../jspf/header.jspf" %>
    
<div class="container">
	<div class="col-lg-2 margin-top centered">
		<form class="centered" action="admin">
			<h1>Administrador</h1>
			<div class="form-group">
				<label for="user">Usuario:</label>
				<input type="user" class="form-control" id="user">
			</div>
			<div class="form-group">
				<label for="pwd">Contraseña:</label>
				<input type="password" class="form-control" id="pwd">
			</div>
			<button type="submit" class="btn btn-default">Entrar</button>
		</form>
	</div>
</div>
	
<%@ include file="../jspf/footer.jspf" %>