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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ucm.fdi.iw.model.Edicion;
import es.ucm.fdi.iw.model.Usuario;

@Controller	
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private EntityManager entityManager; 
	
	@SuppressWarnings("unchecked")
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
		
		List<Usuario> usuarios = entityManager.createNamedQuery("getUsers").getResultList();
		List<Edicion> ediciones = entityManager.createNamedQuery("getSets").getResultList();
		
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("ediciones",ediciones);
	
		return "admin";
	}
	
	@PostMapping("/updateSets")
	@Transactional
	public String actualizaEdiciones(){
		int a = 2;
		a = a/2;
		return "index";
	}
}
