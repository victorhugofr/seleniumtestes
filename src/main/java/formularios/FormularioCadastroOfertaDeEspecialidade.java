package formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.ufrn.imd.sigsaude.dominio.OfertaEspecialidade;
import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroOfertaDeEspecialidade extends AbstractTestSigSaude {

	public void inserirOfertaDeEspecialidade(OfertaEspecialidade ofertaEspecialidade, int n1, String denominacao) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Oferta de especialidade")));
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".v-snack__content")));
		} catch (TimeoutException e) {

		}
		try {
			driver.findElement(By.linkText("Oferta de especialidade")).click();
		} catch (ElementClickInterceptedException e2) {
			actions.click(driver.findElement(By.linkText("Oferta de especialidade"))).build().perform();
			actions.click(driver.findElement(By.linkText("Oferta de especialidade"))).build().perform();
			actions.click(driver.findElement(By.linkText("Oferta de especialidade"))).build().perform();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Nova Oferta de Especialidade")));
		driver.findElement(By.linkText("Nova Oferta de Especialidade")).click();
		// CLIQUE EM DATA INICIO
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".application--wrap")));
		WebElement campoDenominacao = driver.findElement(By.xpath("//input[@aria-label='Denominação']"));
		campoDenominacao.click();
		campoDenominacao.clear();
		campoDenominacao.sendKeys(denominacao);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Data Início']")));
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Data Início']"))).perform();
		for (int i = 0; i < n1; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("button.v-btn:nth-child(3) > div:nth-child(1) > i:nth-child(1)")));
			try {
				driver.findElement(By.cssSelector("button.v-btn:nth-child(3) > div:nth-child(1) > i:nth-child(1)"))
						.click();
			} catch (ElementClickInterceptedException e) {
				driver.findElement(By.cssSelector("button.v-btn:nth-child(3) > div:nth-child(1) > i:nth-child(1)"))
						.click();
			}

		}

		// DATA INICIO
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::div[" + 17 + "]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::div[" + 17 + "]")));
		try {
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::div[" + 17 + "]"))
					.click();
		} catch (ElementNotVisibleException e) {
			actions.click(driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::div[" + 17 + "]")))
					.build().perform();
		}

		// DATA FIM
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::input[1]")));
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::input[1]")).click();
		for (int i = 0; i < n1 + 9; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
					".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(3) > div:nth-child(1) > i:nth-child(1)")));
			actions.click(driver.findElement(By.cssSelector(
					".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(3) > div:nth-child(1) > i:nth-child(1)")))
					.build().perform();
		}
		Thread.sleep(3000);
		
		

		try {
			driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat v-btn--floating theme--light']"
					+ "[.//div[contains(text(), '17')]]")).click();
			// driver.findElement(By
			// .xpath("(.//*[normalize-space(text()) and
			// normalize-space(.)='S'])[6]/following::div[" + 17 + "]")).click();
		} catch (StaleElementReferenceException e) {
			actions.click(driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat v-btn--floating theme--light']"
					+ "[.//div[contains(text(), '17')]]"))).build().perform();
		} catch (ElementNotInteractableException e2) {
			try {
				driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat v-btn--floating theme--light']"
						+ "[.//div[contains(text(), '17')]]")).click();
			} catch (StaleElementReferenceException e) {
				actions.click(driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat v-btn--floating theme--light']"
						+ "[.//div[contains(text(), '17')]]"))).build().perform();
			} catch (ElementNotInteractableException e3) {
				try {
					actions.click(driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat v-btn--floating theme--light']"
							+ "[.//div[contains(text(), '17')]]"))).build().perform();
				} catch (StaleElementReferenceException e4) {
					actions.click(driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat v-btn--floating theme--light']"
							+ "[.//div[contains(text(), '17')]]"))).build().perform();
				}
			}
		}

		// HORA INICIO
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::input[1]")));
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::input[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='--'])[1]/following::span[18]")));
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='--'])[1]/following::span[18]")).click();
		driver.findElement(By.cssSelector("div.v-picker__title__btn:nth-child(1)")).click();
		driver.findElement(By.cssSelector("div.v-picker__title__btn:nth-child(3)")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")));

		try {
			actions.click(driver
					.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")))
					.build().perform();
		} catch (StaleElementReferenceException e) {
			try {
				driver.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)"))
						.click();
				driver.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)"))
						.click();
			} catch (NoSuchElementException e1) {

			}
		}

		// HORA FIM
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::input[2]")));
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::input[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='--'])[1]/following::span[36]")));
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='--'])[1]/following::span[36]")).click();
		driver.findElement(By.cssSelector("div.v-picker__title__btn:nth-child(1)")).click();
		driver.findElement(By.cssSelector("div.v-picker__title__btn:nth-child(3)")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")));
		try {
			driver.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)"))
					.click();
		} catch (StaleElementReferenceException e) {
			try {
				driver.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)"))
						.click();
				driver.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)"))
						.click();
			} catch (NoSuchElementException e1) {

			}
		} catch (ElementClickInterceptedException e2) {
			try {
				actions.click(driver
						.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")))
						.build().perform();
			} catch (StaleElementReferenceException e) {
				try {
					driver.findElement(
							By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")).click();
					driver.findElement(
							By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")).click();
				} catch (NoSuchElementException e1) {

				}
			}
		}

		// TIPO ATENDIMENTO
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Hora Marcada'])[1]/following::div[4]"))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".v-list > div:nth-child(1) > a:nth-child(1) > div:nth-child(1) > div:nth-child(1)")));
		driver.findElement(
				By.cssSelector(".v-list > div:nth-child(1) > a:nth-child(1) > div:nth-child(1) > div:nth-child(1)"))
				.click();

		WebElement slider = driver.findElement(By.cssSelector(".v-slider__thumb-container"));

		// TEMPO MEDIO
		Actions move = new Actions(driver);
		Action action = move.dragAndDropBy(slider, 270, 0).build();
		action.perform();
		// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("//button[@class='v-btn
		// theme--light primary'][.//div[contains(text(), 'Continuar')]]")));
		driver.findElement(
				By.xpath("//button[@class='v-btn theme--light primary'][.//div[contains(text(), 'Continuar')]]"))
				.click();
		if (driver.getPageSource().contains("Datas de início e fim precisam ser selecionadas")) {
			// DATA FIM
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::input[1]")));
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::input[1]"))
					.click();
			for (int i = 0; i < n1 + 9; i++) {
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
						".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(3) > div:nth-child(1) > i:nth-child(1)")));
				actions.click(driver.findElement(By.cssSelector(
						".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(3) > div:nth-child(1) > i:nth-child(1)")))
						.build().perform();
			}

			// wait.until(ExpectedConditions.elementToBeClickable(
			// By.xpath("(.//*[normalize-space(text()) and
			// normalize-space(.)='S'])[6]/following::div[" + 17 + "]")));
			try {
				actions.click(driver.findElement(By.xpath(
						"(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::div[" + 17 + "]")))
						.build().perform();
			} catch (StaleElementReferenceException e) {
				actions.click(driver.findElement(By.xpath(
						"(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::div[" + 17 + "]")))
						.build().perform();
			}
			driver.findElement(
					By.xpath("//button[@class='v-btn theme--light primary'][.//div[contains(text(), 'Continuar')]]"))
					.click();
		}
	}

	public void inserirEspecialidade() {
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='v-btn__content'][.//i[contains(text(),
		// 'add')]]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("div.v-toolbar__items:nth-child(3) > button:nth-child(1)")));
		driver.findElement(By.cssSelector("div.v-toolbar__items:nth-child(3) > button:nth-child(1)")).click();
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Especialidade']"))).build().perform();
		driver.findElement(By.xpath("//input[@aria-label='Especialidade']"))
				.sendKeys("FARMÁCIA CLÍNICA EM TERAPIA ANTINEOPLÁSICA");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("FARMÁCIA CLÍNICA EM TERAPIA ANTINEOPLÁSICA - FAR046")));
		driver.findElement(By.linkText("FARMÁCIA CLÍNICA EM TERAPIA ANTINEOPLÁSICA - FAR046")).click();
		driver.findElement(By.xpath("//div[@class='v-btn__content'][.//i[contains(text(), 'add')]]")).click();
	}

	public void inserirHorarioEscalaProfissional(int n1) {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				".v-list--subheader > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > i:nth-child(1)")));
		driver.findElement(By.cssSelector(
				".v-list--subheader > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > i:nth-child(1)"))
				.click();
		driver.findElement(By.xpath("//input[@aria-label='Descrição']")).sendKeys("escalaselenium");
		driver.findElement(By.xpath("//input[@aria-label='Data Inicial']")).click();
		for (int i = 0; i < n1; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("button.v-btn:nth-child(3) > div:nth-child(1) > i:nth-child(1)")));
			driver.findElement(By.cssSelector("button.v-btn:nth-child(3) > div:nth-child(1) > i:nth-child(1)")).click();

		}
		// wait.until(ExpectedConditions.elementToBeClickable(By.className("v-btn
		// v-btn--flat v-btn--floating v-btn--outline theme--light accent--text")));
		// driver.findElement(By.className("v-btn v-btn--flat v-btn--floating
		// v-btn--outline theme--light accent--text")).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("button[class*=v-btn--floating]:not([class*='v-btn--disabled'])")));
		driver.findElement(By.cssSelector("button[class*=v-btn--floating]:not([class*='v-btn--disabled'])")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='v-btn theme--light primary']")));
		driver.findElement(By.xpath("//button[@class='v-btn theme--light primary']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("div.v-calendar-daily__day:nth-child(2) > div:nth-child(1) > div:nth-child(1)")));
		driver.findElement(
				By.cssSelector("div.v-calendar-daily__day:nth-child(2) > div:nth-child(1) > div:nth-child(1)")).click();
		driver.findElement(
				By.cssSelector("div.v-calendar-daily__day:nth-child(3) > div:nth-child(1) > div:nth-child(1)")).click();
		driver.findElement(
				By.cssSelector("div.v-calendar-daily__day:nth-child(4) > div:nth-child(1) > div:nth-child(1)")).click();
		driver.findElement(
				By.cssSelector("div.v-calendar-daily__day:nth-child(5) > div:nth-child(1) > div:nth-child(1)")).click();
		driver.findElement(
				By.cssSelector("div.v-calendar-daily__day:nth-child(6) > div:nth-child(1) > div:nth-child(1)")).click();
		driver.findElement(
				By.cssSelector("div.v-calendar-daily__day:nth-child(7) > div:nth-child(1) > div:nth-child(1)")).click();
		driver.findElement(
				By.cssSelector("div.v-calendar-daily__day:nth-child(8) > div:nth-child(1) > div:nth-child(1)")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//button[@class='v-btn v-btn--flat theme--dark']" + "[.//div[contains(text(), 'Concluir')]]")));
		driver.findElement(
				By.xpath("//button[@class='v-btn v-btn--flat theme--dark']" + "[.//div[contains(text(), 'Concluir')]]"))
				.click();
	}

	public void inserirEscala(String denominacao, ProfissionalSaude profissional) throws FormularioException {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Oferta de especialidade")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='v-snack__content']")));
		driver.findElement(By.linkText("Oferta de especialidade")).click();
		driver.findElement(By.linkText("Nova escala dos Profissionais")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Oferta de Especialidade']")));
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Oferta de Especialidade']"))).build().perform();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), '"
							+ denominacao + "')]]")));
		} catch (TimeoutException e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
					".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(20)")));
			WebElement elem2 = driver.findElement(By.cssSelector(
					".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(20)"));

			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].scrollIntoView(true);", elem2);

			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), '"
								+ denominacao + "')]]")));
			} catch (TimeoutException e2) {
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
						".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(40)")));
				WebElement elem3 = driver.findElement(By.cssSelector(
						".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(40)"));

				JavascriptExecutor js3 = (JavascriptExecutor) driver;
				js3.executeScript("arguments[0].scrollIntoView(true);", elem3);
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), '"
								+ denominacao + "')]]")));
			}
			// .menuable__content__active > div:nth-child(1) > div:nth-child(1) >
			// div:nth-child(40)
		}
		driver.findElement(
				By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), '"
						+ denominacao + "')]]"))
				.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Escala']")));
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Escala']"))).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), 'escalaselenium')]]")));
		driver.findElement(By.xpath(
				"//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), 'escalaselenium')]]"))
				.click();

		if (profissional == null) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Profissional']")));
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional']"))).build().perform();
			int k = 0;
			int l = 0;

			while (k != 200) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), 'PESSOA F TESTE SELENIUM OITOAGO MCCCXXXIX')]]")));
					k = 200;
					break;
				} catch (TimeoutException e) {
					// .menuable__content__active > div:nth-child(1) > div:nth-child(1) >
					// div:nth-child(19)
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
							".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(" + l
									+ ")")));
					WebElement elem2 = driver.findElement(By.cssSelector(
							".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(" + l
									+ ")"));
					l += 20;

					JavascriptExecutor js2 = (JavascriptExecutor) driver;
					js2.executeScript("arguments[0].scrollIntoView(true);", elem2);
				}
			}
			driver.findElement(By.xpath(
					"//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), 'PESSOA F TESTE SELENIUM OITOAGO MCCCXXXIX')]]"))
					.click();

			driver.findElement(
					By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))
					.click();

			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[79]")));
			// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.my-event:nth-child(1)")));
			// driver.findElement(By.cssSelector("div.my-event:nth-child(1)")).click();

			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[79]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[105]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[131]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[157]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[183]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[209]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[235]"))
					.click();

			// try {
			// wait.until(ExpectedConditions.attributeToBe(By.xpath("(.//*[normalize-space(text())
			// and normalize-space(.)='Cancelar'])[7]/following::div[79]"),
			// "background-color", "#c64136"));
			// } catch (TimeoutException e) {
			// throw new FormularioException("Elemento não foi clicado");
			// }
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Profissional']")));
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Profissional']"))).build().perform();
			int i = 0;
			int j = 0;
			while (i != 200) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), '"
									+ profissional.getColaborador().getNome().toUpperCase() + "')]]")));
					i = 200;
					break;
				} catch (TimeoutException e) {
					j += 20;
					// .menuable__content__active > div:nth-child(1) > div:nth-child(1) >
					// div:nth-child(19)
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
							".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(" + j
									+ ")")));
					WebElement elem2 = driver.findElement(By.cssSelector(
							".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(" + j
									+ ")"));

					JavascriptExecutor js2 = (JavascriptExecutor) driver;
					js2.executeScript("arguments[0].scrollIntoView(true);", elem2);

				}
			}
			driver.findElement(
					By.xpath("//a[@class='v-list__tile v-list__tile--link theme--light'][.//div[contains(text(), '"
							+ profissional.getColaborador().getNome().toUpperCase() + "')]]"))
					.click();

			driver.findElement(
					By.xpath("//button[@class='v-btn theme--light success'][.//div[contains(text(), 'Consultar')]]"))
					.click();

			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[79]")));
			// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.my-event:nth-child(1)")));
			// driver.findElement(By.cssSelector("div.my-event:nth-child(1)")).click();

			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[79]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[105]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[131]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[157]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[183]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[209]"))
					.click();
			driver.findElement(By
					.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[7]/following::div[235]"))
					.click();

			// try {
			// wait.until(ExpectedConditions.attributeToBe(By.xpath("(.//*[normalize-space(text())
			// and normalize-space(.)='Cancelar'])[7]/following::div[79]"),
			// "background-color", "#c64136"));
			// } catch (TimeoutException e) {
			// throw new FormularioException("Elemento não foi clicado");
			// }
		}
	}
	
	public void verificaErro(String erro) throws FormularioException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='v-snack__content']")));
		if (!driver.getPageSource().contains(erro)) {
			throw new FormularioException("mensagem não está sendo mostrada");
		} else {
			System.out.println("Toast sendo exibido");
		}
	}

	public void verificaEspecialidadeCadastrada() throws FormularioException {
		if (!driver.getPageSource().contains("FARMÁCIA CLÍNICA EM TERAPIA ANTINEOPLÁSICA - FAR046")) {
			throw new FormularioException("mensagem não está sendo mostrada");
		} else {
			System.out.println("Toast sendo exibido");
		}
	}

	public void concluirCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@class='v-btn theme--light primary'][.//div[contains(text(), 'Concluir')]]")));
		driver.findElement(
				By.xpath("//button[@class='v-btn theme--light primary'][.//div[contains(text(), 'Concluir')]]"))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[@class='v-btn v-btn--flat theme--light red--text text--darken-1'][.//div[contains(text(), 'Ir para listagem de ofertas de especialidade')]]")));
		driver.findElement(By.xpath("//button[@class='v-btn v-btn--flat theme--light red--text text--darken-1']"
				+ "[.//div[contains(text(), 'Ir para listagem de ofertas de especialidade')]]")).click();
	}
}
