package es.ucm.fdi.iw.model;

import javax.persistence.*;

//@Entity
//@IdClass(RelacionCartaIntercambioId.class)
public class CartasIntercambio {
//	@Id
	private Intercambio intercambio;
//	@Id
	private Carta carta;
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
		return intercambio;
	}
	public void setIntercambio(Intercambio intercambio) {
		this.intercambio = intercambio;
	}
	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
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
