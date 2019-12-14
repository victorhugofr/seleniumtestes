package dados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.ufrn.imd.sigsaude.dominio.Especialidade;

public class DadosEspecialidade {
	
	public static final List<String> denominacaoEspecialidade = new ArrayList<>();
	

	public static Especialidade gerarDadosEspecialidade() {
		String[] arrayCodigo = {"201","70","210","211","200","212","72","73","213","214","215","216","217","218",
				"187","219","220","221","222","223","224","225","226","227","228","199","229","202","230","74","75",
				"77","78","160","79","80","81","82","83","84","65","231","232","233","234","85","86","235","236",
				"237","238","239","161","87","188",
				"162","240","241","242","243","204","244","88","163","89","179","245","203","164","246","209","247",
				"248","249","250","251","252","253","254","255","256","257","258","259","260","261","262","263",
				"264","265","266","267","268","269","125","127","128","129","132","126","131","134","137","138",
				"139","140","130","133","135","136","341","189","186","190","191","192","270","271","90","272",
				"205","91","273","92","193","274","275","276","277","278","279","280","281","282","93","283","94",
				"284","285","286","287","288","95","165","166","289","290","291","292","293","294","295","296",
				"297","96","194","298","299","97","69","98","99","101","100","102","103","104","105","106","107",
				"300","301","302","206","303","304","195","305","108","109","110","157","196","141","143","145",
				"306","307","308","111","170","159","168","167","169","47","49","50","53","57","55","171","112",
				"113","172","114","173","115","309","116","174","117","118","342","175","310","311","312","313",
				"314","119","315","176","177","146","152","149","151","158","147","153","150","148","156",
				"155","154","120","316","121","122","317","123","318","144","197","319","180","178","181","182",
				"183","320","343","321","207","322","185","345","323","324","325","208","326","327","328","329",
				"330","331","332","333","334","335","336","337","184","124","344","338","339","340","198"};
		denominacaoEspecialidade.addAll(Arrays.asList(arrayCodigo));
		
		
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);
        String especialidadeValor = GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000));
		
		Especialidade especialidade = new Especialidade();
		int randomID = (int) Math.round(Math.random() * denominacaoEspecialidade.size() -1 );
		
		while (randomID < 69) {
			randomID = (int) Math.round(Math.random() * denominacaoEspecialidade.size() -1 );
		}
		
		especialidade.setCodigo(denominacaoEspecialidade.get(randomID));
		
		especialidade.setDenominacao("Especialidade Selenium " + especialidadeValor + " " + dataAtual);
		especialidade.setDescricao("Especialidade Selenium Descrição " + especialidadeValor + " " + dataAtual);

		return especialidade;
	}

	public static Especialidade gerarDadosEspecialidadeEmBranco() {
		Especialidade especialidade = gerarDadosEspecialidade();

		especialidade.setDenominacao("");
		especialidade.setDescricao("");

		return especialidade;
	}

	public static Especialidade gerarDadosEspecialidadeDescricaoEmBranco() {
		Especialidade especialidade = gerarDadosEspecialidade();

		especialidade.setDescricao("");

		return especialidade;
	}

	public static Especialidade gerarDadosEspecialidadeComDenominacaoInvalida() {
		Especialidade especialidade = gerarDadosEspecialidade();
		
		especialidade.setDenominacao(GerarStringInvalida.gerarStringAleatoria());
		especialidade.setDescricao("Especialidade Selenium Teste de denominação com caracteres especiais");
		
		return especialidade;
	}

	public static Especialidade gerarDadosEspecialidadeComDenominacaoVazia() {
		Especialidade especialidade = gerarDadosEspecialidade();
		
		especialidade.setDenominacao("");
		especialidade.setDescricao("Especialidade Selenium Teste de denominação vazio");
		
		return especialidade;
	}

	public static Especialidade gerarDadosEspecialidadeComSiglaVazia() {
		Especialidade especialidade = gerarDadosEspecialidade();

		especialidade.setSigla("");
		especialidade.setDescricao("Especialidade Selenium Teste com sigla vazia");
		
		return especialidade;
	}

	public static Especialidade gerarDadosEspecialidadeComDescricaoGrande() {
		Especialidade especialidade = gerarDadosEspecialidade();
		
		especialidade.setDescricao("Caros amigos, a mobilidade dos capitais internacionais facilita a criação das condições financeiras e administrativas exigidas. A prática cotidiana prova que a complexidade dos estudos efetuados apresenta tendências no sentido de aprovar a manutenção das diretrizes de desenvolvimento para o futuro. Assim mesmo, a expansão dos mercados mundiais exige a precisão e a definição dos níveis de motivação departamental. Evidentemente, o desenvolvimento contínuo de distintas formas de atuação prepara-nos para enfrentar situações atípicas decorrentes dos procedimentos normalmente adotados. Percebemos, cada vez mais, que a estrutura atual da organização assume importantes posições no estabelecimento das diversas correntes de pensamento. "
				+ "Desta maneira, a contínua expansão de nossa atividade desafia a capacidade de equalização das direções preferenciais no sentido do progresso. O que temos que ter sempre em mente é que o novo modelo estrutural aqui preconizado não pode mais se dissociar do sistema de formação de quadros que corresponde às necessidades. Acima de tudo, é fundamental ressaltar que a consolidação das estruturas causa impacto indireto na reavaliação das condições inegavelmente apropriadas. As experiências acumuladas demonstram que o entendimento das metas propostas aponta para a melhoria dos paradigmas corporativos. O incentivo ao avanço tecnológico, assim como o julgamento imparcial das eventualidades pode nos levar a considerar a reestruturação das posturas dos órgãos dirigentes com relação às suas atribuições."
				+ "Neste sentido, a crescente influência da mídia promove a alavancagem dos modos de operação convencionais. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que o surgimento do comércio virtual ainda não demonstrou convincentemente que vai participar na mudança do investimento em reciclagem técnica. Pensando mais a longo prazo, o fenômeno da Internet garante a contribuição de um grupo importante na determinação do processo de comunicação como um todo. Ainda assim, existem dúvidas a respeito de como a constante divulgação das informações nos obriga à análise dos métodos utilizados na avaliação de resultados. "
				+ "O empenho em analisar a hegemonia do ambiente político auxilia a preparação e a composição dos conhecimentos estratégicos para atingir a excelência. Podemos já vislumbrar o modo pelo qual o aumento do diálogo entre os diferentes setores produtivos oferece uma interessante oportunidade para verificação do sistema de participação geral. Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se a valorização de fatores subjetivos talvez venha a ressaltar a relatividade das novas proposições. A nível organizacional, a necessidade de renovação processual agrega valor ao estabelecimento dos índices pretendidos. ");
		
		return especialidade;
	}

	public static Especialidade gerarDadosEspecialidadeComTodosOsDadosInvalidos() {
		Especialidade especialidade = gerarDadosEspecialidade();
		
		especialidade.setDenominacao(GerarStringInvalida.gerarStringAleatoria());
		especialidade.setSigla(GerarStringInvalida.gerarStringAleatoria());
		
		return especialidade;
	}

}