package es.ucm.fdi.iw.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.*;


@Entity
public class Valoracion {

	private long id;
	private Usuario usuarioQueValora;
	private Usuario usuarioValorado;
	private String comentario;
	private Integer valor;

	
	public static Valoracion crearValoracion(Usuario usuarioQueValora, Usuario usuarioValorado,
			String comentario, Integer valor) {
		
		Valoracion v = new Valoracion();
		v.usuarioQueValora = usuarioQueValora;
		v.usuarioValorado = usuarioValorado;
		v.comentario = comentario;
		v.valor = valor;
		return v;
	}
	
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
