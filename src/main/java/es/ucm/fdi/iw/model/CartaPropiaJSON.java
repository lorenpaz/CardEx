package es.ucm.fdi.iw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class CartaPropiaJSON {
	private long id;
	private long usuarioPropietario;
	private String estadoCarta;//DETERIORADA, JUGADA, NUEVA
	private CartaJSON carta;
	private int cantidad;
	
	public CartaPropiaJSON() {	}
	
	public CartaPropiaJSON(CartaPropia c) {
		this.carta = new CartaJSON(c.getCarta());
		this.estadoCarta = c.getEstadoCarta();
		this.cantidad = c.getCantidad();
		this.usuarioPropietario = c.getUsuarioPropietario().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUsuarioPropietario() {
		return usuarioPropietario;
	}

	public void setUsuarioPropietario(long usuarioPropietario) {
		this.usuarioPropietario = usuarioPropietario;
	}

	public String getEstadoCarta() {
		return estadoCarta;
	}

	public void setEstadoCarta(String estadoCarta) {
		this.estadoCarta = estadoCarta;
	}

	public CartaJSON getCarta() {
		return carta;
	}

	public void setCarta(CartaJSON carta) {
		this.carta = carta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
