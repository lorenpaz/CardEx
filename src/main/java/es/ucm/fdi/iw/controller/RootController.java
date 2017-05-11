package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.ucm.fdi.iw.IwUserDetailsService;
import es.ucm.fdi.iw.model.Usuario;

@Controller
public class RootController {
	
	//Se usa para registrar lo que hagamos
	private static Logger log = Logger.getLogger(RootController.class);
	
	private static BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private EntityManager entityManager; 

	// Incluimos ${prefix} en todas las páginas
	@ModelAttribute
	public void addAttributes(Model m) {
		m.addAttribute("prefix", "static/");
	}

	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("index.js");
		model.addAttribute("pageExtraScripts", listaJS);
		return "index";
	}

	@GetMapping({ "/info" })
	public String info(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("infoStyle.css");
		model.addAttribute("pageExtraCSS", listaCSS);
		
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("index.js");
		model.addAttribute("pageExtraScripts", listaJS);
		return "info";
	}

	@GetMapping({ "/home" })
	public String home(Model model, HttpSession session) {
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
	/*	if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}*/
		return "home";
	}

	@GetMapping({ "/gestion_cartas" })
	public String gestionCartas(Model model, HttpSession session) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("bootstrap.min.css");
		listaCSS.add("jquery.dataTables.min.css");
		listaCSS.add("gestionStyles.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("jquery.dataTables.min.js");
		listaJS.add("gestionCartas.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	/*	if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}*/
		return "gestion_cartas";
	}

	@GetMapping({ "/intercambio" })
	public String intercambio(Model model, HttpSession session) {

		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("intercambioStyles.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("intercambio.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
		/*if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}
*/
		return "intercambio";
	}
/*
	@PostMapping("/login")
	public String login(@RequestParam("login") String formLogin,
			@RequestParam("password") String formPassword,
			HttpSession session, Model model) {
		
		//Realizamos las pertinentes comprobaciones
		if (formLogin != null && formPassword != null) {
			
			Usuario u = null;
			
			try {
				//Obtenemos el usuario
				u = (Usuario) entityManager.createNamedQuery("userByUserField")
						.setParameter("userParam", formLogin).getSingleResult();
				
				//Comprobamos que es válida la contraseña
				if (bcryptEncoder.matches(formPassword, u.getContraseña())) 
				{
					log.info("pass was valid");				
					session.setAttribute("user", formLogin);
					
					IwUserDetailsService prueba = new IwUserDetailsService();
					UserDetails pr = prueba.loadUserByUsername(u.getUsuario());
					
					//if (formLogin.equals("admin")) 
					{
						return "redirect:admin";
					//}
				} else {
					log.info("pass was NOT valid");
					model.addAttribute("loginError", "error en usuario o contraseña");
				}	
			} catch(NoResultException e) {
				log.info("Error: usuario o contraseña incorrectos");
			}
		}
		return "redirect:home";
	}
*/
	@RequestMapping(value="/register", method = RequestMethod.POST)
	@Transactional
	public String register(@RequestParam("nombre") String formName, 
			@RequestParam("apellidos") String formSurname,
			@RequestParam("email") String formEmail,
			@RequestParam("usuario") String formUser,
			@RequestParam("contrasena") String formPassword,
			@RequestParam("provincia") String formProvincia,
			HttpSession session) {
		Usuario u = Usuario.crearUsuario(formName,formSurname,formEmail,formUser, formPassword, formProvincia);
		log.info("Creating user " + u);
		entityManager.persist(u);
		return "redirect:home";
	}

	/**
	 * Logout (also returns to home view).
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}

	@GetMapping({ "/historial" })
	public String historial(Model model, HttpSession session) {

		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("styleHistorial.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
		/*if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}*/
		return "historial";
	}

	@GetMapping({ "/admin" })
	public String admin(Model model, HttpSession session) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("adminStyles.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	/*	if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (!session.getAttribute("user").equals("admin")) {
			return "redirect:home";
		}*/
		return "admin";
	}

	@GetMapping({ "/perfil" })
	public String perfil(Model model, HttpSession session) {

		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("star-rating.min.css");
		listaCSS.add("perfilEstilo.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("star-rating.min.js");
		listaJS.add("perfil.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	/*	if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}*/
		return "perfil";
	}

}
