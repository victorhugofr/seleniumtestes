package dados;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import br.ufrn.imd.sigsaude.dominio.OfertaServico;

/**
 * Classe responspavel para gerar os dados das ofertas de servicos que serao utilizados
 * durante as etapas do cadastro.
 * @author Jancy Aragao
 *
 */
public class DadosOfertaServico {
	
	public static OfertaServico gerarDadosOfertaServico() {
		int diasInicio = new Random().nextInt(100) + 1;		
		LocalDate dataInicio = LocalDate.now(ZoneId.of("America/Fortaleza")).plusDays(diasInicio);
		Date dataInicioOfertaServico = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		int diasTermino = diasInicio + new Random().nextInt(100) + 1;
		LocalDate dataTermino = LocalDate.now(ZoneId.of("America/Fortaleza")).plusDays(diasTermino);
		Date dataTerminoOfertaServico = Date.from(dataTermino.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		OfertaServico ofertaServico = new OfertaServico();
		
		ofertaServico.setInicio(dataInicioOfertaServico);
		ofertaServico.setTermino(dataTerminoOfertaServico);
		
		return ofertaServico;
	}
	
	public static OfertaServico gerarDadosOfertaServicoVazio() {
		OfertaServico ofertaServico = new OfertaServico();
		
		ofertaServico.setInicio(null);
		ofertaServico.setTermino(null);
		
		return ofertaServico;
	}
	
	public static OfertaServico gerarDadosOfertaServicoDataInicioVazio() {
		OfertaServico ofertaServico = gerarDadosOfertaServico();
		
		ofertaServico.setInicio(null);
		
		return ofertaServico;
	}
	
	public static OfertaServico gerarDadosOfertaServicoDataInicioAtual() {
		LocalDate dataHoje = LocalDate.now(ZoneId.of("America/Fortaleza"));
		Date dataDeHoje = Date.from(dataHoje.atStartOfDay(ZoneId.systemDefault()).toInstant());
		OfertaServico ofertaServico = gerarDadosOfertaServico();
		
		ofertaServico.setInicio(dataDeHoje);
		
		return ofertaServico;
	}
	
	public static OfertaServico gerarDadosOfertaServicoDataInicioAnteriorDataHoje() {
		LocalDate dataInicio = LocalDate.now(ZoneId.of("America/Fortaleza")).minusDays(10);
		Date dataInicioOfertaServico = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
		OfertaServico ofertaServico = gerarDadosOfertaServico();
		
		ofertaServico.setInicio(dataInicioOfertaServico);
		
		return ofertaServico;
	}
	
	public static OfertaServico gerarDadosOfertaServicoDataTerminoVazio() {
		OfertaServico ofertaServico = gerarDadosOfertaServico();
		
		ofertaServico.setTermino(null);
		
		return ofertaServico;
	}
	
	public static OfertaServico gerarDadosOfertaServicoDataTerminoAtual() {
		LocalDate dataHoje = LocalDate.now(ZoneId.of("America/Fortaleza"));
		Date dataDeHoje = Date.from(dataHoje.atStartOfDay(ZoneId.systemDefault()).toInstant());
		OfertaServico ofertaServico = gerarDadosOfertaServico();
		
		ofertaServico.setTermino(dataDeHoje);
		
		return ofertaServico;
	}
	
	public static OfertaServico gerarDadosOfertaServicoDataTerminoAnteriorDataInicio() {
		LocalDate dataTermino = LocalDate.now(ZoneId.of("America/Fortaleza")).plusDays(1);
		Date dataTerminoOfertaServico = Date.from(dataTermino.atStartOfDay(ZoneId.systemDefault()).toInstant());
		OfertaServico ofertaServico = gerarDadosOfertaServico();
		
		ofertaServico.setTermino(dataTerminoOfertaServico);
		
		return ofertaServico;
	}
	
}