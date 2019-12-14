package formularios;

import java.text.DateFormat;

//import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.ufrn.imd.sigsaude.dominio.Paciente;
import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroAgendamento extends AbstractTestSigSaude {

	public void inserirJustificativaCancelamento(String justificativa) {
		WebElement inserirJustificativaCancelamento = driver
				.findElement(By.xpath("//input[@aria-label='Justificativa de Cancelamento:']"));
		inserirJustificativaCancelamento.click();
		inserirJustificativaCancelamento.clear();
		inserirJustificativaCancelamento.sendKeys(justificativa);
	}

	public void inserirAgendamento(Paciente pessoa, String especialidade, ProfissionalSaude profissional,
			String codigoEspecialidade, String mes) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-overlay v-overlay--active")));
		try {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-snack__content")));
		}catch(TimeoutException e) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-snack__content")));
		}
		try {
			driver.findElement(By.cssSelector(
					".v-dialog--active > div:nth-child(1) > nav:nth-child(1) > div:nth-child(1) > button:nth-child(1) > div:nth-child(1) > i:nth-child(1)"))
					.click();
		}catch(NoSuchElementException e) {
			
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agendamento")));
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Realizar novo agendamento")).click();

		// NOME DO USUARIO
		WebElement inserirNome = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome no usuário do serviço*']"));
		inserirNome.click();
		inserirNome.clear();
		if (pessoa == null) {
			inserirNome.sendKeys("TEREZINHA BATISTA");
			wait.until(ExpectedConditions
					.elementToBeClickable(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")));
			driver.findElement(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")).click();
		} else {
			inserirNome.sendKeys(pessoa.getNome());
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
					".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")));
			driver.findElement(By
					.cssSelector(".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"))
					.click();
		}
		// NOME DA ESPECIALIDADE
		WebElement inserirEspecialidade = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']"));
		if (especialidade == null) {
			inserirEspecialidade.click();
			inserirEspecialidade.clear();
			inserirEspecialidade.sendKeys("GEST");
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("GESTÃO HOSPITALAR - FAR073")));
			driver.findElement(By.linkText("GESTÃO HOSPITALAR - FAR073")).click();
		} else {
			inserirEspecialidade.click();
			inserirEspecialidade.clear();
			inserirEspecialidade.sendKeys(especialidade);
			wait.until(
					ExpectedConditions.elementToBeClickable(By.linkText(especialidade + " - " + codigoEspecialidade)));
			driver.findElement(By.linkText(especialidade + " - " + codigoEspecialidade)).click();
		}
		int i=0, j=0;
		if (profissional == null) {
			// PROFISSIONAL DE SAUDE
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build()
					.perform();

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']"
							+ "[.//div[contains(text(), 'PESSOA F TESTE SELENIUM  MMLXXV')]]")));
			// wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA F
			// TESTE SELENIUM MMLXXV")));
			driver.findElement(By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']"
					+ "[.//div[contains(text(), 'PESSOA F TESTE SELENIUM  MMLXXV')]]")).click();
		} else {
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build()
					.perform();
			while(i!= 200) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//a[@class='v-list__tile v-list__tile--link theme--light']" + "[.//div[contains(text(), '"
									+ profissional.getColaborador().getNome().toUpperCase() + "')]]")));
					i=200;
					break;
				} catch (TimeoutException e) {
					j+=20;
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
							".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child("+j+")")));
					WebElement elem2 = driver.findElement(By.cssSelector(
							".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child("+j+")"));
	
					JavascriptExecutor js2 = (JavascriptExecutor) driver;
					js2.executeScript("arguments[0].scrollIntoView(true);", elem2);
				}
			}
			driver.findElement(By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']"
					+ "[.//div[contains(text(), '" + profissional.getColaborador().getNome().toUpperCase() + "')]]"))
					.click();
		}
		// BOTAO CONSULTAR
		driver.findElement(
				By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))
				.click();

		// NAVEGAR MESES
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='v-icon
		// material-icons theme--dark']")));
		while (!driver.findElement(By.className("mesAtual")).getText().equals(mes)) {
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Consultar'])[1]/following::i[2]"))
					.click();
		}

		// CLICAR NO DIA 6 DE NOVEMBRO 8 - 9
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='" + mes.toLowerCase()
						+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		WebElement novembro689 = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"
				+ mes.toLowerCase()
				+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"));
		// try {
		// wait.until(ExpectedConditions.attributeToBe(By.xpath
		// ("(.//*[normalize-space(text()) and
		// normalize-space(.)='novembro'])[1]/following::div[contains(text(),
		// '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"),
		// "background-color", "rgb(248, 62, 31)"));
		if (driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='" + mes.toLowerCase()
				+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"))
				.getCssValue("background-color").equals("rgb(248, 62, 31)")) {
			cancelarAgendamento(pessoa,mes, profissional);
			inserirJustificativaCancelamento("teste");
			confirmarCancelamento();
			negarEmail();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))));
			driver.findElement(
					By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))
					.click();
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='" + mes.toLowerCase()
					+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"))
					.click();
		} else {
			novembro689.click();
		}
	}
	
	public void inserirAgendamentoDataAnterior(Paciente pessoa, String especialidade, ProfissionalSaude profissional,
			String codigoEspecialidade) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-overlay v-overlay--active")));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agendamento")));
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Realizar novo agendamento")).click();

		// NOME DO USUARIO
		WebElement inserirNome = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome no usuário do serviço*']"));
		inserirNome.click();
		inserirNome.clear();
		if (pessoa == null) {
			inserirNome.sendKeys("TEREZINHA BATISTA");
			wait.until(ExpectedConditions
					.elementToBeClickable(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")));
			driver.findElement(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")).click();
		} else {
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getPessoa().getNome());
			inserirNome.sendKeys(pessoa.getNome());
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
					".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")));
			driver.findElement(By
					.cssSelector(".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"))
					.click();
		}
		// NOME DA ESPECIALIDADE
		if (especialidade == null) {
			WebElement inserirEspecialidade = driver
					.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']"));
			inserirEspecialidade.click();
			inserirEspecialidade.clear();
			inserirEspecialidade.sendKeys("GEST");
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("GESTÃO HOSPITALAR - FAR073")));
			driver.findElement(By.linkText("GESTÃO HOSPITALAR - FAR073")).click();
		} else {
			WebElement inserirEspecialidade = driver
					.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']"));
			inserirEspecialidade.click();
			inserirEspecialidade.clear();
			inserirEspecialidade.sendKeys(especialidade);
			wait.until(
					ExpectedConditions.elementToBeClickable(By.linkText(especialidade + " - " + codigoEspecialidade)));
			driver.findElement(By.linkText(especialidade + " - " + codigoEspecialidade)).click();
		}
			// PROFISSIONAL DE SAUDE
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build()
					.perform();

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']"
							+ "[.//div[contains(text(), 'PESSOA DA GESTAO')]]")));
			// wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA F
			// TESTE SELENIUM MMLXXV")));
			driver.findElement(By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']"
					+ "[.//div[contains(text(), 'PESSOA DA GESTAO')]]")).click();
		// BOTAO CONSULTAR
		driver.findElement(
				By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))
				.click();

		// NAVEGAR MESES
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='v-icon
		// material-icons theme--dark']")));
					driver.findElement(By.cssSelector("button.v-btn--absolute:nth-child(1)"))
					.click();
					
					Calendar dat = Calendar.getInstance();
					dat.setTime(new Date());
					dat.add(Calendar.MONTH,-1);
					Date data =  dat.getTime();
					Locale local = new Locale("pt","BR");
					DateFormat formato = new SimpleDateFormat("MMMM",local);
					
					String mes = formato.format(data);
					System.out.println(mes+"<<<<<");
		// CLICAR NO DIA 6 DE NOVEMBRO 8 - 9
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='" + mes.toLowerCase()
						+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		WebElement novembro689 = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"
				+ mes.toLowerCase()
				+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"));
		novembro689.click();
	}

	public void confirmarAgendamento() {
		// CONFIRMAR AGENDAMENTO
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Confirmar agendamento?'])[1]/following::div[3]")));
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Confirmar agendamento?'])[1]/following::div[3]"))
				.click();
	}

	public void inserirAgendamentoSemNome() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agendamento")));
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Realizar novo agendamento")).click();

		// NOME DA ESPECIALIDADE
		WebElement inserirEspecialidade = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']"));
		inserirEspecialidade.click();
		inserirEspecialidade.clear();
		inserirEspecialidade.sendKeys("GEST");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("GESTÃO HOSPITALAR - FAR073")));
		driver.findElement(By.linkText("GESTÃO HOSPITALAR - FAR073")).click();
		// PROFISSIONAL DE SAUDE
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA DA GESTAO")));
		driver.findElement(By.linkText("PESSOA DA GESTAO")).click();

		// BOTAO CONSULTAR
		driver.findElement(
				By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))
				.click();

		// NAVEGAR MESES
		while (!driver.findElement(By.className("mesAtual")).getText().equals("Novembro")) {
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Consultar'])[1]/following::i[2]"))
					.click();
		}

		// CLICAR NO DIA 6 DE NOVEMBRO 8 - 9
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '7')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		WebElement novembro789 = driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='novembro'])[1]/following::div[contains(text(), '7')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"));
		novembro789.click();
	}

	public void verificaAgendamento() throws FormularioException {

		System.out.println("Toast sendo exibido");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Agendamento")));
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Listar Agendamentos")).click();
		// NOME DO USUARIO
		WebElement inserirNome = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome no usuário do serviço*']"));
		inserirNome.click();
		inserirNome.clear();
		inserirNome.sendKeys("TEREZINHA BATISTA");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")));
		driver.findElement(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")).click();

		// BOTAO CONSULTAR
		driver.findElement(
				By.xpath("//button[@class='v-btn v-btn--small theme--light'][.//div[contains(text(), 'Consultar')]]"))
				.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("td.text-xs-center")));

		// ORDENAR POR DATA DE REALIZAÇÃO
		driver.findElement(By.cssSelector("th.sortable:nth-child(1)")).click();

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");

		Calendar data2 = Calendar.getInstance();
		int hora = data2.get(Calendar.HOUR_OF_DAY);
		int min = data2.get(Calendar.MINUTE);

		if (driver.getPageSource().contains(formatarDate.format(data))
				&& (driver.getPageSource().contains(hora + ":" + min)
						|| driver.getPageSource().contains(hora + ":" + (min - 1))
						|| driver.getPageSource().contains((hora - 1) + ":" + "59")
						|| driver.getPageSource().contains((hora) + ":" + "0" + min))) {
			System.out.println(formatarDate.format(data));
		} else {
			throw new FormularioException("não está na verificação");
		}

		if (driver.findElement(By.cssSelector(".v-datatable > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6)"))
				.getText().equals("AGENDADO")) {
			System.out.println("Agendado");
		} else {
			throw new FormularioException("não está com o status correto");
		}

	}

	public void cancelarAgendamento(Paciente pessoa, String mes, ProfissionalSaude profissional) {
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Realizar novo agendamento")).click();
		
		WebElement inserirNome = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome no usuário do serviço*']"));
		inserirNome.click();
		inserirNome.clear();
		
		if (pessoa == null) {
			inserirNome.sendKeys("TEREZINHA BATISTA");
			wait.until(ExpectedConditions
					.elementToBeClickable(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")));
			driver.findElement(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")).click();
		} else {
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getPessoa().getNome());
			inserirNome.sendKeys(pessoa.getNome());
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
					".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")));
			driver.findElement(By
					.cssSelector(".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"))
					.click();
		}
		
		// NOME DA ESPECIALIDADE
		WebElement inserirEspecialidade = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome da especialidade*']"));
		inserirEspecialidade.click();
		inserirEspecialidade.clear();
		inserirEspecialidade.sendKeys("GEST");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("GESTÃO HOSPITALAR - FAR073")));
		driver.findElement(By.linkText("GESTÃO HOSPITALAR - FAR073")).click();

		// PROFISSIONAL DE SAUDE

		if (profissional == null) {
			// PROFISSIONAL DE SAUDE
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build()
					.perform();

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']"
							+ "[.//div[contains(text(), 'PESSOA F TESTE SELENIUM  MMLXXV')]]")));
			// wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA F
			// TESTE SELENIUM MMLXXV")));
			driver.findElement(By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']"
					+ "[.//div[contains(text(), 'PESSOA F TESTE SELENIUM  MMLXXV')]]")).click();
		} else {
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build()
					.perform();

			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']" + "[.//div[contains(text(), '"
							+ profissional.getColaborador().getNome().toUpperCase() + "')]]")));
			driver.findElement(By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light']"
					+ "[.//div[contains(text(), '" + profissional.getColaborador().getNome().toUpperCase() + "')]]"))
					.click();
		}

		// BOTAO CONSULTAR
		driver.findElement(
				By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))
				.click();

		// NAVEGAR MESES
		while (!driver.findElement(By.className("mesAtual")).getText().equals(mes)) {
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Consultar'])[1]/following::i[2]"))
					.click();
		}

		// CLICAR NO DIA 6 DE NOVEMBRO 8 - 9
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='my-event-indisponible']")));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='" + mes.toLowerCase()
						+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='" + mes.toLowerCase()
				+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"))
				.click();

		// CLICAR NO BOTAO CANCELAR AGENDAMENTO
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[@class='v-btn v-btn--flat theme--dark red--text text--darken-1'][.//div[contains(text(), 'Cancelar agendamento')]]")));
		driver.findElement(By.xpath(
				"//button[@class='v-btn v-btn--flat theme--dark red--text text--darken-1'][.//div[contains(text(), 'Cancelar agendamento')]]"))
				.click();

	}

	public void negarEmail() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[@class='v-btn v-btn--flat theme--light red--text text--darken-1'][.//div[contains(text(), 'Não')]]")));
		actions.click(driver.findElement(By.cssSelector("div.v-card__actions:nth-child(4) > button:nth-child(2)")))
				.perform();
		try {
			actions.click(driver.findElement(
					By.cssSelector("div.v-card__actions:nth-child(4) > button:nth-child(2) > div:nth-child(1)")))
					.perform();
		} catch (Exception e) {

		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-overlay v-overlay--active")));
	}

	public void confirmarCancelamento() {
		driver.findElement(
				By.xpath("//button[@class='v-btn v-btn--flat theme--dark'][.//div[contains(text(), 'Continuar')]]"))
				.click();
	}

	public void verificaCancelamento() throws FormularioException {
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Listar Agendamentos")).click();
		// NOME DO USUARIO
		WebElement inserirNome = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome no usuário do serviço*']"));
		inserirNome.click();
		inserirNome.clear();
		inserirNome.sendKeys("TEREZINHA BATISTA");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")));
		driver.findElement(By.linkText("TEREZINHA BATISTA DIAS DA SILVA - 10/06/1950")).click();

		// BOTAO CONSULTAR
		driver.findElement(
				By.xpath("//button[@class='v-btn v-btn--small theme--light'][.//div[contains(text(), 'Consultar')]]"))
				.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("td.text-xs-center")));

		// ORDENAR POR DATA DE REALIZAÇÃO
		driver.findElement(By.cssSelector("th.sortable:nth-child(1)")).click();

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");

		Calendar data2 = Calendar.getInstance();
		int hora = data2.get(Calendar.HOUR_OF_DAY);
		int min = data2.get(Calendar.MINUTE);
		System.out.println(hora + ":" + min);

		if (driver.getPageSource().contains(formatarDate.format(data))
				&& (driver.getPageSource().contains(hora + ":" + min)
						|| driver.getPageSource().contains(hora + ":" + (min - 1))
						|| driver.getPageSource().contains((hora - 1) + ":" + "59")
						|| driver.getPageSource().contains((hora) + ":" + "0" + min))) {
			System.out.println(formatarDate.format(data));
		} else {
			throw new FormularioException("não está na verificação");
		}

		if (driver.findElement(By.cssSelector(".v-datatable > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6)"))
				.getText().equals("CANCELADO")) {
			System.out.println("Cancelado");
		} else {
			throw new FormularioException("não está com o status correto");
		}
	}

	public void inserirAgendamentoMesmaHoraEDia(String mes) {
		inserirAgendamento(null, null, null, null, mes);
		confirmarAgendamento();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Profissional de saúde*']")));
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional de saúde*']"))).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("PESSOA DA GESTAO")));
		driver.findElement(By.linkText("PESSOA DA GESTAO")).click();
		driver.findElement(
				By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))
				.click();

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='" + mes.toLowerCase()
						+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]")));
		wait.until(ExpectedConditions.attributeToBe(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"
				+ mes.toLowerCase()
				+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"),
				"background-color", "rgb(55, 147, 146)"));
		// if(driver.findElement(By.xpath("")).getCssValue("background-color").equals("#379392"))
		// {
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='" + mes.toLowerCase()
				+ "'])[1]/following::div[contains(text(), '6')]/following::div[contains(text(), '08:00:00 - 09:00:00')]"))
				.click();
		// }
		// CONFIRMAR AGENDAMENTO
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Confirmar agendamento?'])[1]/following::div[3]"))
				.click();
		driver.findElement(By.cssSelector(
				".v-dialog--active > div:nth-child(1) > nav:nth-child(1) > div:nth-child(1) > button:nth-child(1) > div:nth-child(1) > i:nth-child(1)"))
				.click();

	}

	public void verificaErro(String erro) throws FormularioException {
		if (!driver.getPageSource().contains(erro)) {
			throw new FormularioException("ao aparece o erro " + erro);
		} else {
			// driver.findElement(By.xpath("//button[@class='v-btn v-btn--icon
			// theme--dark']")).click();
		}

	}

	public void verificaErroSnack(String erro) throws FormularioException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-snack__content")));
		if (!driver.getPageSource().contains(erro)) {
			throw new FormularioException("erro não aparece ou mensagem está indevida");
		} else {
//			driver.findElement(By.xpath("//button[@class='v-btn v-btn--icon theme--dark']")).click();
		}
	}

	public void listarAgendamentoCaractereEspecialNome() throws FormularioException {
		driver.findElement(By.linkText("Agendamento")).click();
		driver.findElement(By.linkText("Listar Agendamentos")).click();
		WebElement inserirNome = driver
				.findElement(By.xpath("//input[@aria-label='Digite o nome no usuário do serviço*']"));
		inserirNome.click();
		inserirNome.clear();
		inserirNome.sendKeys("/");

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//div[@class='v-snack v-snack--active v-snack--right v-snack--top']")));
			throw new FormularioException("alerta indevido sendo exibido");
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	public void inserirObservacao(String observacao) {
		driver.findElement(By.xpath("//input[@aria-label='Observações:']")).sendKeys(observacao);
	}
}
