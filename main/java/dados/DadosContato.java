package dados;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import br.ufrn.imd.sigsaude.dominio.Contato;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.enums.TipoTelefoneContato;

public class DadosContato {

	public DadosContato() {
		// TODO Auto-generated constructor stub
	}

	public static Contato gerarDadosContato() {
		
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);

		Contato contato = new Contato();

		contato.setObservacao("Observação Contato Selenium " + dataAtual + new Random().nextInt()*1000);
		contato.setSms(new Random().nextBoolean());
		
		boolean isTipoTelefone = new Random().nextBoolean();
		if (isTipoTelefone) {
			contato.setTipoTelefone(TipoTelefoneContato.FIXO);
			contato.setTelefone("8436630000");
		} else {
			contato.setTipoTelefone(TipoTelefoneContato.MOVEL);
			contato.setTelefone("84994889991");
		}
		contato.setSms(new Random().nextBoolean());
		contato.setWhatsapp(new Random().nextBoolean());
		contato.setContatoUrgencia(new Random().nextBoolean());

		return contato;
	}
	
	public static Contato gerarDadosContatoVazioOuInvalido(boolean vazio) {
		List<Contato> contact = new ArrayList<>();
		Contato contato = new Contato();

		contato.setContatoUrgencia(true);
		contato.setObservacao("");
		contato.setSms(true);
		if (vazio) {
			contato.setTelefone("");
		} else {
			contato.setTelefone(GerarStringInvalida.gerarStringAleatoria());
		}
		contato.setTipoTelefone(TipoTelefoneContato.MOVEL);
		contato.setWhatsapp(true);

		contact.add(contato);

		return contato;
	}
	
	public static List<Contato> gerarDadosContatoAsList(){
		
		List<Contato> contact = new ArrayList<>();
		
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);

		Contato contato = new Contato();

		contato.setObservacao("Observação Contato Selenium " + dataAtual + new Random().nextInt()*1000);
		contato.setSms(new Random().nextBoolean());

		boolean isTipoTelefone = new Random().nextBoolean();
		if (isTipoTelefone) {
			contato.setTipoTelefone(TipoTelefoneContato.FIXO);
			contato.setTelefone("8436630000");
		} else {
			contato.setTipoTelefone(TipoTelefoneContato.MOVEL);
			contato.setTelefone("84994889991");
		}
		contato.setSms(new Random().nextBoolean());
		contato.setWhatsapp(new Random().nextBoolean());
		contato.setContatoUrgencia(new Random().nextBoolean());

		contact.add(contato);

		return contact;
	}
	
	public static List<Contato> gerarDadosContatoAsList(Pessoa pessoa){
	
		List<Contato> contact = gerarDadosContatoAsList(1);
		
		Contato contato = new Contato();
		contato.setPessoa(pessoa);

		contact.add(contato);

		return contact;
	}

	public static List<Contato> gerarDadosContatoAsList(int tamanhoLista){

		List<Contato> contact = new ArrayList<>();

		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);

		for (int i = 0; i < tamanhoLista; i++){
			Contato contato = new Contato();

			contato.setObservacao("Observação Contato Selenium " + dataAtual + new Random().nextInt()*1000);
			contato.setSms(new Random().nextBoolean());

			boolean isTipoTelefone = new Random().nextBoolean();
			if (isTipoTelefone) {
				contato.setTipoTelefone(TipoTelefoneContato.FIXO);
				contato.setTelefone("8436630000");
			} else {
				contato.setTipoTelefone(TipoTelefoneContato.MOVEL);
				contato.setTelefone("84994889991");
			}
			contato.setSms(new Random().nextBoolean());
			contato.setWhatsapp(new Random().nextBoolean());
			contato.setContatoUrgencia(new Random().nextBoolean());

			contact.add(contato);

		}

		return contact;
	}

	public static List<Contato> gerarDadosContatoAsListComTelefoneVazioOuInvalido(boolean vazio){

		List<Contato> contact = new ArrayList<>();

		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += " de " + calendario.getDisplayName(Calendar.MONTH, Calendar.LONG, localizacao);

		Contato contato = new Contato();

		contato.setObservacao("Observação Contato Selenium " + dataAtual + new Random().nextInt()*1000);
		contato.setSms(new Random().nextBoolean());
		
		if (vazio) {
			contato.setTelefone("");
		} else {
			contato.setTelefone(GerarStringInvalida.gerarStringAleatoria());
		}
		
		boolean isTipoTelefone = new Random().nextBoolean();
		if (isTipoTelefone) {
			contato.setTipoTelefone(TipoTelefoneContato.FIXO);
		} else {
			contato.setTipoTelefone(TipoTelefoneContato.MOVEL);
		}
		contato.setSms(new Random().nextBoolean());
		contato.setWhatsapp(new Random().nextBoolean());
		contato.setContatoUrgencia(new Random().nextBoolean());

		contact.add(contato);

		return contact;
	}

	public static List<Contato> gerarDadosContatoAsListComObsInvalida() {
		List<Contato> contact = new ArrayList<>();

		Contato contato = new Contato();

		contato.setObservacao("Caros amigos, a mobilidade dos capitais internacionais facilita a criação das condições financeiras e administrativas exigidas. A prática cotidiana prova que a complexidade dos estudos efetuados apresenta tendências no sentido de aprovar a manutenção das diretrizes de desenvolvimento para o futuro. Assim mesmo, a expansão dos mercados mundiais exige a precisão e a definição dos níveis de motivação departamental. Evidentemente, o desenvolvimento contínuo de distintas formas de atuação prepara-nos para enfrentar situações atípicas decorrentes dos procedimentos normalmente adotados. Percebemos, cada vez mais, que a estrutura atual da organização assume importantes posições no estabelecimento das diversas correntes de pensamento. "
				+ "Desta maneira, a contínua expansão de nossa atividade desafia a capacidade de equalização das direções preferenciais no sentido do progresso. O que temos que ter sempre em mente é que o novo modelo estrutural aqui preconizado não pode mais se dissociar do sistema de formação de quadros que corresponde às necessidades. Acima de tudo, é fundamental ressaltar que a consolidação das estruturas causa impacto indireto na reavaliação das condições inegavelmente apropriadas. As experiências acumuladas demonstram que o entendimento das metas propostas aponta para a melhoria dos paradigmas corporativos. O incentivo ao avanço tecnológico, assim como o julgamento imparcial das eventualidades pode nos levar a considerar a reestruturação das posturas dos órgãos dirigentes com relação às suas atribuições. "
				+ "Neste sentido, a crescente influência da mídia promove a alavancagem dos modos de operação convencionais. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que o surgimento do comércio virtual ainda não demonstrou convincentemente que vai participar na mudança do investimento em reciclagem técnica. Pensando mais a longo prazo, o fenômeno da Internet garante a contribuição de um grupo importante na determinação do processo de comunicação como um todo. Ainda assim, existem dúvidas a respeito de como a constante divulgação das informações nos obriga à análise dos métodos utilizados na avaliação de resultados. "
				+ "O empenho em analisar a hegemonia do ambiente político auxilia a preparação e a composição dos conhecimentos estratégicos para atingir a excelência. Podemos já vislumbrar o modo pelo qual o aumento do diálogo entre os diferentes setores produtivos oferece uma interessante oportunidade para verificação do sistema de participação geral. Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se a valorização de fatores subjetivos talvez venha a ressaltar a relatividade das novas proposições. A nível organizacional, a necessidade de renovação processual agrega valor ao estabelecimento dos índices pretendidos.");
		contato.setSms(new Random().nextBoolean());


		boolean isTipoTelefone = new Random().nextBoolean();
		if (isTipoTelefone) {
			contato.setTipoTelefone(TipoTelefoneContato.FIXO);
			contato.setTelefone("8436630000");
		} else {
			contato.setTipoTelefone(TipoTelefoneContato.MOVEL);
			contato.setTelefone("84994889991");
		}
		contato.setSms(new Random().nextBoolean());
		contato.setWhatsapp(new Random().nextBoolean());
		contato.setContatoUrgencia(new Random().nextBoolean());

		contact.add(contato);

		return contact;
	}
}
