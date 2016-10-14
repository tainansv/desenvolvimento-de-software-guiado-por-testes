import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Placar {

	Armazenamento armazen;

	public void setArmazenamento(Armazenamento mockArmazen) {
		this.armazen = mockArmazen;
	}

	public void registarPonto(String usuario, String tipo, int pontos) throws IOException {
		armazen.guardar(usuario, tipo, pontos);
	}

	public int getPontosUsuario(String usuario, String tipo) {
		return armazen.getPontosUsuario(usuario, tipo);
	}

	public Map<String, Integer> getTodosPontosUsuario(String usuario) {
		Map<String, Integer> pontos = new HashMap<>();
		List<String> tipos = armazen.getTiposDePontos(usuario);
		for (String tipo : tipos) {
			int pontosDoTipo = armazen.getPontosUsuario(usuario, tipo);
			if(pontosDoTipo > 0)
				pontos.put(tipo, pontosDoTipo);
		}
		return pontos;
	}
	
	public List<String> getRankingDeTipo(String tipo){
		Map<Integer, String> posicao = new HashMap<>();
		List<Integer> pontos = new ArrayList<>();
		List<String> ranking = new ArrayList<>();
		
		for (String usuario : armazen.getTodosUsuarios()) {
			int ponto = armazen.getPontosUsuario(usuario, tipo);
			if(ponto > 0){
				pontos.add(ponto);
				posicao.put(ponto, usuario);
			}
		}
		
		Collections.sort(pontos);
		Collections.reverse(pontos);
		
		for (Integer ponto : pontos) {
			ranking.add(posicao.get(ponto) + " - " + ponto);
		}
		return ranking;
	}
	

}
