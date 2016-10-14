
public class CaixaEletronico {
	
	private ServicoRemoto servico;
	private Hardware hardware;
	private ContaCorrente conta;
	
	public void setServico(ServicoRemoto servico) {
		this.servico = servico;
	}
	
	public void setHardware(Hardware hardware){
		this.hardware = hardware;
	}

	public String depositar(int valor) throws Exception {
		hardware.lerEnvelope();
		conta.setSaldo(valor);
		servico.persistirConta();
		return "Depósito recebido com sucesso";
	}

	public String saldo() {
		return "O saldo é R$" + conta.getSaldo();
	}

	public String sacar(int valor) throws Exception {
		if(valor > conta.getSaldo())
			return "Saldo insuficiente";
		hardware.entregarDinheiro();
		servico.persistirConta();
		return "Retire seu dinheiro";
	}

	public String logar(String usuario, String senha) throws Exception {
		String numeroDaConta = hardware.pegarNumeroDaContaCartão();
		ContaCorrente contaRecuperada = servico.recuperarConta(numeroDaConta);
		if(contaRecuperada.getUsuario() == usuario && contaRecuperada.getSenha() == senha){
			this.conta = contaRecuperada;
			return "Usuário autenticado";
		}
		return "Não foi possível autenticar o usuário";
		
	}

}
