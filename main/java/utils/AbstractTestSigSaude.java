package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
// import org.testng.Reporter;

import SigSaudeAutoTestes.Setup;
import enums.IDsLogin;
import enums.Navegadores;
import model.UsuarioAdmin;

public class AbstractTestSigSaude {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	
	public void login(UsuarioAdmin usuario,Navegadores navegador) {
		Setup login = new Setup();
		System.out.println("setUp iniciado");
		driver = login.setNavegador(navegador);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 5);
		driver.get("https://testessigsaude.imd.ufrn.br/sigsaude/");
		// driver.findElement(By.linkText("Entrar")).click();
		// BOTAO DE ENTRAR
		driver.findElement(By.xpath(IDsLogin.ENTRAR.getIdOrXpath())).click();
		driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).click();
		driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).sendKeys("itamir.filho");
		driver.findElement(By.id(IDsLogin.PASSWORD.getIdOrXpath())).sendKeys("admin12345678");
		// BOTAO LOGAR
		driver.findElement(By.xpath(IDsLogin.LOGAR.getIdOrXpath())).click();
		System.out.println("Setup finalizado.");
		actions = new Actions(driver);
		
	}
	
	public void logout() {
		driver.quit();
	}
	
}
