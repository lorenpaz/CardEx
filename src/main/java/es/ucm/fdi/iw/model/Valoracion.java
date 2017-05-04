package es.ucm.fdi.iw.model;

import javax.persistence.*;

@Entity
public class Valoracion {

	private long id;
	private Usuario usuarioQueValora;
	private Usuario usuarioValorado;
	private String comentario;
	private Integer valor;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long idValoracion) {
		this.id = idValoracion;
	}

	@ManyToOne(targetEntity = Usuario.class)
	public Usuario getUsuarioQueValora() {
		return usuarioQueValora;
	}

	public void setUsuarioQueValora(Usuario idUsuarioQueValora) {
		this.usuarioQueValora = idUsuarioQueValora;
	}

	@ManyToOne(targetEntity = Usuario.class)
	public Usuario getUsuarioValorado() {
		return usuarioValorado;
	}

	public void setUsuarioValorado(Usuario idUsuarioValorado) {
		this.usuarioValorado = idUsuarioValorado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
