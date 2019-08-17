package dados;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import br.ufrn.imd.sigsaude.dominio.InformacaoSocial;
import br.ufrn.imd.sigsaude.dominio.Paciente;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.enums.TipoSanguineo;
import br.ufrn.imd.sigsaude.dominio.enums.TipoVinculo;

/**
 * Classe responspavel para gerar os dados dos pacientes que serão utilizados
 * durante as etapas do cadastro
 * 
 * @author Pedro H B Cavalcante <pedrohbcavalcante@outlook.com>
 * @author Uira Kulesza
 * @author Jancy Aragao
 *
 */
public class DadosPaciente {
	
	public static Paciente gerarDadosPaciente(Pessoa pessoa, boolean isProprioResponsavel) {
		
		Paciente paciente = new Paciente();
		
		paciente.setPessoa(pessoa);

		int tipoSanguineo = (int) Math.round(Math.random() * 9);

		if (tipoSanguineo == 0) {
			paciente.setTipoSanguineo(TipoSanguineo.NAO_DECLARADO);
		} else if (tipoSanguineo == 1) {
			paciente.setTipoSanguineo(TipoSanguineo.A_NEGATIVO);
		} else if (tipoSanguineo == 2) {
			paciente.setTipoSanguineo(TipoSanguineo.A_POSITIVO);
		} else if (tipoSanguineo == 3) {
			paciente.setTipoSanguineo(TipoSanguineo.AB_NEGATIVO);
		} else if (tipoSanguineo == 4) {
			paciente.setTipoSanguineo(TipoSanguineo.AB_POSITIVO);
		} else if (tipoSanguineo == 5) {
			paciente.setTipoSanguineo(TipoSanguineo.B_NEGATIVO);
		} else if (tipoSanguineo == 6) {
			paciente.setTipoSanguineo(TipoSanguineo.B_POSITIVO);
		} else if (tipoSanguineo == 7) {
			paciente.setTipoSanguineo(TipoSanguineo.O_NEGATIVO);
		} else {
			paciente.setTipoSanguineo(TipoSanguineo.O_POSITIVO);
		}

		paciente.setInformacaoSocial(new InformacaoSocial());
		paciente.setResponsavel(DadosResponsavel.gerarDadosResponsavel(DadosPessoa.gerarDadosPessoa(new Random().nextBoolean(), new Random().nextBoolean()), isProprioResponsavel));
		
		int vinculo = (int) Math.round(Math.random() * 4);
		if (vinculo == 0) {
			paciente.setTipoVinculo(TipoVinculo.ALUNO);
		} else if (vinculo == 1) {
			paciente.setTipoVinculo(TipoVinculo.SEM_VINCULO);
		} else if (vinculo == 2) {
			paciente.setTipoVinculo(TipoVinculo.SERVIDOR_ATIVO);
		} else {
			paciente.setTipoVinculo(TipoVinculo.SERVIDOR_INATIVO);
		}
		
		Random gerador = new Random();
        int carteiraSUS = 0;
        for (int i = 0; i < 25; i++) {
        	carteiraSUS += gerador.nextInt();
        }
		paciente.setCarteiraSus(Integer.toString(carteiraSUS));
		paciente.setUnidadeBasica("UBS");
		
		
		String dataAtual = "";
        Calendar calendario = Calendar.getInstance();
        Locale localizacao = new Locale("pt","BR");
        dataAtual += new GerarNumeroExtenso(calendario.get(Calendar.DAY_OF_MONTH)).toString();
        dataAtual += calendario.getDisplayName(Calendar.MONTH, Calendar.SHORT, localizacao).toUpperCase();
		paciente.setDeficiente(new Random().nextBoolean());
		if (paciente.isDeficiente()) {
			paciente.setTipoDeficiencia("Deficiência Teste " + dataAtual);
		}

		return paciente;
	}

	public static Pessoa gerarDadosPessoaComDataDeNascimentoInvalida(boolean isEstrangeiro, boolean possuiCPF, int dia, int mes, int ano) {
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(isEstrangeiro, possuiCPF);

		Date data = (Date.from(LocalDate.of(ano, mes, dia).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		pessoa.setDataNascimento(data);

		return pessoa;
	}


	public static Paciente gerarDadosPacienteProprioPresponsavel(Pessoa pessoa, boolean isProprioResponsavel) {
		Paciente paciente = gerarDadosPaciente(pessoa, isProprioResponsavel);
		paciente.getResponsavel().setProprioResponsavel(false);
		return paciente;
	}
}