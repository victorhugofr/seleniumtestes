package profissionalDeSaude;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import dados.DadosProfissionalDeSaude;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroProfissionalDeSaude;

public class ProfissionalDeSaudeTest {
	static FormularioCadastroProfissionalDeSaude formProfissionalDeSaude = new FormularioCadastroProfissionalDeSaude();
	static Navegadores navegador = Navegadores.PADRAO;

	@BeforeClass
	public static void login() {
		// formProfissionalDeSaude.loginAmbienteHomologacao(null, navegador);
		formProfissionalDeSaude.loginAmbienteTeste(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formProfissionalDeSaude.logout();
	}

	@Test
	public void cadastrarProfissionalDeSaudeTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		formProfissionalDeSaude.confirmarCadastro();

		try {
			formProfissionalDeSaude.verificaProfissionalCadastrado(profissionalSaude);
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarProfissionalDeSaudeSemCPFTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, false);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do cpf nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void cadastrarProfissionalDeSaudeSemIdProfissional() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude
				.gerarDadosProfissionalDeSaudeComIdentidadeProfissionalVazia();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		try {
			formProfissionalDeSaude
					.verificaErroObrigatorio("Mensagem de obrigatoriedade do idprofissional nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	/* Rever se isso deve ser implementado ou não */
	// @Test
	// public void cadastrarProfissionalDeSaudeIdProfissionalInvalida() {
	// ProfissionalSaude profissionalSaude =
	// DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComIdentidadeProfissionalInvalida();
	//
	// formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
	// try {
	// formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do
	// idprofissional nao encontrada");
	// } catch (FormularioException e) {
	// e.printStackTrace();
	// fail();
	// }
	// }
	//
	@Test
	public void cadastrarProfissionalDeSaudeSemEmail() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComEmailVazio();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do email nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarProfissionalDeSaudeEmailInvalido() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComEmailInvalido();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do email nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarProfissionalDeSaudeSemLogin() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComLoginVazio();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		try {
			formProfissionalDeSaude.verificaErroObrigatorio("Mensagem de obrigatoriedade do login nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarProfissionalDeSaudeSemEspecialidade() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();
		profissionalSaude.setEspecialidade(null);

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		try {
			formProfissionalDeSaude
					.verificaErroObrigatorio("Mensagem de obrigatoriedade da especialidade nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarProfissionalDeSaudeComNomeVazioTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComNomeVazio();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do nome nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarProfissionalDeSaudeComNomeInvalidoTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComNomeInvalido();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do nome nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void inativarProfissionalDeSaudeTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();

		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
		formProfissionalDeSaude.confirmarCadastro();
		formProfissionalDeSaude.inativarProfissionalSaude(profissionalSaude, "justificativa");
		try {
			formProfissionalDeSaude.verificaProfissionalCadastrado(profissionalSaude);
			fail();
		} catch (FormularioException e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public void inativarJustificativaGiganteProfissionalDeSaudeTest() {
//		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();
//
//		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude, true);
//		formProfissionalDeSaude.confirmarCadastro();
//		formProfissionalDeSaude.inativarProfissionalSaude(profissionalSaude, GerarStringGrande.gerarString270());
//		try {
//			formProfissionalDeSaude.verificaProfissionalCadastrado(profissionalSaude);
//			fail();
//		} catch (FormularioException e) {
//			e.printStackTrace();
//		}
//	}

}
