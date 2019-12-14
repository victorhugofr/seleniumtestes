package dados;

import br.ufrn.imd.sigsaude.dominio.Turma;

public class DadosTurma {
	public static Turma gerarDadosTurma() {
		Turma turma=new Turma();
		
		turma.setCapacidade(3);
		turma.setCodigoComponenteCurricular("teste");
		turma.setCodigoTurma("codigoturma");
		turma.setNome("TurmaSelenium"+ GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		turma.setPeriodo("2020.2");
		return turma;
	}
	
	public static Turma gerarDadosTurmaSemNome() {
		Turma turma=new Turma();
		
		turma.setCapacidade(3);
		turma.setCodigoComponenteCurricular("teste");
		turma.setNome("");
		turma.setCodigoTurma("codigoturma");
		turma.setPeriodo("2020.2");
		return turma;
	}
	
	public static Turma gerarDadosTurmaSemCapacidade() {
		Turma turma=new Turma();
		
		turma.setCodigoComponenteCurricular("teste");
		turma.setCodigoTurma("codigoturma");
		turma.setNome("TurmaSelenium"+ GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		turma.setPeriodo("2020.2");
		return turma;
	}
	
	public static Turma gerarDadosTurmaSemCodigoComponente() {
		Turma turma=new Turma();
		
		turma.setCapacidade(3);
		turma.setCodigoComponenteCurricular("");
		turma.setCodigoTurma("codigoturma");
		turma.setNome("TurmaSelenium"+ GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		turma.setPeriodo("2020.2");
		return turma;
	}
	
	public static Turma gerarDadosTurmaSemCodigoTurma() {
		Turma turma=new Turma();
		
		turma.setCapacidade(3);
		turma.setCodigoTurma("");
		turma.setCodigoComponenteCurricular("teste");
		turma.setNome("TurmaSelenium"+ GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		turma.setPeriodo("2020.2");
		return turma;
	}
	
	public static Turma gerarDadosTurmaSemPeriodo() {
		Turma turma=new Turma();
		
		turma.setCapacidade(3);
		turma.setPeriodo("");
		turma.setCodigoComponenteCurricular("teste");
		turma.setCodigoTurma("codigoturma");
		turma.setNome("TurmaSelenium"+ GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		return turma;
	}
}
