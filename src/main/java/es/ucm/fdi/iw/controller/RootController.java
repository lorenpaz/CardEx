package es.ucm.fdi.iw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	
public class RootController {

	@GetMapping({"/", "/index"})
	String root() {
		return "test";
	}
	
}
