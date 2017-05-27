package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.Usuario;
import es.ucm.fdi.iw.model.Valoracion;

@Controller	
@RequestMapping("home")
public class HomeController {
	
	private static Logger log = Logger.getLogger(HomeController.class);
	 
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

	@GetMapping({"", "/"})
	public String root(Model model,Principal principal,HttpSession session,SecurityContextHolderAwareRequestWrapper request) { 
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
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) entityManager.createNamedQuery("getUsers").getResultList();
	/*	for(Usuario usuario : usuarios)
		{
			if(usuario.equals(usuarioActual) || usuario.getRoles().contains("ADMIN"))
			{
				usuarios.remove(usuario);
			}
		}*/
		
		model.addAttribute("usuarios",usuarios);
		
		if (request.isUserInRole("ROLE_ADMIN"))
			 return "redirect:admin";
		
		return "home";
	}
		
	
	public static void añadirCSSyJSAlModelo(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("styleHome.css");
		listaCSS.add("popup.css");
		listaCSS.add("star-rating.min.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("jquery-ui-1.12.1/jquery-ui.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("star-rating.min.js");
		listaJS.add("popup.js");
		listaJS.add("home.js");
		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	}
	
	private void actualizaUsuarioSesion(HttpSession session,Usuario u)
	{
		//Actualizo el usuario de la sesión
		session.setAttribute("user", entityManager.find(Usuario.class, u.getId()));	
	}
}
