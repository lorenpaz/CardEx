package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.ucm.fdi.iw.model.Intercambio;
import es.ucm.fdi.iw.model.Usuario;
import es.ucm.fdi.iw.model.Valoracion;

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
		m.addAttribute("prefijo", "../");
	}

	@GetMapping({ "", "/" })
	public String root(Model model, Principal principal, HttpSession session) {
		
		añadirCSSyJSAlModelo(model);
		Usuario u = (Usuario) session.getAttribute("user");
		model.addAttribute("usuario", u);
		
		long count = ((long) entityManager.createQuery("select count(v) from Valoracion v where v.usuarioValorado = :usuario").
				setParameter("usuario", u).getSingleResult());
		
		model.addAttribute("cuantosUsuariosValoraron", count);

		return "perfil";
	}

	@PostMapping("/cambiarAjustes")
	@Transactional
	public String settingsProfile(@RequestParam("nombre") String formNombre,
			@RequestParam("apellidos") String formApellidos, @RequestParam("email") String formEmail,
			@RequestParam("provincia") String formProvincia, @RequestParam("password") String formContraseña,
			@RequestParam("passwordConfirm") String formContraseñaConfirm, Principal principal, HttpSession session) {

		// Obtengo el usuario actual
		Usuario u = (Usuario) session.getAttribute("user");

		log.info("Modifying user " + u);
		if (formNombre != "" && formNombre != u.getNombre()) {
			u.setNombre(formNombre);
		}

		if (formApellidos != "" && formApellidos != u.getApellidos()) {
			u.setApellidos(formApellidos);
		}

		if (formProvincia != "" && formProvincia != u.getProvincia()) {
			u.setProvincia(formProvincia);
		}

		if (formEmail != "" && formEmail != u.getEmail()) {
			u.setEmail(formEmail);
		}

		if (formContraseña == formContraseñaConfirm) {
			u.setContraseña(passwordEncoder.encode(formContraseña));
		}

		// Actualizamos el usuario de la BBDD
		entityManager.merge(u);
		log.info("Cambios realizados");

		actualizaUsuarioSesion(session, u);
		return "redirect:";
	}

	@PostMapping("/valorarUsuario")
	@Transactional
	public String valorarUsuario(@RequestParam("usuarioValorado") String formUsuarioValorado,
			@RequestParam("descripcion") String formDescripcion, @RequestParam("valor") String formValor,
			Principal principal, HttpSession session) {
		Usuario usuarioQueValora = (Usuario) session.getAttribute("user");

		Usuario usuarioValorado = (Usuario) entityManager.createNamedQuery("userByUserField")
				.setParameter("userParam", formUsuarioValorado).getSingleResult();

		Valoracion v = Valoracion.crearValoracion(usuarioQueValora, usuarioValorado, formDescripcion,
				Integer.parseInt(formValor));

		log.info("Creating valoration " + v);

		entityManager.persist(v);
		entityManager.flush();

		/* Actualizamos el usuario que ha sido valorado */
		List<Valoracion> valoracionesRec = usuarioValorado.getValoracionesRecibidas();
		valoracionesRec.add(v);
		usuarioValorado.setValoracionesRecibidas(valoracionesRec);
		usuarioValorado.setValoracionMedia(Usuario.hacerMedia(valoracionesRec));

		/* Actualizamos el usuario que valora */
		List<Valoracion> valoracionesDad = usuarioQueValora.getValoracionesDadas();
		valoracionesDad.add(v);
		usuarioQueValora.setValoracionesDadas(valoracionesDad);

		// Actualizamos los usuarios de la BBDD
		entityManager.merge(usuarioQueValora);
		entityManager.merge(usuarioValorado);
		entityManager.flush();

		actualizaUsuarioSesion(session, usuarioQueValora);
		return "redirect:../perfil/" + usuarioValorado.getId();
	}

	/**
	 * Mostrar detalles de un usuario
	 */
	@RequestMapping(value = "/{id}")
	public String perfil(@PathVariable("id") long id, Model model, HttpSession session) {
		try {
			model.addAttribute("usuario", entityManager.find(Usuario.class, id));
		} catch (NoResultException nre) {
			log.error("No se ha encontrado el usuario:" + id);
		}

		añadirCSSyJSAlModelo(model);
		Usuario actual = (Usuario) session.getAttribute("user");
		if (id != actual.getId())
			model.addAttribute("visitante", "true");
		return "perfil";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/eliminarCuenta")
	@Transactional
	public ModelAndView eliminaUsuario(Principal principal, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("user");
		u.setActivo(false);
		entityManager.merge(u);
		entityManager.flush();

		// elimina todas valoraciones donde aparace usuario que esta dado de
		// baja
		
		List<Valoracion> valoraciones = entityManager
				.createQuery("SELECT v FROM Valoracion v WHERE v.usuarioQueValora LIKE :user or v.usuarioValorado like :user")
				.setParameter("user", u).getResultList();

		for (Valoracion v : valoraciones) {
			entityManager.remove(v);
		}
		entityManager.flush();

		// elimina todos los intercambios no finalizados donde aparace usuario
		// dado de baja
		List<Intercambio> intercambios = entityManager
				.createQuery("select i from Intercambio i where i.usuarioOfrece like :user or i.usuarioRecibe like :user")
				.setParameter("user", u).getResultList();

		for (Intercambio i : intercambios) {
			entityManager.remove(i);
		}
		entityManager.flush();

		return new ModelAndView("redirect:../logout");
	}

	public static void añadirCSSyJSAlModelo(Model model) {
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
	}

	private void actualizaUsuarioSesion(HttpSession session, Usuario u) {
		// Actualizo el usuario de la sesión
		session.setAttribute("user", entityManager.find(Usuario.class, u.getId()));
	}
}
