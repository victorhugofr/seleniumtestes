package dados;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.ufrn.imd.sigsaude.dominio.Endereco;
import br.ufrn.imd.sigsaude.dominio.Municipio;
import br.ufrn.imd.sigsaude.dominio.Servico;
import br.ufrn.imd.sigsaude.dominio.UnidadeFederativa;

/**
 * Classe responspavel para gerar os dados dos servicos que serao utilizados
 * durante as etapas do cadastro.
 * @author Jancy Aragao
 *
 */
public class DadosServico {

	public static Servico gerarDadosServico() {
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);
		
		Servico servico = new Servico();
		
		servico.setNome("Serviço Selenium " 
				+ GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000)) + " " + dataAtual);
		
		servico.setCodigoSipac (Long.toString(Math.round(Math.random() * 10000)));
		servico.setEnderecos(DadosEndereco.gerarDadosEnderecoPessoa());
		
		return servico;
	}
	
	public static Servico gerarDadosServicoVazio() {
		Servico servico = new Servico();
		
		servico.setNome("");
		servico.setCodigoSipac("");
		servico.setEnderecos(gerarDadosEnderecoVazioAsList());
		
		return servico;
	}
	
	public static Servico gerarDadosServicoNomeVazio() {
		Servico servico = gerarDadosServico();
		
		servico.setNome("");
		
		return servico;
	}
	
	public static Servico gerarDadosServicoNomeInvalido() {
		Servico servico = gerarDadosServico();
		
		servico.setNome(GerarStringInvalida.gerarStringAleatoria());
		
		return servico;
	}
	
	public static Servico gerarDadosServicoCodigoSIPACVazio() {
		Servico servico = gerarDadosServico();
		
		servico.setCodigoSipac("");;
		
		return servico;
	}
	
	public static Servico gerarDadosServicoCodigoSIPACInvalido() {
		Servico servico = gerarDadosServico();
		
		servico.setCodigoSipac(GerarStringInvalida.gerarStringAleatoria());;
		
		return servico;
	}
	
	public static Servico gerarDadosServicoEnderecoVazio() {
		Servico servico = gerarDadosServico();
		
		servico.setEnderecos(gerarDadosEnderecoVazioAsList());
		
		return servico;
	}
	
	public static Servico gerarDadosServicoCEPInvalido() {
		List<Endereco> address = new ArrayList<>();
		Servico servico = gerarDadosServico();
		
		address.add(gerarDadosEnderecoServicoCEPInvalido());
		servico.setEnderecos(address);
		
		return servico;
	}
	
	public static Servico gerarDadosServicoLogradouroVazio() {
		List<Endereco> address = new ArrayList<>();
		Servico servico = gerarDadosServico();
		
		address.add(gerarDadosEnderecoServicoLogradouroVazio());
		servico.setEnderecos(address);
		
		return servico;
	}
	
	public static Servico gerarDadosServicoLogradouroInvalido() {
		List<Endereco> address = new ArrayList<>();
		Servico servico = gerarDadosServico();
		
		address.add(gerarDadosEnderecoServicoLogradouroInvalido());
		servico.setEnderecos(address);
		
		return servico;
	}
	
	public static Servico gerarDadosServicoNumeroVazio() {
		List<Endereco> address = new ArrayList<>();
		Servico servico = gerarDadosServico();
		
		address.add(gerarDadosEnderecoServicoNumeroVazio());
		servico.setEnderecos(address);
		
		return servico;
	}
	
	public static Servico gerarDadosServicoNumeroInvalido() {
		List<Endereco> address = new ArrayList<>();
		Servico servico = gerarDadosServico();
		
		address.add(gerarDadosEnderecoServicoNumeroInvalido());
		servico.setEnderecos(address);
		
		return servico;
	}
	
	public static Servico gerarDadosServicoBairroVazio() {
		List<Endereco> address = new ArrayList<>();
		Servico servico = gerarDadosServico();
		
		address.add(gerarDadosEnderecoServicoBairroVazio());
		servico.setEnderecos(address);
		
		return servico;
	}
	
	public static Servico gerarDadosServicoBairroInvalido() {
		List<Endereco> address = new ArrayList<>();
		Servico servico = gerarDadosServico();
		
		address.add(gerarDadosEnderecoServicoBairroInvalido());
		servico.setEnderecos(address);
		
		return servico;
	}
	
	public static List<Endereco> gerarDadosEnderecoVazioAsList(){
		List<Endereco> address = new ArrayList<>();
		
		Endereco endereco = DadosEndereco.gerarDadosEndereco();
		UnidadeFederativa unidadeFederativa = new UnidadeFederativa();
			unidadeFederativa.setNome("");
		Municipio municipio = new Municipio();
			municipio.setNome("");
		
		endereco.setCep("");
		endereco.setLogradouro("");
		endereco.setNumero("");;
		endereco.setPais("");
		endereco.setUf(unidadeFederativa);
		endereco.setMunicipio(municipio);
		endereco.setBairro("");
		
		address.add(endereco);
		
		return address;
	}
	
	public static Endereco gerarDadosEnderecoServicoCEPInvalido() {
		Endereco endereco = DadosEndereco.gerarDadosEndereco();
		
		endereco.setCep(GerarStringInvalida.gerarStringAleatoria());
		
		return endereco;
	}
	
	public static Endereco gerarDadosEnderecoServicoLogradouroVazio() {
		Endereco endereco = DadosEndereco.gerarDadosEndereco();
		
		endereco.setLogradouro(" ");
		
		return endereco;
	}
	
	public static Endereco gerarDadosEnderecoServicoLogradouroInvalido() {
		Endereco endereco = DadosEndereco.gerarDadosEndereco();
		
		endereco.setLogradouro("<!-- Mussum Ipsum, cacilds vidis litro abertis. Sapien in monti palavris qui "
				+ "num significa nadis i pareci latim. Viva Forevis aptent taciti sociosqu ad litora torquent. "
				+ "Suco de cevadiss deixa as pessoas mais interessantis. Não sou faixa preta cumpadi, "
				+ "sou preto inteiris, inteiris.\r\n Interessantiss quisso pudia ce receita de bolis, "
				+ "mais bolis eu num gostis. Quem manda na minha terra sou euzis! Nullam volutpat risus "
				+ "nec leo commodo, ut interdum diam laoreet. Sed non consequat odio. Per aumento de cachacis, "
				+ "eu reclamis. -->");
		
		return endereco;
	}
	
	public static Endereco gerarDadosEnderecoServicoNumeroVazio() {
		Endereco endereco = DadosEndereco.gerarDadosEndereco();
		
		endereco.setNumero(" ");
		
		return endereco;
	}
	
	public static Endereco gerarDadosEnderecoServicoNumeroInvalido() {
		Endereco endereco = DadosEndereco.gerarDadosEndereco();
		
		endereco.setNumero("e123");
		
		return endereco;
	}
	
	public static Endereco gerarDadosEnderecoServicoBairroVazio() {
		Endereco endereco = DadosEndereco.gerarDadosEndereco();
		
		endereco.setBairro(" ");
		
		return endereco;
	}
	
	public static Endereco gerarDadosEnderecoServicoBairroInvalido() {
		Endereco endereco = DadosEndereco.gerarDadosEndereco();
		
		endereco.setBairro("<!-- Mussum Ipsum, cacilds vidis litro abertis. Sapien in monti palavris qui "
				+ "num significa nadis i pareci latim. Viva Forevis aptent taciti sociosqu ad litora torquent. "
				+ "Suco de cevadiss deixa as pessoas mais interessantis. Não sou faixa preta cumpadi, "
				+ "sou preto inteiris, inteiris. Interessantiss quisso pudia ce receita de bolis, "
				+ "mais bolis eu num gostis. Quem manda na minha terra sou euzis! Nullam volutpat risus "
				+ "nec leo commodo, ut interdum diam laoreet. Sed non consequat odio. Per aumento de cachacis, "
				+ "eu reclamis. -->");
		
		return endereco;
	}
	
}