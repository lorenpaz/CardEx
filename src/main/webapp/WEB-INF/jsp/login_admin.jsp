<!DOCTYPE html>
<html lang="es">
<head>
	<%-- Nuestro header --%>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>CardEx</title>
	
	<!-- Bootstrap Core CSS -->
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<!-- MetisMenu CSS -->
	<link href="static/css/metisMenu.min.css" rel="stylesheet">
	<!-- Custom CSS -->
	<link href="static/css/sb-admin-2.min.css" rel="stylesheet">
	<!-- Custom Fonts -->
	<link href="static/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="static/css/main.css" rel="stylesheet" type="text/css">
	<link href="static/css/adminStyles.css" rel="stylesheet" type="text/css">
</head>
<body>
    
<div class="container">
	<div class="col-lg-2 margin-top centered">
		<form class="centered" action="admin">
			<h1>Administrador</h1>
			<div class="form-group">
				<label for="user">Usuario</label>
				<input type="text" class="form-control" id="user">
			</div>
			<div class="form-group">
				<label for="pwd">Contraseña</label>
				<input type="password" class="form-control" id="pwd">
			</div>
			<button type="submit" class="btn btn-default">Entrar</button>
		</form>
	</div>
</div>
	
<%@ include file="../jspf/footer.jspf" %>