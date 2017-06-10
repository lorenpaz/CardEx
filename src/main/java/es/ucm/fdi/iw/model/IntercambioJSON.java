package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;


public class IntercambioJSON {

	private long id;
	private UsuarioJSON usuarioOfrece;
	private UsuarioJSON usuarioRecibe;
	private String estadoIntercambio;//Aceptadas, Rechazadas, Finalizadas, Pendientes
	private List<CartaPropiaJSON> cartasOfrecidas;
	private List<CartaPropiaJSON> cartasRecibidas;
	private String fecha;
	private UsuarioJSON usuarioRealizaUltimaAccion;
	private boolean terminado;
	
	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public IntercambioJSON() {}

	public IntercambioJSON(Intercambio i) {
		this.id = i.getId();
		this.usuarioOfrece = new UsuarioJSON(i.getUsuarioOfrece());
		this.usuarioRecibe = new UsuarioJSON(i.getUsuarioRecibe());
		this.usuarioRealizaUltimaAccion = new UsuarioJSON(i.getUsuarioRealizaUltimaAccion());
		this.estadoIntercambio = i.getEstadoIntercambio();
		this.cartasOfrecidas = parserList(i.getCartasOfrecidas());
		this.cartasRecibidas = parserList(i.getCartasRecibidas());
		this.fecha = i.getFecha().toString();
		this.terminado = i.isTerminado();
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

	public UsuarioJSON getUsuarioOfrece() {
		return usuarioOfrece;
	}

	public void setUsuarioOfrece(UsuarioJSON usuarioOfrece) {
		this.usuarioOfrece = usuarioOfrece;
	}

	public UsuarioJSON getUsuarioRecibe() {
		return usuarioRecibe;
	}

	public void setUsuarioRecibe(UsuarioJSON usuarioRecibe) {
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
	
	public UsuarioJSON getUsuarioRealizaUltimaAccion() {
		return usuarioRealizaUltimaAccion;
	}

	public void setUsuarioRealizaUltimaAccion(UsuarioJSON usuarioRealizaUltimaAccion) {
		this.usuarioRealizaUltimaAccion = usuarioRealizaUltimaAccion;
	}


	

}
