package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ucm.fdi.iw.model.Usuario;

@Controller
@RequestMapping("historial")
public class HistorialController {

	private static Logger log = Logger.getLogger(HistorialController.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EntityManager entityManager;

	// Incluimos ${prefix} en todas las páginas
	@ModelAttribute
	public void addAttributes(Model m) {
		m.addAttribute("prefix", "../static/");
		m.addAttribute("prefijo", "../");
	}

	@GetMapping({ "", "/" })
	public String root(Model model, Principal principal, HttpSession session,
			SecurityContextHolderAwareRequestWrapper request) {
		
		añadirCSSyJSAlModelo(model);
		Usuario usuarioActual = (Usuario) entityManager.createNamedQuery("userByUserField")
				.setParameter("userParam", principal.getName()).getSingleResult();

		if (principal != null && session.getAttribute("user") == null) {
			try {
				session.setAttribute("user", usuarioActual);

			} catch (Exception e) {
				log.info("No such user: " + principal.getName());
			}
		}
	
		//Recojo los usuarios que me han enviado una oferta
		@SuppressWarnings("unchecked")
		ArrayList<Usuario> usuariosReciboOferta = (ArrayList<Usuario>) entityManager.createNamedQuery("getUsersRecibe")
				.setParameter("userRecibe", usuarioActual).getResultList();

		//Recojo los usuarios a los que he enviado una oferta
		@SuppressWarnings("unchecked")
		ArrayList<Usuario> usuariosEnvioOferta = (ArrayList<Usuario>) entityManager.createNamedQuery("getUsersOfrece")
				.setParameter("userOfrece",usuarioActual).getResultList();

		//Se lo paso al modelo
		List<Object> conjuntoDeUsuario = new ArrayList<Object>();
		conjuntoDeUsuario.add(usuariosReciboOferta);
		conjuntoDeUsuario.add(usuariosEnvioOferta);
		
		model.addAttribute("usuariosConjunto",conjuntoDeUsuario);
		
		if(usuariosReciboOferta.isEmpty())
		{
			model.addAttribute("noHayUsuariosReciboOferta",true);
		}
		
		if(usuariosEnvioOferta.isEmpty())
		{
			model.addAttribute("noHayUsuariosEnvioOferta",true);
		}
		
		if (request.isUserInRole("ROLE_ADMIN"))
			return "redirect:admin";

		return "historial";
	}

	public static void añadirCSSyJSAlModelo(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("styleHistorial.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("historial.js");
		
		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	}

	
	  private void actualizaUsuarioSesion(HttpSession session, Usuario u) { 
	 // Actualizo el usuario de la sesión 
		  session.setAttribute("user", entityManager.find(Usuario.class, u.getId())); 
	  }
	 
}
