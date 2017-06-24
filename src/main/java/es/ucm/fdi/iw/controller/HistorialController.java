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
		} else {
			usuarioActual = (Usuario) session.getAttribute("user");
		}

		if (request.isUserInRole("ROLE_ADMIN"))
			return "redirect:admin";

		getAllExchanges(model, usuarioActual);

		return "historial";
	}

	@PostMapping("/finalizar")
	@Transactional
	public String finalizar(@RequestParam("intercambio") long formIntercambio, HttpSession session) {

		Usuario usuarioActual = (Usuario) session.getAttribute("user");
		usuarioActual = entityManager.find(Usuario.class, usuarioActual.getId());

		// Consigo el intercambio y le cambio el estado
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
		inter.setEstadoIntercambio("Finalizado");
		if (!inter.getUsuarioRealizaUltimaAccion().equals(usuarioActual)) {
			inter.setUsuarioRealizaUltimaAccion(usuarioActual);
			inter.setTerminado(true);

			// Obtego todo lo que necesito
			List<CartaPropia> ofrecidas = inter.getCartasOfrecidas();
			List<CartaPropia> recibidas = inter.getCartasRecibidas();
			Usuario ofrece = inter.getUsuarioOfrece();
			ofrece = entityManager.find(Usuario.class, ofrece.getId());
			Usuario recibe = inter.getUsuarioRecibe();
			recibe = entityManager.find(Usuario.class, recibe.getId());
			
			for (CartaPropia carta : ofrecidas) {
				carta = entityManager.find(CartaPropia.class, carta.getId());
				ofrece.getCartasPropias().remove(carta);
				carta.setInExchange(false);
				carta.setUsuarioPropietario(recibe);
				
				if (!juntarDosCartasIguales(recibe.getCartasPropias(), carta, recibe)) {
					recibe.getCartasPropias().add(carta);
				} else {
					ofrece.getCartasPropias().remove(carta);
				}
			}

			for (CartaPropia carta : recibidas) {
				carta = entityManager.find(CartaPropia.class, carta.getId());
				recibe.getCartasPropias().remove(carta);
				carta.setInExchange(false);
				carta.setUsuarioPropietario(ofrece);
				
				if (!juntarDosCartasIguales(ofrece.getCartasPropias(), carta, ofrece)) {
					ofrece.getCartasPropias().add(carta);
				} else {
					ofrece.getCartasPropias().remove(carta);
				}
			}
		} else {
			inter.setUsuarioRealizaUltimaAccion(usuarioActual);
			inter.setEstadoIntercambio("Aceptado");
		}
		
		entityManager.flush();

		actualizaUsuarioSesion(session, usuarioActual);
		return "redirect:";
	}

	@PostMapping("/rechazar")
	@Transactional
	public String rechazar(@RequestParam("intercambio") long formIntercambio, HttpSession session) {
		Usuario actual = (Usuario) session.getAttribute("user");
		actual = entityManager.find(Usuario.class, actual.getId());

		// Consigo el intercambio y lo modifico
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
		inter.setEstadoIntercambio("Rechazado");
		inter.setUsuarioRealizaUltimaAccion(actual);
		inter.setTerminado(true);
		
		Usuario ofrece = entityManager.find(Usuario.class, inter.getUsuarioOfrece().getId());
		Usuario recibe = entityManager.find(Usuario.class, inter.getUsuarioRecibe().getId());
		
		for (CartaPropia c : inter.getCartasOfrecidas()) {
			c = entityManager.find(CartaPropia.class, c.getId());
			c.setInExchange(false);
			if (juntarDosCartasIguales(ofrece.getCartasPropias(), c, ofrece)) {
				c.setUsuarioPropietario(null);
				ofrece.getCartasPropias().remove(c);
			}
		}
		for (CartaPropia c : inter.getCartasRecibidas()) {
			c = entityManager.find(CartaPropia.class, c.getId());
			c.setInExchange(false);
			if (juntarDosCartasIguales(recibe.getCartasPropias(), c,recibe)) {
				c.setUsuarioPropietario(null);
				recibe.getCartasPropias().remove(c);
			}
		}
		
		entityManager.flush();
		 //actual = ofrece.getId() == actual.getId() ? ofrece : recibe;
		 
		actualizaUsuarioSesion(session, actual);
		
		return "redirect:";
	}

	@PostMapping("/aceptar")
	@Transactional
	public String aceptar(@RequestParam("intercambio") long formIntercambio, HttpSession session) {
		// Consigo el intercambio y lo modifico
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);
		inter.setEstadoIntercambio("Aceptado");
		inter.setUsuarioRealizaUltimaAccion((Usuario) session.getAttribute("user"));

		return "redirect:";
	}

	@PostMapping("/contraoferta")
	@Transactional
	public String contraOferta(@RequestParam("intercambio") long formIntercambio, HttpSession session) {
		Intercambio inter = entityManager.find(Intercambio.class, formIntercambio);

		return "redirect:../intercambio/contraoferta/" + inter.getId();
	}
	
	private void actualizaUsuarioSesion(HttpSession session, Usuario u) {
		// Actualizo el usuario de la sesión
		Usuario actual = entityManager.find(Usuario.class, u.getId());
		session.setAttribute("user", actual);
	}

	public void getAllExchanges(Model m, Usuario usuarioActual) {
		Gson gson = new Gson();

		@SuppressWarnings("unchecked")
		List<Intercambio> intercambios = (List<Intercambio>) entityManager.createNamedQuery("allIntercambiosUsuario")
				.setParameter("user", usuarioActual).getResultList();

		String json = "{";
		json += "\"intercambios\":[";
		for (Intercambio i : intercambios) {
			IntercambioJSON intercambioJSON = new IntercambioJSON(i);
			json += gson.toJson(intercambioJSON);
			if (intercambios.indexOf(i) != intercambios.size() - 1) {
				json += ',';
			}
		}
		json += "]}";

		m.addAttribute("intercambios", intercambios);
		m.addAttribute("intercambiosJSON", json);
		m.addAttribute("usuarioSesionJSON", gson.toJson(new UsuarioJSON(usuarioActual)));
	}

	/*
	 * Método para realizar la acción contraria a duplicateCard
	 */
	@Transactional
	public boolean juntarDosCartasIguales(List<CartaPropia> listaCartas, CartaPropia copia, Usuario propietario) {

		for (CartaPropia original : listaCartas) {
			if (original.getCarta().getId() == copia.getCarta().getId()
					&& original.getEstadoCarta() == copia.getEstadoCarta()
					&& original.getUsuarioPropietario().getId() == copia.getUsuarioPropietario().getId()
					&& original.isInExchange() == copia.isInExchange() && original.getId() != copia.getId()) {
				CartaPropia aux = entityManager.find(CartaPropia.class, original.getId());
				aux.setCantidad(original.getCantidad() + copia.getCantidad());
				return true;
			}
		}
		return false;
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
}
