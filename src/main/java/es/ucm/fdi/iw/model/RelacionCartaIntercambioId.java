package es.ucm.fdi.iw.model;

import java.io.Serializable;


public class RelacionCartaIntercambioId implements Serializable{
	private Integer intercambio;
	private Integer carta;
	
	public Integer getIdIntercambio() {
		return intercambio;
	}
	public void setIdIntercambio(Integer idIntercambio) {
		this.intercambio = idIntercambio;
	}
	public Integer getIdCarta() {
		return carta;
	}
	public void setIdCarta(Integer idCarta) {
		this.carta = idCarta;
	}
}
