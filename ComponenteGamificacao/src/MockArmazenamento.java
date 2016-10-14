import java.util.ArrayList;
import java.util.List;

public class MockArmazenamento extends Armazenamento {

	private List<String> _dados = new ArrayList<String>();
	

	@Override
	public void guardar(String usuario, String tipo, int pontos) {
		if(usuario.length() > 0 && tipo.length() > 0)
			_dados.add(usuario+"-"+tipo+"-"+pontos);
	}

	@Override
	public int getPontosUsuario(String usuario, String tipo) {
		int pontos = 0;
		for (String linha : _dados) {
			if(linha.contains(usuario) && linha.contains(tipo)){
				int indexPontos = linha.lastIndexOf("-")+1;
				pontos += Integer.parseInt(linha.substring(indexPontos));
			}
		}
		return pontos;
	}

	@Override
	public List<String> getTiposDePontos(String usuario) {
		List<String> tipos = new ArrayList<>();
		for (int i = 0; i < _dados.size(); i++) {
			String linha = _dados.get(i);
			String tipo = linha.substring(linha.indexOf("-")+1, linha.lastIndexOf("-"));
			if(linha.contains(usuario))
				tipos.add(tipo);
			}
		return tipos;
	}

	@Override
	public List<String> getTodosUsuarios() {
		List<String> usuarios = new ArrayList<>();
		for (int i = 0; i < _dados.size(); i++) {
			String linha = _dados.get(i);
			String usuario = linha.substring(0, linha.indexOf("-"));
			if(!usuarios.contains(usuario))
				usuarios.add(usuario);
		}
		return usuarios;
	}
	
	
}
