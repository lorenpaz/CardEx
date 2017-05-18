package es.ucm.fdi.iw;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.ucm.fdi.iw.model.Carta;
import es.ucm.fdi.iw.model.Edicion;
import net.minidev.json.parser.JSONParser;
import es.ucm.fdi.iw.json.*;

public class W {
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	public static String get(String u) {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.err.println(get("https://api.magicthegathering.io/v1/cards"));
		
		Collection<Edicion> sets = JSONParserSets(get("https://api.magicthegathering.io/v1/sets"));
		int cont = 0;
		for(Edicion set : sets){
			if(set != null){
				Collection<Carta> cartas = getCartasPorEdicion(set.getCode().toString());
				//System.out.println(set.getName() + ": " + cartas.size());
				System.out.println(cont += cartas.size());
			}
		}
	}
	
	public static Collection<Carta> getCartasPorEdicion(String ed){
		Collection<Carta> cartas = new ArrayList<Carta>();
		int cont = 1;
		JSONParserCartas(get("https://api.magicthegathering.io/v1/cards?set="+ed),cartas);
		
		while(cartas.size() >= cont*100)
			JSONParserCartas(get("https://api.magicthegathering.io/v1/cards?page=" + cont++ + "&set=" + ed),cartas);
		
		return cartas;
	}

	public static void JSONParserCartas(String json, Collection<Carta> cartas){
		JSONObject obj = new JSONObject(json);
		
		JSONArray arr = obj.getJSONArray("cards");
		
		for (int i = 0; i < arr.length(); i++)
		{
			try {
				Carta c = JSON_MAPPER.readValue(json,Carta.class);
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
					
		    //cartas.add(new Carta(arr.getJSONObject(i)));
		}
	}
	
	public static Collection<Edicion> JSONParserSets(String json){
		JSONObject obj = new JSONObject(json);
		Collection<Edicion> sets = new ArrayList<Edicion>();
		
		JSONArray arr = obj.getJSONArray("sets");
		for (int i = 0; i < arr.length(); i++)
		{
		    //sets.add(new Edicion(arr.getJSONObject(i)));
		}
		
		return sets;
	}

}
