package es.ucm.fdi.iw.model;

import javax.persistence.*;

@Entity
public class Intercambio {

	private long id;
	private Usuario usuarioOfrece;
	private Usuario usuarioRecibe;
	private String estadoIntercambio;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long idIntercambio) {
		this.id = idIntercambio;
	}
	
	@ManyToOne(targetEntity=Usuario.class)
	public Usuario getUsuarioOfrece() {
		return usuarioOfrece;
	}
	public void setUsuarioOfrece(Usuario usuarioOfrece) {
		this.usuarioOfrece = usuarioOfrece;
	}
	
	@ManyToOne(targetEntity=Usuario.class)
	public Usuario getUsuarioRecibe() {
		return usuarioRecibe;
	}
	public void setUsuarioRecibe(Usuario usuarioRecibe) {
		this.usuarioRecibe = usuarioRecibe;
	}
	public String getEstadoIntercambio() {
		return estadoIntercambio;
	}
	public void setEstadoIntercambio(String estadoIntercambio) {
		this.estadoIntercambio = estadoIntercambio;
	}

}
