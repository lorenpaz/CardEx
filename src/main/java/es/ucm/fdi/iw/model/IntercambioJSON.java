package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

public class IntercambioJSON {

	private long id;
	private long usuarioOfrece;
	private long usuarioRecibe;
	private String estadoIntercambio;//Aceptadas, Rechazadas, Finalizadas, Pendientes
	private List<CartaPropiaJSON> cartasOfrecidas;
	private List<CartaPropiaJSON> cartasRecibidas;
	private String fecha;
	
	public IntercambioJSON() {}

	public IntercambioJSON(Intercambio i) {
		this.usuarioOfrece = i.getUsuarioOfrece().getId();
		this.usuarioRecibe = i.getUsuarioRecibe().getId();
		this.estadoIntercambio = i.getEstadoIntercambio();
		this.cartasOfrecidas = parserList(i.getCartasOfrecidas());
		this.cartasRecibidas = parserList(i.getCartasRecibidas());
		this.fecha = i.getFecha().toString();
	}

	private List<CartaPropiaJSON> parserList(List<CartaPropia> cartas) {
		List<CartaPropiaJSON> list = new ArrayList<CartaPropiaJSON>();
		for(CartaPropia c : cartas){
			CartaPropiaJSON cj = new CartaPropiaJSON(c);
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

	public long getUsuarioOfrece() {
		return usuarioOfrece;
	}

	public void setUsuarioOfrece(long usuarioOfrece) {
		this.usuarioOfrece = usuarioOfrece;
	}

	public long getUsuarioRecibe() {
		return usuarioRecibe;
	}

	public void setUsuarioRecibe(long usuarioRecibe) {
		this.usuarioRecibe = usuarioRecibe;
	}

	public String getEstadoIntercambio() {
		return estadoIntercambio;
	}

	public void setEstadoIntercambio(String estadoIntercambio) {
		this.estadoIntercambio = estadoIntercambio;
	}

	public List<CartaPropiaJSON> getCartasOfrecidas() {
		return cartasOfrecidas;
	}

	public void setCartasOfrecidas(List<CartaPropiaJSON> cartasOfrecidas) {
		this.cartasOfrecidas = cartasOfrecidas;
	}

	public List<CartaPropiaJSON> getCartasRecibidas() {
		return cartasRecibidas;
	}

	public void setCartasRecibidas(List<CartaPropiaJSON> cartasRecibidas) {
		this.cartasRecibidas = cartasRecibidas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

	

}
