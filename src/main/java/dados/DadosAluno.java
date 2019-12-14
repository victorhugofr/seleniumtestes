package dados;

import br.ufrn.imd.sigsaude.dominio.Aluno;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.Usuario;

public class DadosAluno {
	public static Aluno gerarDadosAluno() {
		Aluno aluno = new Aluno();
		Usuario usuario = new Usuario();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
	
		usuario.setPessoa(pessoa);
		
		usuario.setLogin(GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 3000)) + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000)));
		aluno.setUsuario(usuario);
		return aluno;
	}
	
	public static Aluno gerarAlunoNomeVazio() {
		Aluno aluno = gerarDadosAluno();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoaComNomeVazio(false, true);
		aluno.getUsuario().setPessoa(pessoa);
		return aluno;
	}
	
	public static String gerarMatriculaAluno() {
		return  gerarNumeroDeDocumentos.geraCNPJ();
	}
}
