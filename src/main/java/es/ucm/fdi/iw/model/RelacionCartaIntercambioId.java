package es.ucm.fdi.iw.model;

import java.io.Serializable;


public class RelacionCartaIntercambioId implements Serializable{
	private Integer idIntercambio;
	private Integer idCarta;
	
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
}
