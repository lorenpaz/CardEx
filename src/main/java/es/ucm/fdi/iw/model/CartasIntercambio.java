package es.ucm.fdi.iw.model;

import javax.persistence.*;

@Entity
@IdClass(RelacionCartaIntercambioId.class)
public class CartasIntercambio {
	@Id
	private Intercambio Intercambio;
	@Id
	private Carta Carta;
	private String estadoCarta;
	private Integer cantidad;
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Intercambio getIntercambio() {
		return Intercambio;
	}
	public void setIntercambio(Intercambio intercambio) {
		Intercambio = intercambio;
	}
	public Carta getCarta() {
		return Carta;
	}
	public void setCarta(Carta carta) {
		Carta = carta;
	}
	public String getEstadoCarta() {
		return estadoCarta;
	}
	public void setEstadoCarta(String estadoCarta) {
		this.estadoCarta = estadoCarta;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
