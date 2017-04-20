package es.ucm.fdi.iw.model;

import java.io.Serializable;

public class RelacionCartasIdUsu implements Serializable{
	private Integer usuario;
	private Integer carta;
	
	public Integer getIdUsuario() {
		return usuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.usuario = idUsuario;
	}
	public Integer getIdCarta() {
		return carta;
	}
	public void setIdCarta(Integer idCarta) {
		this.carta = idCarta;
	}
}
