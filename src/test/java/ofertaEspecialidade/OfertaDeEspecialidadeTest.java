package ofertaEspecialidade;

import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import br.ufrn.imd.sigsaude.dominio.OfertaEspecialidade;
import dados.DadosOfertaEspecialidade;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroOfertaDeEspecialidade;

public class OfertaDeEspecialidadeTest {
	static FormularioCadastroOfertaDeEspecialidade formOfertaDeEspecialidade = new FormularioCadastroOfertaDeEspecialidade();
	static Navegadores navegador = Navegadores.PADRAO;

	@BeforeClass
	public static void login() {
		// formOfertaDeEspecialidade.loginAmbienteHomologacao(null, navegador);
		formOfertaDeEspecialidade.loginAmbienteTeste(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formOfertaDeEspecialidade.logout();
	}

	@Test
	public void cadastrarOfertaDeEspecialideTest() {
		String denominacao = DadosOfertaEspecialidade.gerarNomeEspecialidade();
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		Random numeroAleatorio = new Random();
		int n1 = numeroAleatorio.nextInt(10);
		if (n1 == 0 || n1 == 1) {
			n1 = n1 + 2;
		}
		try {
			formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, denominacao);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		formOfertaDeEspecialidade.inserirEspecialidade();
		formOfertaDeEspecialidade.inserirHorarioEscalaProfissional(n1);
		formOfertaDeEspecialidade.concluirCadastro();
	}

	@Test
	public void cadastrarOfertasDeEspecialidesIguaisTest() {
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		Random numeroAleatorio = new Random();
		String denominacao = DadosOfertaEspecialidade.gerarNomeEspecialidade();
		int n1 = numeroAleatorio.nextInt(10);
		if (n1 == 0 || n1 == 1) {
			n1 = n1 + 2;
		}
		try {
			formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, denominacao);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formOfertaDeEspecialidade.inserirEspecialidade();
		formOfertaDeEspecialidade.inserirHorarioEscalaProfissional(n1);
		formOfertaDeEspecialidade.concluirCadastro();

		try {
			formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, denominacao);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			formOfertaDeEspecialidade.inserirEspecialidade();
			fail();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarEspecialidadeTest() {
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		Random numeroAleatorio = new Random();
		String denominacao = DadosOfertaEspecialidade.gerarNomeEspecialidade();
		int n1 = numeroAleatorio.nextInt(10);
		if (n1 == 0 || n1 == 1) {
			n1 = n1 + 2;
		}
		try {
			formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, denominacao);
		} catch (InterruptedException e1) {
			System.out.println("entrou excessão de espera");
			e1.printStackTrace();
		}
		formOfertaDeEspecialidade.inserirEspecialidade();
		try {
			formOfertaDeEspecialidade.verificaEspecialidadeCadastrada();
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarEspecialidadeComHorariosTest() {
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		Random numeroAleatorio = new Random();
		String denominacao = DadosOfertaEspecialidade.gerarNomeEspecialidade();
		int n1 = numeroAleatorio.nextInt(10);
		if (n1 == 0 || n1 == 1) {
			n1 = n1 + 2;
		}
		try {
			formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, denominacao);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		formOfertaDeEspecialidade.inserirEspecialidade();
		formOfertaDeEspecialidade.inserirHorarioEscalaProfissional(n1);
		formOfertaDeEspecialidade.concluirCadastro();
	}

	@Test
	public void cadastrarEscalaEmOfertaTest() {
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		Random numeroAleatorio = new Random();
		String denominacao = DadosOfertaEspecialidade.gerarNomeEspecialidade();
		int n1 = numeroAleatorio.nextInt(10);
		if (n1 == 0 || n1 == 1) {
			n1 = n1 + 2;
		}
		try {
			formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, denominacao);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formOfertaDeEspecialidade.inserirEspecialidade();
		formOfertaDeEspecialidade.inserirHorarioEscalaProfissional(n1);
		formOfertaDeEspecialidade.concluirCadastro();
		try {
			formOfertaDeEspecialidade.inserirEscala(denominacao, null);
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void cadastrarOfertaSemDenominacaoTest() {
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		Random numeroAleatorio = new Random();
		int n1 = numeroAleatorio.nextInt(10);
		if (n1 == 0 || n1 == 1) {
			n1 = n1 + 2;
		}
		try {
			formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, "");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			formOfertaDeEspecialidade.verificaErro("O campo denominação deve ser preenchido");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void cadastrarMaisDeUmaVezAMesmaEspecialidadeTest() {
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		Random numeroAleatorio = new Random();
		int n1 = numeroAleatorio.nextInt(11);
		if (n1 == 0 || n1 == 1) {
			n1 = n1 + 2;
		}
		String denominacao = DadosOfertaEspecialidade.gerarNomeEspecialidade();
		try {
			formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade, n1, denominacao);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formOfertaDeEspecialidade.inserirEspecialidade();
		formOfertaDeEspecialidade.inserirEspecialidade();
		try {
			formOfertaDeEspecialidade.verificaErro("Esta especialidade já está vinculada a esta oferta.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
}
