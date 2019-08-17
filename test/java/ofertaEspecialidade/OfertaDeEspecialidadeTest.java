package ofertaEspecialidade;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.OfertaEspecialidade;
import dados.DadosOfertaEspecialidade;
import enums.Navegadores;
import formularios.FormularioCadastroOfertaDeEspecialidade;

public class OfertaDeEspecialidadeTest {
	static FormularioCadastroOfertaDeEspecialidade formOfertaDeEspecialidade = new FormularioCadastroOfertaDeEspecialidade();
	static Navegadores navegador = Navegadores.PADRAO;
	
	@BeforeClass
	public static void login() {
		formOfertaDeEspecialidade.login(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formOfertaDeEspecialidade.logout();
	}
	
	@Test
	public void cadastrarOfertaDeEspecialideTest() {
		OfertaEspecialidade ofertaEspecialidade = DadosOfertaEspecialidade.gerarDadosOfertaEspecialidade(true);
		
		formOfertaDeEspecialidade.inserirOfertaDeEspecialidade(ofertaEspecialidade);
	}
}
