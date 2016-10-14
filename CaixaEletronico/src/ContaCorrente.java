
public class ContaCorrente {
	
	private int saldo;
	private String usuario;
	private String senha;

	public ContaCorrente(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public void setSaldo(int valor) {
		if(valor > 0)
			this.saldo += valor;
	}

	public int getSaldo() {
		return saldo;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

}
