package profissionalDeSaude;


import static org.junit.Assert.fail;

import org.junit.Test;

import SigSaudeAutoTestes.Login;
import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import dados.DadosProfissionalDeSaude;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroProfissionalDeSaude;

public class ProfissionalDeSaudeTest {
	Login login = new Login();
	FormularioCadastroProfissionalDeSaude formProfissionalDeSaude = new FormularioCadastroProfissionalDeSaude();
	Navegadores navegador = Navegadores.PADRAO;
	
	@Test
	public void cadastrarProfissionalDeSaudeTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();
		
		formProfissionalDeSaude.login(null, navegador);
		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
		formProfissionalDeSaude.confirmarCadastro();
		formProfissionalDeSaude.logout();
	    
	}
	
	@Test
	public void cadastrarProfissionalDeSaudeSemCPFTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();
		
		formProfissionalDeSaude.login(null, navegador);
		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,false);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do cpf nao encontrada");
			formProfissionalDeSaude.logout();
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void cadastrarProfissionalDeSaudeSemIdProfissional() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComIdentidadeProfissionalVazia();
		
		formProfissionalDeSaude.login(null, navegador);
		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
		try {
			formProfissionalDeSaude.verificaErroObrigatorio("Mensagem de obrigatoriedade do idprofissional nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		formProfissionalDeSaude.logout();
	}
	
	/*Rever se isso deve ser implementado ou não*/
//	@Test
//	public void cadastrarProfissionalDeSaudeIdProfissionalInvalida() {
//		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComIdentidadeProfissionalInvalida();
//		
//		formProfissionalDeSaude.login(null, Navegadores.PHANTON_JS);
//		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
//		formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do idprofissional nao encontrada");
//		formProfissionalDeSaude.logout();
//	}
	
	@Test
	public void cadastrarProfissionalDeSaudeSemEmail() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComEmailVazio();
		
		formProfissionalDeSaude.login(null, navegador);
		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do email nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		formProfissionalDeSaude.logout();
	}
	/*rever forma com que é avisado*/
//	@Test
//	public void cadastrarProfissionalDeSaudeEmailInvalido() {
//		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComEmailInvalido();
//		
//		formProfissionalDeSaude.login(null, Navegadores.PHANTON_JS);
//		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
//		formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do email nao encontrada");
//		formProfissionalDeSaude.logout();
//	}
	
	
	@Test
	public void cadastrarProfissionalDeSaudeSemLogin() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComLoginVazio();
		
		formProfissionalDeSaude.login(null, navegador);
		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
		try {
			formProfissionalDeSaude.verificaErroObrigatorio("Mensagem de obrigatoriedade do login nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		formProfissionalDeSaude.logout();
	}
	
	@Test
	public void cadastrarProfissionalDeSaudeSemEspecialidade() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();
		profissionalSaude.setEspecialidade(null);
		
		formProfissionalDeSaude.login(null, navegador);
		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
		try {
			formProfissionalDeSaude.verificaErroObrigatorio("Mensagem de obrigatoriedade da especialidade nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		formProfissionalDeSaude.logout();
	}
	
	@Test
	public void cadastrarProfissionalDeSaudeComNomeVazioTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComNomeVazio();
			
		formProfissionalDeSaude.login(null, navegador);
		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do nome nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		formProfissionalDeSaude.logout();
	}
	
	@Test
	public void cadastrarProfissionalDeSaudeComNomeInvalidoTest() {
		ProfissionalSaude profissionalSaude = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaudeComNomeInvalido();
			
		formProfissionalDeSaude.login(null, navegador);
		formProfissionalDeSaude.inserirDadosProfissionalDeSaude(profissionalSaude,true);
		try {
			formProfissionalDeSaude.verificaErro("Mensagem de obrigatoriedade do nome nao encontrada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		formProfissionalDeSaude.logout();
	}
	
}
