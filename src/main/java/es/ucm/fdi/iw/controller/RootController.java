package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.API;
import es.ucm.fdi.iw.model.Usuario;

@Controller 
public class RootController {
	
	//Se usa para registrar lo que hagamos
	private static Logger log = Logger.getLogger(RootController.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EntityManager entityManager; 

	// Incluimos ${prefix} en todas las páginas
	@ModelAttribute
	public void addAttributes(Model m) {
		m.addAttribute("prefix", "static/");
	}

	@GetMapping({ "/", "/index" })
	@Transactional
	public String index(Model model) {
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("index.js");
		model.addAttribute("pageExtraScripts", listaJS);
		
		//API.cardsDataBaseMin(entityManager,2,100);
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

	private void setDefaultJS(Model m, String ... others) {
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("jquery-ui-1.12.1/jquery-ui.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("star-rating.min.js");
		for (String o : others) {
			listaJS.add(o + ".js");
		}
		m.addAttribute("pageExtraScripts", listaJS);
	}

	@RequestMapping(value="/register", method = RequestMethod.POST)
	@Transactional
	public String register(@RequestParam("nombre") String formName, 
			@RequestParam("apellidos") String formSurname,
			@RequestParam("email") String formEmail,
			@RequestParam("usuario") String formUser,
			@RequestParam("contrasena") String formPassword,
			@RequestParam("provincia") String formProvincia,
			HttpSession session) {
		Usuario u = Usuario.crearUsuario(formName,formSurname,formEmail,formUser, 
				passwordEncoder.encode(formPassword), formProvincia);
		log.info("Creating user " + u);
		entityManager.persist(u);
		entityManager.flush();
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

		return "historial";
	}

}
