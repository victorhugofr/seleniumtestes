package formularios;

//import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
// import org.testng.Reporter;

import br.ufrn.imd.sigsaude.dominio.Colaborador;
import dados.gerarNumeroDeDocumentos;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroColaborador extends AbstractTestSigSaude{
	
	public void inserirDadosColaborador(Colaborador colaborador, boolean possuiCpf) {
		
		driver.findElement(By.linkText("Pessoal")).click();
	    driver.findElement(By.linkText("Colaborador")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[2]/following::a[3]")).click();
	    
	    driver.findElement(By.id("inputNome")).click();
	    driver.findElement(By.id("inputNome")).clear();
	    driver.findElement(By.id("inputNome")).sendKeys(colaborador.getNome());
	    
	    if(possuiCpf) {
		    driver.findElement(By.id("inputCPF")).click();
		    driver.findElement(By.id("inputCPF")).clear();
		    driver.findElement(By.id("inputCPF")).sendKeys(gerarNumeroDeDocumentos.geraCPF());
	    }
	    
	    if(colaborador.getTipoColaborador()!=null) {
	    	driver.findElement(By.id("selectTipo")).click();    
	    	new Select(driver.findElement(By.id("selectTipo"))).selectByValue(colaborador.getTipoColaborador().getId().toString());
	    }
	    
	    driver.findElement(By.id("inputEmail")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys(colaborador.getEmail());
	    
	    if(colaborador.getPessoa().getTelefone()!=null) {
		    driver.findElement(By.id("inputTelefone")).click();
		    driver.findElement(By.id("inputTelefone")).clear();
		    driver.findElement(By.id("inputTelefone")).sendKeys(colaborador.getPessoa().getTelefone());
	    }
	    
	    driver.findElement(By.id("inputLogin")).click();
	    driver.findElement(By.id("inputLogin")).clear();
	    driver.findElement(By.id("inputLogin")).sendKeys(colaborador.getLogin());
	    
	    driver.findElement(By.id("cadastrarColaborador")).click();
	}
	
	public void inserirDadosColaboradorCPFInvalido(Colaborador colaborador) {
		
		driver.findElement(By.linkText("Pessoal")).click();
	    driver.findElement(By.linkText("Colaborador")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[2]/following::a[3]")).click();
	    
	    driver.findElement(By.id("inputNome")).click();
	    driver.findElement(By.id("inputNome")).clear();
	    driver.findElement(By.id("inputNome")).sendKeys(colaborador.getNome());
	    
	    driver.findElement(By.id("inputCPF")).click();
	    driver.findElement(By.id("inputCPF")).clear();
	    driver.findElement(By.id("inputCPF")).sendKeys("12345678910");
	    
	    if(colaborador.getTipoColaborador()!=null) {
	    	driver.findElement(By.id("selectTipo")).click();
	    
	    	new Select(driver.findElement(By.id("selectTipo"))).selectByValue(colaborador.getTipoColaborador().getId().toString());
	    }
	    
	    driver.findElement(By.id("inputEmail")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys(colaborador.getEmail());
	    
	    if(colaborador.getPessoa().getTelefone()!=null) {
		    driver.findElement(By.id("inputTelefone")).click();
		    driver.findElement(By.id("inputTelefone")).clear();
		    driver.findElement(By.id("inputTelefone")).sendKeys(colaborador.getPessoa().getTelefone());
	    }
	    
	    driver.findElement(By.id("inputLogin")).click();
	    driver.findElement(By.id("inputLogin")).clear();
	    driver.findElement(By.id("inputLogin")).sendKeys(colaborador.getLogin());
	    
	    driver.findElement(By.id("cadastrarColaborador")).click();
	}
	
	public void verificaErro(String erro) throws FormularioException {

		try {
			if (!driver.findElement(By.className("toast-error")).isDisplayed()) {
				throw new FormularioException(erro);
				
			} else {
				System.out.println("Toast sendo exibido");
				System.out.println(driver.findElement(By.className("toast-error")).getText());
			}
		}catch(NoSuchElementException e) {
			
			wait.until(ExpectedConditions.presenceOfElementLocated((By.className("invalid-feedback"))));

			if(!driver.findElement(By.className("invalid-feedback")).isDisplayed()){
				// Reporter.log((driver.findElement(By.className("invalid-feedback")).getText()));
				// fail(erro);
				throw new FormularioException(erro);
			}else {
				System.out.println("Toast sendo exibido");
				System.out.println(driver.findElement(By.className("invalid-feedback")).getText());
			}
		}
	}
	
	public void verificaErroEmailObrigatorio(String erro) throws Exception {

		if (!driver.getPageSource().contains("Por favor digite um endereço de email válido.")) {
			// fail(erro);
			throw new FormularioException(erro);
		} else {
			System.out.println("Toast sendo exibido");
		}
	}
	
	public void confirmarCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]")));
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]")).click();
	}
}
