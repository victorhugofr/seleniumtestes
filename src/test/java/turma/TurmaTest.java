package turma;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufrn.imd.sigsaude.dominio.Colaborador;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import br.ufrn.imd.sigsaude.dominio.Turma;
import dados.DadosTurma;
import enums.Navegadores;
import exceptions.FormularioException;
import formularios.FormularioCadastroTurma;

public class TurmaTest {
	static FormularioCadastroTurma formTurma = new FormularioCadastroTurma();
	static Navegadores navegador = Navegadores.PADRAO;
	private String ofertaTurma = "testt - 20/11/2019 - 31/05/2020";// SETUP HOMOLOGACAO
//	private String ofertaTurma = "oferta chocada 03 - 08/10/2019 - 31/10/2019";//SETUP TESTES
	
	private String profissionalTurma = "PESSOA DA GESTAO";// SETUP HOMOLOGACAO
	private String alunoNome1 = "CLECIA FREIRE";
	private String alunoMatricula1 = "123";
	private String alunoCurso1 = "BIOMEDICINA";
	private String alunoNome2 = "VICTOR HUGO FREIRE RAMALHO";
	private String alunoMatricula2 = "1234";
	private String alunoCurso2 = "BIOMEDICINA";
	
	@BeforeClass
	public static void login() {
		formTurma.loginAmbienteHomologacao(null, navegador);
//		formTurma.loginAmbienteTeste(null, navegador);
	}

	@AfterClass
	public static void logout() {
		formTurma.logout();
	}
	
	@Test
	public void cadastrarTurmaSemNome() {
		Turma turma=DadosTurma.gerarDadosTurmaSemNome();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		try {
			formTurma.verificaErroSnack("Nome precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarTurmaSemComponenteCurricular() {
		Turma turma=DadosTurma.gerarDadosTurmaSemCodigoComponente();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		try {
			formTurma.verificaErroSnack("O código do componente curricular precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarTurmaSemCodigo() {
		Turma turma=DadosTurma.gerarDadosTurmaSemCodigoTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		try {
			formTurma.verificaErroSnack("O código da turma precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarTurmaSemPeriodo() {
		Turma turma=DadosTurma.gerarDadosTurmaSemPeriodo();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		try {
			formTurma.verificaErroSnack("O período de oferta da turma precisa ser informado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarTurmaSemCapacidade() {
		Turma turma=DadosTurma.gerarDadosTurmaSemCapacidade();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		try {
			formTurma.verificaErroSnack("A capacidade de alunos que a turma comporta precisa ser informada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarTurmaSemOferta() {
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,null,profissionalTurma);
		formTurma.continuarCadastro();
		
		try {
			formTurma.verificaErroSnack("A oferta de especialidade ligada à turma precisa ser selecionada");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarTurmaSemProfissional() {
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,null);
		formTurma.continuarCadastro();
		
		try {
			formTurma.verificaErroSnack("O profissional responsável pela turma precisa ser selecionado");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarTurmaCompleto() {
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		
		formTurma.inserirAluno(alunoNome1,alunoMatricula1,alunoCurso1);
		formTurma.concluirCadastro();
		formTurma.verificaCadastro();
	}
	
	@Test
	public void cadastrarTurmaMaisAlunosQueACapacidade() {
		Turma turma=DadosTurma.gerarDadosTurma();
		turma.setCapacidade(1);
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		
		formTurma.inserirAluno(alunoNome1,alunoMatricula1,alunoCurso1);
		formTurma.inserirAluno(alunoNome2,alunoMatricula2,alunoCurso2);
		
		try {
			formTurma.verificaErroSnack("A capacidade da turma está preenchida.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
//	@Test
//	public void alterarResponsavelPrincipal() {
//		Turma turma=DadosTurma.gerarDadosTurma();
//		formTurma.inserirDadosTurma(turma,"oferta chocada 03 - 08/10/2019 - 31/10/2019","ALTERAID");
//		formTurma.continuarCadastro();
//		
//		formTurma.inserirAluno("COLABORADOR TESTE","2019000001","BIOMEDICINA");
//		formTurma.concluirCadastro();
//		formTurma.verificaCadastro();
//		
//		esperar implementação de filtragem na listagem de turmas
//	}
	
	@Test
	public void adicionarMaisDeUmProfissional() {
		Pessoa pessoa = new Pessoa();
		Colaborador colaborador = new Colaborador();
		colaborador.setPessoa(pessoa);
		colaborador.getPessoa().setNome("ANGIOLOGISTA");
		ProfissionalSaude profissional = new ProfissionalSaude();
		profissional.setColaborador(colaborador);
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		
		formTurma.inserirProfissional(profissional);
		
		try {
			formTurma.verificaErroSnack("Profissional de Saúde adicionado com sucesso na turma.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
//	@Test
//	public void alterarOfertaDeEspecialidade() {
//		
//	}
//	esperar implementação de filtragem na listagem de turmas
	@Test
	public void cadastrarMaisDeUmaVezOMesmoAluno() {
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		
		formTurma.inserirAluno(alunoNome1,alunoMatricula1,alunoCurso1);
		formTurma.inserirAluno(alunoNome1,alunoMatricula1,alunoCurso1);
		
		try {
			formTurma.verificaErroSnack("Já existe um vínculo deste aluno nesta turma.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarMaisDeUmaVezOMesmoProfissional() {
		Pessoa pessoa = new Pessoa();
		Colaborador colaborador = new Colaborador();
		colaborador.setPessoa(pessoa);
		colaborador.getPessoa().setNome("ANGIOLOGISTA");
		ProfissionalSaude profissional = new ProfissionalSaude();
		profissional.setColaborador(colaborador);
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		
		formTurma.inserirProfissional(profissional);
		formTurma.inserirProfissional(profissional);
		try {
			formTurma.verificaErroSnack("Este profissional já está nesta turma.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void removerAluno() {
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		
		formTurma.inserirAluno(alunoNome1,alunoMatricula1,alunoCurso1);
		formTurma.removerAluno();
		try {
			formTurma.verificaErroSnack("Vínculo de Aluno removido com sucesso da turma.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void cadastrarTurmaSemAlunos() {
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		
		formTurma.inserirAluno(alunoNome1,alunoMatricula1,alunoCurso1);
		formTurma.removerAluno();
		formTurma.concluirCadastro();
		try {
			formTurma.verificaErroSnack("É necessário informar pelo menos um aluno.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void removerResponsavelPrincipal() {
		Turma turma=DadosTurma.gerarDadosTurma();
		formTurma.inserirDadosTurma(turma,ofertaTurma,profissionalTurma);
		formTurma.continuarCadastro();
		formTurma.removerResponsavel();
		try {
			formTurma.verificaErroSnack("O profissional de saúde responsável pela turma não pode ser retirado dela.");
		} catch (FormularioException e) {
			e.printStackTrace();
			fail();
		}
	}
	
}
