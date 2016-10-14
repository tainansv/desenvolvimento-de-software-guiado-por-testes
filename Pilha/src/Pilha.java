
public class Pilha {
	
	private Object[] elementos;
	private int quantidade = 0;
	
	public Pilha(int maximo) {
		elementos = new Object[10];
	}

	public boolean estaVazia() {
		return quantidade==0;
	}

	public int tamanho() {
		return quantidade;
	}

	public void empilha(Object elemento) {
		if(quantidade == elementos.length)
			throw new PilhaCheiaException("Não é possível mais empilhar");
		this.elementos[quantidade] = elemento;
		quantidade++;
		
	}

	public Object topo() {
		return elementos[quantidade - 1];
	}

	public Object desempilhar() {
		if(estaVazia())
			throw new PilhaVaziaException("Não é possivel desempilhar");
		Object topo = topo();
		quantidade--;
		return topo;
	}

}
