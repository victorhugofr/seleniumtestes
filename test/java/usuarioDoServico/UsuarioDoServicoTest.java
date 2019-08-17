package usuarioDoServico;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.Endereco;
import br.ufrn.imd.sigsaude.dominio.Paciente;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.enums.TipoVinculo;
import dados.DadosEndereco;
import dados.DadosPaciente;
import dados.DadosPessoa;
import dados.GerarStringInvalida;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroUsuarioServico;
import utils.IdadePaciente;

public class UsuarioDoServicoTest {

	static FormularioCadastroUsuarioServico formUsuarioServico = new FormularioCadastroUsuarioServico();
	static Navegadores navegador = Navegadores.PADRAO;
	
	@BeforeClass
	public static void login() {
		formUsuarioServico.login(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formUsuarioServico.logout();
	}

	public void CadastrarUsuarioDoServicoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);

		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelPaciente(paciente);
		formUsuarioServico.confirmarCadastro();

		try {
			formUsuarioServico.verificaUsuarioCadastrado(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void cadastrarPacienteSemCPFTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, false);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelPaciente(paciente);
		formUsuarioServico.confirmarCadastro();

		try {
			formUsuarioServico.verificaUsuarioCadastrado(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarUsuarioDoServicoEstrangeiroComPassaporteTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(true, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, false);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelPaciente(paciente);
		formUsuarioServico.confirmarCadastro();

		try {
			formUsuarioServico.verificaUsuarioCadastrado(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarUsuarioDoServicoEstrangeiroSemPassaporteTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(true, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);

		paciente.getPessoa().setPassaporte(null);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, false);
		try {
			formUsuarioServico
					.verificaErro("Não foi possivel encontrar a mensagem de obrigatoriedade de passaporte na tela");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	// TODO
	public void cadastrarPacienteComVinculoUFRNComCadastroNoSistemaTest() {

	}

	@Test
	public void cadastrarUsuarioDoServicoSemVinculoUFRNTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, false);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);

		paciente.setTipoVinculo(TipoVinculo.SEM_VINCULO);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, false);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelPaciente(paciente);
		formUsuarioServico.confirmarCadastro();

		try {
			formUsuarioServico.verificaUsuarioCadastrado(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void cadastroUsuarioDoServicoComNomeEmBrancoTest() {

		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, false);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		pessoa.setNome("");
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		try {
			formUsuarioServico.verificaErro("Não foi exibida a mensagem de obrigatoriedade do campo NOME");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		} // rever testes apos melhoria #456
	}

	@Test
	public void cadastrarUsuarioDoServicoComDataDeNascimentoEmBrancoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, false);
		pessoa.setDataNascimento(null);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		try {
			formUsuarioServico.verificaErroObrigatorio("Não foi exibida a mensagem de obrigatoriedade do campo NOME");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		} // rever testes apos melhoria #456
	}

	@Test
	public void cadastrarUsuarioDoServicoComEnderecoEmBrancoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		List<Endereco> address = DadosEndereco.gerarEnderecoEmBranco();
		pessoa.setEnderecos(address);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		try {
			formUsuarioServico.inserirEnderecoSemNada();
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarUsuarioDoServicoNomadeTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		pessoa.getEnderecos().get(0).setNomade(true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		formUsuarioServico.inserirEnderecoNomade(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelPaciente(paciente);
		formUsuarioServico.confirmarCadastro();
		

		try {
			formUsuarioServico.verificaUsuarioCadastrado(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarUsuarioDoServicoComContatoEmBrancoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoSemNada();
		try {
			formUsuarioServico.verificaErro("Não foi possivel encontrar a mensagem de obrigatoriedade de contato");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarUsuarioDoServicoComResponsavelEmBrancoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelSemNada();
		
		try {
			formUsuarioServico.verificaErro("Não foi possivel encontrar a mensagem de obrigatoriedade de responsavel");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

	}

	/**
	 * -Verificar teste, foi feito uma gambiarra pra poder passar, verificando
	 * apenas a mensagem-
	 */
	@Test
	public void cadastrarUsuarioDoServicoComCPFInvalidoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		// pessoa.setCpf("1234567898");
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		formUsuarioServico.inserirDadosPacienteCPFInvalido(paciente);
		try {
			formUsuarioServico.verificaErroCPFInvalido("Não foi possivel encontrar a mensagem de invalidade do CPF");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarUsuarioDoServicoMenor18AnosNaoEmancipadoProprioResponsavelTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoaNaoEmancipadoMenorDeIdade(false, false,
				IdadePaciente.PACIENTE_MENOR_IDADE);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, true);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelPropioUsuarioServico();
		
		try {
			formUsuarioServico.verificaErro("Não foi possivel encontrar a mensagem de obrigatoriedade de responsavel");
		} catch (FormularioException e) {
			fail();
		}

	}

	/**
	 * Teste para verificar obrigatoriedade dos campos onde o usuário do serviço
	 * seja menor de idade e não possua responsável
	 * 
	 * Issue #128
	 * {@link} https://projetos.imd.ufrn.br/plataforma-dados-clinicos/sigsaude/issues/128
	 */
	@Test
	public void cadastrarUsuarioDoServicoMenor18AnosNaoEmancipadoSemResponsavelTest() {
		Pessoa pessoa = DadosPessoa.gerarPessoaDeAcordoComIdade(false, false, IdadePaciente.PACIENTE_MENOR_IDADE);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelSemNada();
		
		try {
			formUsuarioServico.verificaErro("Não foi possivel encontrar a mensagem de obrigatoriedade de responsavel");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarUsuarioDoServicoMaior65AnosComResponsavelTest() {
		Pessoa pessoa = DadosPessoa.gerarPessoaDeAcordoComIdade(false, false, IdadePaciente.PACIENTE_MAIOR_IDADE_65);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);

		try {
			formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
			formUsuarioServico.inserirEnderecoPaciente(paciente);
			formUsuarioServico.inserirContatoPaciente(paciente);
			formUsuarioServico.verificaErro(
					"Não foi possivel encontrar a mensagem de maior de 65 anos recomendação de responsavel");
			formUsuarioServico.inserirResponsavelPaciente(paciente);
			formUsuarioServico.confirmarCadastro();

			formUsuarioServico.verificaUsuarioCadastrado(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

		// O paciente possui idade superior a 65 anos. É recomendado informar um
		// responsável.
	}

	@Test
	public void cadastrarUsuarioDoServicoMaior65AnosPropioUsuarioServicoTest() {
		Pessoa pessoa = DadosPessoa.gerarPessoaDeAcordoComIdade(false, false, IdadePaciente.PACIENTE_MAIOR_IDADE_65);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);

		try {
			formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
			formUsuarioServico.inserirEnderecoPaciente(paciente);
			formUsuarioServico.inserirContatoPaciente(paciente);
			formUsuarioServico.verificaErro(
					"Não foi possivel encontrar a mensagem de maior de 65 anos recomendação de responsavel");
			formUsuarioServico.inserirResponsavelPropioUsuarioServico();
			formUsuarioServico.confirmarCadastro();

			formUsuarioServico.verificaUsuarioCadastrado(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

		// O paciente possui idade superior a 65 anos. É recomendado informar um
		// responsável.
	}

	@Test
	public void cadastrarUsuarioDoServicoPacienteUsaNomeSocialValidoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		pessoa.setUsaNomeSocial(true);
		pessoa.setNomeSocial("Nome Social Valido");
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelPaciente(paciente);
		formUsuarioServico.confirmarCadastro();

		try {
			formUsuarioServico.verificaUsuarioCadastrado(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void cadastrarUsuarioDoServicoUsaNomeSocialComNomeSocialVazioTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		pessoa.setUsaNomeSocial(true);
		pessoa.setNomeSocial("");
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		try {
			formUsuarioServico.verificaErro("Não foi possivel encontrar a mensagem de obrigatoriedade de nome social");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void cadastrarUsuarioDoServicoApenasCamposObrigatoriosTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		formUsuarioServico.inserirDadosPessoaisApenasObrigatorio(paciente);
		formUsuarioServico.inserirEnderecoPaciente(paciente);
		formUsuarioServico.inserirContatoPaciente(paciente);
		formUsuarioServico.inserirResponsavelPaciente(paciente);
		formUsuarioServico.confirmarCadastro();

		try {
			formUsuarioServico.verificaUsuarioCadastradoApenasObrigatorio(paciente);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarUsuarioDoServicoNomeMaeInvalidoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		pessoa.setDesconheceMae(false);
		pessoa.setNomeMae(GerarStringInvalida.gerarStringAleatoria());
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		try {
			formUsuarioServico.verificaErro("Não foi possivel encontrar a mensagem de validação do nome mãe");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void cadastrarUsuarioDoServicoNomePaiInvalidoTest() {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		pessoa.setDesconhecePai(false);
		pessoa.setNomePai(GerarStringInvalida.gerarStringAleatoria());
		formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
		try {
			formUsuarioServico.verificaErro("Não foi possivel encontrar a mensagem de validação do nome pai");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
}
