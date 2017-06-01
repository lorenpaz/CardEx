package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.Carta;
import es.ucm.fdi.iw.model.CartaPropia;
import es.ucm.fdi.iw.model.Intercambio;
import es.ucm.fdi.iw.model.Usuario;

@Controller
@RequestMapping("intercambio")
public class IntercambioController {

	private static Logger log = Logger.getLogger(IntercambioController.class);

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

	@RequestMapping(value = "/{usuarioIntercambio}")
	public String root(@PathVariable("usuarioIntercambio") String usuarioExchange,Model model, Principal principal,
		HttpSession session) {
			
		Usuario usuarioIntercambio = (Usuario) entityManager.createNamedQuery("userByUserField")
				.setParameter("userParam",usuarioExchange).getSingleResult();

		añadirCSSyJSAlModelo(model);
		model.addAttribute("usuarioIntercambio", usuarioIntercambio);
		
		return "intercambio";
	}

	@PostMapping("/ofrecer")
	@Transactional
	public String ofrecerIntercambio (@RequestParam("quantityO[]") String[] cantidadCartasOfrecidas,
	@RequestParam("cartas0[]") String[] cartasOfrecidas,
	@RequestParam("quantityP[]") String[] cantidadCartasPido,
	@RequestParam("cartasP[]") String[] cartasPido,
	@RequestParam("usuarioQuePido") long idUsuarioQuePido,
	Principal principal, HttpSession session)
	{

		//Usuarios
		Usuario usuarioActual = (Usuario) session.getAttribute("user");
		Usuario usuarioQuePido = (Usuario) entityManager.find(Usuario.class, idUsuarioQuePido);
		
		//Listas
		List<CartaPropia> listaCartasOfrecidas = new  ArrayList<CartaPropia>();
		List<CartaPropia> listaCartasPedidas = new  ArrayList<CartaPropia>();
		
		//Rellenamos la lista de cartas Ofrecidas
		for(int i=0; i < cartasOfrecidas.length; i++){
			CartaPropia aux =  (CartaPropia) entityManager.find(CartaPropia.class, cartasOfrecidas[i]);
			aux.setCantidad(Integer.parseInt(cantidadCartasOfrecidas[i]));
				listaCartasOfrecidas.add(aux);
			}		
		
		//Rellenamos la lista de cartas Pedidas
		for(int j=0; j<cartasPido.length; j++){
			CartaPropia aux =  (CartaPropia) entityManager.find(CartaPropia.class, cartasPido[j]);
			aux.setCantidad(Integer.parseInt(cantidadCartasPido[j]));
			listaCartasPedidas.add(aux);	
		} 

		Intercambio intercambio = new Intercambio(usuarioActual,usuarioQuePido,"Pendiente",listaCartasOfrecidas, listaCartasPedidas,new Date(Calendar.getInstance().getTime().getTime()));
		
		entityManager.persist(intercambio);
		entityManager.flush();
		
		return "redirect:../historial";
	}
	
	public static void añadirCSSyJSAlModelo(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("intercambioStyles.css");

		List<String> listaJS = new ArrayList<String>();
		listaJS.add("jquery-3.1.1.min.js");
		listaJS.add("bootstrap.min.js");
		listaJS.add("intercambio.js");

		model.addAttribute("pageExtraCSS", listaCSS);
		model.addAttribute("pageExtraScripts", listaJS);
	}
	
	  private void actualizaUsuarioSesion(HttpSession session, Usuario u) { 
	 // Actualizo el usuario de la sesión 
		  session.setAttribute("user", entityManager.find(Usuario.class, u.getId()));
	}
	 
}
