/**
 * Script para enviar el formulario POST al hacer logout
 */
$(function(){

    $("a#cerrarSesion").click(function()
    	    {
    	    $("#sesionCerrar").submit();
    	    return false;
    	    });
    
});
