package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ucm.fdi.iw.model.API;
import es.ucm.fdi.iw.model.Edicion;
import es.ucm.fdi.iw.model.Usuario;

@Controller	
@RequestMapping("admin")
public class AdminController {
	private static Logger log = Logger.getLogger(PerfilController.class);
	
	@Autowired
	private EntityManager entityManager; 
	
	@GetMapping({"", "/"})
	public String root(Model model,HttpSession session) {
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
		
		API api = new API();
		
		List<Usuario> usuarios = entityManager.createNamedQuery("getUsers").getResultList();
		List<Edicion> ediciones = api.getEdiciones();
		
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("ediciones",ediciones);
	
		return "admin";
	}
}
