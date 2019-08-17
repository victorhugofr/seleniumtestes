package formularios;

// import static org.junit.Assert.fail;

// import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
// import org.testng.Reporter;

import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import dados.gerarNumeroDeDocumentos;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroProfissionalDeSaude extends AbstractTestSigSaude{
	
	public void inserirDadosProfissionalDeSaude(ProfissionalSaude profissionalSaude, boolean possuiCpf) {
		
		driver.findElement(By.linkText("Pessoal")).click();
	    driver.findElement(By.linkText("Profissional de Saúde")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[3]/following::a[2]")).click();
	    
	    driver.findElement(By.id("inputNome")).click();
	    driver.findElement(By.id("inputNome")).clear();
	    driver.findElement(By.id("inputNome")).sendKeys(profissionalSaude.getColaborador().getNome());
	    
	    if(possuiCpf) {
		    driver.findElement(By.id("inputCPF")).click();
		    driver.findElement(By.id("inputCPF")).clear();
		    driver.findElement(By.id("inputCPF")).sendKeys(gerarNumeroDeDocumentos.geraCPF());
	    }
	    
	    if(profissionalSaude.getEspecialidade()!=null) {
	    	driver.findElement(By.id("selectEspecialidade")).click();
	    	new Select(driver.findElement(By.id("selectEspecialidade"))).selectByValue(profissionalSaude.getEspecialidade().getCodigo());
	    }
	    driver.findElement(By.id("inputIdentidadeProfissional")).click();
	    driver.findElement(By.id("inputIdentidadeProfissional")).clear();
	    driver.findElement(By.id("inputIdentidadeProfissional")).sendKeys(profissionalSaude.getIdentidadeProfissional());
	    
	    driver.findElement(By.id("inputEmail")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys(profissionalSaude.getColaborador().getEmail());
	    
	    driver.findElement(By.id("inputLogin")).click();
	    driver.findElement(By.id("inputLogin")).clear();
	    driver.findElement(By.id("inputLogin")).sendKeys(profissionalSaude.getColaborador().getLogin());
	    
	    driver.findElement(By.id("cadastrarColaborador")).click();
	}
	
	public void confirmarCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]")));
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]")).click();
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
			System.out.println("aaaaaaaaa"+driver.findElement(By.className("invalid-feedback")).getText());
			if(!driver.findElement(By.className("invalid-feedback")).isDisplayed()){
				// Reporter.log((driver.findElement(By.className("invalid-feedback")).getText()));
				System.out.println(driver.findElement(By.className("invalid-feedback")).getText());
				throw new FormularioException(erro);
			}else {
				System.out.println("Toast sendo exibido");
				System.out.println(driver.findElement(By.className("invalid-feedback")).getText());
			}
		}
	}
	
	public void verificaErroObrigatorio(String erro) throws FormularioException {

		if (!driver.getPageSource().contains("Campo obrigatório.")) {
			throw new FormularioException(erro);
		} else {
			System.out.println("Toast sendo exibido");
		}
	}
}
