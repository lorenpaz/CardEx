package model;

import javax.persistence.*;

@Entity
@IdClass(RelacionCartasIdUsu.class)
public class CartasOfrecidasUsuario {

	@Id
	@ManyToOne
	private Usuario usuario;
	@Id
	@ManyToOne
	private Carta Carta;
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
