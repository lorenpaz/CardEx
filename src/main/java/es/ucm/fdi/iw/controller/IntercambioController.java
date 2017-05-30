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
@RequestMapping("intercambio")
public class IntercambioController {

	private static Logger log = Logger.getLogger(IntercambioController.class);

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
	public String root(Model model, Principal principal, HttpSession session) {
		
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

		@SuppressWarnings("unchecked")
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) entityManager.createNamedQuery("getActiveUsers")
				.setParameter("roleParam", "USER").setParameter("activeParam", true).setParameter("actual",principal.getName()).getResultList();

		model.addAttribute("usuarios", usuarios);
		
		return "intercambio";
	}

	public static void añadirCSSyJSAlModelo(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("intercambioStyles.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("intercambio.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	}
	/*
	 * private void actualizaUsuarioSesion(HttpSession session, Usuario u) { //
	 * Actualizo el usuario de la sesión session.setAttribute("user",
	 * entityManager.find(Usuario.class, u.getId())); }
	 */
}
