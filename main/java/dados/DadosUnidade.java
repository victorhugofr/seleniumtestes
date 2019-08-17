package dados;

import java.util.Calendar;
import java.util.Locale;

import br.ufrn.imd.sigsaude.dominio.Unidade;

public class DadosUnidade {

	public static Unidade gerarDadosUnidade() {
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);
        String unidadeValor = GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000));
        
		Unidade unidade = new Unidade();
		
		unidade.setNome("Unidade Selenium " + unidadeValor + " " + dataAtual);
		unidade.setSigla("UNI" + unidadeValor);
		unidade.setCodigoSipac(Long.toString(Math.round(Math.random() * 100000000)));
		unidade.setEmail("unidade@email.com");
		unidade.setResponsavelUnidade(DadosResponsavelUnidade.gerarDadosResponsavelUnidade());
		unidade.setEndereco(DadosEndereco.gerarDadosEndereco());
		unidade.setContatos(DadosContato.gerarDadosContatoAsList());
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComNomeVazio() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setNome("");
		
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComNomeInvalido() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setNome(GerarStringInvalida.gerarStringAleatoria());
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComNomeRepetido() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setNome("testando");
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComSiglaVazia() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setSigla("");
	
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComSiglaInvalida() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setSigla(GerarStringInvalida.gerarStringAleatoria());
		
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComCodigoSIPACVazio() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setCodigoSipac("");
		
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComCodigoSIPACInvalido() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setCodigoSipac(GerarStringInvalida.gerarStringAleatoria());
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComEmailVazio() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setEmail("");
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComCEPInvalido() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.getEndereco().setCep(GerarStringInvalida.gerarStringAleatoria());
		
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComCEPVazio() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.getEndereco().setCep("");
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComLogradouroInvalido() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.getEndereco().setLogradouro(GerarStringInvalida.gerarStringAleatoria());
		
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComLogradouroVazio() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.getEndereco().setLogradouro("");
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComNumeroInvalido() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.getEndereco().setNumero(GerarStringInvalida.gerarStringAleatoria());
		
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComNumeroVazio() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.getEndereco().setNumero("");
	
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComBairroInvalido() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.getEndereco().setBairro(GerarStringInvalida.gerarStringAleatoria());
		
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComBairroVazio() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.getEndereco().setBairro("");
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComTelefoneInvalido() {
		Unidade unidade = DadosUnidade.gerarDadosUnidade();
		
		unidade.setContatos(DadosContato.gerarDadosContatoAsListComTelefoneVazioOuInvalido(false));
		
		return unidade;
	}
	
	public static Unidade gerarDadosUnidadeComTelefoneVazio() {
		Unidade unidade = DadosUnidade.gerarDadosUnidade();
		
		unidade.setContatos(DadosContato.gerarDadosContatoAsListComTelefoneVazioOuInvalido(true));
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComObservacaoInvalido() {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setContatos(DadosContato.gerarDadosContatoAsListComObsInvalida());
		
		return unidade;
	}

	public static Unidade gerarDadosUnidadeComMaisDeUmContato(int tamanhoLista) {
		Unidade unidade = gerarDadosUnidade();
		
		unidade.setContatos(DadosContato.gerarDadosContatoAsList(tamanhoLista));
		
		return unidade;
	}
}