package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

@Controller	
@RequestMapping("perfil")
public class PerfilController {
	
	private static Logger log = Logger.getLogger(PerfilController.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EntityManager entityManager; 

	// Incluimos ${prefix} en todas las páginas
	@ModelAttribute
	public void addAttributes(Model m) {
		m.addAttribute("prefix", "../static/");
	}

	@GetMapping({"", "/"})
	public String root(Model model,Principal principal) {
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
		
		//Obtengo el usuario actual
		Usuario u = (Usuario) entityManager.createNamedQuery("userByUserField")
				.setParameter("userParam", principal.getName()).getSingleResult();
		
		//Se lo paso al modelo
		model.addAttribute("usuario",u);
	
		return "perfil";
	}
	
	@PostMapping("/cambiarAjustes")
	@Transactional
	public String settingsProfile (@RequestParam("nombre") String formNombre,
			@RequestParam("apellidos") String formApellidos,
			@RequestParam("email") String formEmail,
			@RequestParam("provincia") String formProvincia,
			@RequestParam("password") String formContraseña,
			@RequestParam("passwordConfirm") String formContraseñaConfirm,
			Principal principal)
	{
		Usuario u = (Usuario) entityManager.createNamedQuery("userByUserField")
				.setParameter("userParam", principal.getName()).getSingleResult();
		
		String sql = "UPDATE Usuario u SET ", sqlFinal = " WHERE u.usuario =:usuario", aux;
		
		log.info("Modifying user " + u);
		if(formNombre != "" && formNombre != u.getNombre())
		{
			aux = sql + "u.nombre=:nombre" + sqlFinal;
			entityManager.createQuery(aux).setParameter("usuario", u.getUsuario()).setParameter("nombre", formNombre).executeUpdate();
		}
		
		if(formApellidos != "" && formApellidos != u.getApellidos())
		{
			aux = sql + "u.apellidos=:apellidos" + sqlFinal;
			entityManager.createQuery(aux).setParameter("usuario", u.getUsuario()).setParameter("apellidos", formApellidos).executeUpdate();
		}
		
		if(formProvincia != "" && formProvincia != u.getProvincia())
		{
			aux = sql + "u.provincia=:provincia" + sqlFinal;
			entityManager.createQuery(aux).setParameter("usuario", u.getUsuario()).setParameter("provincia", formProvincia).executeUpdate();
		}
		
		if(formEmail != "" && formEmail != u.getEmail())
		{
			aux = sql + "u.email=:email" + sqlFinal;
			entityManager.createQuery(aux).setParameter("usuario", u.getUsuario()).setParameter("email", formEmail).executeUpdate();
		}

		if(formContraseña == formContraseñaConfirm)
		{
			aux = sql + "u.contraseña=:contraseña" + sqlFinal;
			entityManager.createQuery(aux).setParameter("usuario", u.getUsuario()).setParameter("contraseña", passwordEncoder.encode(formContraseña)).executeUpdate();
		}
		
		log.info("Cambios realizados");
		
		return "redirect:";
	}
	
	/**
	 * Mostrar detalles de un usuario
	 */
	@RequestMapping(value = "/{id}")
	public String perfil(@PathVariable("id") long id, Model model) {		
		try {
			model.addAttribute("usuario", entityManager.find(Usuario.class, id));
		} catch (NoResultException nre) {
			log.error("No se ha encontrado el usuario:" + id);
		}
		model.addAttribute("prefijo", "../");
		return "perfil";
	}	
	
}
