package model;

import java.util.List;

public class Usuario {
	private Integer idUsuario;
	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String contraseña;
	private String provincia;
	private String sal;
	private String rol;
	private String fechaAta;
	private boolean activo;
	
	private List<Carta> Ofrecidas;
	private List<Carta> Buscadas;
	private List<Valoracion> valoracionesRecibidas;
	private List<Valoracion> valoracionesDadas;
	
	public List<Valoracion> getValoracionesRecibidas() {
		return valoracionesRecibidas;
	}
	public void setValoracionesRecibidas(List<Valoracion> valoracionesRecibidas) {
		this.valoracionesRecibidas = valoracionesRecibidas;
	}
	public List<Valoracion> getValoracionesDadas() {
		return valoracionesDadas;
	}
	public void setValoracionesDadas(List<Valoracion> valoracionesDadas) {
		this.valoracionesDadas = valoracionesDadas;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getSal() {
		return sal;
	}
	public void setSal(String sal) {
		this.sal = sal;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getFechaAta() {
		return fechaAta;
	}
	public void setFechaAta(String fechaAta) {
		this.fechaAta = fechaAta;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public List<Carta> getOfrecidas() {
		return Ofrecidas;
	}
	public void setOfrecidas(List<Carta> ofrecidas) {
		Ofrecidas = ofrecidas;
	}
	public List<Carta> getBuscadas() {
		return Buscadas;
	}
	public void setBuscadas(List<Carta> buscadas) {
		Buscadas = buscadas;
	}
	
	
}
