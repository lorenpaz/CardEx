package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

public class UsuarioJSON {

	private long id;
	private String usuario;
	private List<CartaPropiaJSON> cartasPropias;
	private List<CartaJSON> cartasBuscadas;
	private boolean activo;
	
	public UsuarioJSON() {}

	public UsuarioJSON(Usuario u) {
		this.activo = u.isActivo();
		this.usuario = u.getUsuario();
		this.cartasPropias = parserList(u.getCartasPropias());
		this.cartasBuscadas = parserListTwo(u.getCartasBuscadas());
	}

	private List<CartaPropiaJSON> parserList(List<CartaPropia> cartas) {
		List<CartaPropiaJSON> list = new ArrayList<CartaPropiaJSON>();
		for(CartaPropia c : cartas){
			CartaPropiaJSON cj = new CartaPropiaJSON(c);
			list.add(cj);
		}
		return list;
	}
	private List<CartaJSON> parserListTwo(List<Carta> cartas) {
		
		List<CartaJSON> list = new ArrayList<CartaJSON>();
		for(Carta c : cartas){
			CartaJSON cj = new CartaJSON(c);
			list.add(cj);
		}
		return list;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<CartaPropiaJSON> getCartasPropias() {
		return cartasPropias;
	}

	public void setCartasPropias(List<CartaPropiaJSON> cartasPropias) {
		this.cartasPropias = cartasPropias;
	}

	public List<CartaJSON> getCartasBuscadas() {
		return cartasBuscadas;
	}

	public void setCartasBuscadas(List<CartaJSON> cartasBuscadas) {
		this.cartasBuscadas = cartasBuscadas;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
