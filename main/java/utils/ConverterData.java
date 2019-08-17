package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.language.bm.Languages;

import br.ufrn.imd.sigsaude.dominio.Convenio;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import dados.DadosConvenio;
import dados.DadosPessoa;

public class ConverterData {

	public static String DataNascimentoConvert(Pessoa pessoa) {

		Calendar calendar = Calendar.getInstance();

		Locale brasil = new Locale("pt", "BR");

		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");

		calendar.setTime(pessoa.getDataNascimento());
		System.out.println(calendar.getTime());

		System.out.println(
				day.format(calendar.getTime()) + month.format(calendar.getTime()) + year.format(calendar.getTime()));

		DateFormat mes = DateFormat.getDateInstance(DateFormat.FULL, brasil);
		// mes.format("Data brasil" + pessoa.getDataNascimento());
		SimpleDateFormat brazilTime = new SimpleDateFormat("dd 'de' MMM 'de' yyyy", brasil);
		System.out.println(brazilTime.format(pessoa.getDataNascimento()));
		System.out.println(mes.format(pessoa.getDataNascimento()));

		return day.format(calendar.getTime()) + month.format(calendar.getTime()) + year.format(calendar.getTime());
	}

	public static String DataInicioVigencia(Convenio convenio) {
		Calendar calendar = Calendar.getInstance();

		Locale brasil = new Locale("pt", "BR");

		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");

		calendar.setTime(convenio.getInicioVigencia());
		System.out.println(calendar.getTime());

		System.out.println(
				day.format(calendar.getTime()) + month.format(calendar.getTime()) + year.format(calendar.getTime()));

		return day.format(calendar.getTime()) + month.format(calendar.getTime()) + year.format(calendar.getTime());

	}
	
	public static String DataFimVigencia(Convenio convenio) {
		Calendar calendar = Calendar.getInstance();

		Locale brasil = new Locale("pt", "BR");

		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");

		calendar.setTime(convenio.getFinalVigencia());
		System.out.println(calendar.getTime());

		System.out.println(
				day.format(calendar.getTime()) + month.format(calendar.getTime()) + year.format(calendar.getTime()));

		return day.format(calendar.getTime()) + month.format(calendar.getTime()) + year.format(calendar.getTime());

	}

	public static void main(String[] args) {
		ConverterData c = new ConverterData();
		System.out.println(c.DataNascimentoConvert(DadosPessoa.gerarDadosPessoa(false, false)));
		// System.out.println(c.DataInicioVigencia(DadosConvenio.gerarDadosConvenio()));
	}
}
