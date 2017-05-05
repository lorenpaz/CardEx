package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.Usuario;

@Controller
public class RootController {
	@Autowired
	private EntityManager entityManager; 

	// Incluimos ${prefix} en todas las páginas
	@ModelAttribute
	public void addAttributes(Model m) {
		m.addAttribute("prefix", "static/");
	}

	@GetMapping({ "/", "/index" })
	String index(Model model) {
		
		return "index";
	}
	
	@GetMapping("/login") 
	String login() {
		return "login";
	}

	@GetMapping("/logout") 
	String logout() {
		return "logout";
	}

	
	@GetMapping({ "/info" })
	String info(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("infoStyle.css");
		model.addAttribute("pageExtraCSS", listaCSS);
		return "info";
	}

	@GetMapping({ "/home" })
	String home(Model model, HttpSession session) {
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
		if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}
		return "home";
	}

	@GetMapping({ "/gestion_cartas" })
	String gestionCartas(Model model, HttpSession session) {
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
		if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}
		return "gestion_cartas";
	}

	@GetMapping({ "/intercambio" })
	String intercambio(Model model, HttpSession session) {

		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("intercambioStyles.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("intercambio.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
		if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}

		return "intercambio";
	}

	@Transactional
	@RequestMapping(value="/register", method = RequestMethod.POST)
	String register(@RequestParam("nombre") String formNombre, 
			@RequestParam("apellidos") String formApellidos,
			HttpSession session) {
		
		User u = User.crearUsuario(formNombre, formApellidos, "cliente");
		entityManager.persist(u);
		return "redirect:home";
	}

	@GetMapping({ "/historial" })
	String historial(Model model, HttpSession session) {

		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("styleHistorial.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
		if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}
		return "historial";
	}

	@GetMapping({ "/admin" })
	String admin(Model model, HttpSession session) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("adminStyles.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
		if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (!session.getAttribute("user").equals("admin")) {
			return "redirect:home";
		}
		return "admin";
	}

	@GetMapping({ "/perfil" })
	String perfil(Model model, HttpSession session) {

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
		if (session.getAttribute("user") == null) {
			return "redirect:index";
		}
		if (session.getAttribute("user").equals("admin")) {
			return "redirect:admin";
		}
		return "perfil";
	}

}
