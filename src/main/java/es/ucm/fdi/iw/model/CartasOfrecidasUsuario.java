package es.ucm.fdi.iw.model;

import javax.persistence.*;

//@Entity
//@IdClass(RelacionCartasIdUsu.class)
public class CartasOfrecidasUsuario {
	
	private long id;

public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	//	@Id
//	@ManyToOne
	private Usuario usuario;
//	@Id
//	@ManyToOne
	private Carta carta;
	private Integer cantidad;
	private String estadoCarta;
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstadoCarta() {
		return estadoCarta;
	}
	public void setEstadoCarta(String estadoCarta) {
		this.estadoCarta = estadoCarta;
	}

}
