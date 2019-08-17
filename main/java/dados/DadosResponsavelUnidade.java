package dados;

import br.ufrn.imd.sigsaude.dominio.ResponsavelUnidade;

public class DadosResponsavelUnidade {

	public static ResponsavelUnidade gerarDadosResponsavelUnidade() {
		ResponsavelUnidade responsavelUnidade = new ResponsavelUnidade();
		responsavelUnidade.setColaborador(DadosColaborador.gerarDadosColaborador());
		//responsavelUnidade.setUnidade(DadosUnidade.gerarDadosUnidade());
		return responsavelUnidade;
	}

}
