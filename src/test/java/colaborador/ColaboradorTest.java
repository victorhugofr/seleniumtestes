package colaborador;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.Colaborador;
import dados.DadosColaborador;
import dados.gerarNumeroDeDocumentos;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroColaborador;
import model.UsuarioAdmin;

public class ColaboradorTest {

	static FormularioCadastroColaborador formColaborador = new FormularioCadastroColaborador();
	static Navegadores navegador = Navegadores.PADRAO;

	@BeforeClass
	public static void login() {
//		formColaborador.loginAmbienteHomologacao(null, navegador); // se alterar o ambiente, 
		formColaborador.loginAmbienteTeste(null, navegador);
															//alterar no metodo alterarColaboradorComColaboradorLogadoTest
	}

	@AfterClass
	public static void logout() {
		formColaborador.logout();
	}

	@Test
	public void cadastrarColaboradorTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaborador();

		formColaborador.inserirDadosColaborador(colaborador, gerarNumeroDeDocumentos.geraCPF());
		formColaborador.confirmarCadastro();

		try {
			formColaborador.verificaColaboradorCadastrado(colaborador);
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarColaboradorComCPFVazioTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaborador();

		try {

			formColaborador.inserirDadosColaborador(colaborador, null);
			formColaborador.verificaErro("Nao foi possivel identificar obrigatoriedade do CPF");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarColaboradorComCPFInvalidoTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaborador();

		formColaborador.inserirDadosColaboradorCPFInvalido(colaborador);
		try {
			formColaborador.verificaErro("Nao foi possivel identificar invalidez do CPF");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarColaboradorComNomeVazioTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaboradorComNomeVazio();

		formColaborador.inserirDadosColaborador(colaborador, gerarNumeroDeDocumentos.geraCPF());
		try {
			formColaborador.verificaErro("Nao foi possivel identificar obrigatoriedade do nome");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarColaboradorComNomeInvalidoTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaboradorComNomeInvalido();

		formColaborador.inserirDadosColaborador(colaborador, gerarNumeroDeDocumentos.geraCPF());
		try {
			formColaborador.verificaErro("Nao foi possivel identificar invalidez do nome");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarColaboradorComEmailVazioTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaboradorComEmailVazio();

		formColaborador.inserirDadosColaborador(colaborador, gerarNumeroDeDocumentos.geraCPF());
		try {
			formColaborador.verificaErroEmailObrigatorio("Nao foi possivel identificar obrigatoriedade do email");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarColaboradorComEmailInvalidoTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaboradorComEmailInvalido();

		formColaborador.inserirDadosColaborador(colaborador, gerarNumeroDeDocumentos.geraCPF());
		try {
			formColaborador.verificaErroEmailObrigatorio("Nao foi possivel identificar invalidez do email");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarColaboradorSemTipoTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaboradorSemTipo();

		formColaborador.inserirDadosColaborador(colaborador, gerarNumeroDeDocumentos.geraCPF());
		try {
			formColaborador.verificaErro("Nao foi possivel identificar obrigatoriedade do tipo do colaborador");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void alterarColaboradorComColaboradorLogadoTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaborador();
		Colaborador colaborador2 = DadosColaborador.gerarDadosColaborador();

		String cpf = gerarNumeroDeDocumentos.geraCPF();
		UsuarioAdmin usuario = new UsuarioAdmin(colaborador.getLogin(), cpf, cpf);

		formColaborador.inserirDadosColaborador(colaborador, cpf);
		formColaborador.confirmarCadastro();
		formColaborador.deslogar();
		formColaborador.loginAmbienteTeste(usuario, navegador);
		formColaborador.mudarNomeColaborador(colaborador, colaborador2);
		
		try {
			formColaborador.verificaNomeLogado(colaborador2);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarColaboradorComCPFExistenteTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaborador();
		Colaborador colaborador2 = DadosColaborador.gerarDadosColaborador();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formColaborador.inserirDadosColaborador(colaborador, cpf);
		formColaborador.confirmarCadastro();
		formColaborador.inserirDadosColaboradorCPFExistente(colaborador2, cpf);
		formColaborador.confirmarCadastro();

		try {
			formColaborador.verificaColaboradorCadastrado(colaborador2);
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Teste para verificar a regra de email único Issue #435
	 * {@link} https://projetos.imd.ufrn.br/plataforma-dados-clinicos/sigsaude/issues/435
	 */
	
	 @Test
	 public void cadastrarColaboradorComEmailRepetidoTest() {
		 Colaborador colaborador1 = DadosColaborador.gerarDadosColaborador();
		 Colaborador colaborador2 = DadosColaborador.gerarDadosColaborador();
		 colaborador2.getPessoa().setEmail(colaborador1.getEmail().toUpperCase());
		
		
		 formColaborador.inserirDadosColaborador(colaborador1,gerarNumeroDeDocumentos.geraCPF());
		 formColaborador.confirmarCadastro();
		 formColaborador.inserirDadosColaborador(colaborador2, gerarNumeroDeDocumentos.geraCPF());
		 try {
			 formColaborador.verificaErro("Nao foi possivel identificar mensagem de erro de email repetido");
		 } catch (FormularioException e) {
			 e.printStackTrace();
			 fail();
		 }
	 }

	/**
	 * baseado em: tentar cadastrar colaborador com cpf e logins iguais a de um
	 * colaborador inativo gera erro 500 Issue #512
	 * {@link} https://projetos.imd.ufrn.br/plataforma-dados-clinicos/sigsaude/issues/512
	 */

	@Test
	public void cadastrarColaboradorIgualAInativoTest() { //verificar se nao deve ser possivel cadastrar mesmo
		Colaborador colaborador = DadosColaborador.gerarDadosColaborador();
		String cpf = gerarNumeroDeDocumentos.geraCPF();

		formColaborador.inserirDadosColaborador(colaborador, cpf);
		formColaborador.inativarColaborador(colaborador, "justificativa");
		formColaborador.inserirDadosColaborador(colaborador, gerarNumeroDeDocumentos.geraCPF());

		try {
			formColaborador.verificaColaboradorCadastrado(colaborador);
			fail();
		} catch (FormularioException e) {
			e.printStackTrace();
		}
	}
	 
	 @Test
	 public void inativarColaboradorTest() {
		 Colaborador colaborador = DadosColaborador.gerarDadosColaborador();
		 String cpf = gerarNumeroDeDocumentos.geraCPF();
		
		 formColaborador.inserirDadosColaborador(colaborador,cpf);
		 formColaborador.inativarColaborador(colaborador,"justificativa");
		 try {
			formColaborador.verificaColaboradorCadastrado(colaborador);
			fail();
		} catch (FormularioException e) {
			e.printStackTrace();
		}
	 }
	 // comentado pois a solucao foi limitar o número de caracteres possiveis de serem digitados
//	 @Test
//	 public void inativarJustificativaGiganteColaboradorTest() {
//		 Colaborador colaborador = DadosColaborador.gerarDadosColaborador();
//		 String cpf = gerarNumeroDeDocumentos.geraCPF();
//		
//		 formColaborador.inserirDadosColaborador(colaborador,cpf);
//		 formColaborador.inativarColaborador(colaborador,GerarStringGrande.gerarString270());
//		 try {
//			formColaborador.verificaColaboradorCadastrado(colaborador);
//			fail();
//		} catch (FormularioException e) {
//			e.printStackTrace();
//		}
//	 }
	 /**
		 * baseado em: Issue #544
		 * {@link} https://projetos.imd.ufrn.br/plataforma-dados-clinicos/sigsaude/issues/544
		 */
	 
	 @Test
	 public void alterarEmailColaboradorJaExistenteTest() {
		 Colaborador colaborador = DadosColaborador.gerarDadosColaborador();
		 Colaborador colaborador2 = DadosColaborador.gerarDadosColaborador();
		 String cpf1 = gerarNumeroDeDocumentos.geraCPF();
		 String cpf2 = gerarNumeroDeDocumentos.geraCPF();
		 formColaborador.inserirDadosColaborador(colaborador, cpf1);
		 formColaborador.confirmarCadastro();
		 
		 formColaborador.inserirDadosColaborador(colaborador2, cpf2);
		 formColaborador.confirmarCadastro();
		 
		 UsuarioAdmin usuario = new UsuarioAdmin(colaborador2.getLogin(), cpf2, cpf2);

			formColaborador.deslogar();
			formColaborador.loginAmbienteTeste(usuario, navegador);
			colaborador.getPessoa().setEmail(colaborador2.getEmail());
		 formColaborador.alterarPerfilColaborador(colaborador);
		 try {
			formColaborador.verificaErro("Mensagem de email ja cadastrado nao existente");
		} catch (FormularioException e) {
			// TODO Auto-generated catch block
			fail();
			e.printStackTrace();
		}
			
	 }

}
