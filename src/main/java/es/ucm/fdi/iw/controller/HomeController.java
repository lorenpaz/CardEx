package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import es.ucm.fdi.iw.model.Intercambio;
import es.ucm.fdi.iw.model.Usuario;
import es.ucm.fdi.iw.model.UsuarioJSON;

@Controller
@RequestMapping("home")
public class HomeController {

	private static Logger log = Logger.getLogger(HomeController.class);

	@Autowired
	private EntityManager entityManager;

	// Incluimos ${prefix} en todas las páginas
	@ModelAttribute
	public void addAttributes(Model m) {
		m.addAttribute("prefix", "../static/");
		m.addAttribute("prefijo", "../");
	}

	@GetMapping({ "", "/" })
	public String root(Model model, Principal principal, HttpSession session,
			SecurityContextHolderAwareRequestWrapper request) {
		añadirCSSyJSAlModelo(model);
		Usuario usuarioActual = (Usuario) entityManager.createNamedQuery("userByUserField")
				.setParameter("userParam", principal.getName()).getSingleResult();

		if (principal != null && session.getAttribute("user") == null) {
			try {
				if (!usuarioActual.isActivo()){
					throw new Exception();
				}
				session.setAttribute("user", usuarioActual);

			} catch (Exception e) {
				log.info("No such user: " + principal.getName());
				return "redirect:index";
			}
		}
		@SuppressWarnings("unchecked")
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) entityManager.createNamedQuery("getActiveUsers")
				.setParameter("roleParam", "USER").setParameter("activeParam", true)
				.setParameter("actual", principal.getName()).getResultList();

		Gson gson = new Gson();
		
		String json = "{";
		json +="\"usuarios\":[";
		for(Usuario u : usuarios)
		{
			UsuarioJSON usuarioJSON = new UsuarioJSON(u);
			json += gson.toJson(usuarioJSON);
			if(usuarios.indexOf(u) != usuarios.size()- 1)
			{
				json+= ',';
			} 
		}
		json += "]}";
		model.addAttribute("usuariosJSON",json);
		model.addAttribute("usuarios", usuarios);

		if (request.isUserInRole("ROLE_ADMIN"))
			return "redirect:admin";

		//Enviamos al modelo el usuarioActual (en JSON y normal)
		añadirUsuarioActualJSON(model, usuarioActual);
		model.addAttribute("usuarioActual",usuarioActual);
		
		mensajesPendientes(model,usuarioActual);
		
		return "home";
	}

	private void añadirUsuarioActualJSON(Model model, Usuario usuarioActual)
	{
		UsuarioJSON usuarioActualJSON = new UsuarioJSON(usuarioActual);
		Gson gson = new Gson();
		
		String jsonAux = gson.toJson(usuarioActualJSON);
		model.addAttribute("usuarioActualJSON", jsonAux);
	}
	
	@SuppressWarnings("unchecked")
	private void mensajesPendientes(Model model, Usuario usuarioActual)
	{
		List<Intercambio> intercambios = entityManager.createNamedQuery("allIntercambiosUsuarioPendiente")
				.setParameter("estado", "Pendiente")
				.setParameter("user", usuarioActual)
				.getResultList();
		model.addAttribute("numeroDeMensajes",intercambios.size());
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
		listaJS.add("home.js");
		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	}
}
