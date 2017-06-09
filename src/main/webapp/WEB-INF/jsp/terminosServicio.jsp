<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${userLogin}">
		<%@ include file="../jspf/header.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="../jspf/header2.jspf" %>
	</c:otherwise>
</c:choose>



<div class="container">
	<div id="sobrePagina">
		<h3>Términos y condiciones de uso del servicio</h3>
		<br>
		<p>
			<b>CardEx.com</b> es una plataforma web registrada como tal bajo ©copyrigth en Facultad de Informática UCM. Calle del Prof. José García Santesmases, 9, 28040, Madrid.
		</p>
		<p>Todo usuario debe incluir de manera veraz las cartas que tiene en su poder, en caso contrario puede denegarse dicha cuenta de usuario.</p>
		<p>En cuanto a los intercambios, los usuarios una vez acepten el intercambio, deberán ponerse de acuerdo de manera externa a la empresa para hacer el intercambio. En caso de fraude en la transacción, el usuario disgustado podrá realizar una valoración negativa hacia la otra persona repercutiendo negativamente en la valoración de dicha persona.</p>
		<hr></hr>
		<h3>Obligaciones del Usuario</h3>
		<p>El usuario registrado debe garantizar que sus datos son correctos en todo momento para que las transacciones entre usuarios esté regida bajo los términos del servicio</p>
		<p>En caso contrario la empresa puede denegar e invalidar toda cuenta de usuario que no cumpla con estos requisitos.</p>
	</div>
</div>

<%@ include file="../jspf/footer.jspf" %>
