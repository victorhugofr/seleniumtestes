package agendamento;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroAgendamento;

public class AgendamentoTest {
	
	static FormularioCadastroAgendamento formAgendamento = new FormularioCadastroAgendamento();
	static Navegadores navegador = Navegadores.PADRAO;

	@BeforeClass
	public static void login() {
		formAgendamento.login(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formAgendamento.logout();
	}
	
	@Test
	public void agendarNormalmente() {
		
		try {
			formAgendamento.inserirAgendamento();
			formAgendamento.verificaAgendamento();
			formAgendamento.cancelarAgendamento();
			formAgendamento.verificaCancelamento();
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void agendarMesmaHoraEDiaJaAgendado() {
		
		try {
			formAgendamento.inserirAgendamentoMesmaHoraEDia();
			formAgendamento.verificaErro("O Usuário do Serviço já possui um agendamento cadastrado para o horário escolhido.");
			formAgendamento.cancelarAgendamento();
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
}
