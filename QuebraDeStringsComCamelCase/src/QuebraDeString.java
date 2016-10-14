import java.util.ArrayList;
import java.util.List;


public class QuebraDeString {

	public static List<String> converterCamelCase(String original) {
		List<String> lista = new ArrayList<>();
			String[] palavras = dividirString(original);
			for (String palavra : palavras) {
				lista.add(tratarPlavra(palavra));
			}
		return lista;
	}

	private static String[] dividirString(String string) {
		String stringComEspaco = string.replaceAll(String.format("%s|%s|%s", 
				"(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Z])(?=[A-Z][a-z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
		String[] palavras = stringComEspaco.split(" ");
		if (palavras[0].matches("[0-9]+")) {
			throw new MinhasExcecoes("Não deve começar com número");
		}else return palavras;
	}
	
	private static String tratarPlavra(String palavra) {
		if (todaMaiuscula(palavra)) {
			return palavra;
		}else {
			return palavra.toLowerCase();
		}
	}
	
	private static boolean todaMaiuscula(String palavra) {
		int maiusculas = 0;
		for (int i = 0; i < palavra.length(); i++) {
			Character letra = palavra.charAt(i);
			contemEspecial(letra);
			if (Character.isUpperCase(letra)) 
				maiusculas++;
		}
		return (maiusculas > 1) ? true : false;
	}

	private static void contemEspecial(Character letra) {
		if (!Character.isLetterOrDigit(letra)) {
			throw new MinhasExcecoes("Inválido - caracteres especiais não são permitidos, somente letras e números");
		}	
	}
}
