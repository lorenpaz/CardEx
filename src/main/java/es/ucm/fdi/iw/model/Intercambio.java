package es.ucm.fdi.iw.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name = "getUsersRecibe", query = "select i from Intercambio i where i.usuarioRecibe = :userRecibe"),
	@NamedQuery(name = "getUsersOfrece", query = "select i from Intercambio i where i.usuarioOfrece = :userOfrece")
})
@Entity
public class Intercambio {

	private long id;
	private Usuario usuarioOfrece;
	private Usuario usuarioRecibe;
	private String estadoIntercambio;//Aceptadas, Rechazadas, Finalizadas, Pendientes
	private List<CartaPropia> cartasOfrecidas;
	private List<CartaPropia> cartasRecibidas;
	
	private Date fecha;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public Intercambio() {}

	public Intercambio(Usuario usuarioOfrece, Usuario usuarioRecibe, String estadoIntercambio,
			List<CartaPropia> cartasOfrecidas, List<CartaPropia> cartasRecibidas, Date fecha) {
		super();
		this.usuarioOfrece = usuarioOfrece;
		this.usuarioRecibe = usuarioRecibe;
		this.estadoIntercambio = estadoIntercambio;
		this.cartasOfrecidas = cartasOfrecidas;
		this.cartasRecibidas = cartasRecibidas;
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

	@ManyToMany(targetEntity = CartaPropia.class,fetch = FetchType.EAGER)
	public List<CartaPropia> getCartasOfrecidas() {
		return cartasOfrecidas;
	}

	public void setCartasOfrecidas(List<CartaPropia> cartasOfrecidas) {
		this.cartasOfrecidas = cartasOfrecidas;
	}

	@ManyToMany(targetEntity = CartaPropia.class,fetch = FetchType.EAGER)
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

}
