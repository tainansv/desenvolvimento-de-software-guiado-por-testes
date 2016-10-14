
public class MockServicoRemoto implements ServicoRemoto {

	ContaCorrente novaConta = new ContaCorrente("usuario", "senha");
	
	@Override
	public ContaCorrente recuperarConta(String conta) {
		return novaConta;
	}

	@Override
	public void persistirConta() {
		
	}

}
