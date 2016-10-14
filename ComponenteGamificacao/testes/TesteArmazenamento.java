import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TesteArmazenamento {

	Armazenamento armazen;
	
	@Before
	public void inicializador() throws IOException {
		armazen = new Armazenamento();
	}
	
	@Test
	public void armazenarPontos() throws IOException {
		armazen.guardar("fulano", "estrela", 10);
		armazen.guardar("ciclano", "curtir", 15);
		armazen.guardar("beltrano", "moedas", 5);
		assertEquals(3, armazen.getEntradasNoArquivo());
	}
	
	@Test
		public void ignorarUsuarioSemAlgumParametro() throws IOException {
			armazen.guardar("fulano", "estrela", 10);
			armazen.guardar("ciclano", "curtir", 15);
			armazen.guardar("", "moedas", 10);
			armazen.guardar("joão", "", 10);
			assertEquals(2, armazen.getEntradasNoArquivo());
		}
	
	@Test
	public void armazenarPontosMesmoUsuario() throws IOException {
		armazen.guardar("fulano", "estrela", 10);
		armazen.guardar("fulano", "curtir", 15);
		armazen.guardar("ciclano", "moedas", 5);
		armazen.guardar("ciclano", "estrela", 2);
		assertEquals(4, armazen.getEntradasNoArquivo());
	}
	
	@Test
	public void recuperarPontosUsuario() throws IOException {
		armazen.guardar("fulano", "estrela", 10);
		armazen.guardar("ciclano", "moedas", 5);
		assertEquals(10, armazen.getPontosUsuario("fulano", "estrela"));
		assertEquals(5, armazen.getPontosUsuario("ciclano", "moedas"));
	}
	
	@Test
	public void recuperarPontosDeUmTipoDoUsuario() throws IOException {
		armazen.guardar("fulano", "estrela", 10);
		armazen.guardar("fulano", "curtir", 15);
		assertEquals(10, armazen.getPontosUsuario("fulano", "estrela"));
		assertEquals(15, armazen.getPontosUsuario("fulano", "curtir"));
	}
	
	@Test
	public void recuperarPontosMaisDeUmaEntradaDeUmUsuario() throws IOException {
		armazen.guardar("fulano", "estrela", 10);
		armazen.guardar("fulano", "estrela", 15);
		assertEquals(25, armazen.getPontosUsuario("fulano", "estrela"));
	}
	
	@Test
	public void recuperarTodosUsuarios() throws IOException {
		armazen.guardar("fulano", "estrela", 10);
		armazen.guardar("ciclano", "curtir", 15);
		armazen.guardar("beltrano", "moedas", 20);
		assertEquals(3, armazen.getTodosUsuarios().size());
	}
	
	@Test
	public void recuperarTodosTiposDePontosDoUsuario() throws IOException {
		armazen.guardar("fulano", "estrela", 10);
		armazen.guardar("fulano", "curtir", 15);
		armazen.guardar("beltrano", "moedas", 20);
		assertEquals(2, armazen.getTiposDePontos("fulano").size());
	}
	
	@Test
	public void recuperarDadosDoArquivo() throws IOException {
		armazen.guardar("fulano", "estrela", 10);
		armazen.guardar("ciclano", "curtir", 15);
		armazen.guardar("beltrano", "moedas", 20);
		armazen.setApagarCacheDeDados();
		assertEquals(0, armazen.getTodosUsuarios().size());
		armazen.recuperarDados();
		assertEquals(3, armazen.getTodosUsuarios().size());
	}
}
