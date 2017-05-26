package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.Usuario;
import es.ucm.fdi.iw.model.Valoracion;


@Controller
@RequestMapping("gestion_cartas")
public class GestionController {
	
private static Logger log = Logger.getLogger(PerfilController.class);
	
	@Autowired
	private EntityManager entityManager; 
	
	// Incluimos ${prefix} en todas las páginas
	@ModelAttribute
	public void addAttributes(Model m) {
		m.addAttribute("prefix", "../static/");
		m.addAttribute("prefijo", "../");
	}
	
	@GetMapping({"", "/"})
	public String gestionCartas(Model model,HttpSession session) {
		
		añadirCSSyJSAlModelo(model);
		
		getAllCArds(model);
	
		return "gestion_cartas";
	}
	
	public static void añadirCSSyJSAlModelo(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("bootstrap.min.css");
		listaCSS.add("jquery.dataTables.min.css");
		listaCSS.add("gestion.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("jquery.dataTables.min.js");
		listaJS.add("gestion.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	}
	
	public void getAllCArds(Model m){	
		m.addAttribute("cards", entityManager.createNamedQuery("allCards").getResultList());
	}
	
	@PostMapping("/registrarCartasUsuario")
	@Transactional
	public String registrarCartasUsuario (@RequestParam("cardsS[]") String[] cartasBuscadas,
			@RequestParam("cardsO[]") String[] cartasPropias,Principal principal, HttpSession session)
	{

		return "gestion_cartas";
	}

}
