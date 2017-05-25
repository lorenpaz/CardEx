package es.ucm.fdi.iw.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@NamedQueries({
		@NamedQuery(name = "userByUserField", query = "select u from Usuario u where u.usuario = :userParam"),
	    @NamedQuery(name="delUser", query="delete from Usuario u where u.usuario= :userParam")
})
@Entity
public class Usuario {
	private long id;
	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String contraseña;
	private String provincia;
	private String roles;
	private Date fechaAlta;
	private boolean activo;
	private float valoracionMedia;
	
	private List<Valoracion> valoracionesRecibidas;
	private List<Valoracion> valoracionesDadas;
	
	private List<Carta> cartasPropias;
	private List<Carta> cartasBuscadas;


	public Usuario() {
	}

	public static Usuario crearUsuario(String nombre, String apellidos, String email, String usuario, String contraseña,
			String provincia) {
		Usuario u = new Usuario();
		u.nombre = nombre;
		u.apellidos = apellidos;
		u.email = email;
		u.usuario = usuario;
		u.contraseña = contraseña;
		u.provincia = provincia;
		u.roles = "USER";
		u.fechaAlta = new Date(Calendar.getInstance().getTime().getTime());
		u.activo = true;
		u.valoracionMedia = 0;
		return u;
	}

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Valoracion.class, mappedBy = "usuarioValorado")
	public List<Valoracion> getValoracionesRecibidas() {
		return valoracionesRecibidas;
	}

	public void setValoracionesRecibidas(List<Valoracion> valoracionesRecibidas) {
		this.valoracionesRecibidas = valoracionesRecibidas;
	}

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Valoracion.class, mappedBy = "usuarioQueValora")
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public float getValoracionMedia() {
		return valoracionMedia;
	}

	public void setValoracionMedia(float valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Carta.class, mappedBy = "usuariosQueMeTienen")
	public List<Carta> getCartasPropias() {
		return cartasPropias;
	}

	public void setCartasPropias(List<Carta> cartasPropias) {
		this.cartasPropias = cartasPropias;
	}
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Carta.class, mappedBy = "usuariosQueMeBuscan")
	public List<Carta> getCartasBuscadas() {
		return cartasBuscadas;
	}

	public void setCartasBuscadas(List<Carta> cartasBuscadas) {
		this.cartasBuscadas = cartasBuscadas;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", usuario=" + usuario + ", contraseña=" + contraseña + ", provincia=" + provincia + ", roles="
				+ roles + ", fechaAlta=" + fechaAlta + ", activo=" + activo + ", valoracionesRecibidas="
				+ valoracionesRecibidas + ", valoracionesDadas=" + valoracionesDadas + ", valoracionMedia="
				+ valoracionMedia + "]";
	}
}
