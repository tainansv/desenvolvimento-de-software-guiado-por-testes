import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Armazenamento {

	private File _arquivo = new File("pontos.txt");
	private List<String> _dados = new ArrayList<String>();
	
	public void setApagarCacheDeDados() {
		_dados.clear();
	}
	
	public void guardar(String usuario, String tipo, int pontos) throws IOException {
		if(usuario.length() > 0 && tipo.length() > 0){
			_dados.add(usuario+"-"+tipo+"-"+pontos);
			gerarArquivo(_dados);
		}
	}
	
	private void gerarArquivo(List<String> dados) throws IOException {
		FileWriter abreArquivo = new FileWriter(_arquivo);
		BufferedWriter gravaDados = new BufferedWriter(abreArquivo);
		for(String linha : dados){
			gravaDados.write(linha);
			gravaDados.newLine();
		}
		gravaDados.flush();
		gravaDados.close();
	}

	public int getEntradasNoArquivo() throws FileNotFoundException {
		Scanner lerArquivo = new Scanner(_arquivo);
		int entradas = 0;
		while(lerArquivo.hasNextLine()){
			entradas++;
			lerArquivo.nextLine();
		}	
		lerArquivo.close();
		return entradas;
	}

	public int getPontosUsuario(String usuario, String tipo) {
		int pontos = 0;
		for (int i = 0; i < _dados.size(); i++) {
			String linha = _dados.get(i);
			if(linha.contains(usuario))
				if(linha.contains(tipo)){
					int indexPontos = linha.lastIndexOf("-")+1;
					pontos += Integer.parseInt(linha.substring(indexPontos));
				}
		}
		return pontos;
	}

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

	public List<String> getTiposDePontos(String usuario) {
		List<String> tipos = new ArrayList<>();
		for (int i = 0; i < _dados.size(); i++) {
			String linha = _dados.get(i);
			String tipo = linha.substring(linha.indexOf("-")+1, linha.lastIndexOf("-"));
			if(linha.contains(usuario))
				if(!tipos.contains(tipo))
					tipos.add(tipo);
			}
		return tipos;
		}


	public void recuperarDados() throws FileNotFoundException {
		Scanner lerArquivo = new Scanner(_arquivo);
		while(lerArquivo.hasNextLine()){
			String linha = lerArquivo.nextLine();
			_dados.add(linha);
		}
		lerArquivo.close();
			
	}
	
}
