package dados;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class DadosTipoEspecialidade {

	public static void openFile() {

		try {
			Reader fileReader = Files.newBufferedReader(Paths.get("/home/pedrohbcavalcante/denominacao.csv"));

			CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
			String[] nextRecord;

			while ((nextRecord = csvReader.readNext()) != null) {
				/*
				 * System.out.println("id= " + nextRecord[0].replace("'", ""));
				 * System.out.println("denominacao= " + nextRecord[1].replace("'", ""));
				 * System.out.println("codigo= " + nextRecord[2].replace("'", ""));
				 */
				System.out.print('"' + nextRecord[0].replace("'", "") + '"' + ",");
			}

		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		DadosTipoEspecialidade.openFile();
	}

}
