package dados;

import java.util.Random;

public class GerarStringInvalida {

	public static String gerarStringAleatoria() {

		String letras = "!@#$%¨&*()*-+.-_=';:.<,?][~´`1234567890";
		Random random = new Random();
		String minhaPalavra = "";
		int index = -1;
		
		for (int i = 0; i < 9; i++) {
			index = random.nextInt(letras.length());
			minhaPalavra += letras.substring(index, index+1);
		}
		return minhaPalavra;
	}

}
