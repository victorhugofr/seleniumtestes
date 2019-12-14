package convenio;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.Convenio;
import dados.DadosConvenio;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroConvenio;

public class ConvenioTest {

	static FormularioCadastroConvenio formConvenio = new FormularioCadastroConvenio();
	static Navegadores navegador = Navegadores.CHROME;

	@BeforeClass
	public static void login() {
//		formConvenio.loginAmbienteHomologacao(null, navegador);
		formConvenio.loginAmbienteTeste(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formConvenio.logout();
	}

	@Test
	public void cadastrarConvenioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenio();

		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		formConvenio.confirmarCadastro();
	}
	
	@Test
	public void cadastrarConvenioObservacaoCaractereEspecial() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioObservacaoCaractereEspecial();

		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		formConvenio.confirmarCadastro();
	}
	
	@Test
	public void editarConvenioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenio();
		Convenio convenio2 = DadosConvenio.gerarDadosConvenio();

		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		formConvenio.confirmarCadastro();
		
		formConvenio.alterarDadosConvenio(convenio,true);
		formConvenio.inserirEnderecoConvenio(convenio2);
		formConvenio.inserirContatoConvenio(convenio2);
		formConvenio.salvarConvenio();
		formConvenio.confirmarCadastro();
	}

	@Test
	public void cadastrarConvenioNomeVazioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComNomeVazio();

		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
//		formConvenio.confirmarCadastro();
		try {
			formConvenio.verificaErro("Mensagem de nome obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioNomeInvalidoTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComNomeInvalido();

		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de nome invalido não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioEmailVazioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComEmailVazio();

		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de email obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioSemArquivoTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenio();

		formConvenio.inserirDadosConvenio(convenio, false);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de arquivo obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioComCEPVazioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComCEPVazio();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de cep obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

//	@Test
//	public void cadastrarConvenioComCEPInvalidoTest() {
//		Convenio convenio = DadosConvenio.gerarDadosConvenioComCEPInvalido();
//		formConvenio.inserirDadosConvenio(convenio, true);
//		formConvenio.inserirEnderecoConvenio(convenio);
//		formConvenio.inserirContatoConvenio(convenio);
//		formConvenio.salvarConvenio();
//		try {
//			formConvenio.verificaErro("Mensagem de cep invalido não mostrada");
//		} catch (FormularioException e) {
//			fail();
//			e.printStackTrace();
//		}
//	}

	@Test
	public void cadastrarConvenioComLogradouroVazioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComLogradouroVazio();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de logradouro obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

//	@Test
//	public void cadastrarConvenioComLogradouroInvalidoTest() {
//		Convenio convenio = DadosConvenio.gerarDadosConvenioComLogradouroInvalido();
//		formConvenio.inserirDadosConvenio(convenio, true);
//		formConvenio.inserirEnderecoConvenio(convenio);
//		formConvenio.inserirContatoConvenio(convenio);
//		formConvenio.salvarConvenio();
//		try {
//			formConvenio.verificaErro("Mensagem de logradouro inválido não mostrada");
//		} catch (FormularioException e) {
//			fail();
//			e.printStackTrace();
//		}
//	}

	@Test
	public void cadastrarConvenioComNumeroVazioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComNumeroVazio();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de numero obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioComNumeroInvalidoTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComNumeroInvalido();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de numero invalido não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioSemUFTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenio();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenioSemUF(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de uf obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioSemMunicipioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenio();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenioSemMunicipio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de municipio obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioComBairroVazioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComBairroVazio();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de bairro obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

//	@Test
//	public void cadastrarConvenioComBairroInvalidoTest() {
//		Convenio convenio = DadosConvenio.gerarDadosConvenioComBairroInvalido();
//		formConvenio.inserirDadosConvenio(convenio, true);
//		formConvenio.inserirEnderecoConvenio(convenio);
//		formConvenio.inserirContatoConvenio(convenio);
//		formConvenio.salvarConvenio();
//		try {
//			formConvenio.verificaErro("Mensagem de bairro invalido não mostrada");
//		} catch (FormularioException e) {
//			fail();
//			e.printStackTrace();
//		}
//	}

	@Test
	public void cadastrarConvenioComTelefoneVazioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComTelefoneVazio();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de telefone obrigatório não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioComTelefoneInvalidoTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComTelefoneInvalido();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		try {
			formConvenio.verificaErro("Mensagem de telefone invalido não mostrada");
		} catch (FormularioException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarConvenioComObservacaoVaziaTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenioComObservacaoVazia();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		formConvenio.confirmarCadastro();
	}
	
	@Test
	public void inativarConvenioTest() {
		Convenio convenio = DadosConvenio.gerarDadosConvenio();
		formConvenio.inserirDadosConvenio(convenio, true);
		formConvenio.inserirEnderecoConvenio(convenio);
		formConvenio.inserirContatoConvenio(convenio);
		formConvenio.salvarConvenio();
		formConvenio.confirmarCadastro();
		formConvenio.inativarConvenio(convenio,"Justificativa");
	}
	 // comentado pois a solucao foi limitar o número de caracteres possiveis de serem digitados
//	@Test
//	public void inativarConvenioJustificativaGiganteTest() {
//		Convenio convenio = DadosConvenio.gerarDadosConvenio();
//		formConvenio.inserirDadosConvenio(convenio, true);
//		formConvenio.inserirEnderecoConvenio(convenio);
//		formConvenio.inserirContatoConvenio(convenio);
//		formConvenio.salvarConvenio();
//		formConvenio.confirmarCadastro();
//		formConvenio.inativarConvenio(convenio,GerarStringGrande.gerarString270());
//	}
}
