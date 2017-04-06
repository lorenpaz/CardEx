package model;

import java.io.Serializable;

public class RelacionCartasIdUsu implements Serializable{
	private Integer idUsuario;
	private Integer idCarta;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(Integer idCarta) {
		this.idCarta = idCarta;
	}
}
