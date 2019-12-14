package formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import br.ufrn.imd.sigsaude.dominio.Turma;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroTurma extends AbstractTestSigSaude{
	
	public void clicarTurma() {
		driver.findElement(By.linkText("Acadêmico")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Turma")));
		driver.findElement(By.linkText("Turma")).click();
	}
	
	public void inserirNome(String nome) {
		WebElement campoNome = driver.findElement(By.xpath("//input[@aria-label='Nome']"));
		campoNome.click();
		campoNome.clear();
		campoNome.sendKeys(nome);
	}
	
	public void inserirComponenteCurricular(String nome) {
		WebElement campoNome = driver.findElement(By.xpath("//input[@aria-label='Componente curricular']"));
		campoNome.click();
		campoNome.clear();
		campoNome.sendKeys(nome);
	}
	
	public void inserirCodigoTurma(String nome) {
		WebElement campoNome = driver.findElement(By.xpath("//input[@aria-label='Código da turma']"));
		campoNome.click();
		campoNome.clear();
		campoNome.sendKeys(nome);
	}
	
	public void inserirPeriodo(String periodo) {
		WebElement campoNome = driver.findElement(By.xpath("//input[@aria-label='Período']"));
		campoNome.click();
		campoNome.clear();
		campoNome.sendKeys(periodo);
	}
	
	public void inserirCapacidade(String capacidade) {
		WebElement campoNome = driver.findElement(By.xpath("//input[@aria-label='Capacidade']"));
		campoNome.click();
		campoNome.clear();
		campoNome.sendKeys(capacidade);
	}
	
	public void inserirDadosTurma(Turma turma, String nomeOferta, String nomeProfissional) {
		clicarTurma();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Nova")));
		driver.findElement(By.linkText("Nova")).click();
		
		inserirNome(turma.getNome());
		inserirComponenteCurricular(turma.getCodigoComponenteCurricular());
		inserirCodigoTurma(turma.getCodigoTurma());
		inserirPeriodo(turma.getPeriodo());
		
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Tipo']"))).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Regular")));
		driver.findElement(By.linkText("Regular")).click();
		
		if(turma.getCapacidade()!=null)
		inserirCapacidade(turma.getCapacidade().toString());
		
		if(nomeOferta!=null) {
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Oferta de Especialidade']"))).build().perform();
			
			
			int k = 0;
			int l = 0;

			while (k != 200) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.linkText(nomeOferta)));
					k = 200;
					break;
				} catch (TimeoutException e) {
					l += 20;
					//.menuable__content__active
					// .menuable__content__active > div:nth-child(1) > div:nth-child(1) >
					// div:nth-child(19)
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
							".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(" + l
									+ ")")));
					WebElement elem2 = driver.findElement(By.cssSelector(
							".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(" + l
									+ ")"));

					JavascriptExecutor js2 = (JavascriptExecutor) driver;
					js2.executeScript("arguments[0].scrollIntoView(true);", elem2);
				}
			}
			
			driver.findElement(By.linkText(nomeOferta)).click();
		}
		
		if(nomeProfissional!=null) {
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Responsável Principal']"))).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(nomeProfissional)));
			driver.findElement(By.linkText(nomeProfissional)).click();
		}
	}
	
	public void continuarCadastro() {
		driver.findElement(By.xpath("//button[@class='v-btn theme--light primary']")).click();
	}
	
	public void verificaErroSnack(String erro) throws FormularioException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-snack__content")));
		if (!driver.getPageSource().contains(erro)) {
			throw new FormularioException("erro não aparece ou mensagem está indevida");
		} else {
//			driver.findElement(By.xpath("//button[@class='v-btn v-btn--icon theme--dark']")).click();
		}
	}

	public void inserirAluno(String nomeAluno,String matricula, String curso) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='v-btn v-btn--flat theme--dark'][.//div[contains(text(), 'Adicionar')]]")));
		driver.findElements(By.xpath("//button[@class='v-btn v-btn--flat theme--dark'][.//div[contains(text(), 'Adicionar')]]")).get(1).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Aluno']")));
		WebElement alunoElement = driver.findElement(By.xpath("//input[@aria-label='Aluno']"));
		alunoElement.click();
		alunoElement.clear();
		alunoElement.sendKeys(nomeAluno);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(nomeAluno+" - "+matricula+" - "+curso)));
		driver.findElement(By.linkText(nomeAluno+" - "+matricula+" - "+curso)).click();
		driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat theme--light success--text'][.//div[contains(text(), 'Adicionar')]]")).click();
	}
	
	public void inserirProfissional(ProfissionalSaude profissional) {
		driver.findElements(By.xpath("//button[@class='v-btn v-btn--flat theme--dark'][.//div[contains(text(), 'Adicionar')]]")).get(0).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Profissional']")));
		WebElement profissionalElement = driver.findElement(By.xpath("//input[@aria-label='Profissional']"));
		profissionalElement.click();
		profissionalElement.clear();
		profissionalElement.sendKeys(profissional.getNome());
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(profissional.getNome())));
		driver.findElement(By.linkText(profissional.getNome())).click();
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='v-btn v-btn--flat theme--light success--text'][.//div[contains(text(), 'Adicionar')]]")));
		driver.findElement(By.xpath("(//i[@aria-hidden='true'][contains(.,'add')])[2]")).click();
	}
	
	public void concluirCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='v-btn theme--light primary'][.//div[contains(text(), 'Concluir')]]")));
		driver.findElement(By.xpath("//button[@class='v-btn theme--light primary'][.//div[contains(text(), 'Concluir')]]")).click();
	}

	public void removerAluno() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//i[@aria-hidden='true'][contains(.,'delete')])[2]")));
		driver.findElement(By.xpath("(//i[@aria-hidden='true'][contains(.,'delete')])[2]")).click();
	}
	public void verificaCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='v-btn theme--light primary'][.//div[contains(text(), 'Concluir')]]")));
		driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat theme--light red--text text--darken-1'][.//div[contains(text(), 'Ir para listagem de turmas')]]")).click();
	}

	public void removerResponsavel() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//i[contains(.,'delete')])[1]")));
		driver.findElement(By.xpath("(//i[contains(.,'delete')])[1]")).click();
	}
}
