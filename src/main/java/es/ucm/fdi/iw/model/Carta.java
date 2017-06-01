package es.ucm.fdi.iw.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NamedQueries({
	@NamedQuery(name = "allCards", query = "select c from Carta c"),
	@NamedQuery(name = "findCardByNameAndEdition", query = "select c from Carta c where c.name = :paramName and c.setName= :paramEdition"),
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Carta {

	@JsonIgnore
	private long id;
	private String name;
	private String manaCost;
	private Integer cmc;
	private String[] colors;
	private String[] colorIdentity;
	private String type;
	private String[] types;
	private String rarity;

	private Edicion edicion;
	private String setName;
	private String text;
	private String artist;
	private String power;
	private String toughness;

	private String layout;
	private String multiverseid;
	private String imageUrl;
	private String[] printings;
	
	
	//private List<Usuario> usuariosQueMeTienen;
	private List<Usuario> UsuariosQueMeBuscan;
	
	private List<CartaPropia> cartaPropia;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public String[] getColorIdentity() {
		return colorIdentity;
	}

	public void setColorIdentity(String[] colorIdentity) {
		this.colorIdentity = colorIdentity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	@ManyToOne(targetEntity = Edicion.class)
	public Edicion getEdicion() {
		return edicion;
	}

	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
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
	
	public String[] getPrintings() {
		return printings;
	}

	public void setPrintings(String[] printings) {
		this.printings = printings;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}


	
	/*@ManyToMany(fetch = FetchType.EAGER, targetEntity = Usuario.class, mappedBy = "cartasPropias")
	public List<Usuario> getUsuariosQueMeTienen() {
		return usuariosQueMeTienen;
	}

	public void setUsuariosQueMeTienen(List<Usuario> usuariosQueMeTienen) {
		this.usuariosQueMeTienen = usuariosQueMeTienen;
	}*/
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Usuario.class, mappedBy = "cartasBuscadas")
	public List<Usuario> getUsuariosQueMeBuscan() {
		return UsuariosQueMeBuscan;
	}

	public void setUsuariosQueMeBuscan(List<Usuario> usuariosQueMeBuscan) {
		UsuariosQueMeBuscan = usuariosQueMeBuscan;
	}
	@OneToMany(fetch = FetchType.EAGER, targetEntity = CartaPropia.class, mappedBy="carta")
	public List<CartaPropia> getCartasPropias() {
		return cartaPropia;
	}

	public void setCartasPropias(List<CartaPropia> cartaPropia) {
		this.cartaPropia = cartaPropia;
	}
	
	
	

}
