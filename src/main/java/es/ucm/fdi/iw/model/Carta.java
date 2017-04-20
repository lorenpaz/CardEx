package es.ucm.fdi.iw.model;

import java.util.List;

import javax.persistence.*;

//@Entity
public class Carta {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String manaCost;
	private Integer cmc;
	private List<String> colors;
	private List<String> colorIdentity;
	private String type;
	private List<String> supertypes;
	private List<String> types;
	private List<String> subtypes;
	private String rarity;
//	@ManyToOne(targetEntity=Edicion.class)
	private String set;
	private String setName;
	private String text;
	private String artist;
	private String number;
	private String power;
	private String toughness;
	private String layout;
	private String multiverseid;
	private String imageUrl;
	private List<String> rulings;
	private List<String> foreignNames;
	private List<String> printings;
	//private String id;
//	@ManyToMany(mappedBy="buscadas")
	private List<Usuario> usuariosBuscanCarta;
	
//	@OneToMany(mappedBy="carta")
	private List<CartasOfrecidasUsuario> usuariosOfrecenCarta;
	
	public long getId() {
		return id;
	}
	public void setId(long idCarta) {
		this.id = idCarta;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManaCost() {
		return manaCost;
	}
	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}
	public Integer getCmc() {
		return cmc;
	}
	public void setCmc(Integer cmc) {
		this.cmc = cmc;
	}
	public List<String> getColors() {
		return colors;
	}
	public void setColors(List<String> colors) {
		this.colors = colors;
	}
	public List<String> getColorIdentity() {
		return colorIdentity;
	}
	public void setColorIdentity(List<String> colorIdentity) {
		this.colorIdentity = colorIdentity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getSupertypes() {
		return supertypes;
	}
	public void setSupertypes(List<String> supertypes) {
		this.supertypes = supertypes;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public List<String> getSubtypes() {
		return subtypes;
	}
	public void setSubtypes(List<String> subtypes) {
		this.subtypes = subtypes;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getToughness() {
		return toughness;
	}
	public void setToughness(String toughness) {
		this.toughness = toughness;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getMultiverseid() {
		return multiverseid;
	}
	public void setMultiverseid(String multiverseid) {
		this.multiverseid = multiverseid;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<String> getRulings() {
		return rulings;
	}
	public void setRulings(List<String> rulings) {
		this.rulings = rulings;
	}
	public List<String> getForeignNames() {
		return foreignNames;
	}
	public void setForeignNames(List<String> foreignNames) {
		this.foreignNames = foreignNames;
	}
	public List<String> getPrintings() {
		return printings;
	}
	public void setPrintings(List<String> printings) {
		this.printings = printings;
	}
	/*public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}*/
	public List<Usuario> getUsuariosBuscanCarta() {
		return usuariosBuscanCarta;
	}
	public void setUsuariosBuscanCarta(List<Usuario> usuariosBuscanCarta) {
		this.usuariosBuscanCarta = usuariosBuscanCarta;
	}
	public String getSetName() {
		return setName;
	}
	public void setSetName(String setName) {
		this.setName = setName;
	}
	public List<CartasOfrecidasUsuario> getUsuariosOfrecenCarta() {
		return usuariosOfrecenCarta;
	}
	public void setUsuariosOfrecenCarta(List<CartasOfrecidasUsuario> usuariosOfrecenCarta) {
		this.usuariosOfrecenCarta = usuariosOfrecenCarta;
	}
	
	
}
