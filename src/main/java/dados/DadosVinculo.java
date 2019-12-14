package dados;

import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import br.ufrn.imd.sigsaude.dominio.Vinculo;

public class DadosVinculo {
	public static Vinculo gerarDadosVinculo() {
		Vinculo vinculo = new Vinculo();
		ProfissionalSaude profissionalResponsavel = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();
		vinculo.setMatricula(DadosAluno.gerarMatriculaAluno());
		vinculo.setProfissionalResponsavel(profissionalResponsavel);
		return vinculo;
	}
	
	public static Vinculo gerarDadosVinculoSemMatricula() {
		Vinculo vinculo = new Vinculo();
		ProfissionalSaude profissionalResponsavel = DadosProfissionalDeSaude.gerarDadosProfissionalDeSaude();
		vinculo.setMatricula("");
		vinculo.setProfissionalResponsavel(profissionalResponsavel);
		return vinculo;
	}
}
