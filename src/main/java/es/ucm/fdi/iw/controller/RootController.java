package es.ucm.fdi.iw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	
public class RootController {

	@GetMapping({"/", "/index"})
	String root() {
		return "index";
	}
	
	@GetMapping({"/home"})
	String home() {
		return "home";
	}
	
	@GetMapping({"/gestionDeTusCartas"})
	String gestionCartas() {
		return "gestionTusCartas";
	}
	@GetMapping({"/intercambio"})
	String intercambio() {
		return "intercambio";
	}
	
	@GetMapping({"/historial"})
	String historial() {
		return "historial";
	}
	
	@GetMapping({"/admin"})
	String admin() {
		return "admin";
	}
	
	@GetMapping({"/login_admin"})
	String loginAdmin() {
		return "login_admin";
	}
	
	@GetMapping({"/perfil"})
	String perfil() {
		return "perfil";
	}
	
}
