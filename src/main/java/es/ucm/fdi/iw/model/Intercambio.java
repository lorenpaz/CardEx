package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@NamedQueries({
		@NamedQuery(name = "getUsersRecibe", query = "select i from Intercambio i where i.usuarioRecibe = :userRecibe"),
		@NamedQuery(name = "getUsersOfrece", query = "select i from Intercambio i where i.usuarioOfrece = :userOfrece"),
		@NamedQuery(name = "estado", query = "select i from Intercambio i where i.estadoIntercambio = :estado"),
		@NamedQuery(name = "exchangeOfrecidas", query = "select i.cartasOfrecidas from Intercambio i"),
		@NamedQuery(name = "exchangeRecibidas", query = "select i.cartasRecibidas from Intercambio i"),
		@NamedQuery(name = "allIntercambios", query = "select i from Intercambio i") })
@Entity
public class Intercambio {

	private long id;
	private Usuario usuarioOfrece;
	private Usuario usuarioRecibe;
	private String estadoIntercambio;// Aceptadas, Rechazadas, Finalizadas,
										// Pendientes
	private List<CartaPropia> cartasOfrecidas;
	private List<CartaPropia> cartasRecibidas;

	private Date fecha;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public Intercambio() {
	}

	public Intercambio(Usuario usuarioOfrece, Usuario usuarioRecibe, String estadoIntercambio, Date fecha) {
		this.usuarioOfrece = usuarioOfrece;
		this.usuarioRecibe = usuarioRecibe;
		this.estadoIntercambio = estadoIntercambio;
		this.cartasOfrecidas = new ArrayList<CartaPropia>();
		this.cartasRecibidas = new ArrayList<CartaPropia>();
		this.fecha = fecha;
	}

	public void setId(long idIntercambio) {
		this.id = idIntercambio;
	}

	@ManyToOne(targetEntity = Usuario.class)
	public Usuario getUsuarioOfrece() {
		return usuarioOfrece;
	}

	public void setUsuarioOfrece(Usuario usuarioOfrece) {
		this.usuarioOfrece = usuarioOfrece;
	}

	@ManyToOne(targetEntity = Usuario.class)
	public Usuario getUsuarioRecibe() {
		return usuarioRecibe;
	}

	public void setUsuarioRecibe(Usuario usuarioRecibe) {
		this.usuarioRecibe = usuarioRecibe;
	}

	public String getEstadoIntercambio() {
		return estadoIntercambio;
	}

	public void setEstadoIntercambio(String estadoIntercambio) {
		this.estadoIntercambio = estadoIntercambio;
	}

	@ManyToMany(targetEntity = CartaPropia.class, fetch = FetchType.EAGER)
	public List<CartaPropia> getCartasOfrecidas() {
		return cartasOfrecidas;
	}

	public void setCartasOfrecidas(List<CartaPropia> cartasOfrecidas) {
		this.cartasOfrecidas = cartasOfrecidas;
	}

	@ManyToMany(targetEntity = CartaPropia.class, fetch = FetchType.EAGER)
	public List<CartaPropia> getCartasRecibidas() {
		return cartasRecibidas;
	}

	public void setCartasRecibidas(List<CartaPropia> cartasRecibidas) {
		this.cartasRecibidas = cartasRecibidas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String toJSON() {
		String json = "";
		json += "{";
		json += "\"id\": \"" + this.id + "\" ,";
		json += "\"usuarioOfrece\": { \"id\": \"" + this.usuarioOfrece.getId() + "\", \"user\": \""
				+ usuarioOfrece.getUsuario() + "\" },";
		json += "\"usuarioRecibe\": { \"id\": \"" + this.usuarioRecibe.getId() + "\", \"user\": \""
				+ usuarioRecibe.getUsuario() + "\" },";
		json += "\"estado\": \"" + this.estadoIntercambio + "\" ,";
		json += "\"cartasOfrecidas\": [";
		for (int i = 0; i < this.cartasOfrecidas.size(); i++) {
			CartaPropia c = this.cartasOfrecidas.get(i);
			json += "{";
			json += "\"nombre\": \"" + c.getCarta().getName() + "\",";
			json += "\"estado\": \"" + c.getEstadoCarta() + "\",";
			json += "\"cantidad\": \"" + c.getCantidad() + "\"";
			json += "}";
			if (i < this.cartasOfrecidas.size() - 1) {
				json += ",";
			}
		}
		json += "],";
		json += "\"cartasRecibidas\": [";
		for (int i = 0; i < this.cartasRecibidas.size(); i++) {
			CartaPropia c = this.cartasRecibidas.get(i);
			json += "{";
			json += "\"nombre\": \"" + c.getCarta().getName() + "\",";
			json += "\"estado\": \"" + c.getEstadoCarta() + "\",";
			json += "\"cantidad\": \"" + c.getCantidad() + "\"";
			json += "}";
			if (i < this.cartasRecibidas.size() - 1) {
				json += ",";
			}
		}
		json += "]";
		json += "}";
		return json;
	}

}
