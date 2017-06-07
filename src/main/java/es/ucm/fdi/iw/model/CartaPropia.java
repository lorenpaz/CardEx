package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@NamedQueries({
	@NamedQuery(name = "allOwnCardsByUserEditionName", query = "select c from CartaPropia c where c.usuarioPropietario = :userParam  and c.carta.setName = :EdParam and c.carta.name = :NParam")
	})
@Entity
public class CartaPropia {
	private long id;
	private Usuario usuarioPropietario;
	private String estadoCarta;// DETERIORADA, JUGADA, NUEVA
	private Carta carta;
	private int cantidad;
	private boolean inExchange;

	private List<Intercambio> intercambiosOfrecidos;
	private List<Intercambio> intercambiosRecibidos;

	public CartaPropia() {
	}

	public CartaPropia(Carta c, String estadoCarta, int cantidad, Usuario u, boolean inEx) {
		this.carta = c;
		this.estadoCarta = estadoCarta;
		this.cantidad = cantidad;
		this.usuarioPropietario = u;
		this.inExchange = inEx;
		this.intercambiosOfrecidos = new ArrayList<Intercambio>();
		this.intercambiosRecibidos = new ArrayList<Intercambio>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Usuario.class)
	public Usuario getUsuarioPropietario() {
		return usuarioPropietario;
	}

	public void setUsuarioPropietario(Usuario usuarioPropietario) {
		this.usuarioPropietario = usuarioPropietario;
	}

	public String getEstadoCarta() {
		return estadoCarta;
	}

	public void setEstadoCarta(String estadoCarta) {
		this.estadoCarta = estadoCarta;
	}

	@ManyToOne(targetEntity = Carta.class, cascade = CascadeType.ALL)
	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@ManyToMany(targetEntity = Intercambio.class, fetch = FetchType.EAGER, mappedBy = "cartasOfrecidas")
	public List<Intercambio> getIntercambiosOfrecidos() {
		return intercambiosOfrecidos;
	}

	public void setIntercambiosOfrecidos(List<Intercambio> intercambiosOfrecidos) {
		this.intercambiosOfrecidos = intercambiosOfrecidos;
	}

	@ManyToMany(targetEntity = Intercambio.class, fetch = FetchType.EAGER, mappedBy = "cartasRecibidas")
	public List<Intercambio> getIntercambiosRecibidos() {
		return intercambiosRecibidos;
	}

	public void setIntercambiosRecibidos(List<Intercambio> intercambiosRecibidos) {
		this.intercambiosRecibidos = intercambiosRecibidos;
	}

	public boolean isInExchange() {
		return inExchange;
	}

	public void setInExchange(boolean inExchange) {
		this.inExchange = inExchange;
	}
	
	

}
