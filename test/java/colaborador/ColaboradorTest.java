package colaborador;


import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.Colaborador;
import dados.DadosColaborador;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroColaborador;

public class ColaboradorTest {

	static FormularioCadastroColaborador formColaborador = new FormularioCadastroColaborador();
	static Navegadores navegador = Navegadores.PADRAO;
	
	@BeforeClass
	public static void login() {
		formColaborador.login(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formColaborador.logout();
	}
	
	@Test
	public void cadastrarColaboradorTest() {
		Colaborador colaborador = DadosColaborador.gerarDadosColaborador();	
		
		formColaborador.inserirDadosColaborador(colaborador,true);
		formColaborador.confirmarCadastro();
	}
	
	@Test
	public void cadastrarColaboradorComCPFVazioTest() {
		
		try {
			Colaborador colaborador = DadosColaborador.gerarDadosColaborador();	
			
			formColaborador.inserirDadosColaborador(colaborador,false);
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
		
		formColaborador.inserirDadosColaborador(colaborador,true);
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
		
		formColaborador.inserirDadosColaborador(colaborador,true);
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
		
		formColaborador.inserirDadosColaborador(colaborador,true);
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
		
		formColaborador.inserirDadosColaborador(colaborador,true);
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
		
		formColaborador.inserirDadosColaborador(colaborador,true);
		try {
			formColaborador.verificaErro("Nao foi possivel identificar obrigatoriedade do tipo do colaborador");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
}
