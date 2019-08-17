package dados;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.HTTPCall;
import utils.HTTPResponse;
import utils.JSONDataHandler;

public class gerarEstadoEMunicipio {
	
	private final static String URL_ESTADOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
	private final static String BASE_URL_MUNICIPIOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";
	private final static String FINAL_URL_MUNICIPIOS = "/municipios";


	public static List<String> gerarMunicipio(String estado) {
		HTTPCall municipioHTTP = new HTTPCall(BASE_URL_MUNICIPIOS + estado + FINAL_URL_MUNICIPIOS);
		String respondeStr = null;
		try {
			HTTPResponse response = municipioHTTP.execute(HTTPCall.Method.GET);
			respondeStr = response.extractDataAsString();

		} catch (IOException ioe) {
			Logger.getLogger(Level.SEVERE + " " + DadosPaciente.class.getName() + " " + ioe);
		}

		return new JSONDataHandler().extractDados(respondeStr);
	}
	
	public static Map<String, String> gerarEstado() {
		HTTPCall estadoHTTP = new HTTPCall(URL_ESTADOS);
		String respondeStr = null;
		try {
			HTTPResponse response = estadoHTTP.execute(HTTPCall.Method.GET);
			respondeStr = response.extractDataAsString();
		} catch (IOException ioe) {
			Logger.getLogger(Level.SEVERE + " " + DadosPaciente.class.getName() + " " + ioe);
		}

		return new JSONDataHandler().extractDadosAsMap(respondeStr);
	}

}
