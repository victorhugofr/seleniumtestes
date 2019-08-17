package dados;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// import org.testng.Reporter;

import br.ufrn.imd.sigsaude.dominio.Endereco;
import br.ufrn.imd.sigsaude.dominio.Municipio;
import br.ufrn.imd.sigsaude.dominio.UnidadeFederativa;


public class DadosEndereco {

	public DadosEndereco() {
		// TODO Auto-generated constructor stub
	}

	public static List<Endereco> gerarDadosEnderecoPessoa() {
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);
		
		List<Endereco> address = new ArrayList<>();
		Endereco endereco = new Endereco();

		endereco.setCep("59151-610");
		endereco.setLogradouro("Avenida Ayrton Senna");
		endereco.setNumero(Integer.toString((int) Math.round(Math.random() * 10000)));
		endereco.setPais("Brasil");

		Map<String, String> estadosMap = gerarEstadoEMunicipio.gerarEstado();

		String[] chaves = estadosMap.keySet().toArray(new String[estadosMap.size()]);

		int valueOfEstados = (int) Math.round(Math.random() * chaves.length);
		List<String> municipiosArray = null;
		try {
			municipiosArray = gerarEstadoEMunicipio.gerarMunicipio(chaves[valueOfEstados]);
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			valueOfEstados = (int) Math.round(Math.random() * chaves.length);
			municipiosArray = gerarEstadoEMunicipio.gerarMunicipio(chaves[valueOfEstados]);
			System.out.println("Valor de municipiosArray.size (gerarDadosEnderecoPessoa): " + municipiosArray.size());
			System.out.println("Valor de valueOfEstados (gerarDadosEnderecoPessoa): " + valueOfEstados);
		}

		UnidadeFederativa unidadeFederativa = new UnidadeFederativa();
		Municipio municipio = new Municipio();

		unidadeFederativa.setSigla("RN");
		municipio.setNome("Parnamirim");

		endereco.setUf(unidadeFederativa);
		endereco.setMunicipio(municipio);

		endereco.setBairro("Nova Parnamirim");
		endereco.setNomade(false);
		endereco.setComplemento("Complemento Selenium " + (int) Math.round(Math.random() * 1000) + " " + dataAtual);;
		endereco.setReferencia(
				"Rerefência de Endereço Selenium " + (int) Math.round(Math.random() * 1000) + " " + dataAtual);
		
		address.add(endereco);
		
		return address;
	}
	
	public static Endereco gerarDadosEndereco() {
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);
		
		Endereco endereco = new Endereco();

		endereco.setCep("59151-610");
		endereco.setLogradouro("Avenida Ayrton Senna");
		endereco.setNumero(Integer.toString((int) Math.round(Math.random() * 10000)));
		endereco.setPais("Brasil");

		Map<String, String> estadosMap = gerarEstadoEMunicipio.gerarEstado();

		String[] chaves = estadosMap.keySet().toArray(new String[estadosMap.size()]);

		int valueOfEstados = (int) Math.round(Math.random() * chaves.length);
		List<String> municipiosArray = null;
		try {
			municipiosArray = gerarEstadoEMunicipio.gerarMunicipio(chaves[valueOfEstados]);
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			valueOfEstados = (int) Math.round(Math.random() * chaves.length);
			municipiosArray = gerarEstadoEMunicipio.gerarMunicipio(chaves[valueOfEstados]);
			System.out.println("Valor de municipiosArray.size (gerarDadosEnderecoPessoa): " + municipiosArray.size());
			System.out.println("Valor de valueOfEstados (gerarDadosEnderecoPessoa): " + valueOfEstados);
		}

		UnidadeFederativa unidadeFederativa = new UnidadeFederativa();
		Municipio municipio = new Municipio();

		unidadeFederativa.setSigla(estadosMap.get(chaves[valueOfEstados]));

		int valueOfMunicipio = (int) Math.round(Math.random() * municipiosArray.size());

		try {
			System.out.println("Valor de unidadefederativa: " + unidadeFederativa.getSigla());
			System.out.println("Valor de municipiosArray.size (gerarDadosEnderecoPessoa-try): " + municipiosArray.size());
			System.out.println("Valor de valueOfMunicipio (gerarDadosEnderecoPessoa-try): " + valueOfMunicipio);
			municipio.setNome(municipiosArray.get(valueOfMunicipio));
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("Valor de municipiosArray.size (gerarDadosEnderecoPessoa-catch): " + municipiosArray.size());
			System.out.println("Valor de valueOfMunicipio (gerarDadosEnderecoPessoa-catch): " + valueOfMunicipio);
			if(municipiosArray.size() == 1) {
				municipio.setNome(municipiosArray.get(0));
			} else {
				municipio.setNome(municipiosArray.get(valueOfMunicipio));
			}
			
		}

		endereco.setUf(unidadeFederativa);
		endereco.setMunicipio(municipio);

		endereco.setBairro("Nova Parnamirim");
		endereco.setNomade(false);
		endereco.setReferencia(
				"Rerefência de Endereço Selenium " + (int) Math.round(Math.random() * 1000) + " " + dataAtual);
		
		return endereco;
	}
	
	public static List<Endereco> gerarEnderecoEmBranco() {
		List<Endereco> address = gerarDadosEnderecoPessoa();
		Endereco endereco = address.get(address.size() - 1);
		
		endereco.setCep("");
		endereco.setComplemento("");
		endereco.setNomade(false);
		endereco.setLogradouro("");
		address.add(endereco);
		
		return address;
	}
	
}