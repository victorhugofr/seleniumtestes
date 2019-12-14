package utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

	public static Integer getIdadeEmAnos(Date dataNascimento) {
		return calculaIdade(dataNascimento).getYears();
	}
	
	public static String getIdadeExtenso(Date dataNascimento) {
		Period idadeCalculada = calculaIdade(dataNascimento);
		StringBuilder strBuilder = new StringBuilder();
		
		if (idadeCalculada.getYears() > 0) {
			strBuilder
				.append(idadeCalculada.getYears())
				.append(" ano(s) e ");
		}
		strBuilder
			.append(idadeCalculada.getMonths())
			.append(" mes(es)");
		
		return strBuilder.toString();
	}
	
	private static Period calculaIdade(Date dataNascimento) {
		LocalDate ldNascimento = LocalDate.from(dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		LocalDate ldHoje = LocalDate.now();
		return Period.between(ldNascimento, ldHoje);
	}
}
