package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import es.ucm.fdi.iw.model.CartaPropia;
import es.ucm.fdi.iw.model.Intercambio;
import es.ucm.fdi.iw.model.IntercambioJSON;
import es.ucm.fdi.iw.model.Usuario;
import es.ucm.fdi.iw.model.UsuarioJSON;

@Controller
@RequestMapping("historial")
public class HistorialController {

	private static Logger log = Logger.getLogger(HistorialController.class);

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

	@GetMapping({ "", "/" })
	public String root(Model model, Principal principal, HttpSession session,
			SecurityContextHolderAwareRequestWrapper request) {
		
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

		if (request.isUserInRole("ROLE_ADMIN"))
			return "redirect:admin";

		getAllExchanges(model,usuarioActual);

		return "historial";
	}

	
	@PostMapping("/finalizar")
	@Transactional
	public String finalizar (@RequestParam("intercambio") long formIntercambio,
	HttpSession session)
	{
		//Consigo el intercambio y le cambio el estado
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
		inter.setEstadoIntercambio("Finalizado");
		
		//Obtego todo lo que necesito
		List<CartaPropia> ofrecidas = inter.getCartasOfrecidas();
		List<CartaPropia> recibidas = inter.getCartasRecibidas();
		Usuario ofrece = inter.getUsuarioOfrece();
		Usuario recibe = inter.getUsuarioRecibe();
		
		
		for(CartaPropia carta : ofrecidas)
		{
			/*for(CartaPropia cartaP : ofrece.getCartasPropias())
			{
				if(cartaP.isInExchange() && carta.getCarta().equals(cartaP.getCarta()))
				{
						//se borra
						ofrece.getCartasPropias().remove(cartaP);
				}
			}*/
			ofrece.getCartasPropias().remove(carta);
			carta.setInExchange(false);
			carta.setUsuarioPropietario(recibe);
			entityManager.persist(carta);
			entityManager.flush();
			recibe.getCartasPropias().add(carta);
		}
		
		for(CartaPropia carta : recibidas)
		{
			/*for(CartaPropia cartaP :  recibe.getCartasPropias())
			{
				if(carta.getCarta().equals(cartaP.getCarta()))
				{
					//se borra
					recibe.getCartasPropias().remove(cartaP);
				}
			}*/
			recibe.getCartasPropias().remove(carta);
			carta.setInExchange(false);
			carta.setUsuarioPropietario(ofrece);
			entityManager.persist(carta);
			entityManager.flush();
			ofrece.getCartasPropias().add(carta);
		}	
		
		entityManager.persist(recibe);
		entityManager.persist(ofrece);
		entityManager.persist(inter);
		entityManager.flush();
		
		Usuario usuarioActual = (Usuario) session.getAttribute("user");
		actualizaUsuarioSesion(session, usuarioActual);
		return "redirect:";
	}
	
	@PostMapping("/rechazar")
	@Transactional
	public String rechazar (@RequestParam("intercambio") long formIntercambio,
	HttpSession session)
	{
		//Consigo el intercambio y lo modifico
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
		inter.setEstadoIntercambio("Rechazado");
		
		//Actualizo la BBDD
		entityManager.merge(inter);
		entityManager.flush();
		
		return "redirect:";
	}
	
	@PostMapping("/aceptar")
	@Transactional
	public String aceptar (@RequestParam("intercambio") long formIntercambio,
	HttpSession session)
	{
		//Consigo el intercambio y lo modifico
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
		inter.setEstadoIntercambio("Aceptado");
		
		//Actualizo la BBDD
		entityManager.merge(inter);
		entityManager.flush();
		
		return "redirect:";
	}
	
	@PostMapping("/contraoferta")
	@Transactional
	public String contraOferta (@RequestParam("intercambio") long formIntercambio,
	HttpSession session)
	{
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
	
		return "redirect:";
	}	
	public static void añadirCSSyJSAlModelo(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("styleHistorial.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("historial.js");
		
		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	}

	
	  private void actualizaUsuarioSesion(HttpSession session, Usuario u) { 
	 // Actualizo el usuario de la sesión 
		  session.setAttribute("user", entityManager.find(Usuario.class, u.getId())); 
	  }
	  
	public void getAllExchanges(Model m,Usuario usuarioActual){	
		Gson gson = new Gson();
		
		@SuppressWarnings("unchecked")
		List<Intercambio> intercambios = (List<Intercambio>) entityManager.createNamedQuery("allIntercambios").getResultList();
		
		String json = "{";
		json +="\"intercambios\":[";
		for(Intercambio i : intercambios)
		{
			IntercambioJSON intercambioJSON = new IntercambioJSON(i);
			json += gson.toJson(intercambioJSON);
			if(intercambios.indexOf(i) != intercambios.size()- 1)
			{
				json+= ',';
			} 
		}
		json += "]}";

		m.addAttribute("intercambios", intercambios);
		m.addAttribute("intercambiosJSON", json);
		m.addAttribute("usuarioSesionJSON",gson.toJson(new UsuarioJSON(usuarioActual)));
	}
}
