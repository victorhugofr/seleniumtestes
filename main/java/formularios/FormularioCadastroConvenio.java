package formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import br.ufrn.imd.sigsaude.dominio.Convenio;
import dados.gerarNumeroDeDocumentos;
import utils.AbstractTestSigSaude;
import utils.ConverterData;

public class FormularioCadastroConvenio extends AbstractTestSigSaude {
		public void inserirDadosConvenio(Convenio convenio) {
			
			driver.findElement(By.linkText("Convênio")).click();
		    // NOVO CONVENIO
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[4]/following::a[2]")).click();
		    
			wait.until(ExpectedConditions.elementToBeClickable(By.id("inputNome")));
			driver.findElement(By.id("inputNome")).click();
			driver.findElement(By.id("inputNome")).clear();
			driver.findElement(By.id("inputNome")).sendKeys(convenio.getNome());
			
			driver.findElement(By.id("inputEmail")).click();
		    driver.findElement(By.id("inputEmail")).clear();
			driver.findElement(By.id("inputEmail")).sendKeys(convenio.getEmail());
			
		    driver.findElement(By.id("inputInicioVigencia")).click();
		    driver.findElement(By.id("inputInicioVigencia")).sendKeys("06");
		    driver.findElement(By.id("inputInicioVigencia")).sendKeys("10");
		    driver.findElement(By.id("inputInicioVigencia")).sendKeys("2019");
		    
		    
		    driver.findElement(By.id("inputFinalVigencia")).click();
		    driver.findElement(By.id("inputFinalVigencia")).sendKeys(ConverterData.DataFimVigencia(convenio));
		    
		    driver.findElement(By.id("endereco.cep")).click();
		    driver.findElement(By.id("endereco.cep")).clear();
		    driver.findElement(By.id("endereco.cep")).sendKeys(convenio.getEndereco().getCep());
		    
		    
		    driver.findElement(By.id("endereco.logradouro")).click();
		    driver.findElement(By.id("endereco.logradouro")).clear();
		    driver.findElement(By.id("endereco.logradouro")).sendKeys(convenio.getEndereco().getLogradouro());
		    
		    driver.findElement(By.id("endereco.numero")).click();
		    driver.findElement(By.id("endereco.numero")).clear();
		    driver.findElement(By.id("endereco.numero")).sendKeys(convenio.getEndereco().getNumero());
		    
		    driver.findElement(By.id("endereco.uf.id")).click();
		    new Select(driver.findElement(By.id("endereco.uf.id"))).selectByVisibleText("RN");
		    
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-background")));
		    driver.findElement(By.id("endereco.municipio.id")).click();
		    new Select(driver.findElement(By.id("endereco.municipio.id"))).selectByVisibleText("Natal");
		    
		    driver.findElement(By.id("endereco.bairro")).click();
		    driver.findElement(By.id("endereco.bairro")).clear();
		    driver.findElement(By.id("endereco.bairro")).sendKeys(convenio.getEndereco().getBairro());
		    
		    
		    driver.findElement(By.name("contato.telefone")).click();
		    driver.findElement(By.name("contato.telefone")).clear();
		    driver.findElement(By.name("contato.telefone")).sendKeys("99999999999");
		    
		    driver.findElement(By.name("contato.tipoTelefone")).click();

		    new Select(driver.findElement(By.name("contato.tipoTelefone"))).selectByValue("MOVEL");
		    
		    driver.findElement(By.name("contato.observacao")).click();
		    driver.findElement(By.name("contato.observacao")).sendKeys("teste");
		    
		    driver.findElement(By.id("inputTermoCompromisso")).sendKeys(gerarNumeroDeDocumentos.gerarPDF());
		    
		    driver.findElement(By.id("salvarConvenio")).click();
		}
		
		public void confirmarCadastro() {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]")));
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]")).click();
		}
	}
