package es.ucm.fdi.iw.model;

import java.util.List;

import javax.persistence.*;

//@Entity
public class Edicion {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
//	@OneToMany(targetEntity=Carta.class,  mappedBy="set")
	private List<String> code;
	private String name;
	private String type;
	private Integer mkm_id;
	private String mkm_name;
	private String releaseDate;
	private String block;
	private String magicCardsInfoCode;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	}@Id
//	@G
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
	public List<String> getCode() {
		return code;
	}
	public void setCode(List<String> code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
