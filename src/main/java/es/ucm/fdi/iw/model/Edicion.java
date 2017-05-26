package es.ucm.fdi.iw.model;

import java.util.List;

import javax.persistence.*;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQueries({
    @NamedQuery(name="getEdiciones", query="select e from Edicion e")
})
@Entity
public class Edicion {

	private long id;

	private List<Carta> cartas;
	private String code;

	private String name;
	private String type;
	private Integer mkm_id;
	private String mkm_name;
	private String releaseDate;
	private String block;
	private String magicCardsInfoCode;
	private String fechaUltimaActualizacion;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMkm_id() {
		return mkm_id;
	}

	public void setMkm_id(Integer mkm_id) {
		this.mkm_id = mkm_id;
	}

	public String getMkm_name() {
		return mkm_name;
	}

	public void setMkm_name(String mkm_name) {
		this.mkm_name = mkm_name;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getMagicCardsInfoCode() {
		return magicCardsInfoCode;
	}

	public void setMagicCardsInfoCode(String magicCardsInfoCode) {
		this.magicCardsInfoCode = magicCardsInfoCode;
	}

	@OneToMany(targetEntity = Carta.class, mappedBy = "edicion")
	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

}
