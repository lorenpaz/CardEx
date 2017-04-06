package model;

import javax.persistence.*;

@Entity
public class CartasIntercambio {
	
	private Integer idIntercambio;
	private Integer idCarta;
	private String estadoCarta;
	private Integer cantidad;
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getIdIntercambio() {
		return idIntercambio;
	}
	public void setIdIntercambio(Integer idIntercambio) {
		this.idIntercambio = idIntercambio;
	}
	public Integer getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(Integer idCarta) {
		this.idCarta = idCarta;
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
