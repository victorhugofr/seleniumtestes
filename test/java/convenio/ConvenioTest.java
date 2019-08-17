package convenio;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.Convenio;
import dados.DadosConvenio;
import enums.Navegadores;
import formularios.FormularioCadastroConvenio;

public class ConvenioTest {
	
	static FormularioCadastroConvenio formConvenio = new FormularioCadastroConvenio();
	static Navegadores navegador = Navegadores.CHROME;
	
	@BeforeClass
	public static void login() {
		formConvenio.login(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formConvenio.logout();
	}
	
	@Test
	public void cadastrarConvenioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenio();

		formConvenio.inserirDadosConvenio(convenio);
		formConvenio.confirmarCadastro();
	}

}
