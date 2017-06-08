package es.ucm.fdi.iw.crawler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.ucm.fdi.iw.model.Carta;
import es.ucm.fdi.iw.model.Edicion;



public class MagicTgIoAPI {
	public final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	private String get(String u){
		try {
			URL url = new URL(u);
			URLConnection hc = url.openConnection();
			hc.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			InputStream is = hc.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			return new String(buffer.toByteArray(), Charset.forName("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public void cardsDataBase(EntityManager entityManager){
		Collection<Edicion> sets = getEdiciones();
		
		for(Edicion set : sets){
			if(set != null) {
				entityManager.persist(set);
				entityManager.flush();
				Collection<Carta> cartas = getCartasPorEdicion(set.getCode());
				for(Carta card : cartas)
				{
					if(card != null){
					//card.setText("");
					String[] aux = new String[10];
					aux[0]="fg";
					//card.setPrintings(aux);
					System.out.println("---------------"+"\n"+card.toString()+"\n");
					card.setEdicion(set);
					entityManager.persist(card);
					entityManager.flush();
					}
				}
				//System.out.println(set.getName() + ": " + cartas.size());
				System.out.println(cartas.size());
			}
		}
	}
	
	@Transactional
	public void cardsDataBaseMin(EntityManager entityManager,int limiteEdiciones,int limiteCartasPorEdicion){
		Collection<Edicion> sets = getEdiciones();
		int cont = 0;
		for(Edicion set : sets){
			if(set != null) {
				entityManager.persist(set);
				entityManager.flush();
				Collection<Carta> cartas = getCartasPorEdicionMin(set.getCode(),limiteCartasPorEdicion);
				for(Carta card : cartas)
				{
					if(card != null){
					//card.setText("");
					String[] aux = new String[10];
					aux[0]="fg";
					//card.setPrintings(aux);
					card.setEdicion(set);
					entityManager.persist(card);
					entityManager.flush();
					}
				}
				//System.out.println(set.getName() + ": " + cartas.size());
				System.out.println(cartas.size());
				cont++;
				if(cont == limiteEdiciones)
				{
					break;
				}
			}

		}
	}
	
	public Collection<Carta> getCartasPorEdicion(String ed){
		Collection<Carta> cartas = new ArrayList<Carta>();
		//int cont = 1;
		cartas = JSONParserCartas(get("https://api.magicthegathering.io/v1/cards?set="+ed));
		
		//while(cartas.size() >= cont*100)
		//	cartasJSONParserCartas(get("https://api.magicthegathering.io/v1/cards?page=" + cont++ + "&set=" + ed),cartas);
		
		return cartas;
	}
	
	public Collection<Carta> getCartasPorEdicionMin(String ed,int limiteCartasPorEdicion){
		Collection<Carta> cartas = new ArrayList<Carta>();
		//int cont = 1;
		cartas = JSONParserCartasMin(get("https://api.magicthegathering.io/v1/cards?set="+ed),limiteCartasPorEdicion);
			
		return cartas;
	}
	
	public List<Edicion> getEdiciones(){
		return JSONParserSets(get("https://api.magicthegathering.io/v1/sets"));
	}

	private Collection<Carta> JSONParserCartas(String json){
		Collection<Carta> cartas = new ArrayList<Carta>();
		JSONObject obj;
		try {
			obj = new JSONObject(json);

			JSONArray arr;
			arr = obj.getJSONArray("cards");
			for (int i = 0; i < arr.length(); i++)
			{
				try {
				//	System.out.println("cada carta:"+arr.getJSONObject(i).toString());
					Carta c = JSON_MAPPER.readValue(arr.getJSONObject(i).toString(),Carta.class);
				    cartas.add(c);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return cartas;
	}
	
	private Collection<Carta> JSONParserCartasMin(String json,int limiteCartasPorEdicion){
		Collection<Carta> cartas = new ArrayList<Carta>();
		JSONObject obj;
		try {
			obj = new JSONObject(json);
			int cont=0;
			JSONArray arr;
			arr = obj.getJSONArray("cards");
			for (int i = 0; i < arr.length(); i++)
			{
				try {
				//	System.out.println("cada carta:"+arr.getJSONObject(i).toString());
					Carta c = JSON_MAPPER.readValue(arr.getJSONObject(i).toString(),Carta.class);
				    cartas.add(c);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				cont++;
				if(cont == limiteCartasPorEdicion)
				{
					break;
				}
			}
			
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return cartas;
	}
	
	private List<Edicion> JSONParserSets(String json){
		JSONObject obj;

		Collection<Edicion> sets = new ArrayList<Edicion>();
		try {
			obj = new JSONObject(json);

			JSONArray arr = obj.getJSONArray("sets");

			for (int i = 0; i < arr.length(); i++)
			{
				try{
					Edicion e = JSON_MAPPER.readValue(arr.getJSONObject(i).toString(),Edicion.class);
				    sets.add(e);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (List<Edicion>) sets;
	}

}