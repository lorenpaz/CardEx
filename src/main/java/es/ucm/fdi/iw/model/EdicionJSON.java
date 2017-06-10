package es.ucm.fdi.iw.model;

public class EdicionJSON {

	private long id;
	private String code;
	private String name;
	private String type;
	private String releaseDate;
	private String block;
	private String magicCardsInfoCode;
	private String fechaUltimaActualizacion;
	
	
	
	public EdicionJSON(Edicion e) {
		this.id = e.getId();
		this.code = e.getCode();
		this.name = e.getName();
		this.type = e.getType();
		this.releaseDate = e.getReleaseDate();
		this.block = e.getBlock();
		this.magicCardsInfoCode = e.getMagicCardsInfoCode();
		this.fechaUltimaActualizacion = e.getFechaUltimaActualizacion();
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	
	
}
