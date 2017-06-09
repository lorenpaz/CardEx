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

import com.google.gson.Gson;

import es.ucm.fdi.iw.model.Carta;
import es.ucm.fdi.iw.model.CartaPropia;
import es.ucm.fdi.iw.model.Edicion;
import es.ucm.fdi.iw.model.Usuario;

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

	@GetMapping({ "", "/" })
	public String gestionCartas(Model model, HttpSession session, Principal principal) {

		Usuario usuarioActual = (Usuario) entityManager.createNamedQuery("userByUserField")
				.setParameter("userParam", principal.getName()).getSingleResult();

		añadirCSSyJSAlModelo(model);
		actualizaUsuarioSesion(session, usuarioActual);

		getAllSets(model);
		getAllCards(model);

		return "gestion_cartas";
	}

	@PostMapping("/registrarCartasUsuario")
	@Transactional
	public String registrarCartasUsuario(@RequestParam("cardsS[]") String[] cartasBuscadas,
			@RequestParam("cardsSE[]") String[] edicionCartasBuscadas, @RequestParam("cardsO[]") String[] cartasPropias,
			@RequestParam("cardsOS[]") String[] estadoCartasPropias,
			@RequestParam("cardsOQ[]") String[] cantidadCartasPropias,
			@RequestParam("cardsOE[]") String[] edicionCartasPropias, Principal principal, HttpSession session) {

		Usuario usuarioActual = (Usuario) entityManager.createNamedQuery("userByUserField")
				.setParameter("userParam", principal.getName()).getSingleResult();

		// CartasBuscadas
		usuarioActual.setCartasBuscadas(new ArrayList<Carta>());
		for (int i = 1; i < cartasBuscadas.length; i++) {
			@SuppressWarnings("unchecked")
			List<Carta> lista = (List<Carta>) entityManager.createNamedQuery("findCardByNameAndEdition")
					.setParameter("paramName", cartasBuscadas[i]).setParameter("paramEdition", edicionCartasBuscadas[i])
					.getResultList();
			usuarioActual.getCartasBuscadas().add(lista.get(0));
		}

		List<CartaPropia> cartasOfrecidas = entityManager.createNamedQuery("exchangeOfrecidas").getResultList();
		List<CartaPropia> cartasRecibidas = entityManager.createNamedQuery("exchangeRecibidas").getResultList();
		// CartasPropias
		for (CartaPropia c : usuarioActual.getCartasPropias()) {
			if (!isInExchange(c, cartasOfrecidas, cartasRecibidas)) {
				entityManager.remove(c);
			}
		}

		for (int j = 1; j < cartasPropias.length; j++) {
			@SuppressWarnings("unchecked")
			List<Carta> lista = (List<Carta>) entityManager.createNamedQuery("findCardByNameAndEdition")
					.setParameter("paramName", cartasPropias[j]).setParameter("paramEdition", edicionCartasPropias[j])
					.getResultList();
			//Cogemos la primera carta de la lista ya que hay algún caso en el que filtrando por carta y edición salen varias cartas
			Carta c = lista.get(0);
			CartaPropia cp = null;
			try {
				cp = (CartaPropia) entityManager.createNamedQuery("allOwnCardsByUserEditionName")
						.setParameter("userParam", usuarioActual).setParameter("EdParam", c.getSetName())
						.setParameter("NParam", c.getName()).getSingleResult();
			} catch (Exception e) {
				cp = new CartaPropia(c, estadoCartasPropias[j], Integer.parseInt(cantidadCartasPropias[j]),
						usuarioActual, false);
				cp.setCarta(c);
				cp.setUsuarioPropietario(usuarioActual);
			}

			cp.setCantidad(Integer.parseInt(cantidadCartasPropias[j]));
			cp.setEstadoCarta(estadoCartasPropias[j]);

			entityManager.persist(cp);

			usuarioActual.getCartasPropias().add(cp);
			int index = usuarioActual.getCartasPropias().indexOf(cp);
			usuarioActual.getCartasPropias().get(index).setUsuarioPropietario(usuarioActual);
		}

		entityManager.persist(usuarioActual);
		entityManager.flush();
		actualizaUsuarioSesion(session, usuarioActual);

		return "redirect:";
	}

	private boolean isInExchange(CartaPropia c, List<CartaPropia> cartasOfrecidas, List<CartaPropia> cartasRecibidas) {
		if (cartasOfrecidas.contains(c) || cartasRecibidas.contains(c)) {
			return true;
		} else {
			return false;
		}
	}

	private void actualizaUsuarioSesion(HttpSession session, Usuario u) {
		// Actualizo el usuario de la sesión
		session.setAttribute("user", entityManager.find(Usuario.class, u.getId()));
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

	public void getAllCards(Model m) {
		Gson gson = new Gson();
		List<Carta> cartas = (List<Carta>) entityManager.createNamedQuery("allCards").getResultList();
		for (Carta c : cartas) {
			c.setCartasPropias(null);
			c.setUsuariosQueMeBuscan(null);
			c.setEdicion(null);
		}
		String json = gson.toJson(cartas);
		m.addAttribute("cards", cartas);
		m.addAttribute("jsonCards", json);
	}

	private void getAllSets(Model m) {
		// TODO Auto-generated method stub
		List<Edicion> ediciones = (List<Edicion>) entityManager.createNamedQuery("getActiveSets").getResultList();
		m.addAttribute("sets", ediciones);
	}

}
