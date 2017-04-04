package model;

public class Valoracion {
	private Integer idValoracion;
	private Integer idUsuarioQueValora;
	private Integer idUsuarioValorado;
	private String comentario;
	private Integer valor;
	public Integer getIdValoracion() {
		return idValoracion;
	}
	public void setIdValoracion(Integer idValoracion) {
		this.idValoracion = idValoracion;
	}
	public Integer getIdUsuarioQueValora() {
		return idUsuarioQueValora;
	}
	public void setIdUsuarioQueValora(Integer idUsuarioQueValora) {
		this.idUsuarioQueValora = idUsuarioQueValora;
	}
	public Integer getIdUsuarioValorado() {
		return idUsuarioValorado;
	}
	public void setIdUsuarioValorado(Integer idUsuarioValorado) {
		this.idUsuarioValorado = idUsuarioValorado;
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
