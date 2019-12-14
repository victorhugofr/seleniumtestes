package dados;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import br.ufrn.imd.sigsaude.dominio.Procedimento;
import br.ufrn.imd.sigsaude.dominio.enums.OrigemProcedimento;

/**
 * Classe responspavel para gerar os dados dos procedimentos que serao utilizados
 * durante as etapas do cadastro.
 * @author Jancy Aragao
 *
 */

public class DadosProcedimento {
	
	public static Procedimento gerarDadosProcedimento(boolean sigtap) {
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);
        String procedimentoValor = GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000));
        
        Random gerador = new Random();
        String codigo = "";
        for (int i = 0; i < 15; i++) {
        	codigo = codigo +  Integer.toString(gerador.nextInt());
        }
        
		Procedimento procedimento = new Procedimento();
		
		procedimento.setCodigo(codigo);
		procedimento.setDenominacao("Procedimento Selenium  " + procedimentoValor + " " + dataAtual );
		
		if (sigtap) {
			procedimento.setOrigem(OrigemProcedimento.SIGTAP);
		} else {
			procedimento.setOrigem(OrigemProcedimento.LOCAL);
		}
		
		int data = new Random().nextInt(100) + 1;		
		LocalDate dataCompetencia = LocalDate.now(ZoneId.of("America/Fortaleza")).plusDays(data);
		Date dataCompetenciaProcedimento = Date.from(dataCompetencia.atStartOfDay(ZoneId.systemDefault()).toInstant());
		procedimento.setDataDeCompetencia(dataCompetenciaProcedimento);
		
		procedimento.setJustificativa("Justificativa Selenium " + procedimentoValor + " " + dataAtual);
		
//		procedimento.setDuracao((int) Math.round(Math.random() * 1000));
		
		return procedimento;
	}
	
	public static Procedimento gerarDadosProcedimentoCodigoVazio() {
		Procedimento procedimento = gerarDadosProcedimento(new Random().nextBoolean());
		
		procedimento.setCodigo("");
		
		return procedimento;
	}
	
	public static Procedimento gerarDadosProcedimentoCodigoInvalido() {
		Procedimento procedimento = gerarDadosProcedimento(new Random().nextBoolean());
		
		procedimento.setCodigo(GerarStringInvalida.gerarStringAleatoria());
		
		return procedimento;
	}
	
	public static Procedimento gerarDadosProcedimentoDenominacaoVazia() {
		Procedimento procedimento = gerarDadosProcedimento(new Random().nextBoolean());

		procedimento.setDenominacao("");
		
		return procedimento;
	}
	
	public static Procedimento gerarDadosProcedimentoDenominacaoInvalida() {
		Procedimento procedimento = gerarDadosProcedimento(new Random().nextBoolean());
		
		procedimento.setDenominacao("Mussum Ipsum cacilds vidis litro abertis Sapien in monti palavris qui "
				+ "num significa nadis i pareci latim Viva Forevis aptent taciti sociosqu ad litora torquent "
				+ "Suco de cevadiss deixa as pessoas mais interessantis Não sou faixa preta cumpadi "
				+ "sou preto inteiris inteiris Interessantiss quisso pudia ce receita de bolis "
				+ "mais bolis eu num gostis Quem manda na minha terra sou euzis Nullam volutpat risus "
				+ "nec leo commodo ut interdum diam laoreet Sed non consequat odio Per aumento de cachacis "
				+ "eu reclamis");
		
		return procedimento;
	}
	
	public static Procedimento gerarDadosProcedimentoJustificativaVazia() {
		Procedimento procedimento = gerarDadosProcedimento(new Random().nextBoolean());
		
		procedimento.setJustificativa("");
		
		return procedimento;
	}
	
	public static Procedimento gerarDadosProcedimentoJustificativaMuitoGrande() {
		Procedimento procedimento = gerarDadosProcedimento(new Random().nextBoolean());
		
		procedimento.setJustificativa("Mussum Ipsum cacilds vidis litro abertis Sapien in monti palavris qui "
				+ "num significa nadis i pareci latim Viva Forevis aptent taciti sociosqu ad litora torquent "
				+ "Suco de cevadiss deixa as pessoas mais interessantis Não sou faixa preta cumpadi "
				+ "sou preto inteiris inteiris Interessantiss quisso pudia ce receita de bolis "
				+ "mais bolis eu num gostis Quem manda na minha terra sou euzis Nullam volutpat risus "
				+ "nec leo commodo ut interdum diam laoreet Sed non consequat odio Per aumento de cachacis "
				+ "eu reclamis");
		
		return procedimento;
	}
	
//	public static Procedimento gerarDadosProcedimentoDuracaoInvalido() {
//		Procedimento procedimento = gerarDadosProcedimento();
//		
//		procedimento.setDuracao(-12);
//		
//		return procedimento;
//	}
//	
//	public static Procedimento gerarDadosProcedimentoDuracaoVazio() {
//		Procedimento procedimento = gerarDadosProcedimento();
//		
//		procedimento.setDuracao(0);
//		
//		return procedimento;
//	}
	
}