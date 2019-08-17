package dados;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import br.ufrn.imd.sigsaude.dominio.OfertaEspecialidade;
import br.ufrn.imd.sigsaude.dominio.enums.TipoAgendamento;

public class DadosOfertaEspecialidade {

	public static OfertaEspecialidade gerarDadosOfertaEspecialidade(boolean isHoraMarcada) {
		
		OfertaEspecialidade oferta = new OfertaEspecialidade();
		oferta.setInicio(new Date());
		
		if (isHoraMarcada) {
			oferta.setTipoAgendamento(TipoAgendamento.HORA_MARCADA);
		} else {
			oferta.setTipoAgendamento(TipoAgendamento.ORDEM_CHEGADA);
		}
		
		oferta.setTempoMedioAtendimento(60);
		
		return oferta;
		
	}
	
	public static Date gerarDataInicio() {
		Date data = (Date.from(LocalDate.of(1999, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		return data;
		
	}
}
