package model;

public class Intercambio {
	private Integer idIntercambio;
	private Integer idUsuarioOfrece;
	private Integer idUsuarioRecibe;
	private String estadoIntercambio;
	
	public Integer getIdIntercambio() {
		return idIntercambio;
	}
	public void setIdIntercambio(Integer idIntercambio) {
		this.idIntercambio = idIntercambio;
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
