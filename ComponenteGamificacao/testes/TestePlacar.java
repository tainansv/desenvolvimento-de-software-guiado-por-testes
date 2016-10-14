import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TestePlacar {

	Armazenamento armazenMock;
	Placar placar;
	
	@Before
	public void inicializa() throws IOException{
		armazenMock = new MockArmazenamento();
		placar = new Placar();
		placar.setArmazenamento(armazenMock);
		placar.registarPonto("joão", "moeda", 15);
		placar.registarPonto("pedro", "moeda", 15);
		placar.registarPonto("maria", "moeda", 25);
		placar.registarPonto("joão", "ouro", 0);
		placar.registarPonto("joão", "moeda", 5);
		placar.registarPonto("maria", "", 40);
		placar.registarPonto("pedro", "ouro", 5);
	}
	
	@Test
	public void registraPontoUsuario() throws IOException  {
		placar.registarPonto("ciclano", "estrela", 10);
		assertEquals(10, placar.getPontosUsuario("ciclano", "estrela"));
	}

	@Test
	public void recuperaTodosPontosUsuario() throws IOException  {
		assertEquals(2, placar.getTodosPontosUsuario("pedro").size());
		assertEquals(1, placar.getTodosPontosUsuario("joão").size());
	}
	
	@Test
	public void rankingDeUmTipoDePonto() throws IOException {
		assertEquals("maria - 25", placar.getRankingDeTipo("moeda").get(0));
		assertEquals("joão - 20", placar.getRankingDeTipo("moeda").get(1));
	}
}
