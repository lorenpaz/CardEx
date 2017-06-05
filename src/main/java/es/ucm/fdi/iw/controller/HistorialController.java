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
import es.ucm.fdi.iw.model.Usuario;

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
	
	/*	//Recojo los intercambios que me han enviado una oferta
		@SuppressWarnings("unchecked")
		ArrayList<Intercambio> intercambiosReciboOferta = (ArrayList<Intercambio>) entityManager.createNamedQuery("getUsersRecibe")
				.setParameter("userRecibe", usuarioActual).getResultList();
		
		//Recojo los intercambios a los que he enviado una oferta
		@SuppressWarnings("unchecked")
		ArrayList<Intercambio> intercambioEnvioOferta = (ArrayList<Intercambio>) entityManager.createNamedQuery("getUsersOfrece")
				.setParameter("userOfrece",usuarioActual).getResultList();

		
		//Recojo los intercambios rechazados
		@SuppressWarnings("unchecked")
		ArrayList<Intercambio> intercambiosRechazados = (ArrayList<Intercambio>) entityManager.createNamedQuery("estado")
				.setParameter("estado","Rechazado").getResultList();
		
		//Recojo los intercambios finalizados
		@SuppressWarnings("unchecked")
		ArrayList<Intercambio> intercambiosFinalizados = (ArrayList<Intercambio>) entityManager.createNamedQuery("estado")
				.setParameter("estado","Finalizado").getResultList();
		
		
		//Se lo paso al modelo
		List<Object> conjuntoDeIntercambios = new ArrayList<Object>();
		conjuntoDeIntercambios.add(intercambiosReciboOferta);
		conjuntoDeIntercambios.add(intercambioEnvioOferta);
		conjuntoDeIntercambios.add(intercambiosRechazados);
		conjuntoDeIntercambios.add(intercambiosFinalizados);
		
		model.addAttribute("intercambioConjunto",conjuntoDeIntercambios);
		*/
		if (request.isUserInRole("ROLE_ADMIN"))
			return "redirect:admin";

		getAllExchanges(model);
		
		return "historial";
	}

	
	@PostMapping("/aceptar")
	@Transactional
	public String aceptar (@RequestParam("intercambio") long formIntercambio,
	HttpSession session)
	{
		//Consigo el intercambio y le cambio el estado
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
		inter.setEstadoIntercambio("aceptada");
		
		//Obtego todo lo que necesito
		List<CartaPropia> ofrecidas = inter.getCartasOfrecidas();
		List<CartaPropia> recibidas = inter.getCartasRecibidas();
		Usuario ofrece = inter.getUsuarioOfrece();
		Usuario recibe = inter.getUsuarioRecibe();
		List<CartaPropia> cartasPropiasOfrece = ofrece.getCartasPropias();
		List<CartaPropia> cartasPropiasRecibe = recibe.getCartasPropias();
		
		for(CartaPropia carta : ofrecidas)
		{
			for(CartaPropia cartaP : cartasPropiasOfrece)
			{
				if(carta.getCarta().equals(cartaP.getCarta()))
				{
					//Si se ha ofrecido la cantidad justa de esa carta
					if(carta.getCantidad() == cartaP.getCantidad())
					{
						cartasPropiasOfrece.remove(cartaP);
					}else{
						cartasPropiasOfrece.remove(cartaP);
						cartaP.setCantidad(cartaP.getCantidad() - carta.getCantidad());
						cartasPropiasOfrece.add(cartaP);
					}
				}
			}
		}
		
		for(CartaPropia carta : recibidas)
		{
			for(CartaPropia cartaP : cartasPropiasRecibe)
			{
				if(carta.getCarta().equals(cartaP.getCarta()))
				{
					//Si se ha ofrecido la cantidad justa de esa carta
					if(carta.getCantidad() == cartaP.getCantidad())
					{
						cartasPropiasRecibe.remove(cartaP);
					}else{
						cartasPropiasRecibe.remove(cartaP);
						cartaP.setCantidad(cartaP.getCantidad() - carta.getCantidad());
						cartasPropiasRecibe.add(cartaP);
					}
				}
			}
		}
		
		cartasPropiasRecibe.addAll(ofrecidas);
		cartasPropiasOfrece.addAll(recibidas);
		
		recibe.setCartasPropias(cartasPropiasRecibe);
		ofrece.setCartasPropias(cartasPropiasOfrece);
		
		
		entityManager.persist(recibe);
		entityManager.persist(ofrece);
		entityManager.persist(inter);
		entityManager.flush();
		
		return "redirect:";
	}
	
	@PostMapping("/rechazar")
	@Transactional
	public String rechazar (@RequestParam("intercambio") long formIntercambio,
	HttpSession session)
	{
		//Consigo el intercambio y lo modifico
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
		inter.setEstadoIntercambio("rechazado");
		
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

	
	/*  private void actualizaUsuarioSesion(HttpSession session, Usuario u) { 
	 // Actualizo el usuario de la sesión 
		  session.setAttribute("user", entityManager.find(Usuario.class, u.getId())); 
	  }*/
	public void getAllExchanges(Model m){	
		Gson gson = new Gson();
		
		@SuppressWarnings("unchecked")
		List<Intercambio> intercambios = (List<Intercambio>) entityManager.createNamedQuery("allIntercambios").getResultList();

		String json = gson.toJson(intercambios);
		m.addAttribute("intercambios", intercambios);
		m.addAttribute("jsonIntercambios", json);
	}
}
