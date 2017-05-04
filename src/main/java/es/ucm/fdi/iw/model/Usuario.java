package es.ucm.fdi.iw.model;

import java.util.List;
import javax.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Entity
public class Usuario {
	private static BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

	private long id;
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

	private List<Valoracion> valoracionesRecibidas;
	private List<Valoracion> valoracionesDadas;
	
	private float valoracionMedia;

	public Usuario() {
	}

	public static Usuario crearUsuario(String usuario, String contraseña, String role) {
		Usuario u = new Usuario();
		u.usuario = usuario;
		u.contraseña = generateHashedAndSalted(contraseña);
		u.rol = role;
		return u;
	}

	@OneToMany(targetEntity = Valoracion.class, mappedBy = "usuarioQueValora")
	public List<Valoracion> getValoracionesRecibidas() {
		return valoracionesRecibidas;
	}

	public void setValoracionesRecibidas(List<Valoracion> valoracionesRecibidas) {
		this.valoracionesRecibidas = valoracionesRecibidas;
	}

	@OneToMany(targetEntity = Valoracion.class, mappedBy = "usuarioValorado")
	public List<Valoracion> getValoracionesDadas() {
		return valoracionesDadas;
	}

	public void setValoracionesDadas(List<Valoracion> valoracionesDadas) {
		this.valoracionesDadas = valoracionesDadas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long idUsuario) {
		this.id = idUsuario;
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

	public static String generateHashedAndSalted(String pass) {
		return bcryptEncoder.encode(pass);
	}

	public boolean isPassValid(String pass) {
		return bcryptEncoder.matches(pass, contraseña);
	}

	public float getValoracionMedia() {
		return valoracionMedia;
	}

	public void setValoracionMedia(float valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}
	
	

}
