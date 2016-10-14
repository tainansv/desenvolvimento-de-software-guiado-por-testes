
public class MockHardware implements Hardware {

	private boolean erroLerEnvelope = false;
	private boolean erroEntregarDinheiro = false;
	private boolean erroPegarNumero = false;
	
	
	public void setErroLerEnvelope(boolean erroLerEnvelope) {
		this.erroLerEnvelope = erroLerEnvelope;
	}

	public void setErroEntregarDinheiro(boolean erroEntregarDinheiro) {
		this.erroEntregarDinheiro = erroEntregarDinheiro;
	}

	public void setErroPegarNumero(boolean erroPegarNumero) {
		this.erroPegarNumero = erroPegarNumero;
	}

	@Override
	public void lerEnvelope() throws HardwareException {
		if(erroLerEnvelope)
			throw new HardwareException("Erro ao ler envelope");
	}

	@Override
	public void entregarDinheiro() throws HardwareException {
		if(erroEntregarDinheiro)
			throw new HardwareException("Erro ao entregar dinheiro");
		
	}

	@Override
	public String pegarNumeroDaContaCartão() throws HardwareException {
		if(erroPegarNumero)
			throw new HardwareException("Erro ao obter número da conta");
		return "01234";
	}
	

}
