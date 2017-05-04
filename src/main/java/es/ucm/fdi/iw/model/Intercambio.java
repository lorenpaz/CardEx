package es.ucm.fdi.iw.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Intercambio {

	private long id;
	private Usuario usuarioOfrece;
	private Usuario usuarioRecibe;
	private String estadoIntercambio;//Aceptadas, Rechazadas, Finalizadas, Pendientes
	private List<Carta> cartasOfrecidas;
	private List<Carta> cartasRecibidas;
	private Date fecha;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
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

	@ManyToMany(targetEntity = Carta.class, mappedBy = "intercambiosOfrecidos")
	public List<Carta> getCartasOfrecidas() {
		return cartasOfrecidas;
	}

	public void setCartasOfrecidas(List<Carta> cartasOfrecidas) {
		this.cartasOfrecidas = cartasOfrecidas;
	}

	@ManyToMany(targetEntity = Carta.class, mappedBy = "intercambiosRecibidos")
	public List<Carta> getCartasRecibidas() {
		return cartasRecibidas;
	}

	public void setCartasRecibidas(List<Carta> cartasRecibidas) {
		this.cartasRecibidas = cartasRecibidas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
