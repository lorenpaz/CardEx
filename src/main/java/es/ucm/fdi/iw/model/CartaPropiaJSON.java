package es.ucm.fdi.iw.model;

public class CartaPropiaJSON {
	private long id;
	private String usuarioPropietario;
	private String estadoCarta;//DETERIORADA, JUGADA, NUEVA
	private CartaJSON carta;
	private int cantidad;
	
	public CartaPropiaJSON() {	}
	
	public CartaPropiaJSON(CartaPropia c) {
		this.id = c.getId();
		this.carta = new CartaJSON(c.getCarta());
		this.estadoCarta = c.getEstadoCarta();
		this.cantidad = c.getCantidad();
		this.usuarioPropietario = c.getUsuarioPropietario().getUsuario();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getUsuarioPropietario() {
		return usuarioPropietario;
	}

	public void setUsuarioPropietario(String usuarioPropietario) {
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
