package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller	
public class RootController {
	
	//Incluimos ${prefix} en todas las páginas
	@ModelAttribute
	public void addAttributes(Model m)
	{
		m.addAttribute("prefix", "static/");
	}
	
	@GetMapping({"/", "/index"})
	String index(Model model) {

		return "index";
	}
	
	@GetMapping({"/home"})
	String home(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("styleHome.css");
		listaCSS.add("popup.css");
		listaCSS.add("star-rating.min.css");
		
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("jquery-ui-1.12.1/jquery-ui.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("popup.js");
		listaJS.add("star-rating.min.js");		
		
		model.addAttribute("pageExtraCSS",listaCSS);
		model.addAttribute("pageExtraScripts",listaJS);
				
		return "home";
	}
	
	@GetMapping({"/gestion_cartas"})
	String gestionCartas(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS .add("gestionStyles.css");
		
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("gestionCartas.js");	

		model.addAttribute("pageExtraCSS",listaCSS);
		model.addAttribute("pageExtraScripts",listaJS);
		
		return "gestion_cartas";
	}
	
	@GetMapping({"/intercambio"})
	String intercambio(Model model) {
		
		List<String> listaCSS = new ArrayList<String>();
		listaCSS .add("intercambioStyles.css");
		
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("intercambio.js");
		
		model.addAttribute("pageExtraCSS",listaCSS);
		model.addAttribute("pageExtraScripts",listaJS);
		
		return "intercambio";
	}
	
	@GetMapping({"/historial"})
	String historial(Model model) {
		
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("styleHistorial.css");
		
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		
		model.addAttribute("pageExtraCSS",listaCSS);
		model.addAttribute("pageExtraScripts",listaJS);
		
		return "historial";
	}
	
	@GetMapping({"/admin"})
	String admin(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("adminStyles.css");
		
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		
		model.addAttribute("pageExtraCSS",listaCSS);
		model.addAttribute("pageExtraScripts",listaJS);
		
		return "admin";
	}
	
	@GetMapping({"/login_admin"})
	String loginAdmin(Model model) {
		
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("adminStyles.css");
		
		List<String> listaJS = new ArrayList<String>();

		model.addAttribute("pageExtraCSS",listaCSS);
		model.addAttribute("pageExtraScripts",listaJS);
		
		return "login_admin";
	}
	
	@GetMapping({"/perfil"})
	String perfil(Model model) {
		
		List<String> listaCSS = new ArrayList<String>();
		listaCSS .add("star-rating.min.css");
		listaCSS .add("perfilEstilo.css");
		
		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("star-rating.min.js");	
		listaJS.add("perfil.js");	
		
		model.addAttribute("pageExtraCSS",listaCSS);
		model.addAttribute("pageExtraScripts",listaJS);
		return "perfil";
	}
	
}
