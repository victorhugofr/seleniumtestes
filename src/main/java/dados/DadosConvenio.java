package dados;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.ufrn.imd.sigsaude.dominio.Convenio;

public class DadosConvenio {

	public static Convenio gerarDadosConvenio() {
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);
		
		LocalDate dataHoje = LocalDate.now(ZoneId.of("America/Fortaleza"));
		Date dataDeHoje = Date.from(dataHoje.atStartOfDay(ZoneId.systemDefault()).toInstant());
		LocalDate dataFimVigencia = LocalDate.now(ZoneId.of("America/Fortaleza")).plusDays(360);
		Date dataFinalVigencia = Date.from(dataFimVigencia.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Convenio convenio = new Convenio();
		convenio.setNome("Convênio " + 
				GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000)) +GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 10))+ " " + dataAtual);
		convenio.setEmail("convenio@teste.com");
		convenio.setInicioVigencia(dataDeHoje);
		convenio.setFinalVigencia(dataFinalVigencia);
		convenio.setEndereco(DadosEndereco.gerarDadosEndereco());
		convenio.setContato(DadosContato.gerarDadosContato());

		return convenio;
	}

	public static Convenio gerarDadosConvenioObservacaoCaractereEspecial() {
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);
		
		LocalDate dataHoje = LocalDate.now(ZoneId.of("America/Fortaleza"));
		Date dataDeHoje = Date.from(dataHoje.atStartOfDay(ZoneId.systemDefault()).toInstant());
		LocalDate dataFimVigencia = LocalDate.now(ZoneId.of("America/Fortaleza")).plusDays(360);
		Date dataFinalVigencia = Date.from(dataFimVigencia.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Convenio convenio = new Convenio();
		convenio.setNome("Convênio " + 
				GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000)) +GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 10))+ " " + dataAtual);
		convenio.setEmail("convenio@teste.com");
		convenio.setInicioVigencia(dataDeHoje);
		convenio.setFinalVigencia(dataFinalVigencia);
		convenio.setEndereco(DadosEndereco.gerarDadosEndereco());
		convenio.setContato(DadosContato.gerarDadosContatoObservacaoCaractereEspecial());

		return convenio;
	}
	
	public static Convenio gerarDadosConvenioComNomeInvalido() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.setNome(GerarStringInvalida.gerarStringAleatoria());
		
		return convenio;
	}

	public static Convenio gerarDadosConvenioComNomeVazio() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.setNome("");
		
		return convenio;
	}

	public static Convenio gerarDadosConvenioComEmailVazio() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.setEmail("");
		
		return convenio;
	}
	
	public static Convenio gerarDadosConveniocomDataInicialAnteriorAHoje() {
		Convenio convenio = gerarDadosConvenio();
		
		LocalDate dataHoje = LocalDate.now(ZoneId.of("America/Fortaleza"));
		Date dataDeHoje = Date.from(dataHoje.atStartOfDay(ZoneId.systemDefault()).minusDays(30).toInstant());
		LocalDate dataFimVigencia = LocalDate.now(ZoneId.of("America/Fortaleza")).plusDays(360);
		Date dataFinalVigencia = Date.from(dataFimVigencia.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		convenio.setInicioVigencia(dataDeHoje);
		convenio.setFinalVigencia(dataFinalVigencia);
		
		return convenio;
	}
	
	public static Convenio gerarDadosConveniocomDataFinalAnteriorAInicial() {
		Convenio convenio = gerarDadosConvenio();
		
		LocalDate dataHoje = LocalDate.now(ZoneId.of("America/Fortaleza"));
		Date dataDeHoje = Date.from(dataHoje.atStartOfDay(ZoneId.systemDefault()).toInstant());
		LocalDate dataFimVigencia = LocalDate.now(ZoneId.of("America/Fortaleza")).minusDays(360);
		Date dataFinalVigencia = Date.from(dataFimVigencia.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		convenio.setInicioVigencia(dataDeHoje);
		convenio.setFinalVigencia(dataFinalVigencia);
		
		return convenio;
	}

	public static Convenio gerarDadosConvenioComCEPVazio() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getEndereco().setCep("");
		
		return convenio;
	}
	
	public static Convenio gerarDadosConvenioComCEPInvalido() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getEndereco().setCep("55555555");
		
		return convenio;
	}

	public static Convenio gerarDadosConvenioComLogradouroVazio() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getEndereco().setLogradouro("");
		
		return convenio;
	}
	
	public static Convenio gerarDadosConvenioComLogradouroInvalido() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getEndereco().setLogradouro(GerarStringInvalida.gerarStringAleatoria());
		
		return convenio;
	}

	public static Convenio gerarDadosConvenioComNumeroVazio() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getEndereco().setNumero("");
		
		return convenio;
	}
	
	public static Convenio gerarDadosConvenioComNumeroInvalido() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getEndereco().setNumero(GerarStringInvalida.gerarStringAleatoria());
		
		return convenio;
	}
	
	public static Convenio gerarDadosConvenioComBairroVazio() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getEndereco().setBairro("");
		
		return convenio;
	}
	
	public static Convenio gerarDadosConvenioComBairroInvalido() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getEndereco().setBairro(GerarStringInvalida.gerarStringAleatoria());
		
		return convenio;
	}

	public static Convenio gerarDadosConvenioComTelefoneVazio() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getContato().setTelefone("");
		
		return convenio;
	}
	
	public static Convenio gerarDadosConvenioComTelefoneInvalido() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getContato().setTelefone("{}[-=--");
		
		return convenio;
	}

	public static Convenio gerarDadosConvenioComObservacaoVazia() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getContato().setObservacao("");

		return convenio;
	}
	
	public static Convenio gerarDadosConvenioComObservacaoMuitoGrande() {
		Convenio convenio = gerarDadosConvenio();
		
		convenio.getContato().setObservacao(
				"Caros amigos, a mobilidade dos capitais internacionais facilita a criação das condições financeiras e administrativas exigidas. A prática cotidiana prova que a complexidade dos estudos efetuados apresenta tendências no sentido de aprovar a manutenção das diretrizes de desenvolvimento para o futuro. Assim mesmo, a expansão dos mercados mundiais exige a precisão e a definição dos níveis de motivação departamental. Evidentemente, o desenvolvimento contínuo de distintas formas de atuação prepara-nos para enfrentar situações atípicas decorrentes dos procedimentos normalmente adotados. Percebemos, cada vez mais, que a estrutura atual da organização assume importantes posições no estabelecimento das diversas correntes de pensamento. "
						+ "Desta maneira, a contínua expansão de nossa atividade desafia a capacidade de equalização das direções preferenciais no sentido do progresso. O que temos que ter sempre em mente é que o novo modelo estrutural aqui preconizado não pode mais se dissociar do sistema de formação de quadros que corresponde às necessidades. Acima de tudo, é fundamental ressaltar que a consolidação das estruturas causa impacto indireto na reavaliação das condições inegavelmente apropriadas. As experiências acumuladas demonstram que o entendimento das metas propostas aponta para a melhoria dos paradigmas corporativos. O incentivo ao avanço tecnológico, assim como o julgamento imparcial das eventualidades pode nos levar a considerar a reestruturação das posturas dos órgãos dirigentes com relação às suas atribuições. "
						+ "Neste sentido, a crescente influência da mídia promove a alavancagem dos modos de operação convencionais. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que o surgimento do comércio virtual ainda não demonstrou convincentemente que vai participar na mudança do investimento em reciclagem técnica. Pensando mais a longo prazo, o fenômeno da Internet garante a contribuição de um grupo importante na determinação do processo de comunicação como um todo. Ainda assim, existem dúvidas a respeito de como a constante divulgação das informações nos obriga à análise dos métodos utilizados na avaliação de resultados. "
						+ "O empenho em analisar a hegemonia do ambiente político auxilia a preparação e a composição dos conhecimentos estratégicos para atingir a excelência. Podemos já vislumbrar o modo pelo qual o aumento do diálogo entre os diferentes setores produtivos oferece uma interessante oportunidade para verificação do sistema de participação geral. Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se a valorização de fatores subjetivos talvez venha a ressaltar a relatividade das novas proposições. A nível organizacional, a necessidade de renovação processual agrega valor ao estabelecimento dos índices pretendidos.");

		return convenio;
	}

}