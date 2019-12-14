package dados;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.ufrn.imd.sigsaude.dominio.Contato;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.Responsavel;
import br.ufrn.imd.sigsaude.dominio.enums.TipoResponsavel;

public class DadosResponsavel {

	public static Responsavel gerarDadosResponsavel(Pessoa pessoa, boolean isProprioResponsavel) {

		Responsavel responsavel = new Responsavel();
		responsavel.setContatos(DadosContato.gerarDadosContatoAsList(pessoa));
		
		int relacao = (int) Math.round(Math.random() * 7);
		if (relacao == 0) {
			responsavel.setRelacao(TipoResponsavel.AMIGO);
		} else if (relacao == 1) {
			responsavel.setRelacao(TipoResponsavel.ESPOSA);
		} else if (relacao == 2) {
			responsavel.setRelacao(TipoResponsavel.FAMILIAR);
		} else if (relacao == 3) {
			responsavel.setRelacao(TipoResponsavel.MARIDO);
		} else if (relacao == 4) {
			responsavel.setRelacao(TipoResponsavel.NAMORADO);
		} else if (relacao == 5) {
			responsavel.setRelacao(TipoResponsavel.OUTRO);
		} else {
			responsavel.setRelacao(TipoResponsavel.RESPONSAVEL_LEGAL);
		}
		responsavel.setProprioResponsavel(isProprioResponsavel);


		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += calendario.getDisplayName(Calendar.MONTH, Calendar.SHORT, localizacao).toUpperCase();
		responsavel.setNome("Responsável " + dataAtual);
		responsavel.setRg(gerarNumeroDeDocumentos.geraRG());
		responsavel.setOrgaoExpedidor("SSP");
		//responsavel.setProprioResponsavel(new Random().nextBoolean());
		responsavel.setEmail("dev@sigsaude.co");
		
		return responsavel;
	}
	
	public static Responsavel gerarDadosResponsavelVazio(Pessoa pessoa) {
		
		Responsavel responsavel = DadosResponsavel.gerarDadosResponsavel(pessoa, false);
		
		responsavel.setNome("");
		responsavel.setOrgaoExpedidor("");
		responsavel.setRg("");
		Contato contato = DadosContato.gerarDadosContatoVazioOuInvalido(true);
		List<Contato> contatos = new ArrayList<>();
		contatos.add(contato);
		responsavel.setContatos(contatos);
		
		return responsavel;
		
	}
	
}