package es.ucm.fdi.iw.model;

import javax.persistence.*;

//@Entity
public class Intercambio {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Integer idUsuarioOfrece;
	private Integer idUsuarioRecibe;
	private String estadoIntercambio;
	
	public long getId() {
		return id;
	}
	public void setId(long idIntercambio) {
		this.id = idIntercambio;
	}
	public Integer getIdUsuarioOfrece() {
		return idUsuarioOfrece;
	}
	public void setIdUsuarioOfrece(Integer idUsuarioOfrece) {
		this.idUsuarioOfrece = idUsuarioOfrece;
	}
	public Integer getIdUsuarioRecibe() {
		return idUsuarioRecibe;
	}
	public void setIdUsuarioRecibe(Integer idUsuarioRecibe) {
		this.idUsuarioRecibe = idUsuarioRecibe;
	}
	public String getEstadoIntercambio() {
		return estadoIntercambio;
	}
	public void setEstadoIntercambio(String estadoIntercambio) {
		this.estadoIntercambio = estadoIntercambio;
	}

}
