package SigSaudeAutoTestes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import agendamento.AgendamentoTest;
import colaborador.ColaboradorTest;
import convenio.ConvenioTest;
import usuarioDoServico.UsuarioDoServicoTest;

@RunWith(Suite.class)
@SuiteClasses({
	AgendamentoTest.class,
	ColaboradorTest.class,
	ConvenioTest.class,
	UsuarioDoServicoTest.class
})
public class SuiteTest {

}
