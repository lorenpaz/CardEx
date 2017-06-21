package es.ucm.fdi.iw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartaJSON {

	@JsonIgnore
	private long id;
	private String name;
	private String colorIdentity;
	private String type;
	private String setName;
	private String text;
	private String artist;
	private String power;
	private String imageUrl;
	private String[] manaCost;
	private String toughness;
	private String color;
	private String rarity;
	private String[] colors;
	private String[] types;
	
	public CartaJSON(Carta c) {
		this.id = c.getId();
		this.name = c.getName();
		//daba error cuando colorIdentity[] era null
		this.colorIdentity = c.getColorIdentity() == null ? null : c.getColorIdentity()[0];
		this.type = c.getType();
		this.types = c.getTypes();
		this.setName = c.getSetName();
		this.text = "";//c.getText();
		this.artist = c.getArtist();
		this.power = c.getPower();
		this.imageUrl = c.getImageUrl();
		this.color = c.getColors() == null || c.getColors().length == 0 ? "" : c.getColors()[0];
		this.colors = c.getColors();
		this.toughness = c.getToughness();
		this.rarity = c.getRarity();
		this.manaCost = c.getManaCost().split("");
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColorIdentity() {
		return colorIdentity;
	}
	public void setColorIdentity(String colorIdentity) {
		this.colorIdentity = colorIdentity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSetName() {
		return setName;
	}
	public void setSetName(String setName) {
		this.setName = setName;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
