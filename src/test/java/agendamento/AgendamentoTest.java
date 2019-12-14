package agendamento;

import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.OfertaEspecialidade;
import br.ufrn.imd.sigsaude.dominio.Paciente;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import dados.DadosOfertaEspecialidade;
import dados.DadosPaciente;
import dados.DadosPessoa;
import dados.DadosProfissionalDeSaude;
import dados.GerarStringGrande;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroAgendamento;
import formularios.FormularioCadastroOfertaDeEspecialidade;
import formularios.FormularioCadastroProfissionalDeSaude;
import formularios.FormularioCadastroUsuarioServico;

public class AgendamentoTest {

	static FormularioCadastroProfissionalDeSaude formProfissionalDeSaude = new FormularioCadastroProfissionalDeSaude();
	static FormularioCadastroAgendamento formAgendamento = new FormularioCadastroAgendamento();
	static FormularioCadastroUsuarioServico formUsuarioServico = new FormularioCadastroUsuarioServico();
	static FormularioCadastroOfertaDeEspecialidade formOfertaDeEspecialidade = new FormularioCadastroOfertaDeEspecialidade();

	static Navegadores navegador = Navegadores.PADRAO;

	@BeforeClass
	public static void login() {
		// formAgendamento.loginAmbienteHomologacao(null, navegador);
		formAgendamento.loginAmbienteTeste(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formAgendamento.logout();
	}

	@Test
	public void agendamentoCompleto() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();
		String denominacao = DadosOfertaEspecialidade.gerarNomeEspecialidade();
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		Random numeroAleatorio = new Random();
		int n1 = numeroAleatorio.nextInt(10);
		if (n1 == 0 || n1 == 1) {
			n1 = n1 + 2;
		}
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		pessoa.setUsaNomeSocial(false);
		Paciente paciente = DadosPaciente.gerarDadosPaciente(pessoa, false);
		try {

			formUsuarioServico.loginAmbienteTeste(null, navegador);
			formUsuarioServico.inserirDadosPessoaisPaciente(paciente, true);
			formUsuarioServico.inserirEnderecoPaciente(paciente);
			formUsuarioServico.inserirContatoPaciente(paciente);
			formUsuarioServico.salvarContatoPaciente();
			formUsuarioServico.inserirResponsavelPaciente(paciente);
			formUsuarioServico.confirmarCadastro();
			formUsuarioServico.logout();

			formProfissionalDeSaude.loginAmbienteTeste(null, navegador);
			formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
			formProfissionalDeSaude.confirmarCadastro();
			formProfissionalDeSaude.logout();
			// TESTE DE ESCALA
			formOfertaDeEspecialidade.loginAmbienteTeste(null, navegador);
			try {
				formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, denominacao);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			formOfertaDeEspecialidade.inserirEspecialidade();
			formOfertaDeEspecialidade.inserirHorarioEscalaProfissional(n1);
			formOfertaDeEspecialidade.concluirCadastro();
			formOfertaDeEspecialidade.inserirEscala(denominacao, profissionalSaude);
			formOfertaDeEspecialidade.logout();
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		formAgendamento.loginAmbienteTeste(null, navegador);
		formAgendamento.inserirAgendamento(paciente, "FARMÁCIA CLÍNICA EM TERAPIA ANTINEOPLÁSICA", profissionalSaude,
				"FAR046", "Abril");
		formAgendamento.inserirObservacao("teste");
		formAgendamento.confirmarAgendamento();
		try {
			formAgendamento.verificaAgendamento();
			formAgendamento.logout();
		} catch (FormularioException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void agendarNormalmente() {
		formAgendamento.inserirAgendamento(null, null, null, null, "Março");
		formAgendamento.inserirObservacao("teste");
		formAgendamento.confirmarAgendamento();
		try {
			formAgendamento.verificaAgendamento();
		} catch (FormularioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void agendarMesmaHoraEDiaJaAgendado() {

		try {
			formAgendamento.inserirAgendamentoMesmaHoraEDia("Março");
			formAgendamento
					.verificaErro("O Usuário do Serviço já possui um agendamento cadastrado para o horário escolhido.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void agendarSemUsuario() {

		try {
			formAgendamento.inserirAgendamentoSemNome();
			formAgendamento.verificaErro("É necessário informar todos os campos para realizar o agendamento");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Teste com base em um bug ja ocorrido Issue #460
	 * {@link} https://projetos.imd.ufrn.br/plataforma-dados-clinicos/sigsaude/issues/460
	 */
	@Test
	public void agendarObservacaoGigante() {
		formAgendamento.inserirAgendamento(null, null, null, null, "Março");
		formAgendamento.inserirObservacao(GerarStringGrande.gerarString270());
		formAgendamento.confirmarAgendamento();
		try {
			formAgendamento.verificaErroSnack("A observação não pode ultrapassar o limite de 255 caracteres.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void agendarDataAnteriorAAtual() {
		formAgendamento.inserirAgendamentoDataAnterior(null, null, null, null);
		formAgendamento.inserirObservacao("teste");
		formAgendamento.confirmarAgendamento();
		try {
			formAgendamento.verificaErroSnack("Não é possível realizar um agendamento para uma data anterior a data atual.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void justificativaGigante() {
		formAgendamento.inserirAgendamento(null, null, null, null, "Março");
		formAgendamento.inserirObservacao("teste");
		formAgendamento.confirmarAgendamento();
		formAgendamento.cancelarAgendamento(null,"Março", null);
		formAgendamento.inserirJustificativaCancelamento(GerarStringGrande.gerarString270());
		formAgendamento.confirmarCancelamento();
		try {
			formAgendamento.verificaErroSnack(
					"A justificativa do cancelamento não pode ultrapassar o limite de 255 caracteres.");
		} catch (FormularioException e) {
			fail();
		}
	}
	/**
	 * Teste para verificar um bug ja ocorrido Issue #500
	 * {@link} https://projetos.imd.ufrn.br/plataforma-dados-clinicos/sigsaude/issues/500
	 */
	// @Test
	// public void listarAgendamentosComCaractereEspecialEmNome() {
	// try {
	// formAgendamento.listarAgendamentoCaractereEspecialNome();
	// }catch (FormularioException e) {
	// e.printStackTrace();
	// fail();
	// }
	// }
}
