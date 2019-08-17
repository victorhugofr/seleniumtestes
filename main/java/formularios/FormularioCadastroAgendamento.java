package formularios;

// import static org.junit.Assert.assertTrue;
// import static org.testng.Assert.assertTrue;
// import static org.junit.Assert.fail;
// import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroAgendamento extends AbstractTestSigSaude{

	public void inserirAgendamento() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-overlay v-overlay--active")));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agendamento")));
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Realizar novo agendamento")).click();

		//NOME DO USUARIO
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='No data available'])[2]/following::input[1]")).click();
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("TEREZINHA BATISTA");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")));
		driver.findElement(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")).click();

		//NOME DA ESPECIALIDADE
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).clear();
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).sendKeys("GEST");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("GESTÃO HOSPITALAR - FAR073")));
		driver.findElement(By.linkText("GESTÃO HOSPITALAR - FAR073")).click();

		//PROFISSIONAL DE SAUDE 
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA F TESTE SELENIUM ONZEJUL MII")));
		driver.findElement(By.linkText("PESSOA F TESTE SELENIUM ONZEJUL MII")).click();

		//BOTAO CONSULTAR
		driver.findElement(By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]")).click();

		//NAVEGAR MESES
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='v-icon material-icons theme--dark']")));
		while(!driver.findElement(By.className("mesAtual")).getText().equals("Novembro")) {
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Consultar'])[1]/following::i[2]")).click();
		}

		//CLICAR NO DIA 6 DE NOVEMBRO 8 - 9
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")).click();

		//CONFIRMAR AGENDAMENTO
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Confirmar agendamento?'])[1]/following::div[3]")).click();
	}
	
	public void inserirAgendamentoSemNome() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agendamento")));
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Realizar novo agendamento")).click();

		//NOME DA ESPECIALIDADE
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).clear();
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).sendKeys("GEST");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("GESTÃO HOSPITALAR - FAR073")));
		driver.findElement(By.linkText("GESTÃO HOSPITALAR - FAR073")).click();

		//PROFISSIONAL DE SAUDE 
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA F TESTE SELENIUM ONZEJUL MII")));
		driver.findElement(By.linkText("PESSOA F TESTE SELENIUM ONZEJUL MII")).click();

		//BOTAO CONSULTAR
		driver.findElement(By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]")).click();

		//NAVEGAR MESES
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='v-icon material-icons theme--dark']")));
		while(!driver.findElement(By.className("mesAtual")).getText().equals("Novembro")) {
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Consultar'])[1]/following::i[2]")).click();
		}

		//CLICAR NO DIA 6 DE NOVEMBRO 8 - 9
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")).click();
	}

	public void verificaAgendamento() throws FormularioException {

		if (!driver.getPageSource().contains("Agendamento realizado com sucesso!")) {
			throw new FormularioException("Agendamento não foi realizado");
		} else {
			//	driver.findElement(By.xpath("//button[@class='v-btn__content'][.//div[contains(text(), 'Fechar')]]")).click();
			System.out.println("Toast sendo exibido");
		}

	}

	public void cancelarAgendamento() {
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Realizar novo agendamento")).click();

		//NOME DO USUARIO
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='No data available'])[2]/following::input[1]")).click();
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("TEREZINHA BATISTA");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")));
		driver.findElement(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")).click();

		//NOME DA ESPECIALIDADE
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).clear();
		driver.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']")).sendKeys("GEST");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("GESTÃO HOSPITALAR - FAR073")));
		driver.findElement(By.linkText("GESTÃO HOSPITALAR - FAR073")).click();

		//PROFISSIONAL DE SAUDE 
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA F TESTE SELENIUM ONZEJUL MII")));
		driver.findElement(By.linkText("PESSOA F TESTE SELENIUM ONZEJUL MII")).click();

		//BOTAO CONSULTAR
		driver.findElement(By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]")).click();

		//NAVEGAR MESES
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='v-icon material-icons theme--dark']")));
		while(!driver.findElement(By.className("mesAtual")).getText().equals("Novembro")) {
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Consultar'])[1]/following::i[2]")).click();
		}

		//CLICAR NO DIA 6 DE NOVEMBRO 8 - 9
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='my-event-indisponible']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")).click();

		//CLICAR NO BOTAO CANCELAR AGENDAMENTO
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='v-btn v-btn--flat theme--dark red--text text--darken-1'][.//div[contains(text(), 'Cancelar agendamento')]]")));
		driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat theme--dark red--text text--darken-1'][.//div[contains(text(), 'Cancelar agendamento')]]")).click();
		//JUSTIFICATIVA DO CANCELAMENTO
		driver.findElement(By.xpath("//input[@aria-label='Justificativa de Cancelamento:']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Justificativa de Cancelamento:']")).clear();
		driver.findElement(By.xpath("//input[@aria-label='Justificativa de Cancelamento:']")).sendKeys("JustificativaSelenium");
		driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat theme--dark'][.//div[contains(text(), 'Continuar')]]")).click();



		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='v-btn v-btn--flat theme--light red--text text--darken-1'][.//div[contains(text(), 'Não')]]")));
		actions.click(driver.findElement(By.cssSelector("div.v-card__actions:nth-child(4) > button:nth-child(2)"))).perform();
		actions.click(driver.findElement(By.cssSelector("div.v-card__actions:nth-child(4) > button:nth-child(2) > div:nth-child(1)"))).perform();
		//div.v-card__actions:nth-child(4) > button:nth-child(2) > div:nth-child(1)
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-overlay v-overlay--active")));
		
	}

	public void verificaCancelamento() throws FormularioException {
		if (!driver.getPageSource().contains("Agendamento cancelado com sucesso!")) {
			throw new FormularioException("Agendamento não foi cancelado");
		} else {
			//	driver.findElement(By.xpath("//button[@class='v-btn__content'][.//div[contains(text(), 'Fechar')]]")).click();
			System.out.println("Toast sendo exibido");
		}
	}

	public void inserirAgendamentoMesmaHoraEDia() {
		inserirAgendamento();
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA DA GESTAO")));
		driver.findElement(By.linkText("PESSOA DA GESTAO")).click();
		driver.findElement(By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		wait.until(ExpectedConditions.attributeToBe(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"), "background-color", "rgb(55, 147, 146)"));
		//	if(driver.findElement(By.xpath("")).getCssValue("background-color").equals("#379392")) {
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")).click();
		//}
		//CONFIRMAR AGENDAMENTO
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Confirmar agendamento?'])[1]/following::div[3]")).click();
		driver.findElement(By.cssSelector(".v-dialog--active > div:nth-child(1) > nav:nth-child(1) > div:nth-child(1) > button:nth-child(1) > div:nth-child(1) > i:nth-child(1)")).click();

	}

	public void verificaErro(String erro) throws FormularioException {
		if (!driver.getPageSource().contains(erro)) {
			throw new FormularioException("ao aparece o erro " + erro);
		}
		
	}
}
