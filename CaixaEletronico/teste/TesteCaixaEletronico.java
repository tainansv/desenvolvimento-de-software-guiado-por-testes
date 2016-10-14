import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesteCaixaEletronico {

	CaixaEletronico caixa;
	MockHardware mockHardware;
	
	@Before
	public void inicializa(){
		caixa = new CaixaEletronico();
		ServicoRemoto mockServico = new MockServicoRemoto();
		mockHardware = new MockHardware();
		caixa.setServico(mockServico);
		caixa.setHardware(mockHardware);
	}
	
	@Test
	public void login() throws Exception {
		assertEquals("Usuário autenticado", caixa.logar("usuario", "senha"));
	}
	
	@Test
	public void loginFalha() throws Exception {
		assertEquals("Não foi possível autenticar o usuário", caixa.logar("usuaro", "sena"));
	}
	
	@Test
	public void depositar() throws Exception {
		caixa.logar("usuario", "senha");
		assertEquals("Depósito recebido com sucesso", caixa.depositar(100));
	}

	@Test
	public void saldo() throws Exception {
		caixa.logar("usuario", "senha");
		caixa.depositar(150);
		assertEquals("O saldo é R$150", caixa.saldo());
	}
	
	@Test
	public void sacar() throws Exception {
		caixa.logar("usuario", "senha");
		caixa.depositar(100);
		assertEquals("Retire seu dinheiro", caixa.sacar(50));
	}
	
	@Test
	public void sacarSaldoInsuficiente() throws Exception {
		caixa.logar("usuario", "senha");
		caixa.depositar(100);
		assertEquals("Saldo insuficiente", caixa.sacar(150));
	}
	
	@Test
	public void erroDeHardwareLerEnvelope(){
		mockHardware.setErroLerEnvelope(true);
		try{
			caixa.logar("usuario", "senha");
			caixa.depositar(100);
		}catch (Exception e){
			assertEquals("Erro ao ler envelope", e.getMessage());;
		}
	}
	
	@Test
	public void erroDeHardwareEntregarDinheiro(){
		mockHardware.setErroEntregarDinheiro(true);
		try{
			caixa.logar("usuario", "senha");
			caixa.depositar(100);
			caixa.sacar(50);
		}catch (Exception e){
			assertEquals("Erro ao entregar dinheiro", e.getMessage());;
		}
	}
	
	@Test
	public void erroDeHardwarePegarNumero(){
		mockHardware.setErroPegarNumero(true);
		try{
			caixa.logar("usuario", "senha");
		}catch (Exception e){
			assertEquals("Erro ao obter número da conta", e.getMessage());;
		}
	}
}
