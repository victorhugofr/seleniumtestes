package dados;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import br.ufrn.imd.sigsaude.dominio.Municipio;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.UnidadeFederativa;
import br.ufrn.imd.sigsaude.dominio.enums.CorRaca;
import br.ufrn.imd.sigsaude.dominio.enums.Escolaridade;
import br.ufrn.imd.sigsaude.dominio.enums.EstadoCivil;
import utils.IdadePaciente;

// import org.testng.Reporter;

public class DadosPessoa {

	public DadosPessoa() {
		// TODO Auto-generated constructor stub
	}

	public static Pessoa gerarDadosPessoa(boolean isEstrangeiro, boolean possuiCPF) {

		Pessoa pessoa = new Pessoa();
		
		pessoa.setPassaporte("");
		pessoa.setEstrangeiro(isEstrangeiro);
		if(isEstrangeiro) {
			pessoa.setPassaporte("US" + gerarNumeroDeDocumentos.geraRG());
			pessoa.setPaisNascimento("United States of America");
		} else {
			pessoa.setPaisNascimento("Brasil");
		}
		/*
		 * if (possuiCPF) { pessoa.setCpf(gerarNumeroDeDocumentos.geraCPF()); } else {
		 * pessoa.setCpf(""); }
		 */
		
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += calendario.getDisplayName(Calendar.MONTH, Calendar.SHORT, localizacao).toUpperCase();

		pessoa.setNome("NOME DEFAULT");
        boolean sexo = new Random().nextBoolean();

        if (sexo) {
			pessoa.setSexo("F");
			pessoa.setNome("Pessoa F Teste Selenium " + dataAtual + " " + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		} else {
			pessoa.setSexo("M");
			pessoa.setNome("Pessoa M Teste Selenium " + dataAtual + " " + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		}
		
		pessoa.setDesconheceMae(new Random().nextBoolean());
		if (!pessoa.isDesconheceMae()) {
			pessoa.setNomeMae("Selenium Mother " + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		}

		pessoa.setDesconhecePai(new Random().nextBoolean());
		if (!pessoa.isDesconhecePai()) {
			pessoa.setNomePai("Selenium Father " + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		}
		
		pessoa.setEmancipado(false);
		
		pessoa.setUsaNomeSocial(new Random().nextBoolean());
		if (pessoa.getUsaNomeSocial()) {
			pessoa.setNomeSocial("Selenium Nome Social " + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		}
		
		Date data = (Date.from(LocalDate.of(1994, 10, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		pessoa.setDataNascimento(data);
		
		pessoa.setRg(gerarNumeroDeDocumentos.geraRG());
		pessoa.setOrgaoExpedidor("SSP");
		
		Map<String, String> estadosMap = gerarEstadoEMunicipio.gerarEstado();

		String[] chaves = estadosMap.keySet().toArray(new String[estadosMap.size()]);

		int valueOfEstados = (int) Math.round(Math.random() * chaves.length);
		List<String> municipiosArray = null;

		try {
			municipiosArray = gerarEstadoEMunicipio.gerarMunicipio(chaves[valueOfEstados]);
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			
			municipiosArray = gerarEstadoEMunicipio.gerarMunicipio(chaves[valueOfEstados - 1]);
			valueOfEstados -= 1;
			System.out.println("Valor de municipiosArray.size (gerarDadosPessoa): " + municipiosArray.size());
			System.out.println("Valor de valueOfEstados (gerarDadosPessoa): " + valueOfEstados);
		}

		UnidadeFederativa unidadeFederativa = new UnidadeFederativa();
		Municipio municipioDeNascimento = new Municipio();

		unidadeFederativa.setSigla(estadosMap.get(chaves[valueOfEstados]));

		int valueOfMunicipio = (int) Math.round(Math.random() * (municipiosArray.size()));

		try{
			/*
			 * Reporter.
			 * log("definindo municipio de nascimento - try: valueOfMunicipiosArray.size " +
			 * municipiosArray.size());
			 * Reporter.log("defininco municipio de nascimento - try: valueofMunicipio" +
			 * valueOfMunicipio );
			 */
			municipioDeNascimento.setNome(municipiosArray.get(valueOfMunicipio));
		} catch (ArrayIndexOutOfBoundsException aoobe) {
			valueOfMunicipio = (int) Math.round(Math.random() * municipiosArray.size());
			System.out.println("definindo municipio de nascimento - catch: valueOfMunicipiosArray.size " + municipiosArray.size());
			System.out.println("defininco municipio de nascimento - catch: novo valueofMunicipio" + valueOfMunicipio );
		} catch (IndexOutOfBoundsException ioobe) {
			municipioDeNascimento.setNome(municipiosArray.get(0));
		}

		pessoa.setUfNascimento(unidadeFederativa);
		pessoa.setMunicipioNascimento(municipioDeNascimento);
		
		int corRaca = (int) Math.round(Math.random() * 6);
		if (corRaca == 0) {
			pessoa.setCorRaca(CorRaca.AMARELA);
		} else if (corRaca == 1) {
			pessoa.setCorRaca(CorRaca.BRANCA);
		} else if (corRaca == 2) {
			pessoa.setCorRaca(CorRaca.INDIGENA);
		} else if (corRaca == 3) {
			pessoa.setCorRaca(CorRaca.NAO_DECLARADA);
		} else if (corRaca == 4) {
			pessoa.setCorRaca(CorRaca.NEGRA);
		} else {
			pessoa.setCorRaca(CorRaca.PARDA);
		}
		
		int estadoCivil = (int) Math.round(Math.random() * 4);
		if (estadoCivil == 0) {
			pessoa.setEstadoCivil(EstadoCivil.CASADO);
		} else if (estadoCivil == 1) {
			pessoa.setEstadoCivil(EstadoCivil.DIVORCIADO);
		} else if (estadoCivil == 2) {
			pessoa.setEstadoCivil(EstadoCivil.SOLTEIRO);
		} else {
			pessoa.setEstadoCivil(EstadoCivil.VIUVO);
		}
		
		pessoa.setEmail("sigsaude@"+GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000))+"testes.com");
		
		pessoa.setInformaGenero(new Random().nextBoolean());
		if (pessoa.getInformaGenero()) {
			pessoa.setInformacaoGenero("Gênero Aleatório");
		}

		pessoa.setCelular("01234567890");
		pessoa.adicionarContato(DadosContato.gerarDadosContato());
		
		pessoa.setEnderecos(DadosEndereco.gerarDadosEnderecoPessoa());
		
		pessoa.setCurso("TI");
		
		int escolaridade = (int) Math.round(Math.random() * 9);
		if (escolaridade == 0) {
			pessoa.setEscolaridade(Escolaridade.FUNDAMENTAL_COMPLETO);
		} else if (escolaridade == 1) {
			pessoa.setEscolaridade(Escolaridade.FUNDAMENTAL_INCOMPLETO);
		} else if (escolaridade == 2) {
			pessoa.setEscolaridade(Escolaridade.MEDIO_COMPLETO);
		} else if (escolaridade == 3) {
			pessoa.setEscolaridade(Escolaridade.MEDIO_INCOMPLETO);
		} else if (escolaridade == 4) {
			pessoa.setEscolaridade(Escolaridade.NENHUMA);
		} else if (escolaridade == 5){
			pessoa.setEscolaridade(Escolaridade.POS_COMPLETO);
		} else if (escolaridade == 6) {
			pessoa.setEscolaridade(Escolaridade.POS_INCOMPLETO);
		} else if (escolaridade == 7) {
			pessoa.setEscolaridade(Escolaridade.SUPERIOR_COMPLETO);
		} else {
			pessoa.setEscolaridade(Escolaridade.SUPERIOR_INCOMPLETO);
		}
		
		return pessoa;

	}
	
	public static Pessoa gerarDadosPessoaComNomeSocialValido(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		
		pessoa.setUsaNomeSocial(true);
		pessoa.setNomeSocial("Nome social");

		return pessoa;
	}

	public static Pessoa gerarDadosPessoaComNomeSocialVazio(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = gerarDadosPessoaComNomeSocialValido(isEstrangeiro, possuiCPF);
		
		pessoa.setNomeSocial("");
		
		return pessoa;
	}

	public static Pessoa gerarDadosPessoaComContatoVazio(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		
		pessoa.setCelular("");
		pessoa.adicionarContato(DadosContato.gerarDadosContatoVazioOuInvalido(true));
		
		return pessoa;
	}
	
	public static Pessoa gerarDadosPessoaComContatoInvalido(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		
		pessoa.setCelular(GerarStringInvalida.gerarStringAleatoria());
//		pessoa.adicionarContato(DadosContato.gerarDadosContatoVazioOuInvalido(false));
		
		return pessoa;
	}

	public static Pessoa gerarDadosPessoaComNomeVazio(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		
		pessoa.setNome("");
		
		return pessoa;
	}
	
	public static Pessoa gerarDadosPessoaComNomeInvalido(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		
		pessoa.setNome(GerarStringInvalida.gerarStringAleatoria());
		
		return pessoa;
	}

	public static Pessoa gerarDadosPessoaComEmailVazio(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		
		pessoa.setEmail("");
		
		return pessoa;
	}
	
	public static Pessoa gerarDadosPessoaComEmailInvalido(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		
		pessoa.setEmail(GerarStringInvalida.gerarStringAleatoria());
		
		return pessoa;
	}

	public static Pessoa gerarDadosPessoaNaoEmancipado(boolean isEstrangeiro, boolean possuiCPF) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		
		pessoa.setEmancipado(false);
		
		return pessoa;
	}
	
	public static Pessoa gerarDadosPessoaComDataDeNascimentoInvalida(boolean isEstrangeiro, boolean possuiCPF, int dia, int mes, int ano) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);
		Date data = (Date.from(LocalDate.of(ano, mes, dia).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		pessoa.setDataNascimento(data);

		return pessoa;
	}
	
	public static Pessoa gerarDadosPessoaNaoEmancipadoMenorDeIdade(boolean isEstrangeiro, boolean possuiCPF, IdadePaciente opcao) {
		Pessoa pessoa = gerarDadosPessoaNaoEmancipado(isEstrangeiro, possuiCPF);

		if (opcao.equals(IdadePaciente.PACIENTE_MENOR_IDADE)) {
			pessoa.setDataNascimento(gerarIdadePacienteMenorIdade());
		} else if (opcao.equals(IdadePaciente.PACIENTE_MAIOR_IDADE_65)) {
			pessoa.setDataNascimento(gerarIdadePacienteMaior65());
		} else if (opcao.equals(IdadePaciente.PACIENTE_MAIOR_IDADE_ENTRE_18_E_65)) {
			pessoa.setDataNascimento(gerarIdadePacienteEntre18e65());
		} else if (opcao.equals(IdadePaciente.PACIENTE_COM_18_ANOS)) {
			pessoa.setDataNascimento(gerarIdadePacienteCom18Anos());
		} else if (opcao.equals(IdadePaciente.PACIENTE_COM_65_ANOS)) {
			pessoa.setDataNascimento(gerarIdadePacienteCom65Anos());
		}
		return pessoa;
	}

	public static Pessoa gerarPessoaDeAcordoComIdade(boolean isEstrangeiro, boolean possuiCPF, IdadePaciente opcao) {
		Pessoa pessoa = gerarDadosPessoa(isEstrangeiro, possuiCPF);

		if (opcao.equals(IdadePaciente.PACIENTE_MENOR_IDADE)) {
			pessoa.setDataNascimento(gerarIdadePacienteMenorIdade());
		} else if (opcao.equals(IdadePaciente.PACIENTE_MAIOR_IDADE_65)) {
			pessoa.setDataNascimento(gerarIdadePacienteMaior65());
		} else if (opcao.equals(IdadePaciente.PACIENTE_MAIOR_IDADE_ENTRE_18_E_65)) {
			pessoa.setDataNascimento(gerarIdadePacienteEntre18e65());
		} else if (opcao.equals(IdadePaciente.PACIENTE_COM_18_ANOS)) {
			pessoa.setDataNascimento(gerarIdadePacienteCom18Anos());
		} else if (opcao.equals(IdadePaciente.PACIENTE_COM_65_ANOS)) {
			pessoa.setDataNascimento(gerarIdadePacienteCom65Anos());
		}

		return pessoa;
	}
	
	private static Date gerarIdadePacienteCom65Anos() {
		Date data = (Date.from(LocalDate.of(1953, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return data;
	}

	private static Date gerarIdadePacienteCom18Anos() {
		Date data = (Date.from(LocalDate.of(1999, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		return data;
	}

	private static Date gerarIdadePacienteEntre18e65() {
		Date data = (Date.from(LocalDate.of(1994, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return data;
	}

	private static Date gerarIdadePacienteMenorIdade() {
		Date data = (Date.from(LocalDate.of(2009, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		return data;
	}

	private static Date gerarIdadePacienteMaior65() {
		Date data = (Date.from(LocalDate.of(1950, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		return data;
	}

}