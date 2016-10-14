import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;


public class TesteQuebraDeString {

	@Test
	public void listaVazia() {
		List<String> lista = QuebraDeString.converterCamelCase("");
		assertTrue(lista.get(0).length()==0);
	}

	@Test
	public void umaPalavraSemCamelCase() {
		List<String> lista = QuebraDeString.converterCamelCase("nome");
		assertFalse(lista.isEmpty());
		assertEquals("nome", lista.get(0));
	}
	
	@Test
	public void umaPalavraComCamelCase() {
		List<String> lista = QuebraDeString.converterCamelCase("Nome");
		assertFalse(lista.isEmpty());
		assertEquals("nome", lista.get(0));
	}
	
	@Test
	public void stringCompostaPrimeiroMinusculo() {
		List<String> lista = QuebraDeString.converterCamelCase("nomeComposto");
		assertFalse(lista.isEmpty());
		assertEquals("nome", lista.get(0));
		assertEquals("composto", lista.get(1));
	}
	
	@Test
	public void stringCompostaPrimeiroMaiusculo() {
		List<String> lista = QuebraDeString.converterCamelCase("NomeComposto");
		assertFalse(lista.isEmpty());
		assertEquals("nome", lista.get(0));
		assertEquals("composto", lista.get(1));
	}
	
	@Test
	public void stringCompostaTodosMaiusculo() {
		List<String> lista = QuebraDeString.converterCamelCase("CPF");
		assertFalse(lista.isEmpty());
		assertEquals("CPF", lista.get(0));
	}
	
	@Test
	public void stringCompostaUmaPalavraMaiusculo() {
		List<String> lista = QuebraDeString.converterCamelCase("numeroCPF");
		assertFalse(lista.isEmpty());
		assertEquals("numero", lista.get(0));
		assertEquals("CPF", lista.get(1));
	}
	
	@Test
	public void stringCompostaUmaPalavraMaiusculoOutrasMinusculo() {
		List<String> lista = QuebraDeString.converterCamelCase("numeroCPFContribuinte");
		assertFalse(lista.isEmpty());
		assertEquals("numero", lista.get(0));
		assertEquals("CPF", lista.get(1));
		assertEquals("contribuinte", lista.get(2));
	}
	
	@Test
	public void stringCompostaComNumero() {
		List<String> lista = QuebraDeString.converterCamelCase("recupera10Primeiros");
		assertFalse(lista.isEmpty());
		assertEquals("recupera", lista.get(0));
		assertEquals("10", lista.get(1));
		assertEquals("primeiros", lista.get(2));
	}
	
	@Test
	public void stringCompostaComecaComNumero() {
		List<String> lista;
		try {
			lista = QuebraDeString.converterCamelCase("10Primeiros");
			fail();
		} catch (MinhasExcecoes e) {
			assertEquals("Não deve começar com número", e.getMessage());
		}
	}
	
	@Test
	public void stringCompostaComCaractereEpecial() {
		List<String> lista;
		try {
			lista = QuebraDeString.converterCamelCase("nome#Composto");
			fail();
		} catch (MinhasExcecoes e) {
			assertEquals("Inválido - caracteres especiais não são permitidos, somente letras e números",
					e.getMessage());
		}
	}
}
	