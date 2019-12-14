package aluno;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.Aluno;
import br.ufrn.imd.sigsaude.dominio.Vinculo;
import dados.DadosAluno;
import dados.DadosVinculo;
import dados.gerarNumeroDeDocumentos;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroAluno;

public class AlunoTest {
	static FormularioCadastroAluno formAluno = new FormularioCadastroAluno();
	static Navegadores navegador = Navegadores.PADRAO;

	@BeforeClass
	public static void login() {
//		formColaborador.loginAmbienteHomologacao(null, navegador); // se alterar o ambiente, 
		formAluno.loginAmbienteTeste(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formAluno.logout();
	}

	@Test
	public void cadastrarAlunoTest() throws InterruptedException {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculo(vinculo,"GRADUAÇÃO","BIOMEDICINA","PESSOA DA GESTAO");
		formAluno.confirmarCadastro();
		
		try {
			formAluno.verificaAlunoCadastrado(aluno);
		}catch(FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemVinculo() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.confirmarCadastro();
		try {
			formAluno.verificaErroSnack("É obrigatório cadastrar pelo menos um vínculo.");
		}catch(FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemProfissionalTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculoComErros(vinculo,"GRADUAÇÃO","BIOMEDICINA",null);
		try {
			formAluno.verificaErroSnack("Profissional Responsável deve ser selecionado");
		}catch(FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoMatriculaExistente() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculo(vinculo,"GRADUAÇÃO","BIOMEDICINA","PESSOA DA GESTAO");
		formAluno.inserirVinculoComErros(vinculo,"LATO SENSU","BIOMEDICINA","PESSOA DA GESTAO");
		
		try {
			formAluno.verificaErroSnack("Já existe um vínculo com essa matrícula.");
		}catch(FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoNivelExistente() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		Vinculo vinculo2 = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculo(vinculo,"GRADUAÇÃO","BIOMEDICINA","PESSOA DA GESTAO");
		formAluno.inserirVinculoComErros(vinculo2,"GRADUAÇÃO","BIOMEDICINA","PESSOA DA GESTAO");
		
		try {
			formAluno.verificaErroSnack("Já existe um vínculo com esse nível para esse aluno.");
		}catch(FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemMatricula() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculoSemMatricula();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculoComErros(vinculo,"GRADUAÇÃO","BIOMEDICINA","PESSOA DA GESTAO");
		
		try {
			formAluno.verificaErroSnack("Matrícula precisa ser informada");
		}catch(FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemNivel() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculoComErros(vinculo,null,"BIOMEDICINA","PESSOA DA GESTAO");
		try {
			formAluno.verificaErroSnack("Nível precisa ser selecionado");
		}catch(FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemCurso() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculoComErros(vinculo,"GRADUAÇÃO",null,"PESSOA DA GESTAO");
		try {
			formAluno.verificaErroSnack("Curso precisa ser selecionado");
		}catch(FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoEmailExistente() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculo(vinculo,"GRADUAÇÃO","BIOMEDICINA","PESSOA DA GESTAO");
		formAluno.confirmarCadastro();
		
		Aluno aluno2 = DadosAluno.gerarDadosAluno();
		String cpf2 = gerarNumeroDeDocumentos.geraCPF();
		aluno2.getUsuario().getPessoa().setEmail(aluno.getUsuario().getPessoa().getEmail());
		formAluno.inserirDadosAluno(aluno2,cpf2);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno2.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Já existe um aluno com esse e-mail.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void cadastrarAlunoSemNomeTest() {
		Aluno aluno = DadosAluno.gerarAlunoNomeVazio();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Nome precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoNomeInvalido() {
		
	}
	
	@Test
	public void cadastrarAlunoSemCpfTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		formAluno.inserirDadosAluno(aluno,"");
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("CPF precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoCpfInvalidoTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		formAluno.inserirDadosAluno(aluno,"22122322123");
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("O CPF informado é inválido.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoCpfExistente() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculo(vinculo,"GRADUAÇÃO","BIOMEDICINA","PESSOA DA GESTAO");
		formAluno.confirmarCadastro();
		
		Aluno aluno2 = DadosAluno.gerarDadosAluno();
		formAluno.inserirDadosAluno(aluno2,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno2.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Já existe uma pessoa com o CPF informado!");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void cadastrarAlunoSemDataNascimentoTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAlunoSemDataNascimento(aluno,cpf);
		try {
			formAluno.verificaErroSnack("Data de nascimento precisa ser informada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemSexoTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		aluno.getUsuario().getPessoa().setSexo("");
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Sexo precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void cadastrarAlunoTelefoneVazioTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno("");
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Telefone precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoTelefoneCom1CaractereTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno("1");
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Telefone deve ter no mínimo 8 caracteres");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoTelefoneSemTipoTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAlunoSemTipo(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Tipo de telefone precisa ser selecionado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemEmailTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		aluno.getUsuario().getPessoa().setEmail("");
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("E-mail precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoEmailInvalido() {
		
	}
	
	@Test
	public void cadastrarAlunoSemLoginTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		aluno.getUsuario().setLogin("");;
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Login precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoLoginExistente() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		Vinculo vinculo = DadosVinculo.gerarDadosVinculo();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		formAluno.inserirVinculo(vinculo,"GRADUAÇÃO","BIOMEDICINA","PESSOA DA GESTAO");
		formAluno.confirmarCadastro();
		
		Aluno aluno2 = DadosAluno.gerarDadosAluno();
		String cpf2 = gerarNumeroDeDocumentos.geraCPF();
		aluno2.getUsuario().setLogin(aluno.getUsuario().getLogin());
		formAluno.inserirDadosAluno(aluno2,cpf2);
		formAluno.inserirEnderecoNomadeAluno("referencia");
		formAluno.inserirTelefoneAluno(aluno2.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Já existe um aluno com esse login.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
		
	}

	@Test
	public void cadastrarAlunoNomadeSemReferenciaTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoNomadeAluno("");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Um ponto de referência precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemCEPTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoAluno("","Rua 3","2","tirol","brasil","AC","Acrelândia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("CEP precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemLogradouroTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoAluno("59014560","","2","tirol","brasil","AC","Acrelândia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Logradouro precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemNumeroTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoAluno("59014560","Rua 3","","tirol","brasil","AC","Acrelândia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Número do endereço precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemBairroTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoAluno("59014560","Rua 3","2","","brasil","AC","Acrelândia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Bairro precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemPaisTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoAluno("59014560","Rua 3","2","tirol","","AC","Acrelândia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("País precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemUFTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoAluno("59014560","Rua 3","2","tirol","brasil",null,"Acrelândia");
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("UF precisa ser selecionada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarAlunoSemMunicipioTest() {
		Aluno aluno = DadosAluno.gerarDadosAluno();
		String cpf = gerarNumeroDeDocumentos.geraCPF();
		formAluno.inserirDadosAluno(aluno,cpf);
		formAluno.inserirEnderecoAluno("59014560","Rua 3","2","tirol","brasil","AC",null);
		formAluno.inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		formAluno.continuarCadastro();
		try {
			formAluno.verificaErroSnack("Município precisa ser selecionado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
}
