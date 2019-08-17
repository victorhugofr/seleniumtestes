package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONDataHandler {

	/**
	 * Metodo que retorda uma lista de municípios dado um JSON como parâmetro
	 * @param string
	 * @return List<String> 
	 */
	public List<String> extractDados(String string) {
		List<String> dados = new ArrayList<>();
		try {
			

			JSONArray jsonArray = new JSONArray(string);
			for (int i = 0; i < jsonArray.length(); i++) {
				//estado = new JSONObject(jsonArray.getJSONObject(i).getString("sigla"));
				dados.add(jsonArray.getJSONObject(i).getString("nome"));
			}
			return dados;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException npe) {
			dados.add("Sao Joao do Sabugi");
			return dados;
		}
	}
	
	/**
	 * Metodo para extrair os dados de um JSON
	 * @param string com um JSON
	 * @return Map<String, String> sendo Map<Id-IBGE, SiglaEstado>
	 */
	public Map<String, String> extractDadosAsMap(String string){
		Map<String, String> mapaEstados = new HashMap<>();
		try {
			JSONArray jsonArray = new JSONArray(string);
			for (int i = 0; i < jsonArray.length(); i++) {
				mapaEstados.put(Integer.toString(jsonArray.getJSONObject(i).getInt("id")), jsonArray.getJSONObject(i).getString("sigla"));
				
			}
			return mapaEstados;
		} catch (JSONException j) {
			Logger.getLogger(Level.SEVERE + " " + JSONDataHandler.class.getName() + " " + j);
			j.printStackTrace();
			return null;
		} catch(NullPointerException npe) {
			Logger.getLogger(Level.WARNING + " " + JSONDataHandler.class.getName() + " " + " NUll pointer Exception. Retornando 24-RN");
			mapaEstados.put("24", "RN");
			return mapaEstados;
		}
	}
	

}
